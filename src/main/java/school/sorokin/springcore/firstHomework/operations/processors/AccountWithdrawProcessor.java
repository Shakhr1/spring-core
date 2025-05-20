package school.sorokin.springcore.firstHomework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.firstHomework.operations.ConsoleOperationType;
import school.sorokin.springcore.firstHomework.operations.OperationCommandProcessor;
import school.sorokin.springcore.firstHomework.service.AccountService;

import java.util.Scanner;

@Component
public class AccountWithdrawProcessor  implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public AccountWithdrawProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to withdraw:");
        int amountToWithdraw = Integer.parseInt(scanner.nextLine());
        accountService.withdrawFromAccount(accountId, amountToWithdraw);
        System.out.printf("Successfully withdrawn amount=%s to accountId=%s%n", amountToWithdraw, accountId);
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}
