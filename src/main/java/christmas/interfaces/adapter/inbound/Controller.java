package christmas.interfaces.adapter.inbound;

import christmas.application.port.inbound.ChristmasUseCase;
import christmas.domain.OrderLine;
import christmas.domain.VisitDay;

import java.util.List;
import java.util.function.Supplier;

public class Controller {
    private final ChristmasUseCase useCase;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(ChristmasUseCase useCase, InputView inputView, OutputView outputView) {
        this.useCase = useCase;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    public void run() {
        VisitDay visitDay = getRetryUntilSuccess(inputView::promptVisitDay);
        List<OrderLine> orderLines = getRetryUntilSuccess(inputView::promptOrderLine);
        outputView.displayVisitDay(visitDay);
        outputView.displayOrderLine(orderLines);
        ChristmasUseCase.Receipt order = useCase.order(visitDay, orderLines);
        outputView.display(order);
    }
    private <T> T getRetryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void runRetryUntilSuccess(Runnable task) {
        while (true) {
            try {
                task.run();
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
