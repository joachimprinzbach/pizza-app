package ch.springboot.fundamentals.pizzaapp.api;

import ch.springboot.fundamentals.pizzaapp.api.PizzaDto;
import ch.springboot.fundamentals.pizzaapp.infrastructure.CatalogEntryDto;
import ch.springboot.fundamentals.pizzaapp.infrastructure.CatalogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PizzaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogService catalogService;

    @Test
    public void getAll() throws Exception {
        CatalogEntryDto margherita = new CatalogEntryDto("Margherita", true);
        CatalogEntryDto salami = new CatalogEntryDto("Salami", false);
        List<CatalogEntryDto> allEntries = Arrays.asList(margherita, salami);
        when(catalogService.getPizzaInventory()).thenReturn(allEntries);

        mockMvc.perform(get("/pizzas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Margherita"))
                .andExpect(jsonPath("$[0].available").value(true))
                .andExpect(jsonPath("$[1].name").value("Salami"))
                .andExpect(jsonPath("$[1].available").value(false));
    }

    @Test
    public void order_pizzaAvailable() throws Exception {
        PizzaDto quattro_formaggi = new PizzaDto("Quattro Formaggi");
        when(catalogService.isPizzaAvailable("Quattro Formaggi")).thenReturn(true);

        mockMvc.perform(
                post("/pizzas")
                        .content(asJsonString(quattro_formaggi))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("SUCCESS"));
    }

    @Test
    public void order_pizzaUnAvailable() throws Exception {
        PizzaDto quattro_formaggi = new PizzaDto("Quattro Formaggi");
        when(catalogService.isPizzaAvailable("Quattro Formaggi")).thenReturn(false);

        mockMvc.perform(
                post("/pizzas")
                        .content(asJsonString(quattro_formaggi))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("FAIL"));
    }

    private String asJsonString(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}