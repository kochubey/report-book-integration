package logbook.dbl.system;

import javax.persistence.*;

//@JsonPropertyOrder({"id", "login", "lastName", "firstName", "middleName", "email"})

//  Пользователь журнала загрузок и отчетности

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_USER_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SYS_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"LOGIN"})})
public class User extends Base {
    //    Конструкторы
    public User() {
        this(null, null, null, null, null);
    }

    public User(String login) {
        this(login, null, null, null, null);
    }

    public User(String login, String lastName, String firstName) {
        this(login, lastName, firstName, null, null);
    }

    public User(String login, String lastName, String firstName, String middleName, String email) {
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
    }

    //    ID записи
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    Логин
    @Column(name = "LOGIN", unique = true, length = 64, nullable = false)
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    //    Фамилия
    @Column(name = "LAST_NAME", length = 64, nullable = false)
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //    Имя
    @Column(name = "FIRST_NAME", length = 64, nullable = false)
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //    Отчество
    @Column(name = "MIDDLE_NAME", length = 64, nullable = true)
    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    //    Email
    @Column(name = "EMAIL", length = 64, nullable = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getFio() {
        return String.valueOf(lastName).replace("null", "") +
                " " + String.valueOf(firstName).replace("null", "") +
                " " + String.valueOf(middleName).replace("null", "");
    }
}
