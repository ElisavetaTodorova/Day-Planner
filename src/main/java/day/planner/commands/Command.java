package day.planner.commands;

import day.planner.data.DataStorage;
import day.planner.engine.ProcessManager;
import day.planner.events.EventDoesNotExist;

import java.io.IOException;

public abstract class Command {

    private DataStorage storage;
    private ProcessManager manager;

    protected Command(DataStorage storage, ProcessManager manager) {
        this.storage = storage;
        this.manager = manager;
    }


    public abstract void execute() throws IOException, EventDoesNotExist;

    protected DataStorage getStorage() {
        return storage;
    }

    protected ProcessManager getManager() {
        return manager;
    }
}
