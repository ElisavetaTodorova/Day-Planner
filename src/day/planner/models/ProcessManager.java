package day.planner.models;

import day.planner.enums.CommnadMessages;
import day.planner.exceptions.EventDoesNotExist;
import day.planner.exceptions.InvalidEventType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class ProcessManager {

    private DateStorage storage;
    private EventSelector eventSelector;

    public ProcessManager() {
        this.storage = new DateStorage();
        this.eventSelector = new EventSelector(this.storage);
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter command");
            System.out.println(CommnadMessages.COMMAND_ADD.getValue());
            System.out.println(CommnadMessages.COMMAND_MODIFY.getValue());
            System.out.println(CommnadMessages.COMMAND_DELETE.getValue());
            System.out.println(CommnadMessages.COMMAND_GET_EVENTS_BY.getValue());
            System.out.println(CommnadMessages.COMMAND_EXIT.getValue());
            String command = reader.readLine();

            switch (command) {
                case "1":
                    System.out.println("Enter event type : task or date:");
                    String type = reader.readLine();
                    System.out.println("Enter event marker:\r\n"
                            + "Public\r\n"
                            + "Confidential\r\n"
                            + "Private");

                    String marker = reader.readLine();
                    System.out.println("Enter hour and day for the event in format 12D-12H");
                    String date = reader.readLine();

                   while (!DateValidator.validate(date)){
                        date = reader.readLine();
                    }
                    System.out.println("Enter event description:");
                    String description = reader.readLine();
                    Event event = null;
                    try {
                        event = new Event(type, date, marker, description);
                    } catch (InvalidEventType invalidEventType) {
                        invalidEventType.printStackTrace();
                    }
                    this.addEvent(event);
                    break;
                case "2":
                    System.out.println("Enter the date of the event you want to change in format 12H-12D.");
                    String dateOfTheEventYouWantToChange = reader.readLine();
                    while (!DateValidator.validate(dateOfTheEventYouWantToChange)){
                        dateOfTheEventYouWantToChange = reader.readLine();
                    }
                    try {
                        try {
                            this.modifyEvent(dateOfTheEventYouWantToChange);
                        } catch (InvalidEventType invalidEventType) {
                            invalidEventType.printStackTrace();
                        }
                    } catch (EventDoesNotExist eventDoesNotExist) {
                        System.out.println("this event does not exist.Please enter another date:");
                        dateOfTheEventYouWantToChange = reader.readLine();
                        try {
                            this.modifyEvent(dateOfTheEventYouWantToChange);
                        } catch (EventDoesNotExist | InvalidEventType eventDoesNotExist1) {
                            eventDoesNotExist1.printStackTrace();
                        }
                        eventDoesNotExist.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("Enter date of the event you want to delete:");
                    String eventDate = reader.readLine();
                    try {
                        this.deleteEvent(eventDate);
                    } catch (EventDoesNotExist eventDoesNotExist) {
                        System.out.println("this event does not exist.Please enter another date:");
                        eventDate = reader.readLine();
                        try {
                            this.deleteEvent(eventDate);
                        } catch (EventDoesNotExist eventDoesNotExist1) {
                            eventDoesNotExist1.printStackTrace();
                        }
                    }
                    break;
                case "4":
                    System.out.println("1.For hour");
                    System.out.println("2.For whole day");
                    System.out.println("3.For mount");
                    System.out.println("Enter your choice using one of the words: hour, day or mount");
                    String choice = reader.readLine();
                    String value = null;
                    if (choice.equals("hour")) {
                        System.out.println("Enter hour and day int the format 12D-12H");
                        value = reader.readLine();
                    } else if (choice.equals("day")) {
                        System.out.println("Enter day of mount in the format 12D");
                        value = reader.readLine();
                    }
                    try {
                        this.showCalendar(choice, value);
                    } catch (EventDoesNotExist eventDoesNotExist) {
                        eventDoesNotExist.printStackTrace();
                    }
                    break;
                case "5":
                    return;
            }
        }

    }

    private void addEvent(Event event) {
        this.storage.writeEvent(event);
    }

    private void deleteEvent(String date) throws EventDoesNotExist {
        Event event = this.eventSelector.getEventByHourAndDay(date);

        Map<String, Event> events = this.storage.readEvents();

        events.remove(event.getDate());

        this.storage.refreshDB(events);

    }

    public void modifyEvent(String date) throws EventDoesNotExist, IOException, InvalidEventType {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Event> events = this.storage.readEvents();
        Event event = events.remove(date);
        Event newEvent = new Event(event.getType(), event.getDate(), event.getMarker(), event.getDescription());

        System.out.println("Enter the what about the event you want to change:");
        System.out.println("1.Type");
        System.out.println("2.Date");
        System.out.println("3.Marker");
        System.out.println("4.Description");

        String choice = reader.readLine();

        switch (choice) {
            case "1":
                System.out.println("Enter event type : task or date:");
                String type = reader.readLine();
                try {
                    event.setType(type);
                } catch (InvalidEventType invalidEventType) {
                    invalidEventType.printStackTrace();
                }
                break;
            case "2":
                System.out.println("Enter hour and day for the event in format 1D-10H");
                String newDate = reader.readLine();

                while (events.containsKey(newDate)) {
                    System.out.println("Event with that date already exist");
                    System.out.println(events.get(newDate).toString());
                    System.out.println("Please enter new date:");
                    newDate = reader.readLine();
                }
                event.setDate(newDate);
                break;
            case "3":
                System.out.println("Enter event marker:\r\n"
                        + "Public\r\n"
                        + "Confidential\r\n"
                        + "Private");

                String marker = reader.readLine();
                event.setMarker(marker);
                break;
            case "4":
                System.out.println("Enter event description:");
                String description = reader.readLine();
                event.setDescription(description);
                break;
        }
        events.put(event.getDate(),event);
        this.storage.refreshDB(events);
    }

    private void showCalendar(String by, String value) throws EventDoesNotExist {
        switch (by) {
            case "hour":
                System.out.println(this.eventSelector.getEventByHourAndDay(value).toString());
                break;
            case "day":
                this.eventSelector.getEventsByDay(value)
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .forEach(event -> System.out.println(event.toString()));
                break;
            case "mount":
                this.eventSelector.getAllEvents()
                        .stream()
                        .sorted(Comparator.comparing(Event::getDate))
                        .forEach(event -> System.out.println(event.toString()));
                break;
        }

    }
}
