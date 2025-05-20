package school.sorokin.springcore.firstHomework.model;

import java.util.List;

public record User(int id, String login, List<Account> accountList) {
}
