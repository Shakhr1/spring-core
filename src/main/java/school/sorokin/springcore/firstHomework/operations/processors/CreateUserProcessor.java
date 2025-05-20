package school.sorokin.springcore.firstHomework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.firstHomework.model.User;
import school.sorokin.springcore.firstHomework.operations.ConsoleOperationType;
import school.sorokin.springcore.firstHomework.operations.OperationCommandProcessor;
import school.sorokin.springcore.firstHomework.service.UserService;

import java.util.Scanner;

@Component
public class CreateUserProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final UserService userService;

    public CreateUserProcessor(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter login for new user:");
        String login = scanner.nextLine();
        User user = userService.createUser(login);
        System.out.println("User created: " + user.toString());
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
