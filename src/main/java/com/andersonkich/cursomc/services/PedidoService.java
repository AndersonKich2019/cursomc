package com.andersonkich.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersonkich.cursomc.domain.ItemPedido;
import com.andersonkich.cursomc.domain.PagamentoComBoleto;
import com.andersonkich.cursomc.domain.Pedido;
import com.andersonkich.cursomc.domain.enuns.EstadoPagamento;
import com.andersonkich.cursomc.repositories.ClienteRepository;
import com.andersonkich.cursomc.repositories.ItemPedidoReposirory;
import com.andersonkich.cursomc.repositories.PagamentoRepository;
import com.andersonkich.cursomc.repositories.PedidoRepository;
import com.andersonkich.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoReposirory itemPedidoRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	@Transactional//para salvar o cliente e o endereco na mesma transação
	public Pedido insert(Pedido obj) {//Post
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.getOne(obj.getCliente().getId()));//usou o id do obj para setar o cliente inteiro
		obj.getPagamento().setStatus(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);//pagamento tem que conhecer o peddo 
		if(obj.getPagamento() instanceof PagamentoComBoleto) {//(instanceof significa é um, usado quando tem herança) Se o Pagamento for do tipo Pagamento com boleto
			PagamentoComBoleto pag = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pag, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {//acessando a lista de itens dentro do obj
			ip.setDesconto(0.00);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
}
