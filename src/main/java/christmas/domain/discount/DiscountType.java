package christmas.domain.discount;

import java.security.PublicKey;

public enum DiscountType {
    크리스마스_디데이_할인("크리스마스 디데이 할인"),
    평일_할인("평일 할인"),
    주말_할인("주말 할인"),
    특별_할인("특별 할인"),
    증정_이벤트("증정 이벤트"),
    NONE("없음") {
        @Override
        public boolean hasEvent() {
            return false;
        }
    };
    private final String display;

    DiscountType(String display) {
        this.display = display;
    }
    public boolean hasEvent() {
        return true;
    }

    public String getDisplay() {
        return display;
    }
}
