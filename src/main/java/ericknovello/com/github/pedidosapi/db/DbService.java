package ericknovello.com.github.pedidosapi.db;

import ericknovello.com.github.pedidosapi.entity.Cidade;
import ericknovello.com.github.pedidosapi.entity.Estado;
import ericknovello.com.github.pedidosapi.repository.CidadeRepository;
import ericknovello.com.github.pedidosapi.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DbService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public void instantiateTestDatabase() throws ParseException {
        // Criar Estados
        Estado est1 = new Estado(null, "Sao Paulo");
        Estado est2 = new Estado(null, "Minas Gerais");

        // Criar Cidades
        Cidade cid1 = new Cidade(null,"Uberlandia", est2);
        Cidade cid2 = new Cidade(null,"Sao paulo",est1);
        Cidade cid3 = new Cidade(null,"Campinas",est1);

        est1.getCidades().addAll(Arrays.asList(cid2, cid3));
        est2.getCidades().addAll(Arrays.asList(cid1));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
    }

}
