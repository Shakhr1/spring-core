package school.sorokin.springcore;

import org.springframework.stereotype.Component;

@Component
public class Task {
    private final String name;
    private final int taskId;

    public Task () {
        this.name = "Task";
        this.taskId = 1;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Task's name is -> " + name + ", task ID -> " + taskId;
    }
}
