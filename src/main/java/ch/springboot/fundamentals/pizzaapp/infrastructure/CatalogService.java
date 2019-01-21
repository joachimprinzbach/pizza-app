package ch.springboot.fundamentals.pizzaapp.infrastructure;

import java.util.Set;

public interface CatalogService {


    boolean isPizzaAvailable(String name);

    Set<CatalogEntryDto> getPizzaInventory();
}
