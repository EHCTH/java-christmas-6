package christmas.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dishes {
    private final List<Dish> dishes;

    public Dishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Dish findByDish(String name) {
        return dishes.stream()
                .filter(dish -> dish.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));

    }

    public Map<MenuType, Long> countByType() {
        return dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    }

}
