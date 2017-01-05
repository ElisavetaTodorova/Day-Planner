package day.planner.models;

import day.planner.interfaces.IOParser;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class ProcessManager {

    private DateStorage storage;
    private EventSelector eventSelector;
    private IOParser parser;

    public ProcessManager() {
        this.storage = new DateStorage();
        this.eventSelector = new EventSelector(this.storage);
        this.parser = new ConsoleParser();
    }

    public void run() {
        while (true) {
            parser.write("Enter command");
            parser.write("1.Add event.");
            parser.write("2.Modify event.");
            parser.write("3.Delete event");
            parser.write("4.Get all events by:");
            parser.write("5.Exit");
            String command = parser.read();

            switch (command) {
                case "1":
                    parser.write("Enter event type : task or date:");
                    String type = parser.read();
                    parser.write("Enter event marker:");
                    parser.write("Public");
                    parser.write("Confidential");
                    parser.write("Private");
                    String marker = parser.read();
                    parser.write("Enter hour and day for the event in format 1D-10H");
                    String date = parser.read();
                    parser.write("Enter event description:");
                    String description = parser.read();
                    Event event = new Event(type, date, marker, description);
                    this.addEvent(event);
                    break;
                case "2":
                    break;
                case "3":
                case "4":
                    break;
                case "5":
                    return;
            }
        }

    }

    private void addEvent(Event event) {
        this.storage.writeEvent(event);
    }

    public void deleteEvent(String date) {

    }

    public void modifyEvent(String date) {

    }

    public void showCalendar() {

    }
}
