package school.sorokin.springcore.first_module;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("school.sorokin.springcore");
        TaskManager taskManager = context.getBean(TaskManager.class);

//        taskManager.print();

        Task task = context.getBean("main-task", Task.class);
        System.out.println(task);

        context.close();
    }
}