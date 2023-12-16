package ma.formation.irisi;


import com.fasterxml.jackson.databind.ObjectMapper;
import ma.formation.irisi.service.facade.admin.commun.CategorieProduitAdminService;
import ma.formation.irisi.ws.dto.commun.CategorieProduitDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc; // to make requests to the ProductController
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper; // convert the ProductRequest to String
    @Autowired
    private CategorieProduitAdminService categorieProduitAdminService;
    private String path= "http://localhost:8036/api/admin/categorieProduit/";


    @Test
    void shouldCreateProduct() throws Exception {
        CategorieProduitDto categorieProduitRequest = getCategorieProduitRequest();
        String productRequestString = objectMapper.writeValueAsString(categorieProduitRequest);
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(103, categorieProduitAdminService.findAllOptimized().size());
    }

    private CategorieProduitDto getCategorieProduitRequest() {
        CategorieProduitDto categorieProduitDto = new CategorieProduitDto();
        categorieProduitDto.setLibelle("iPhone13");
        categorieProduitDto.setReference("ipho13");
        return categorieProduitDto;

    }
}
