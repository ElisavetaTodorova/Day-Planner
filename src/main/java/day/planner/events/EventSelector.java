package day.planner.events;

import day.planner.data.DataStorage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class EventSelector {

    private DataStorage storage;

    public EventSelector(DataStorage storage) {
        this.storage = storage;
    }

    public Event getEventByHourAndDay(String eventDate) throws EventDoesNotExist {
        Map<String, Event> events = this.storage.getAllEvents();
        for (String date : events.keySet()) {
            if (date.equals(eventDate)) {
                return events.get(eventDate);
            }
        }

        throw new EventDoesNotExist("No such event!");
    }

    public List<Event> getEventsByDay(String day) {
        Map<String, Event> events = this.storage.getAllEvents();
        List<Event> result = new LinkedList<>();

        for (String date : events.keySet()) {
            String[] eventDayAndHour = date.split("-");
            String eventDay = eventDayAndHour[1];
            if (eventDay.equals(day)) {
                result.add(events.get(date));
            }
        }

        return result;

    }

    public List<Event> getEventsByMount(String mount) {
        Map<String, Event> events = this.storage.getAllEvents();
        List<Event> result = new LinkedList<>();

        for (String date : events.keySet()) {
            String[] eventDayAndHour = date.split("-");
            String eventDay = eventDayAndHour[0];
            if (eventDay.equals(mount)) {
                result.add(events.get(date));
            }
        }

        return result;

    }
    public List<Event> getAllEvents() {
        List<Event> result = new ArrayList<>();

        this.storage.getAllEvents()
                .entrySet()
                .forEach(e -> result.add(e.getValue()));

        return result;
    }
}
