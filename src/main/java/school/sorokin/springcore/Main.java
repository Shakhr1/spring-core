package school.sorokin.springcore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ObjectInputFilter;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("school.sorokin.springcore");
        Task task = context.getBean(Task.class);
        System.out.println(task);
    }
}