package day.planner.events;

import org.junit.Test;

/**
 * Created by ELISAV on 12.1.2017 г..
 */
public class EventTest {

    @Test(expected = InvalidEventMarker.class)
    public void setInvalidMarker() throws InvalidEventMarker {
        Event event = null;
        try {
            event = new Event("date","12M-12D-12H","private","Test");
        } catch (InvalidEventType | InvalidEventMarker e) {
            System.out.println(e.getMessage());
        }

        event.setMarker("Something Else");

    }


    @Test(expected = InvalidEventType.class)
    public void setInvalidType() throws InvalidEventType {
        Event event = null;
        try {
            event = new Event("date","12M-12D-12H","private","Test");
        } catch (InvalidEventType | InvalidEventMarker e) {
            System.out.println(e.getMessage());
        }

        event.setType("Something Else");

    }



}