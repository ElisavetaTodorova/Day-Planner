package day.planner.models;


public class DateValidator{

    public static boolean validate(String input) {
        String[] hourDayPair = input.split("-");

        int mount = Integer.parseInt(hourDayPair[0].substring(0, hourDayPair[0].length() - 1));
        int hour = Integer.parseInt(hourDayPair[1].substring(0, hourDayPair[1].length() - 1));
        int day = Integer.parseInt(hourDayPair[2].substring(0, hourDayPair[2].length() - 1));


        return !(day > 31 || day < 0) && !(hour > 24 || hour < 0) && !(mount > 12 || mount < 1);
    }
}
