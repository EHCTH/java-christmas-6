package christmas.application.service;

import christmas.application.port.inbound.ChristmasUseCase;
import christmas.domain.Badge;
import christmas.domain.Order;
import christmas.domain.OrderLine;
import christmas.domain.VisitDay;
import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.discount.DiscountType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChristmasService implements ChristmasUseCase {
    private final List<DiscountPolicy> discountPolicies;

    public ChristmasService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    @Override
    public Receipt order(VisitDay visitDay, List<OrderLine> orderLines) {
        Order order = new Order(visitDay, orderLines);
        int discountBeforeTotalAmount = order.calculateTotalAmount();
        List<Discount> benefitInfo = computeBenefitInfo(order);
        Discount gift = findByType(benefitInfo, DiscountType.증정_이벤트);
        int benefitTotalAmount = calculateBenefitTotalAmount(benefitInfo);
        int discountAfterTotalAmount = discountBeforeTotalAmount - (benefitTotalAmount - gift.cost());
        Badge badge = Badge.computeByCost(benefitTotalAmount);
        return new Receipt(
                discountBeforeTotalAmount,
                gift,
                benefitInfo,
                benefitTotalAmount,
                discountAfterTotalAmount,
                badge
        );
    }

    private Discount findByType(List<Discount> benefitInfo, DiscountType discountType) {
        return benefitInfo.stream()
                .filter(x -> x.type().equals(discountType))
                .findFirst()
                .orElse(new Discount(DiscountType.NONE, 0));
    }

    private static int calculateBenefitTotalAmount(List<Discount> benefitInfo) {
        return benefitInfo.stream()
                .filter(Discount::hasEvent)
                .mapToInt(Discount::cost)
                .sum();
    }

    private List<Discount> computeBenefitInfo(Order order) {
        if (order.isEvent()) {
            return discountPolicies.stream()
                    .map(x -> x.apply(order))
                    .toList();
        }
        return Collections.emptyList();
    }
}
