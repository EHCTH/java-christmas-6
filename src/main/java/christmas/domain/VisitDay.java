package christmas.domain;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class VisitDay {
    private static final List<Integer> SPECIAL_DAY_LIST = List.of(3, 10, 17, 24, 25, 31);
    private final LocalDate date;

    public VisitDay(int day) {
        validateDate(day);
        this.date = LocalDate.of(2023, 12, day);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isSpecialDay() {
        int day = date.getDayOfMonth();
        return SPECIAL_DAY_LIST.contains(day);
    }

    public int computeFirstDayBetween() {
        LocalDate start = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = LocalDate.of(2023, 12, 26);
        if (this.date.isBefore(end)) {
            Period period = Period.between(start, date);
            return period.getDays();
        }
        return 0;

    }

    private void validateDate(int day) {
        try {
            LocalDate localDate = LocalDate.of(2023, 12, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
