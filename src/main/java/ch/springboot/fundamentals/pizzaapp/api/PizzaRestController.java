package ch.springboot.fundamentals.pizzaapp.api;

import ch.springboot.fundamentals.pizzaapp.infrastructure.CatalogEntryDto;
import ch.springboot.fundamentals.pizzaapp.infrastructure.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pizzas")
public class PizzaRestController {

    private final CatalogService catalogService;

    @Autowired
    public PizzaRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public Set<CatalogEntryDto> getAll() {
        return catalogService.getPizzaInventory();
    }

    @PostMapping
    public String order(@RequestBody PizzaDto pizza) {
        boolean isPizzaAvailable = catalogService.isPizzaAvailable(pizza.getName());
        if (isPizzaAvailable) {
            System.out.println(pizza.getName());
            return "SUCCESS";
        } else {
            return "NOOOO";
        }
    }
}
