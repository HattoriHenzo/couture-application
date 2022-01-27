package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericPerson;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User extends GenericPerson {

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private UserCategory userCategory;

    public User() {
        super();
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "userCategory = " + getUserCategory() + ", " +
                "firstName = " + getFirstName() + ", " +
                "lastName = " + getLastName() + ", " +
                "telephone = " + getTelephone() + ", " +
                "gender = " + getGender() + ")";
    }
}
