package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.web.meal.MealRestController.REST_MEAL_URL;

@RestController
@RequestMapping(REST_MEAL_URL)
public class MealRestController extends AbstractMealController {

    static final String REST_MEAL_URL = "/rest/meals";

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal create(@RequestBody Meal meal) {
        return super.create(meal);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

//    @Override
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
//        return super.getBetween(startDate, startTime, endDate, endTime);
//    }

    //@Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalDate endDate) {
        return super.getBetween(startDate, null, endDate, null);
    }
}