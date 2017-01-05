package day.planner.models;

import day.planner.interfaces.Validator;

/**
 * Created by ELISAV on 5.1.2017 г..
 */
public class DateValidator implements Validator {

    @Override
    public boolean validate(String input) {
        String[] hourDayPair = input.split("-");

        int hour = Integer.parseInt(hourDayPair[0].substring(0, hourDayPair[0].length() - 1));
        int day = Integer.parseInt(hourDayPair[1].substring(0, hourDayPair[1].length() - 1));


        return !(day > 31 || day < 0) && !(hour > 24 || hour < 0);

    }
}
