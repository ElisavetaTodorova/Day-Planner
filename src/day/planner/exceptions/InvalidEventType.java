package day.planner.exceptions;

/**
 * Created by ELISAV on 5.1.2017 г..
 */
public class InvalidEventType extends Throwable {

    public InvalidEventType(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
