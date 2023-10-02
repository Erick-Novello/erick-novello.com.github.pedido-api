package ericknovello.com.github.pedidosapi.service;

import ericknovello.com.github.pedidosapi.entity.Estado;
import ericknovello.com.github.pedidosapi.exception.ObjectNotFoundException;
import ericknovello.com.github.pedidosapi.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado find(Integer id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        return estado.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id +
                ", Tipo" + Estado.class.getName()));
    }

    public Estado insert(Estado estado) {
        return estadoRepository.save(estado);
    }


    public Estado update(Estado estado, Integer id) {
        find(id);
        estado.setId(id);
        return estadoRepository.save(estado);
    }

    public void delete(Integer id) {
        find(id);
        estadoRepository.deleteById(id);
    }

}
