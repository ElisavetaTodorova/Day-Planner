package day.planner.models;


public class DateValidator{

    public static boolean validate(String input) {
        String[] hourDayMount = input.split("-");

        int mount = Integer.parseInt(hourDayMount[0].substring(0, hourDayMount[0].length() - 1));
        int hour = Integer.parseInt(hourDayMount[1].substring(0, hourDayMount[1].length() - 1));
        int day = Integer.parseInt(hourDayMount[2].substring(0, hourDayMount[2].length() - 1));


        return !(day > 31 || day < 1) && !(hour > 24 || hour < 1) && !(mount > 12 || mount < 1);
    }
}
