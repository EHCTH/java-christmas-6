package christmas.bootstrap;

import christmas.application.port.inbound.ChristmasUseCase;
import christmas.application.service.ChristmasService;
import christmas.domain.discount.*;
import christmas.interfaces.adapter.inbound.Controller;
import christmas.interfaces.adapter.inbound.InputView;
import christmas.interfaces.adapter.inbound.OutputView;

import java.util.List;

public class AppConfig {
    /*
    크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
     */
    private final List<DiscountPolicy> discountPolicies = List.of(
            new ChristmasDiscountPolicy(),
            new DayDiscountPolicy(),
            new SpecialDiscountPolicy(),
            new GiftDiscountPolicy()
    );
    private final ChristmasUseCase useCase = new ChristmasService(discountPolicies);

    public Controller controller() {
        return new Controller(useCase, new InputView(), new OutputView());
    }
}
