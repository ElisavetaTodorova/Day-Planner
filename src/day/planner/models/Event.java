package day.planner.models;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class Event {

    private String type;
    private String date;
    private String marker;
    private String description;

    public Event(String type, String date, String marker, String description) {
       this.setType(type);
       this.setDate(date);
       this.setMarker(marker);
       this.setDescription(description);
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public String getMarker() {
        return marker;
    }

    private void setMarker(String marker) {
        this.marker = marker;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }
}
