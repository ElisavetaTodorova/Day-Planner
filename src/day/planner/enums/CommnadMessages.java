package day.planner.enums;

/**
 * Created by ELISAV on 6.1.2017 г..
 */
public enum CommnadMessages {

//    "Enter command");
//            parser.write("1.Add event.");
//            parser.write("2.Modify event.");
//            parser.write("3.Delete event");
//            parser.write("4.Get all events by:");
//            parser.write("5.Exit");
    COMMAND_ADD("1.Add event."),
    COMMAND_MODIFY("2.Modify event."),
    COMMAND_DELETE("3.Delete event."),
    COMMAND_GET_EVENTS_BY("Get all events by:"),
    COMMAND_EXIT("5.Exit.");

    private String message;

    CommnadMessages(String command) {
        this.message = command;
    }

    public String getValue(){
        return this.message;
    }

}
