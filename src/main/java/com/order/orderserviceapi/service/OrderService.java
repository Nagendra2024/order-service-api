package com.order.orderserviceapi.service;

import com.order.orderserviceapi.entity.Orders;
import com.order.orderserviceapi.repository.OrderRepository;
import com.order.orderserviceapi.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Orders createOrder(Orders order) {
        // Fetch product details from Product Service
        String productServiceUrl = "http://localhost:8092/api/products/" + order.getProductId();
        ProductVO product = restTemplate.getForObject(productServiceUrl, ProductVO.class);

        // Check if product is available and has sufficient quantity
        if (product == null || product.getQuantity() < order.getQuantity()) {
            throw new IllegalArgumentException("Insufficient product quantity or product not found");
        }

        // Calculate total price and save order
        order.setTotalPrice(product.getPrice() * order.getQuantity());
        return orderRepository.save(order);
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrdersId(Long id) {
        return  orderRepository.findById(id);
    }
}
