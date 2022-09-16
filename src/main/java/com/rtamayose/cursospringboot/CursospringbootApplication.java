package com.rtamayose.cursospringboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rtamayose.cursospringboot.domain.Categoria;
import com.rtamayose.cursospringboot.domain.Cidade;
import com.rtamayose.cursospringboot.domain.Cliente;
import com.rtamayose.cursospringboot.domain.Endereco;
import com.rtamayose.cursospringboot.domain.Estado;
import com.rtamayose.cursospringboot.domain.ItemPedido;
import com.rtamayose.cursospringboot.domain.Pagamento;
import com.rtamayose.cursospringboot.domain.PagamentoComBoleto;
import com.rtamayose.cursospringboot.domain.PagamentoComCartao;
import com.rtamayose.cursospringboot.domain.Pedido;
import com.rtamayose.cursospringboot.domain.Produto;
import com.rtamayose.cursospringboot.domain.enums.EstadoPagamento;
import com.rtamayose.cursospringboot.domain.enums.TipoCliente;
import com.rtamayose.cursospringboot.repositories.CategoriaRepository;
import com.rtamayose.cursospringboot.repositories.CidadeRepository;
import com.rtamayose.cursospringboot.repositories.ClienteRepository;
import com.rtamayose.cursospringboot.repositories.EnderecoRepository;
import com.rtamayose.cursospringboot.repositories.EstadoRepository;
import com.rtamayose.cursospringboot.repositories.ItemPedidoRepository;
import com.rtamayose.cursospringboot.repositories.PagamentoRepository;
import com.rtamayose.cursospringboot.repositories.PedidoRepository;
import com.rtamayose.cursospringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringbootApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		Categoria cat1  = new Categoria(null, "Informática");
		Categoria cat2  = new Categoria(null, "Escritorio");
		Categoria cat3  = new Categoria(null, "Esporte");
		Categoria cat4  = new Categoria(null, "Culinaria");
		Categoria cat5  = new Categoria(null, "Roupas");
		Categoria cat6  = new Categoria(null, "Alimentos");
		Categoria cat7  = new Categoria(null, "Festas");
		Categoria cat8  = new Categoria(null, "Bebidas");
		
		Produto p1 = new Produto(null, "computador", 200.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "SP");
		Estado est2 = new Estado(null, "RJ"); 
		
		Cidade cid1 = new Cidade(null, "São paulo", est1);
		Cidade cid2 = new Cidade(null, "Rio de Janeiro", est2);
		Cidade cid3 = new Cidade(null, "Limeira", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		 
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "28082034330", TipoCliente.PESSSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("964915878","997696217"));
		
		Endereco end1 = new Endereco(null, "Av. Paulista","280","Predio Roxo","Paulista","02472110", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av. Flores","300","Predio Azul","Flores","02472200", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/09/2017 10:20"), null);
		ped2.setPagamento(pagto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 100, 1.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 800, 1.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 700, 1.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));	
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
