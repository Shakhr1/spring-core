package school.sorokin.springcore.first_module.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("school.sorokin.springcore.first_module.homework");
    }
}
