package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericPerson;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENT")
public class Client extends GenericPerson {

    @OneToMany(mappedBy = "client",
               cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<Order> orders;

    public Client() {
        super();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getId() != null && Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "firstName = " + getFirstName() + ", " +
                "lastName = " + getLastName() + ", " +
                "telephone = " + getTelephone() + ", " +
                "gender = " + getGender() + ")";
    }
}
