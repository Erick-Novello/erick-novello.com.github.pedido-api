package ericknovello.com.github.pedidosapi.service;

import ericknovello.com.github.pedidosapi.entity.Cidade;
import ericknovello.com.github.pedidosapi.exception.ObjectNotFoundException;
import ericknovello.com.github.pedidosapi.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade find(Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        return cidade.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id +
                ", Tipo" + Cidade.class.getName()));
    }

    public Cidade insert(Cidade cidade, String requestURI) {

        ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cidade.getId())
                .toUri();
        return cidadeRepository.save(cidade);
    }

//    public Cidade insert(Cidade cidade) {
//        return cidadeRepository.save(cidade);
//    }

    public Cidade update(Cidade cidade, Integer id) {
        find(id);
        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }

    public void delete(Integer id) {
        find(id);
        cidadeRepository.deleteById(id);
    }

}
