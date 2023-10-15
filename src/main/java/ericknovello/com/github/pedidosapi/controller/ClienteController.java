package ericknovello.com.github.pedidosapi.controller;

import ericknovello.com.github.pedidosapi.dto.ClienteNewDto;
import ericknovello.com.github.pedidosapi.dto.ClienteUpdateDto;
import ericknovello.com.github.pedidosapi.entity.Cliente;
import ericknovello.com.github.pedidosapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> listCliente = clienteService.findAll();
        return ResponseEntity.ok().body(listCliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto clienteNewDto) {
        URI uri = clienteService.insert(clienteNewDto);
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteUpdateDto clienteUpdateDto) {
        clienteService.update(id, clienteUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
