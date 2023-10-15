package ericknovello.com.github.pedidosapi.controller;

import ericknovello.com.github.pedidosapi.entity.Estado;
import ericknovello.com.github.pedidosapi.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> listEstado = estadoService.findAll();
        return ResponseEntity.ok().body(listEstado);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estado> find(@PathVariable Integer id) {
        Estado estado = estadoService.find(id);
        return ResponseEntity.ok().body(estado);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Estado estado) {
        URI uri = estadoService.insert(estado);
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody Estado estado) {
        estadoService.update(estado, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
