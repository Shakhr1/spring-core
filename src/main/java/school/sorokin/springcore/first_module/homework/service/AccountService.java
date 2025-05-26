package school.sorokin.springcore.first_module.homework.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import school.sorokin.springcore.first_module.homework.AccountProperties;
import school.sorokin.springcore.first_module.homework.TransactionHelper;
import school.sorokin.springcore.first_module.homework.model.Account;
import school.sorokin.springcore.first_module.homework.model.User;

import java.util.*;

@Service
public class AccountService {
    private final TransactionHelper transactionHelper;
    private final AccountProperties accountProperties;
    private final SessionFactory sessionFactory;

    public AccountService(AccountProperties accountProperties, TransactionHelper transactionHelper,
                          SessionFactory sessionFactory) {
        this.accountProperties = accountProperties;
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Account createAccount(User user) {
        return transactionHelper.executeInTransaction(() -> {
            Account account = new Account(user, accountProperties.defaultAmount());
            sessionFactory.getCurrentSession().persist(account);
            return account;
        });
    }

    private Optional<Account> findAccountById(int id) {
        var account = sessionFactory.getCurrentSession()
                .get(Account.class, id);
        return Optional.of(account);
    }

    public void depositAccount(int accountId, int moneyToDeposit) {
        transactionHelper.executeInTransaction(() -> {
            var account = findAccountById(accountId)
                    .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s" .formatted(accountId)));
            if (moneyToDeposit <= 0) {
                throw new IllegalArgumentException("Cannot deposit not positive amount: amount=%s"
                        .formatted(moneyToDeposit));
            }
            account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
            return 0;
        });
    }

    public void withdrawFromAccount(int accountId, int amountToWithdraw) {
        transactionHelper.executeInTransaction(() -> {
            var account = findAccountById(accountId)
                    .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s" .formatted(accountId)));

            if (amountToWithdraw <= 0) {
                throw new IllegalArgumentException("Cannot withdraw not positive amount: amount=%s"
                        .formatted(amountToWithdraw));
            }
            if (account.getMoneyAmount() < amountToWithdraw) {
                throw new IllegalArgumentException(
                        "Cannot withdraw from account: id=%s, moneyAmount=%s, attemptedWithdraw=%s"
                                .formatted(accountId, account.getMoneyAmount(), amountToWithdraw)
                );
            }

            account.setMoneyAmount(account.getMoneyAmount() - amountToWithdraw);
            return 0;
        });
    }

    public Account closeAccount(int accountId) {
        return transactionHelper.executeInTransaction(() -> {
            var accountToRemove = findAccountById(accountId)
                    .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s" .formatted(accountId)));

            List<Account> accountList = accountToRemove.getUser().getAccountList();
            if (accountList.size() == 1) {
                throw new IllegalArgumentException("Cannot close the only one account");
            }
            Account accountToDeposit = accountList.stream()
                    .filter(it -> it.getId() != accountId)
                    .findFirst()
                    .orElseThrow();

            accountToDeposit.setMoneyAmount(accountToDeposit.getMoneyAmount() + accountToRemove.getMoneyAmount());

            sessionFactory.getCurrentSession().remove(accountToRemove);
            return accountToRemove;
        });
    }

    public void transfer(int fromAccountId, int toAccountId, int amountToTransfer) {
        if (amountToTransfer <= 0) {
            throw new IllegalArgumentException("Cannot transfer not positive amount");
        }

        transactionHelper.executeInTransaction(() -> {
            var accountFrom = findAccountById(fromAccountId)
                    .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s" .formatted(fromAccountId)));
            var accountTo = findAccountById(toAccountId)
                    .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s" .formatted(toAccountId)));
            if (accountFrom.getMoneyAmount() < amountToTransfer) {
                throw new IllegalArgumentException(
                        "Cannot transfer from account: id=%s, moneyAmount=%s, attemptedTransfer=%s"
                                .formatted(accountFrom, accountFrom.getMoneyAmount(), amountToTransfer)
                );
            }

            int totalAmountToDeposit = accountTo.getId() != accountFrom.getId()
                    ? (int) (amountToTransfer * (1 - accountProperties.transferCommission()))
                    : amountToTransfer;

            accountFrom.setMoneyAmount(accountFrom.getMoneyAmount() - amountToTransfer);
            accountTo.setMoneyAmount(accountTo.getMoneyAmount() + totalAmountToDeposit);
            return 0;
        });
    }
}