package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Collection<Meal> getAll(){
        log.info("getAll");
        return service.getAll();
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