package com.shop.shopping.pagerealm.controller;


import com.shop.shopping.pagerealm.dto.response.UserOrderResponse;
import com.shop.shopping.pagerealm.security.service.UserDetailsImpl;
import com.shop.shopping.pagerealm.service.UserOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class UserOrderController {
    private final UserOrderService userOrderService;

    public  UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    /**
     * 取得用戶歷史訂單
     * @param userDetails
     * @return
     */
    @GetMapping("/view")
    public ResponseEntity<List<UserOrderResponse>> getUserOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<UserOrderResponse> responses = userOrderService.getOrder(userDetails.getId());
        return ResponseEntity.ok(responses);
    }

}
