package com.shop.shopping.coupons_points.service;

import com.shop.shopping.coupons_points.dto.coupon.UserAddCouponRequest;
import org.springframework.transaction.annotation.Transactional;

public interface UserCouponService {

    /**
     * 添加優惠劵
     */
    @Transactional(rollbackFor = Exception.class)
    void addCoupon(Long userId, UserAddCouponRequest request);


}
