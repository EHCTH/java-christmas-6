package christmas.application.port.inbound;

import christmas.domain.Badge;
import christmas.domain.OrderLine;
import christmas.domain.VisitDay;
import christmas.domain.discount.Discount;

import java.util.List;

public interface ChristmasUseCase {
    Receipt order(VisitDay visitDay, List<OrderLine> orderLines);

    record Receipt(
            int discountBeforeTotalAmount,
            Discount gift,
            List<Discount> benefitInfo,
            int befitTotalAmount,
            int discountAfterTotalAmount,
            Badge badge
            ) {

    }

    /*
    <할인 전 총주문 금액>
8,500원

<증정 메뉴>
없음

<혜택 내역>
없음

<총혜택 금액>
0원

<할인 후 예상 결제 금액>
8,500원

<12월 이벤트 배지>
없음
     */


}
