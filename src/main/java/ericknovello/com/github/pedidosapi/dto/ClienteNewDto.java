package ericknovello.com.github.pedidosapi.dto;

import ericknovello.com.github.pedidosapi.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDto implements Serializable {

    private static final long serialVersionUUID = 1L;
    @NotEmpty(message = "Campo NOME é de preenchimento obrigatório.")
    @Length(min = 5, max = 80, message = "O tamanho do campo NOME deve ser entre 5 e 80 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo E_MAIL é de preenchimento obrigatório.")
    @Email(message = "Campo E-mail inválido.")
    private String email;

    @NotEmpty(message = "Campo CPF/CNPJ é de preenchimento obrigatório.")
    private String cpfCnpj;

    private Integer tipo;

    @NotEmpty(message = "Campo SENHA é de preenchimento obrigatório")
    private String senha;

    @NotEmpty(message = "Campo LOGRADOURO é de preenchimento obrigatório.")
    private String logradouro;

    @NotEmpty(message = "Campo NUMERO é de preenchimento obrigatório.")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = "Campo cep é de preenchimento obrigatório.")
    private String cep;

    @NotEmpty(message = "Campo TELEFONE 1 é de preenchimento obrigatório.")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDto() {
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
