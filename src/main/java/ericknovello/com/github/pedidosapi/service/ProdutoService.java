package ericknovello.com.github.pedidosapi.service;

import ericknovello.com.github.pedidosapi.dto.ProdutoDto;
import ericknovello.com.github.pedidosapi.entity.Categoria;
import ericknovello.com.github.pedidosapi.entity.Produto;
import ericknovello.com.github.pedidosapi.exception.DataIntegrityException;
import ericknovello.com.github.pedidosapi.exception.ObjectNotFoundException;
import ericknovello.com.github.pedidosapi.repository.CategoriaRepository;
import ericknovello.com.github.pedidosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Produto.class.getName()));
    }

    public URI insert(ProdutoDto produtoDto) {
        Produto produto = fromDto(produtoDto);

        Produto produtoSalvo = produtoRepository.save(produto);
        categoriaRepository.save(produtoSalvo.getCategorias().get(0));
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
    }

    public void update(Integer id, ProdutoDto produtoUpdateDto) {
        Produto produtoToUpdate = find(id);
        updateData(produtoToUpdate, produtoUpdateDto);
        produtoRepository.save(produtoToUpdate);
    }

    public void delete(Integer id) {
        find(id);
        try {
            produtoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir um Produto que possui pedidos relacionados."
                    + e.getMessage());
        }
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome, categorias, pageRequest);
    }

    public void updateData(Produto produtoToUpdate, ProdutoDto produtoUpdateDto) {
        produtoToUpdate.setNome(produtoUpdateDto.getNome());
        produtoToUpdate.setPreco(produtoUpdateDto.getPreco());
    }

    public Produto fromDto(ProdutoDto produtoDto) {
        return new Produto(
                null,
                produtoDto.getNome(),
                produtoDto.getPreco()
        );
    }
}
