package day.planner.data;


import day.planner.events.InvalidEventMarker;
import day.planner.events.InvalidEventType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public static boolean validateDate(String input) {
        String[] hourDayMount = input.split("-");

        int mount = Integer.parseInt(hourDayMount[0].substring(0, hourDayMount[0].length() - 1));
        int hour = Integer.parseInt(hourDayMount[1].substring(0, hourDayMount[1].length() - 1));
        int day = Integer.parseInt(hourDayMount[2].substring(0, hourDayMount[2].length() - 1));


        return !(day > 31 || day < 1) && !(hour > 24 || hour < 1) && !(mount > 12 || mount < 1);
    }

    public static boolean dateFormatValidate(String input) {
        Pattern pattern = Pattern.compile("[0-9]{1,2}M\\-[0-9]{1,2}D\\-[0-9]{1,2}H");

        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    public static boolean eventTypeValidate(String type) throws InvalidEventType {
        if(type.toLowerCase().equals("date") || type.toLowerCase().equals("task")){
            return true;
        }
        throw new InvalidEventType("Type of the event must be date or task.");
    }

    public static boolean eventMarkerValidate(String input) throws InvalidEventMarker {
        if(input.toLowerCase().equals("private") || input.toLowerCase().equals("public")
                || input.toLowerCase().equals("confidential")){
            return true;
        }

        throw new InvalidEventMarker("Marker must be private, public or confidential.");
    }

    public static boolean nullValidate(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        return true;
    }


}
