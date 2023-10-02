package ericknovello.com.github.pedidosapi.repository;

import ericknovello.com.github.pedidosapi.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
