package ma.formation.irisi;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.*;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.cache.annotation.EnableCaching;
import com.fasterxml.jackson.databind.SerializationFeature;


import ma.formation.irisi.bean.core.stock.Client;
import ma.formation.irisi.service.facade.admin.stock.ClientAdminService;
import ma.formation.irisi.bean.core.commun.Produit;
import ma.formation.irisi.service.facade.admin.commun.ProduitAdminService;
import ma.formation.irisi.bean.core.commun.CategorieProduit;
import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService;
import ma.formation.irisi.zynerator.security.common.AuthoritiesConstants;
import ma.formation.irisi.zynerator.security.bean.User;
import ma.formation.irisi.zynerator.security.bean.Permission;
import ma.formation.irisi.zynerator.security.bean.Role;
import ma.formation.irisi.zynerator.security.service.facade.UserService;
import ma.formation.irisi.zynerator.security.service.facade.RoleService;

//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
//@EnableElasticsearchRepositories("ma.formation.irisi.dao")
//@EnableFeignClients("ma.formation.irisi.required.facade")
public class IrisiApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(IrisiApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService) {
    return (args) -> {
        if(false){

            createClient();
            createProduit();
            createCategorieProduit();


    // Role admin

        User userForAdmin = new User("admin");

        Role roleForAdmin = new Role();
        roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
        List<Permission> permissionsForAdmin = new ArrayList<>();
        addPermissionForAdmin(permissionsForAdmin);
        roleForAdmin.setPermissions(permissionsForAdmin);
        if(userForAdmin.getRoles()==null)
            userForAdmin.setRoles(new ArrayList<>());

        userForAdmin.getRoles().add(roleForAdmin);
        userService.save(userForAdmin);
            }
        };
    }



    private void createClient(){
        String cin = "cin";
        String nom = "nom";
        String tel = "tel";
        String email = "email";
        String adresse = "adresse";
        String description = "description";
         String creance = "creance";
        for (int i = 1; i < 100; i++) {
            Client item = new Client();
            item.setCin(fakeString(cin, i));
            item.setNom(fakeString(nom, i));
            item.setTel(fakeString(tel, i));
            item.setEmail(fakeString(email, i));
            item.setAdresse(fakeString(adresse, i));
            item.setDescription(fakeString(description, i));
            item.setCreance(fakeBigDecimal(creance, i));
            clientService.create(item);
        }
    }
    private void createProduit(){
        String reference = "reference";
        String libelle = "libelle";
        String barcode = "barcode";
        String discription = "discription";
         String prixAchatMoyen = "prixAchatMoyen";
         String quantite = "quantite";
         String seuilAlert = "seuilAlert";
        for (int i = 1; i < 100; i++) {
            Produit item = new Produit();
            item.setReference(fakeString(reference, i));
            item.setLibelle(fakeString(libelle, i));
            item.setBarcode(fakeString(barcode, i));
            item.setDiscription(fakeString(discription, i));
            item.setPrixAchatMoyen(fakeBigDecimal(prixAchatMoyen, i));
            item.setQuantite(fakeBigDecimal(quantite, i));
            item.setSeuilAlert(fakeBigDecimal(seuilAlert, i));
            produitService.create(item);
        }
    }
    private void createCategorieProduit(){
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
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }
    private static void addPermissionForAdmin(List<Permission> permissions){
        permissions.add(new Permission("Client.edit"));
        permissions.add(new Permission("Client.list"));
        permissions.add(new Permission("Client.view"));
        permissions.add(new Permission("Client.add"));
        permissions.add(new Permission("Client.delete"));
        permissions.add(new Permission("Achat.edit"));
        permissions.add(new Permission("Achat.list"));
        permissions.add(new Permission("Achat.view"));
        permissions.add(new Permission("Achat.add"));
        permissions.add(new Permission("Achat.delete"));
        permissions.add(new Permission("AchatItem.edit"));
        permissions.add(new Permission("AchatItem.list"));
        permissions.add(new Permission("AchatItem.view"));
        permissions.add(new Permission("AchatItem.add"));
        permissions.add(new Permission("AchatItem.delete"));
        permissions.add(new Permission("Produit.edit"));
        permissions.add(new Permission("Produit.list"));
        permissions.add(new Permission("Produit.view"));
        permissions.add(new Permission("Produit.add"));
        permissions.add(new Permission("Produit.delete"));
        permissions.add(new Permission("CategorieProduit.edit"));
        permissions.add(new Permission("CategorieProduit.list"));
        permissions.add(new Permission("CategorieProduit.view"));
        permissions.add(new Permission("CategorieProduit.add"));
        permissions.add(new Permission("CategorieProduit.delete"));
    }

    @Autowired
    ClientAdminService clientService;
    @Autowired
    ProduitAdminService produitService;
    @Autowired
    CategorieProduitAdminService categorieProduitService;
}


