package school.sorokin.springcore.first_module.homework.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import school.sorokin.springcore.first_module.homework.TransactionHelper;
import school.sorokin.springcore.first_module.homework.model.User;

import java.util.*;

@Service
public class UserService {
    private final TransactionHelper transactionHelper;
    private final SessionFactory sessionFactory;
    private final AccountService accountService;

    public UserService(AccountService accountService,TransactionHelper transactionHelper,
                       SessionFactory sessionFactory) {
        this.accountService = accountService;
        this.transactionHelper = transactionHelper;
        this.sessionFactory = sessionFactory;
    }

    public User createUser(String login) {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("Login is wrong.");

        return transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            User userExist = session
                    .createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResultOrNull();

            if (userExist != null) {
                throw new IllegalArgumentException("User with login " + login + " already exists");
            }
            User user = new User(login, new ArrayList<>());

            session.persist(user);
            accountService.createAccount(user);
            return user;
        });
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(transactionHelper.executeInTransaction(
                () -> sessionFactory.getCurrentSession().get(User.class, id)));
    }

    public List<User> getAllUsers() {
        return transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            return session
                    .createQuery("""
                                SELECT s FROM User s
                                LEFT JOIN FETCH s.accountList
                            """, User.class)
                    .list();
        });
    }
}
