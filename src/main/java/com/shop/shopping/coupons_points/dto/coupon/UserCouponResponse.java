package com.shop.shopping.coupons_points.dto.coupon;

import com.shop.shopping.coupons_points.entity.Coupon;
import com.shop.shopping.coupons_points.entity.CouponRedemption;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class UserCouponResponse {
    private LocalDate startsAt;
    private LocalDate endsAt;
    private String name;
    private String discountType;
    private String status;

    public UserCouponResponse(LocalDateTime startsAt,
                              LocalDateTime endsAt,
                              String name,
                              Coupon.DiscountType discountType,
                              CouponRedemption.RedemptionStatus status) {
        this.startsAt = startsAt.toLocalDate();
        this.endsAt = endsAt.toLocalDate();
        this.name = name;
        this.discountType = discountType.toString();
        this.status = status.toString();
    }

}
