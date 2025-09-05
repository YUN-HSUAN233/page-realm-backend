package com.shop.shopping.coupons_points.controller;

import com.shop.shopping.coupons_points.dto.coupon.UserAddCouponRequest;
import com.shop.shopping.coupons_points.dto.coupon.UserCouponResponse;
import com.shop.shopping.coupons_points.service.UserCouponService;
import com.shop.shopping.pagerealm.security.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/coupon")
public class UserCouponController {
    private UserCouponService userCouponService;

    UserCouponController(UserCouponService userCouponService) {
        this.userCouponService = userCouponService;
    }

    /**
     * 添加優惠劵
     */
    @PostMapping("/add")
    public ResponseEntity<?> addCoupon(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody UserAddCouponRequest request) {

        userCouponService.addCoupon(userDetails.getId(), request);
        return ResponseEntity.ok(Map.of("status", "ok", "couponCode", request.getCouponCode()));
    }

    /**
     * 查詢優惠劵紀錄
     */
    @GetMapping("/view")
    public ResponseEntity<List<UserCouponResponse>> getCoupon(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<UserCouponResponse> responses = userCouponService.getCoupon(userDetails.getId());
        return ResponseEntity.ok(responses);
    }
}
