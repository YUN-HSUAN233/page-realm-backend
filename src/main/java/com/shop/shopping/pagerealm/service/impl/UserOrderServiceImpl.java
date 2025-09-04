package com.shop.shopping.pagerealm.service.impl;

import com.shop.shopping.pagerealm.dto.response.UserOrderResponse;
import com.shop.shopping.pagerealm.service.UserOrderService;
import com.shop.shopping.shoppingcart.entity.Orders;
import com.shop.shopping.shoppingcart.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    private OrdersRepository ordersRepository;

    public UserOrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /**
     * 取得該用戶歷史訂單
     * @param userId
     * @return
     */
    @Override
    public List<UserOrderResponse> getOrder(Long userId) {
        List<Orders> orders = ordersRepository.findAllByUserId(userId);

        return orders.stream()
                .map(order -> new UserOrderResponse(
                        order.getOrderNo(),
                        order.getPaidAt(),
                        order.getStatus(),
                        order.getPayableAmount(),
                        order.getPaymentType()
                ))
                .toList();
    }
}
