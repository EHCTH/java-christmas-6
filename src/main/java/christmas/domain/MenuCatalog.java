package christmas.domain;

import java.util.List;

public final class MenuCatalog {
    private static final Dishes DEFAULT = new Dishes(
            List.of(
            // 애피타이저
            new Dish(MenuType.애피타이저, "양송이수프", 6_000),
            new Dish(MenuType.애피타이저, "타파스", 5_500),
            new Dish(MenuType.애피타이저, "시저샐러드", 8_000),

            // 메인
            new Dish(MenuType.메인, "티본스테이크", 55_000),
            new Dish(MenuType.메인, "바비큐립", 54_000),
            new Dish(MenuType.메인, "해산물파스타", 35_000),
            new Dish(MenuType.메인, "크리스마스파스타", 25_000),

            // 디저트
            new Dish(MenuType.디저트, "초코케이크", 15_000),
            new Dish(MenuType.디저트, "아이스크림", 5_000),

            // 음료
            new Dish(MenuType.음료, "제로콜라", 3_000),
            new Dish(MenuType.음료, "레드와인", 60_000),
            new Dish(MenuType.음료, "샴페인", 25_000)
    ));

    public static Dish findByName(String name) {
        return DEFAULT.findByDish(name);
    }

}
