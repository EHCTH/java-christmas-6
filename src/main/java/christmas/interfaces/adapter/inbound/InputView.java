package christmas.interfaces.adapter.inbound;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public List<OrderLine> promptOrderLine() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String data = readLine();
        List<String> parseData = Arrays.stream(data.split(",")).toList();
        List<OrderLine> orderLines = parseData.stream()
                .map(this::promptOrderLine)
                .toList();
        validateOrderLine(orderLines);
        return orderLines;
    }
    public VisitDay promptVisitDay() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String data = readLine();
        int parseInt = parseDay(data);
        return new VisitDay(parseInt);
    }

    private OrderLine promptOrderLine(String data) {
        List<String> parseData = Arrays.stream(data.split("-")).toList();
        Dish dish = MenuCatalog.findByName(parseData.get(0));
        int quantity = parseQuantity(parseData.get(1));
        return new OrderLine(dish, quantity);
    }
    private int parseDay(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
    private int parseQuantity(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }


    private int parseInt(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수를 입력해주세요");
        }
    }

    private LocalDate parseDate(String data) {
        try {
            return LocalDate.parse(data);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 날짜 형식으로 입력해주세요");
        }
    }

    private LocalTime parseTime(String data) {
        try {
            return LocalTime.parse(data);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 시간 형식으로 입력해주세요");
        }
    }

    private LocalDateTime parseDateTime(String data) {
        try {
            return LocalDateTime.parse(data);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 날짜 형식으로 입력해주세요");
        }
    }

    private String readLine() {
        return Console.readLine();
    }
    /*
    음료만 주문 시, 주문할 수 없습니다.
메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
     */
    private void validateOrderLine(List<OrderLine> orderLines) {
        boolean onlyBeverage = orderLines.stream()
                .allMatch(x -> x.getMenuType() == MenuType.음료);

        boolean isOrderOver20 = orderLines.stream()
                .mapToInt(OrderLine::getQuantity)
                .sum() > 20;


        if (onlyBeverage) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문 할 수 없습니다");
        }
        if (isOrderOver20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 최대 20개까지만 주문할 수 있습니다");
        }
    }
}
