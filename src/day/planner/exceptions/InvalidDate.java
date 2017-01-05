package day.planner.exceptions;

/**
 * Created by ELISAV on 5.1.2017 г..
 */
public class InvalidDate extends Throwable {

    public InvalidDate(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
