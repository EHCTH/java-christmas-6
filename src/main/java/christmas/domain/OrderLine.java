package christmas.domain;

import java.util.IntSummaryStatistics;
import java.util.Map;

public class OrderLine {
    private final Dish dish;
    private final int quantity;

    public OrderLine(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public int calculateAmount() {
        return dish.calculateAmount(quantity);
    }
    public MenuType getMenuType() {
        return dish.getType();
    }
    private void validateMaxQuantity(int quantity) {
        if (quantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
