package ericknovello.com.github.pedidosapi.repository;

import ericknovello.com.github.pedidosapi.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
