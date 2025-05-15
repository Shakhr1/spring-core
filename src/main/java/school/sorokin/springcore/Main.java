package school.sorokin.springcore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("school.sorokin.springcore");

        Task task = context.getBean(Task.class);
        System.out.println(task);
        System.out.println("------------------------------");

        TaskManager taskManager = context.getBean(TaskManager.class);
        taskManager.print();
        System.out.println("------------------------------");

        Task task2 = (Task) context.getBean("task");
        System.out.println(task2);
    }
}