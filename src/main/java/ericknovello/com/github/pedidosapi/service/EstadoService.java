package ericknovello.com.github.pedidosapi.service;

import ericknovello.com.github.pedidosapi.entity.Estado;
import ericknovello.com.github.pedidosapi.exception.ObjectNotFoundException;
import ericknovello.com.github.pedidosapi.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    public URI insert(Estado estado) {

        Estado newEstado = estadoRepository.save(estado);

        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEstado.getId())
                .toUri();
    }

    public Estado find(Integer id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        return estado.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id +
                ", Tipo" + Estado.class.getName()));
    }

    public void update(Estado estado, Integer id) {
        find(id);
        estado.setId(id);
        estadoRepository.save(estado);
    }

    public void delete(Integer id) {
        find(id);
        estadoRepository.deleteById(id);
    }

}
