package school.sorokin.springcore.firstHomework.operations.processors;

import org.springframework.stereotype.Component;
import school.sorokin.springcore.firstHomework.operations.ConsoleOperationType;
import school.sorokin.springcore.firstHomework.operations.OperationCommandProcessor;
import school.sorokin.springcore.firstHomework.service.AccountService;

import java.util.Scanner;

@Component
public class AccountTransferProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountTransferProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter source account id:");
        int fromAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter destination account id:");
        int toAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to transfer:");
        int amountToTransfer = Integer.parseInt(scanner.nextLine());
        accountService.transfer(fromAccountId, toAccountId, amountToTransfer);
        System.out.printf("Successfully transferred %s from accountId %s to accountId %s%n", amountToTransfer, fromAccountId, toAccountId);
    }

    @Override
    public ConsoleOperationType getConsoleOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
