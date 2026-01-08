package christmas.domain;

public class Dish {
    private final MenuType type;
    private final String name;
    private final int cost;

    public Dish(MenuType type, String name, int cost) {
        this.type = type;
        this.name = name;
        this.cost = cost;
    }
    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public MenuType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
    public int calculateAmount(int quantity) {
        return cost * quantity;
    }
}
