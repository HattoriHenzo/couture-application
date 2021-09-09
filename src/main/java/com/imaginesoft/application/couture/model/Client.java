package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericPerson;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "CLIENT")
public class Client extends GenericPerson {

    @OneToMany(mappedBy = "client")
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
}
