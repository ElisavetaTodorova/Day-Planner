package day.planner.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class DateStorage {

    public DateStorage() {

    }

    public void writeEvent(Event event) {
        try (FileWriter writer = new FileWriter("resources/info.json", true)) {
            JsonObject eventAsJSObject = new JsonObject();

            eventAsJSObject.addProperty("type", event.getType());
            eventAsJSObject.addProperty("date", event.getDate());
            eventAsJSObject.addProperty("marker", event.getMarker());
            eventAsJSObject.addProperty("description", event.getDescription());

            writer.write(eventAsJSObject.toString() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void refreshDB(Map<String,Event> events) {
            try (FileWriter writer = new FileWriter("resources/info.json",false)) {
                for (Event event : events.values()) {

                        JsonObject eventAsJSObject = new JsonObject();

                        eventAsJSObject.addProperty("type", event.getType());
                        eventAsJSObject.addProperty("date", event.getDate());
                        eventAsJSObject.addProperty("marker", event.getMarker());
                        eventAsJSObject.addProperty("description", event.getDescription());

                        writer.write(eventAsJSObject.toString() + "\r\n");
                    }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    public Map<String, Event> readEvents() {
        Map<String, Event> events = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/info.json"))) {
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
            e.printStackTrace();
        }


        return events;
    }
}
