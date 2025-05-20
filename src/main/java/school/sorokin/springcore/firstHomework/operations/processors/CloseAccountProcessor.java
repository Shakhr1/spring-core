package school.sorokin.springcore.firstHomework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.firstHomework.operations.*;
import school.sorokin.springcore.firstHomework.service.*;
import school.sorokin.springcore.firstHomework.model.*;

import java.util.Scanner;

@Component
public class CloseAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;
    private final UserService userService;

    public CloseAccountProcessor(
            Scanner scanner,
            AccountService accountService,
            UserService userService
    ) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id to close:");
        int accountId = Integer.parseInt(scanner.nextLine());
        Account account = accountService.closeAccount(accountId);

        User user = userService.findUserById(account.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("No such user with id=%s"
                        .formatted(account.getUserId())));
        user.accountList().remove(account);

        System.out.printf("Account successfully closed with id=%s%n", accountId);
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
