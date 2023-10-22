package ericknovello.com.github.pedidosapi.db;

import ericknovello.com.github.pedidosapi.entity.*;
import ericknovello.com.github.pedidosapi.enums.TipoCliente;
import ericknovello.com.github.pedidosapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;

@Service
public class DbService {

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

    public void instantiateTestDatabase() throws ParseException {
        // Criar categorias
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        // Criar produtos
        Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(2000.00));
        Produto p2 = new Produto(null, "Impressora", BigDecimal.valueOf(800.00));
        Produto p3 = new Produto(null, "Mouse", BigDecimal.valueOf(80.00));
        Produto p4 = new Produto(null, "Mesa de escritório", BigDecimal.valueOf(300.00));
        Produto p5 = new Produto(null, "Toalha", BigDecimal.valueOf(50.00));
        Produto p6 = new Produto(null, "Colcha", BigDecimal.valueOf(200.00));
        Produto p7 = new Produto(null, "TV True color", BigDecimal.valueOf(1200.00));
        Produto p8 = new Produto(null, "Roçadeira", BigDecimal.valueOf(800.00));
        Produto p9 = new Produto(null, "Abajour", BigDecimal.valueOf(100.00));
        Produto p10 = new Produto(null, "Pendente", BigDecimal.valueOf(180.00));
        Produto p11 = new Produto(null, "Shampoo", BigDecimal.valueOf(90.00));

        // Criar categorias x produtos
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        // Criar produtos x categorias
        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        // Criar Estados
        Estado est1 = new Estado(null, "São Paulo");
        Estado est2 = new Estado(null, "Minas Gerais");

        // Criar Cidades
        Cidade cid1 = new Cidade(null, "Uberlandia", est2);
        Cidade cid2 = new Cidade(null, "São Paulo", est1);
        Cidade cid3 = new Cidade(null, "Campinas", est1);

        // Criar cliente
        Cliente cli1 = new Cliente(null, "Maria Silva", "ewertoncarreira@gmail.com",
                "36378912377", TipoCliente.PESSOAFISICA, "123");
        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Cliente cli2 = new Cliente(null, "Ana Costa", "ewerton.carreira.web@gmail.com",
                "31628382740", TipoCliente.PESSOAFISICA, "123");
        cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300",
                "jardim", "38220834", cli1, cid1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105",
                "centro", "38777012", cli1, cid2);
        Endereco e3 = new Endereco(null, "Avenida Floriano", "2106",
                "Centro", "281777012", cli2, cid2);

        // Persistencia dos objetos na base de dados
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        est1.getCidades().addAll(Arrays.asList(cid2, cid3));
        est2.getCidades().addAll(Arrays.asList(cid1));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2,e3));
    }
}