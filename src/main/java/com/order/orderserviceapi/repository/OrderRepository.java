package com.order.orderserviceapi.repository;

import com.order.orderserviceapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
