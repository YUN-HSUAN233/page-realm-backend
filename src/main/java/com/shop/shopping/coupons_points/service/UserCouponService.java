package com.shop.shopping.coupons_points.service;

import com.shop.shopping.coupons_points.dto.coupon.UserAddCouponRequest;
import com.shop.shopping.coupons_points.dto.coupon.UserCouponResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCouponService {

    /**
     * 添加優惠劵
     */
    @Transactional(rollbackFor = Exception.class)
    void addCoupon(Long userId, UserAddCouponRequest request);

    /**
     * 查詢優惠劵紀錄
     */
    List<UserCouponResponse> getCoupon(Long userId);

}
