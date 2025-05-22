package school.sorokin.springcore.first_module.homework.model;

import java.util.List;

public record User(int id, String login, List<Account> accountList) {
}
