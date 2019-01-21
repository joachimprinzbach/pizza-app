package ch.springboot.fundamentals.pizzaapp.infrastructure;

public class CatalogEntryDto {

    private String name;
    private boolean isAvailable;

    public CatalogEntryDto(String name, boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}