package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Strogino on 06.09.2017.
 */


public class MealRestControllerTest extends AbstractControllerTest{

    private static final String REST_MEAL_URL = MealRestController.REST_MEAL_URL + "/";

    @Test
    public void testGet() throws Exception{
        mockMvc.perform(get(REST_MEAL_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testGetAll() throws Exception{
        TestUtil.print(mockMvc.perform(get(REST_MEAL_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MATCHER.contentListMatcher(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1)));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete(REST_MEAL_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertListEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2), mealService.getAll(USER_ID));
    }


    @Test
    public void testUpdate() throws Exception{
        Meal meal = new Meal(MEAL1_ID, LocalDateTime.of(2015, Month.MAY, 30, 10,0),
                "Завтрак", 500);
        meal.setUser(USER);
        mockMvc.perform(put(REST_MEAL_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(meal)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(meal, mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void testCreate() throws Exception{
        Meal expected = new Meal(null, LocalDateTime.of(1989, Month.SEPTEMBER, 16,23,59,59),
                "New meal", 1234);
        ResultActions action = mockMvc.perform(post(REST_MEAL_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected,returned);
        //MEALS.add(expected);
        //MATCHER.assertListEquals(MEALS, mealService.getAll(USER_ID));
    }
}
