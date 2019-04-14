package src.Tasks;

import org.osbot.rs07.script.Script;

public abstract class Task {
    protected Script api;

    public Task(Script api){
        this.api = api;
    }

    public abstract boolean canRun();

    public abstract int run();

    public abstract String status();

}
