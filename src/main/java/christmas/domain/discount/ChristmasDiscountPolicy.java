package christmas.domain.discount;

import christmas.domain.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT = 1000;
    private static final int ADD_DISCOUNT = 100;
    @Override
    public Discount apply(Order order) {
        int computeFirstDayBetween = order.computeFirstDayBetween();
        if (computeFirstDayBetween == 0) {
            return new Discount(DiscountType.NONE, 0);
        }
        int calculateDiscount = calculateDiscount(computeFirstDayBetween);
        return new Discount(DiscountType.크리스마스_디데이_할인, calculateDiscount);
    }

    public int calculateDiscount(int dayBetween) {
        return ADD_DISCOUNT * dayBetween + DISCOUNT;
    }

}
