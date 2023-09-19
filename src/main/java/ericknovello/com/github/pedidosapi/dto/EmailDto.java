package ericknovello.com.github.pedidosapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDto implements Serializable {

    private static final long serialVersionUUID = 1L;

    @NotEmpty(message = "Campo E_MAIL é de preenchimento obrigatório.")
    @Email(message = "Campo E-mail inválido.")
    private String email;

    public EmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
