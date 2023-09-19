package ericknovello.com.github.pedidosapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import ericknovello.com.github.pedidosapi.enums.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private static final long serialVersionUUID = 1L;

    private Integer numereDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numereDeParcelas) {
        super(id, estado, pedido);
        this.numereDeParcelas = numereDeParcelas;
    }

    public Integer getNumereDeParcelas() {
        return numereDeParcelas;
    }

    public void setNumereDeParcelas(Integer numereDeParcelas) {
        this.numereDeParcelas = numereDeParcelas;
    }
}
