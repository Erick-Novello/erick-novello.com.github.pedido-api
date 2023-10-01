package ericknovello.com.github.pedidosapi.service;

import ericknovello.com.github.pedidosapi.dto.ClienteNewDto;
import ericknovello.com.github.pedidosapi.dto.ClienteUpdateDto;
import ericknovello.com.github.pedidosapi.entity.Cidade;
import ericknovello.com.github.pedidosapi.entity.Cliente;
import ericknovello.com.github.pedidosapi.entity.Endereco;
import ericknovello.com.github.pedidosapi.enums.TipoCliente;
import ericknovello.com.github.pedidosapi.exception.DataIntegrityException;
import ericknovello.com.github.pedidosapi.exception.ObjectNotFoundException;
import ericknovello.com.github.pedidosapi.repository.ClienteRepository;
import ericknovello.com.github.pedidosapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id +
                ", Tipo" + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSaved = clienteRepository.save(cliente);
        enderecoRepository.save(clienteSaved.getEnderecos().get(0));
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente clientToUpdate = find(cliente.getId());
        updateData(clientToUpdate, cliente);
        return clienteRepository.save(clientToUpdate);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir um Cliente que possui pedidos relacionados."
                    + e.getMessage());
        }
    }

    private void updateData(Cliente clienteToUpdate, Cliente cliente) {
        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setEmail(cliente.getEmail());
    }

    public Cliente fromDto(ClienteNewDto clienteNewDto) {
        Cliente cliente = new Cliente(null,
                clienteNewDto.getNome(),
                clienteNewDto.getEmail(),
                clienteNewDto.getCpfCnpj(),
                TipoCliente.toEnum(clienteNewDto.getTipo()),
                clienteNewDto.getSenha());

        Cidade cidade = new Cidade(clienteNewDto.getCidadeId(), null, null);

        Endereco endereco = new Endereco(null,
                clienteNewDto.getLogradouro(),
                clienteNewDto.getNumero(),
                clienteNewDto.getComplemento(),
                clienteNewDto.getBairro(),
                cliente,
                cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNewDto.getTelefone1());
        if (clienteNewDto.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone2());
        }
        if (clienteNewDto.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone3());
        }

        return cliente;
    }

    public Cliente fromDto(ClienteUpdateDto clienteUpdateDto) {
        return new Cliente(
                clienteUpdateDto.getId(),
                clienteUpdateDto.getNome(),
                clienteUpdateDto.getEmail(),
                null,
                null,
                null);
    }

}
