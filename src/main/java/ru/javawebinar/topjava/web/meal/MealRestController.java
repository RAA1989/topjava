package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll(){
        log.info("getAll");
        List<Meal> list = service.getAll();
        List<MealWithExceed> res = MealsUtil.getWithExceeded(list, MealsUtil.DEFAULT_CALORIES_PER_DAY);
        return res;
    }

    public List<MealWithExceed> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        log.info("getAllFiltered");
        List<Meal> list = service.getAll();
        List<MealWithExceed> res = MealsUtil.getFilteredWithExceededTime(list, startDate, startTime, endDate, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
        return res;
    }

    public Meal get(int id){
        log.info("get");
        return service.get(id);
    }

    public Meal create (Meal meal){
        log.info("meal");
        checkNew(meal);
        return service.save(meal);
    }

    public void delete(int id){
        log.info("delete");
        service.delete(id);
    }

    public void update(Meal meal, int id){
        log.info("update");
        checkIdConsistent(meal,id);
        service.update(meal);
    }

}