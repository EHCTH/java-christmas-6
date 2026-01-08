package christmas.domain.discount;

public record Discount(DiscountType type, int cost) {
    public boolean hasEvent() {
        return type.hasEvent();
    }
    public String displayType() {
        return type.getDisplay();
    }
}
