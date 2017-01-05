package day.planner.models;

import day.planner.exceptions.EventDoesNotExist;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class EventSelector {

    private DateStorage storage;

    public EventSelector(DateStorage storage) {
        this.storage = storage;
    }

    public Event getEventByHourAndDay(String eventDate) throws EventDoesNotExist {
        Map<String, Event> events = this.getAllEvents();

        for (String date : events.keySet()) {
            if(date.equals(eventDate)){
                return events.get(eventDate);
            }
        }

        throw new EventDoesNotExist("No such event!");
    }

    public List<Event> getEventsByDay(String day) {
        Map<String, Event> events = this.getAllEvents();
        List<Event> result = new LinkedList<>();

        for (String date : events.keySet()) {
            String[] eventDayAndHour = date.split("-");
            String eventDay = eventDayAndHour[0];
            if (eventDay.equals(day)) {
                result.add(events.get(date));
            }
        }

        return result;

    }

    public List<Event> getEventsByMount(String mount) {
        List<Event> result = new LinkedList<>();



        return null;

    }


    private Map<String, Event> getAllEvents() {
        return storage.readEvents();
    }
}
