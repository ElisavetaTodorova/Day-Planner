package day.planner.exceptions;

public class InvalidEventType extends Throwable {

    public InvalidEventType(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
