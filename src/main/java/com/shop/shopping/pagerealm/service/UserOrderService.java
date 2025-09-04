package com.shop.shopping.pagerealm.service;

import com.shop.shopping.pagerealm.dto.response.UserOrderResponse;

import java.util.List;

public interface UserOrderService {

    /**
     * 取得該用戶歷史訂單
     * @param userId
     * @return
     */
    List<UserOrderResponse> getOrder(Long userId);
}
