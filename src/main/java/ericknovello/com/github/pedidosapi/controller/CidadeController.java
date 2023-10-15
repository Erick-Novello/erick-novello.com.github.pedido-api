package ericknovello.com.github.pedidosapi.controller;

import ericknovello.com.github.pedidosapi.entity.Cidade;
import ericknovello.com.github.pedidosapi.entity.Cliente;
import ericknovello.com.github.pedidosapi.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cidade>> findAll() {
        List<Cidade> listCidade = cidadeService.findAll();
        return ResponseEntity.ok().body(listCidade);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cidade> find(@PathVariable Integer id) {
        Cidade cidade = cidadeService.find(id);
        return ResponseEntity.ok().body(cidade);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Cidade cidade) {
        URI uri = cidadeService.insert(cidade);
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Cidade cidade) {
        cidadeService.update(cidade, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
