package christmas.interfaces.adapter.inbound;

import christmas.application.port.inbound.ChristmasUseCase;
import christmas.domain.Badge;
import christmas.domain.Dish;
import christmas.domain.OrderLine;
import christmas.domain.VisitDay;
import christmas.domain.discount.Discount;
import net.bytebuddy.pool.TypePool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OutputView {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM월 d일");
    public void displayOrderLine(List<OrderLine> orderLines) {
        System.out.println("<주문 메뉴>");
        orderLines.forEach(this::displayOrderLine);
        System.out.println();
    }
    public void displayVisitDay(VisitDay visitDay) {
        LocalDate date = visitDay.getDate();
        String formatted = date.format(FORMATTER);
        System.out.printf("%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", formatted);
    }
    public void displayOrderLine(OrderLine orderLine) {
        Dish dish = orderLine.getDish();
        System.out.printf("%s %d개%n", dish.getName(), orderLine.getQuantity());
    }
    public void display(ChristmasUseCase.Receipt receipt) {
        displayDiscountBeforeTotalAmount(receipt.discountBeforeTotalAmount());
        displayGift(receipt.gift());
        displayBenefitInfo(receipt.benefitInfo());
        displayBenefitTotalAmount(receipt.befitTotalAmount());
        displayDiscountAfterTotalAmount(receipt.discountAfterTotalAmount());
        displayBadge(receipt.badge());
    }

    private void displayDiscountBeforeTotalAmount(int discountBeforeTotalAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원%n%n", discountBeforeTotalAmount);
    }

    private void displayGift(Discount gift) {
        System.out.println("<증정 메뉴>");
        if (gift.hasEvent()) {
            System.out.println("샴페인 1개");
            System.out.println();
            return;
        }
        System.out.println("없음");
        System.out.println();
    }

    private void displayBenefitInfo(List<Discount> discountList) {
        System.out.println("<혜택 내역>");
        if (isNotApplyEvent(discountList)) {
            System.out.println("없음");
        }
        discountList.stream()
                .filter(Discount::hasEvent)
                .forEach(this::displayDiscount);
        System.out.println();
    }

    private boolean isNotApplyEvent(List<Discount> discountList) {
        return discountList.stream().noneMatch(Discount::hasEvent);
    }

    private void displayDiscount(Discount discount) {
        System.out.printf("%s: -%,d원%n", discount.displayType(), discount.cost());
    }

    private void displayBenefitTotalAmount(int benefitTotalAmount) {
        System.out.println("<총혜택 금액>");
        if (benefitTotalAmount == 0) {
            System.out.println("0원");
            System.out.println();
            return;
        }
        System.out.printf("-%,d원%n%n", benefitTotalAmount);
    }

    private void displayDiscountAfterTotalAmount(int discountAfterTotalAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n%n", discountAfterTotalAmount);
    }

    private void displayBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getDisplay());
    }
}
