package christmas.domain.discount;

import christmas.domain.Order;

public class SpecialDiscountPolicy implements DiscountPolicy{
    @Override
    public Discount apply(Order order) {
        if (order.isSpecialDay()) {
            return new Discount(DiscountType.특별_할인, 1000);
        }
        return new Discount(DiscountType.NONE, 0);
    }
}
