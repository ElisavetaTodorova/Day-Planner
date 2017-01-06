package day.planner.exceptions;


public class EventDoesNotExist extends Throwable {

    public EventDoesNotExist(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
