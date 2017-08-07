package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;


public interface MealService {

    Collection<Meal> getAll();

    Meal get(int id);

    Meal save(Meal meal);

    void delete(int id);

    void update(Meal meal);
}