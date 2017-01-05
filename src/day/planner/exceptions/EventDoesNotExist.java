package day.planner.exceptions;

/**
 * Created by ELISAV on 5.1.2017 г..
 */
public class EventDoesNotExist extends Throwable {

    public EventDoesNotExist(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
