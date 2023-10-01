package ericknovello.com.github.pedidosapi.dto;

import ericknovello.com.github.pedidosapi.entity.Cliente;
import ericknovello.com.github.pedidosapi.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteUpdateDto implements Serializable {

    private static final long serialVersionUUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo NOME é de Preenchimento obrigatorio.")
    @Length(min = 5, max = 80, message = "O tamanho do campo CATEGORIA deve ser entre 5 a 80 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo EMAIL é de Preenchimento obrigatorio.")
    @Email(message = "Campo E-mail invalido.")
    private String email;

    public ClienteUpdateDto() {
    }

    public ClienteUpdateDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
