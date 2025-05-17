package school.sorokin.springcore.firstModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {
    private final Task task;

    @Autowired
    public TaskManager(Task task) {
        this.task = task;
    }

    public void print() {
        System.out.println("Current task: " + task);
    }
}
