package ericknovello.com.github.pedidosapi.validation;

import ericknovello.com.github.pedidosapi.dto.ClienteNewDto;
import ericknovello.com.github.pedidosapi.entity.Cliente;
import ericknovello.com.github.pedidosapi.enums.TipoCliente;
import ericknovello.com.github.pedidosapi.repository.ClienteRepository;
import ericknovello.com.github.pedidosapi.util.BR;
import ericknovello.com.github.pedidosapi.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInserValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDto clienteNewDto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())
                && !BR.isValidCpf(clienteNewDto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CPF Invalido"));
        }

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
                && !BR.isValidCnpj(clienteNewDto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Invalido"));
        }

        Cliente cliente = clienteRepository.findByEmail(clienteNewDto.getEmail());

        if(cliente != null){
            list.add(new FieldMessage("email", "Email ja existente."));
        }

        for (FieldMessage e : list){
            constraintValidatorContext.disableDefaultConstraintViolation();;
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }

}
