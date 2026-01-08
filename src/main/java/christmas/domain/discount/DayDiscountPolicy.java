package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.Order;

import java.util.Map;
import java.util.function.Supplier;

public class DayDiscountPolicy implements DiscountPolicy{
    private static final int DISCOUNT = 2023;

    @Override
    public Discount apply(Order order) {
        Map<MenuType, Integer> menuTypeIntegerMap = order.quantityByType();
        if (order.isWeekDay()) {
            return calculateDiscountByType(menuTypeIntegerMap, MenuType.디저트, DiscountType.평일_할인);
        }
        return calculateDiscountByType(menuTypeIntegerMap, MenuType.메인, DiscountType.주말_할인);
    }

    private Discount calculateDiscountByType(Map<MenuType, Integer> menuTypeIntegerMap, MenuType menuType, DiscountType discountType) {
        int quantity = menuTypeIntegerMap.getOrDefault(menuType, 0);
        int calculateTotalDiscount = calculateTotalDiscount(quantity);
        return new Discount(discountType, calculateTotalDiscount);
    }

    private int calculateTotalDiscount(int count) {
        return DISCOUNT * count;
    }


}
