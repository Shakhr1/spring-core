package school.sorokin.springcore.first_module.homework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.first_module.homework.operations.ConsoleOperationType;
import school.sorokin.springcore.first_module.homework.operations.OperationCommandProcessor;
import school.sorokin.springcore.first_module.homework.service.AccountService;

import java.util.Scanner;

@Component
public class DepositAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public DepositAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to deposit:");
        int amountToDeposit = Integer.parseInt(scanner.nextLine());
        accountService.depositAccount(accountId, amountToDeposit);
        System.out.printf("Successfully deposited amount=%s to accountId=%s%n", amountToDeposit, accountId);
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}
