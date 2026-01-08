package christmas.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private final VisitDay visitDay;
    private final List<OrderLine> orderLines;

    public Order(VisitDay visitDay, List<OrderLine> orderLines) {
        this.visitDay = visitDay;
        this.orderLines = orderLines;
    }

    public int calculateTotalAmount() {
        return orderLines.stream()
                .mapToInt(OrderLine::calculateAmount)
                .sum();
    }

    public boolean isEvent() {
        int calculateTotalAmount = calculateTotalAmount();
        return calculateTotalAmount >= 10000;
    }
    public boolean isWeekend() {
        return visitDay.isWeekend();
    }

    public boolean isWeekDay() {
        return visitDay.isWeekDay();
    }

    public boolean isSpecialDay() {
        return visitDay.isSpecialDay();
    }

    public int computeFirstDayBetween() {
        return visitDay.computeFirstDayBetween();
    }

    public boolean isApplyGiveEvent(int threshold) {
        int totalAmount = calculateTotalAmount();
        return threshold <= totalAmount;

    }

    public Map<MenuType, Integer> quantityByType() {
        return orderLines.stream()
                .collect(Collectors.groupingBy(
                        OrderLine::getMenuType,
                        Collectors.summingInt(OrderLine::getQuantity)
                ));
    }


}
