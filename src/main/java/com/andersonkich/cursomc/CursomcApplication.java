package com.andersonkich.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andersonkich.cursomc.domain.Categoria;
import com.andersonkich.cursomc.domain.Cidade;
import com.andersonkich.cursomc.domain.Cliente;
import com.andersonkich.cursomc.domain.Endereco;
import com.andersonkich.cursomc.domain.Estado;
import com.andersonkich.cursomc.domain.Pagamento;
import com.andersonkich.cursomc.domain.PagamentoComBoleto;
import com.andersonkich.cursomc.domain.PagamentoComCartao;
import com.andersonkich.cursomc.domain.Pedido;
import com.andersonkich.cursomc.domain.Produto;
import com.andersonkich.cursomc.domain.enuns.EstadoPagamento;
import com.andersonkich.cursomc.domain.enuns.TipoCliente;
import com.andersonkich.cursomc.repositories.CategoriaRepository;
import com.andersonkich.cursomc.repositories.CidadeRepository;
import com.andersonkich.cursomc.repositories.ClienteRepository;
import com.andersonkich.cursomc.repositories.EnderecoRepository;
import com.andersonkich.cursomc.repositories.EstadoRepository;
import com.andersonkich.cursomc.repositories.PagamentoRepository;
import com.andersonkich.cursomc.repositories.PedidoRepository;
import com.andersonkich.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {//CommandLineRunner usado para instanciar obetos ao iniciar a aplicação

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {//Metodo do CommandLineRunner
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1  = new Cliente(null, "Maria Silva", "maria@gmail.com", "36376912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco en1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c2);
		Endereco en2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(en1, en2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(en1, en2));
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, en1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, en2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf1.parse("20/10/2017"), null); 
		ped2.setPagamento(pag2);
		
		cli1.getPedidios().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		clienteRepository.saveAll(Arrays.asList(cli1));

	}

}
