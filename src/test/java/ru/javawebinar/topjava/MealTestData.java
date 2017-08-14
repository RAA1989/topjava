package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

public class MealTestData {

    public static Meal m1 = new Meal(LocalDateTime.of(2017, 8, 5,0,0), "Breakfast", 1000);
    public static Meal m2 = new Meal(LocalDateTime.of(2017,8,5,0,0), "Lunch", 1500);
    public static Meal m3 = new Meal(LocalDateTime.of(2017,8,5,0,0), "Supper", 500);

    static {
        m1.setId(100002);
        m2.setId(100003);
        m3.setId(100004);
    }
    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            ((expected, actual) -> expected == actual ||
                    Objects.equals(expected.getDescription(), actual.getDescription()))
    );

}
