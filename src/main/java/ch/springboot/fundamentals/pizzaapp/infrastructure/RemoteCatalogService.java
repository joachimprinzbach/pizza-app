package ch.springboot.fundamentals.pizzaapp.infrastructure;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@Profile("all")
public class RemoteCatalogService implements CatalogService {

    @Override
    public boolean isPizzaAvailable(String name) {
        RestTemplate restTemplate = new RestTemplate();
        CatalogEntryDto catalogEntryDto = restTemplate.getForObject("http://localhost:8090/api/catalog/" + name, CatalogEntryDto.class);
        System.out.println("real");
        return catalogEntryDto.isAvailable();
    }

    @Override
    public Set<CatalogEntryDto> getPizzaInventory() {
        RestTemplate restTemplate = new RestTemplate();
        Set<CatalogEntryDto> catalog = restTemplate.getForObject("http://localhost:8090/api/catalog/", Set.class);
        System.out.println("real");
        return catalog;
    }
}
