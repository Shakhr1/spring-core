package school.sorokin.springcore.firstHomework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.firstHomework.model.User;
import school.sorokin.springcore.firstHomework.operations.ConsoleOperationType;
import school.sorokin.springcore.firstHomework.operations.OperationCommandProcessor;
import school.sorokin.springcore.firstHomework.service.UserService;

import java.util.List;

@Component
public class ShowAllUsersProcessor implements OperationCommandProcessor {

    private final UserService userService;

    public ShowAllUsersProcessor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        List<User> users = userService.getAllUsers();
        System.out.println("List of all users:");
        users.forEach(System.out::println);
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}
