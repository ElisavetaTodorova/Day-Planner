package day.planner.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import day.planner.events.Event;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class DataStorage {

    private static Map<String, Event> events;

    static {
        load();
    }


    public DataStorage() {
        events = new HashMap<>();
    }

    public void writeEvent(Event event) {
        DataStorage.events.put(event.getDate(), event);

        File file = null;
        try {
            file = new File(this.getClass().getResource("/info.json").toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        assert file != null;
        try (FileWriter writer = new FileWriter(file, true)) {
            JsonObject eventAsJSObject = new JsonObject();

            eventAsJSObject.addProperty("type", event.getType());
            eventAsJSObject.addProperty("date", event.getDate());
            eventAsJSObject.addProperty("marker", event.getMarker());
            eventAsJSObject.addProperty("description", event.getDescription());

            writer.write(eventAsJSObject.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void refreshDB() {
        File file = null;
        try {
            file = new File(this.getClass().getResource("/info.json").toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        assert file != null;
        try (FileWriter writer = new FileWriter(file, false)) {
            for (Event event : events.values()) {

                JsonObject eventAsJSObject = new JsonObject();

                eventAsJSObject.addProperty("type", event.getType());
                eventAsJSObject.addProperty("date", event.getDate());
                eventAsJSObject.addProperty("marker", event.getMarker());
                eventAsJSObject.addProperty("description", event.getDescription());

                writer.write(eventAsJSObject.toString() + System.lineSeparator());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public Map<String, Event> getAllEvents() {
        return DataStorage.events;
    }


    private static void load() {

        File file = null;
        try {
            file = new File(DataStorage.class.getResource("/info.json").toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        assert file != null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            JsonParser parser = new JsonParser();
            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    break;
                }

                JsonObject eventaSJSON = (JsonObject) parser.parse(line);
                Event event = new Gson().fromJson(eventaSJSON, Event.class);
                events.put(event.getDate(), event);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
