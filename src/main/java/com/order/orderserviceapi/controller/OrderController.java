package com.order.orderserviceapi.controller;

import com.order.orderserviceapi.entity.Orders;
import com.order.orderserviceapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders(){
      return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/id")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
      return ResponseEntity.of(orderService.getOrdersId(id));
    }
}