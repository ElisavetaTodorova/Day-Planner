package day.planner.events;

import day.planner.data.DataStorage;
import org.junit.Assert;
import org.junit.Test;

public class EventSelectorTest {

    @Test(expected = EventDoesNotExist.class)
    public void gettingEventWithNotExistingDate() throws EventDoesNotExist {
        EventSelector selector = new EventSelector(new DataStorage());

        selector.getEventByHourAndDay("12M-3D-12H");
    }

    @Test
    public void gettingEventWithExistingDate() {
        EventSelector selector = new EventSelector(new DataStorage());
        Event event = null;
        String date = "1M-20D-11H";
        try {
            event = selector.getEventByHourAndDay(date);
        } catch (EventDoesNotExist e) {
            System.out.println(e.getMessage());
        }

        String eventDate = event.getDate();

        Assert.assertEquals(date,eventDate);

    }


    @Test
    public void getEventsWithNonExistingMount(){
        EventSelector selector = new EventSelector(new DataStorage());

        int expectedSize = 0;
        int size = selector.getEventsByMount("5M").size();

        Assert.assertEquals(expectedSize,size);

    }


}