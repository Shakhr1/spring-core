package school.sorokin.springcore.first_module.homework.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(unique = true, nullable = false, length = 50)
        private String login;

        @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
        private List<Account> accountList;

        public User() {
        }

        public User(String login, List<Account> accountList) {
                this.login = login;
                this.accountList = accountList;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getLogin() {
                return login;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        public List<Account> getAccountList() {
                return accountList;
        }

        public void setAccountList(List<Account> accountList) {
                this.accountList = accountList;
        }

        @Override
        public String toString() {
                return "User{" +
                       "id=" + id +
                       ", login='" + login + '\'' +
                       ", accountList=" + accountList +
                       '}';
        }
}