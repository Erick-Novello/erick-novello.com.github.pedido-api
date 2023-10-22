package ericknovello.com.github.pedidosapi.service;

import ericknovello.com.github.pedidosapi.dto.CategoriaDto;
import ericknovello.com.github.pedidosapi.entity.Categoria;
import ericknovello.com.github.pedidosapi.entity.Produto;
import ericknovello.com.github.pedidosapi.exception.DataIntegrityException;
import ericknovello.com.github.pedidosapi.exception.ObjectNotFoundException;
import ericknovello.com.github.pedidosapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }

    public URI insert(CategoriaDto categoriaDTO) {
        Categoria categoria = fromDTO(categoriaDTO);
        Categoria newCategoria = categoriaRepository.save(categoria);
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCategoria.getId())
                .toUri();
    }

    public void update(CategoriaDto categoriaDTO, Integer id) {
        Categoria categoriaToUpdate = find(id);
        Categoria categoria = fromDTO(categoriaDTO);
        updateData(categoriaToUpdate, categoria);
        categoriaRepository.save(categoriaToUpdate);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Categoria que possui produtos.");
        }
    }

    public List<CategoriaDto> findAll() {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        return categoriaList.stream().map(categoria -> new CategoriaDto(categoria))
                .collect(Collectors.toList());

    }

    private void updateData(Categoria categoriaToUpdate, Categoria categoria) {
        categoriaToUpdate.setNome(categoria.getNome());
    }

    public Categoria fromDTO(CategoriaDto categoriaDTO) {
        categoriaDTO.setId(null);
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

}
