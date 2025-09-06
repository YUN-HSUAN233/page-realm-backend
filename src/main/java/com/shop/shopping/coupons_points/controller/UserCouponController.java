package com.shop.shopping.coupons_points.controller;

import com.shop.shopping.coupons_points.dto.coupon.UserAddCouponRequest;
import com.shop.shopping.coupons_points.dto.coupon.UserCouponResponse;
import com.shop.shopping.coupons_points.repository.CouponRedemptionRepository;
import com.shop.shopping.coupons_points.service.UserCouponService;
import com.shop.shopping.pagerealm.security.service.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/coupon")
public class UserCouponController {
    private UserCouponService userCouponService;
    private CouponRedemptionRepository couponRedemptionRepository;

    UserCouponController(UserCouponService userCouponService, CouponRedemptionRepository couponRedemptionRepository) {
        this.couponRedemptionRepository = couponRedemptionRepository;
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
    public ResponseEntity<Page<UserCouponResponse>> getCoupon(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                              @RequestParam int page,
                                                              @RequestParam(defaultValue = "5") int size
    )
    {
        Page<UserCouponResponse> responses = couponRedemptionRepository.findAllDTOByUserId(userDetails.getId(), PageRequest.of(page, size));
        return ResponseEntity.ok(responses);
    }
}
