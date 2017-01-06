package day.planner.models;

import day.planner.exceptions.InvalidEventType;

/**
 * Created by ELISAV on 4.1.2017 г..
 */
public class Event {

    private String type;
    private String date;
    private String marker;
    private String description;

    public Event(String type, String date, String marker, String description) throws InvalidEventType {
        this.setType(type);
        this.setDate(date);
        this.setMarker(marker);
        this.setDescription(description);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws InvalidEventType {
        if (type.equals("date") || type.equals("meeting")) {
            this.type = type;
        } else {
            throw new InvalidEventType("Type must be date or meeting");
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        if (DateValidator.validate(date)) {
            this.date = date;
        }

    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        if (marker.equals("private") || marker.equals("public") || marker.equals("confidential")) {
            this.marker = marker;
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("|Date: %s | Type: %-7s | Marker: %-13s | Description: %s |",
                this.getDate()
                , this.getType()
                , this.getMarker()
                , this.getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Event)){
            return false;
        }

        Event event = (Event)obj;
        return this.getType().equals(((Event) obj).getType()) && this.getMarker().equals(((Event) obj).getMarker())
                && this.getDate().equals(((Event) obj).getDate()) && this.getDescription().equals(((Event) obj).getDescription());
    }
}
