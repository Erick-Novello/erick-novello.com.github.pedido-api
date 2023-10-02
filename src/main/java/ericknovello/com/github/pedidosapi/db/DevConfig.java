package ericknovello.com.github.pedidosapi.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DbService dbService;

    @Bean
    public boolean instantiateTestDatabase() throws ParseException{
        dbService.instantiateTestDatabase();
        return true;
    }

}
