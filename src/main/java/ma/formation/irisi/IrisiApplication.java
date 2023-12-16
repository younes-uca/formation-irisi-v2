package ma.formation.irisi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
//@EnableElasticsearchRepositories("ma.formation.irisi.dao")
//@EnableFeignClients("ma.formation.irisi.required.facade")
public class IrisiApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(IrisiApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            if (false) {

                createCategorieProduit();


            }
        };
    }


    private void createCategorieProduit() {
        String reference = "reference";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            CategorieProduit item = new CategorieProduit();
            item.setReference(fakeString(reference, i));
            item.setLibelle(fakeString(libelle, i));
            categorieProduitService.create(item);
        }
    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return 10L * i;
    }

    private static Integer fakeInteger(String attributeName, int i) {
        return 10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return BigDecimal.valueOf(i * 1L * 10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }

    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    @Autowired
    CategorieProduitAdminService categorieProduitService;
}


