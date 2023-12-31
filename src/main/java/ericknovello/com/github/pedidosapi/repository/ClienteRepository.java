package ericknovello.com.github.pedidosapi.repository;

import ericknovello.com.github.pedidosapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository <Cliente, Integer>{

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
