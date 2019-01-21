package ch.springboot.fundamentals.pizzaapp.infrastructure;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("mock")
public class MockedCatalogService implements CatalogService {

    private static final List<CatalogEntryDto> CATALOG = Arrays.asList(
            new CatalogEntryDto("Funghi", true),
            new CatalogEntryDto("Salami", true)
    );

    @Override
    public boolean isPizzaAvailable(String name) {
        System.out.println("mock");
        Optional<Boolean> pizzaIsAvailable = CATALOG.stream()
                .filter(entry -> entry.getName().equals(name))
                .map(CatalogEntryDto::isAvailable)
                .findFirst();
        return pizzaIsAvailable.orElse(false);
    }

    @Override
    public Set<CatalogEntryDto> getPizzaInventory() {
        return new HashSet(CATALOG);
    }
}
