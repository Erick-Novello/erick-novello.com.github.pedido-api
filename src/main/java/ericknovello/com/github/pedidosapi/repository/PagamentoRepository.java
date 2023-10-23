package ericknovello.com.github.pedidosapi.repository;

import ericknovello.com.github.pedidosapi.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
