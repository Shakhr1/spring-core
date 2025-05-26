package school.sorokin.springcore.first_module.homework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.first_module.homework.operations.ConsoleOperationType;
import school.sorokin.springcore.first_module.homework.operations.OperationCommandProcessor;
import school.sorokin.springcore.first_module.homework.service.AccountService;
import school.sorokin.springcore.first_module.homework.service.UserService;

import java.util.Scanner;

@Component
public class CreateAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public CreateAccountProcessor(
            Scanner scanner,
            UserService userService,
            AccountService accountService
    ) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter the user id for which to create an account:");
        int userId = Integer.parseInt(scanner.nextLine());
        var user = userService.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No such user with id=%s"
                        .formatted(userId)));
        var account = accountService.createAccount(user);
        user.getAccountList().add(account);

        System.out.printf("New account created with Id: %s for user: %s%n", account.getId(), user.getLogin());
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}
