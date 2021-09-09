package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
