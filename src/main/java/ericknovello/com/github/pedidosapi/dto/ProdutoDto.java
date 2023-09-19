package ericknovello.com.github.pedidosapi.dto;

import ericknovello.com.github.pedidosapi.entity.Produto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoDto implements Serializable {

    private static final long serialVersionUUID = 1L;

    private Integer id;

    private String nome;

    private BigDecimal preco;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
