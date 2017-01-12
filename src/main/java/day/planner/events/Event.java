package day.planner.events;

import day.planner.data.DataValidator;


public class Event {

    private String type;
    private String date;
    private String marker;
    private String description;

    public Event(String type, String date, String marker, String description) throws InvalidEventType, InvalidEventMarker {
        this.setType(type);
        this.setDate(date);
        this.setMarker(marker);
        this.setDescription(description);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws InvalidEventType {
        if (DataValidator.eventTypeValidate(type)) {
            this.type = type;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        if (DataValidator.validateDate(date)) {
            this.date = date;
        }

    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) throws InvalidEventMarker {
        if (DataValidator.eventMarkerValidate(marker)) {
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
        return String.format("|Date: %s | Type: %s | Marker: %s | Description: %s |",
                this.getDate()
                , this.getType()
                , this.getMarker()
                , this.getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }

        Event event = (Event) obj;
        return this.getType().equals(((Event) obj).getType()) && this.getMarker().equals(((Event) obj).getMarker())
                && this.getDate().equals(((Event) obj).getDate()) && this.getDescription().equals(((Event) obj)
                .getDescription());
    }
}
