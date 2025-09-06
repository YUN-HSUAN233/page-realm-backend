package com.shop.shopping.shoppingcart.repository;

import com.shop.shopping.order.dto.response.UserOrderResponse;
import com.shop.shopping.shoppingcart.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByOrderNo(String orderNo);

    @Query("SELECT new com.shop.shopping.order.dto.response.UserOrderResponse(o.orderNo, o.paidAt, o.status, o.payableAmount, o.paymentType) " +
            "FROM Orders o WHERE o.userId = :userId ORDER BY o.createdAt DESC")
    Page<UserOrderResponse> findAllByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);

}
