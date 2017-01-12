package day.planner.events;

/**
 * Created by ELISAV on 12.1.2017 г..
 */
public class InvalidEventMarker extends Throwable {

    public InvalidEventMarker(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
