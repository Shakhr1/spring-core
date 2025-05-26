package school.sorokin.springcore.first_module.homework.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sorokin.springcore.first_module.homework.model.Account;
import school.sorokin.springcore.first_module.homework.model.User;

@Configuration
public class HibernateConfiguration {
    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(User.class)
                .addPackage("school.sorokin.springcore.first_module.homework")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.current_session_context_class", "thread");
        return configuration.buildSessionFactory();
    }
}
