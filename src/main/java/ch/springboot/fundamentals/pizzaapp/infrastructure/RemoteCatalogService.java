package ch.springboot.fundamentals.pizzaapp.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("!mock")
public class RemoteCatalogService implements CatalogService {

    @Value("${pizzaservice.url}")
    private String pizzaServiceUrl;

    @Value("${pizzaservice.port}")
    private String pizzaServicePort;

    @Override
    public boolean isPizzaAvailable(String name) {
        RestTemplate restTemplate = new RestTemplate();
        CatalogEntryDto catalogEntryDto = restTemplate.getForObject(getCatalogBaseApiUrl()+ "/" + name, CatalogEntryDto.class);
        System.out.println("real");
        return catalogEntryDto.isAvailable();
    }

    @Override
    public List<CatalogEntryDto> getPizzaInventory() {
        RestTemplate restTemplate = new RestTemplate();
        List<CatalogEntryDto> catalog = restTemplate.getForObject(getCatalogBaseApiUrl(), ArrayList.class);
        System.out.println("real");
        return catalog;
    }

    private String getCatalogBaseApiUrl() {
        return pizzaServiceUrl + ":" + pizzaServicePort + "/api/catalog";
    }
}
