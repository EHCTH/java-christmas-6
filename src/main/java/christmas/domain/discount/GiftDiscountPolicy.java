package christmas.domain.discount;

import christmas.domain.Order;

public class GiftDiscountPolicy implements DiscountPolicy{
    private static final int THRESHOLD = 120000;
    @Override
    public Discount apply(Order order) {
        if (order.isApplyGiveEvent(THRESHOLD)) {
            return new Discount(DiscountType.증정_이벤트, 25000);
        }
        return new Discount(DiscountType.NONE, 0);
    }
}
