package ericknovello.com.github.pedidosapi.validation;

import ericknovello.com.github.pedidosapi.dto.ClienteUpdateDto;
import ericknovello.com.github.pedidosapi.entity.Cliente;
import ericknovello.com.github.pedidosapi.repository.ClienteRepository;
import ericknovello.com.github.pedidosapi.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteUpdateDto> {


    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(ClienteUpdateDto clienteUpdateDto, ConstraintValidatorContext constraintValidatorContext) {
        Map<String, String> map = (Map<String, String>) httpServletRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente cliente = clienteRepository.findByEmail(clienteUpdateDto.getEmail());

        if (cliente != null && !cliente.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente."));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}

