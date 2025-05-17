package school.sorokin.springcore;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("main-task")
//@Scope("prototype")
public class Task {
    private final String name;
    private final int taskId;

    public Task (@Value("${task.name}") String name,
                 @Value("${task.taskId}") int taskID) {
        this.name = name;
        this.taskId = taskID;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Post-construct task: " + name);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Pre-destroy task: " + name);
    }

    @Override
    public String toString() {
        return "Task's name is -> " + name + ", task ID -> " + taskId;
    }
}
