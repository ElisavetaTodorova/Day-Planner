package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.events.EventDoesNotExist;
import day.planner.events.EventSelector;

import java.io.IOException;

public abstract class Command {

    private DataStorage storage;
    private EventSelector eventSelector;

    protected Command(DataStorage storage, EventSelector eventSelector) {
        this.storage = storage;
        this.eventSelector = eventSelector;
    }


    public abstract void execute() throws IOException, EventDoesNotExist;

    protected DataStorage getStorage() {
        return storage;
    }

    protected EventSelector getEventSelector() {
        return eventSelector;
    }
}
