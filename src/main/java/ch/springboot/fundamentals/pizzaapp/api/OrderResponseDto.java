package ch.springboot.fundamentals.pizzaapp.api;

public class OrderResponseDto {

    private String state;

    public OrderResponseDto(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
