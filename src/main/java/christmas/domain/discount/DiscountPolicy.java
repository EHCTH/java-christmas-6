package christmas.domain.discount;

import christmas.domain.Order;

public interface DiscountPolicy {
    Discount apply(Order order);
}
