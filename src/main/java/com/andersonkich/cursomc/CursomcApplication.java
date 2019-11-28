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
import com.andersonkich.cursomc.domain.ItemPedido;
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
import com.andersonkich.cursomc.repositories.ItemPedidoReposirory;
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
	@Autowired
	private ItemPedidoReposirory itemPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {//Metodo do CommandLineRunner
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Jardinagem");
		Categoria cat4 = new Categoria(null, "Cama mesa e banho");
		Categoria cat5 = new Categoria(null, "Eletronicos");
		Categoria cat6 = new Categoria(null, "Decoraçao");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Tv true collor", 1200.00);
		Produto p8 = new Produto(null, "Rocadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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

		ItemPedido item1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(item1, item2));
		ped2.getItens().addAll(Arrays.asList(item3));
		
		p1.getItens().addAll(Arrays.asList(item1));
		p2.getItens().addAll(Arrays.asList(item3));
		p3.getItens().addAll(Arrays.asList(item2));
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
		
		
		
		
	}

}
