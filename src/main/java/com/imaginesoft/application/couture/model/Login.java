package com.imaginesoft.application.couture.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "LOGIN")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(mappedBy = "login")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOGIN_CATEGORY")
    private LoginCategory loginCategory;

    public Login() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LoginCategory getLoginCategory() {
        return loginCategory;
    }

    public void setLoginCategory(LoginCategory loginCategory) {
        this.loginCategory = loginCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Login user = (Login) o;

        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "employee = " + employee + ", " +
                "userCategory = " + loginCategory + ")";
    }
}
