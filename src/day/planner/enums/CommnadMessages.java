package day.planner.enums;


public enum CommnadMessages {

    COMMAND_ADD("1.Add event."),
    COMMAND_MODIFY("2.Modify event."),
    COMMAND_DELETE("3.Delete event."),
    COMMAND_GET_EVENTS_BY("4.Get all events by:"),
    COMMAND_EXIT("5.Exit.");

    private String message;

    CommnadMessages(String command) {
        this.message = command;
    }

    public String getValue(){
        return this.message;
    }

}
