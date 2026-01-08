package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);
    private final String display;
    private final int cost;

    Badge(String display, int cost) {
        this.display = display;
        this.cost = cost;
    }
    public boolean ge(int cost) {
        return cost >= this.cost;
    }
    public boolean hasNone() {
        return this == NONE;
    }

    public static Badge computeByCost(int cost) {
        return Arrays.stream(values())
                .filter(x -> x.ge(cost))
                .findFirst()
                .orElse(NONE);
    }

    public String getDisplay() {
        return display;
    }

    public int getCost() {
        return cost;
    }
}
