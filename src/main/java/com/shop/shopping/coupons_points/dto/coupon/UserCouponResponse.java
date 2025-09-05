package com.shop.shopping.coupons_points.dto.coupon;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserCouponResponse {
    private LocalDate start;
    private LocalDate end;
    private String name;
    private String discountType;
    private String status;

}
