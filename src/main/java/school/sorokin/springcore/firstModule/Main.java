package school.sorokin.springcore.firstModule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("school.sorokin.springcore");
        TaskManager taskManager = context.getBean(TaskManager.class);

        taskManager.print();

        context.close();
    }
}