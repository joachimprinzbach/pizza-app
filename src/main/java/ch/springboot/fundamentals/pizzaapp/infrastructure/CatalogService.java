package ch.springboot.fundamentals.pizzaapp.infrastructure;

import java.util.List;

public interface CatalogService {


    boolean isPizzaAvailable(String name);

    List<CatalogEntryDto> getPizzaInventory();
}
