package christmas;

import christmas.bootstrap.AppConfig;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        AppConfig appConfig = new AppConfig();
        appConfig.controller().run();

//        LocalDate localDate = LocalDate.now();
//        LocalDate with = localDate.with(TemporalAdjusters.firstDayOfMonth());
//        Period period = Period.between(with, localDate);
//        System.out.println(period.getDays());
    }
}

