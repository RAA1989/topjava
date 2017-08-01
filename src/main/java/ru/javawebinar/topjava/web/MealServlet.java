package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Strogino on 28.07.2017.
 */
public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to topjava");


        List<MealWithExceed> list =  MealsUtil.getFilteredWithExceeded(MealsUtil.getMeals(),  LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        request.setAttribute("list", list);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }


}
