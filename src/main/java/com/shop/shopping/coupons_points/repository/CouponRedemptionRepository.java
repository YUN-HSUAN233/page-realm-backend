package com.shop.shopping.coupons_points.repository;

import com.shop.shopping.coupons_points.dto.coupon.UserCouponResponse;
import com.shop.shopping.coupons_points.entity.CouponRedemption;
import com.shop.shopping.coupons_points.entity.CouponRedemption.RedemptionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponRedemptionRepository extends JpaRepository<CouponRedemption, Long> {
    long countByCouponIdAndStatus(Long couponId, RedemptionStatus status);
    long countByCouponIdAndUserIdAndStatus(Long couponId, Long userId, RedemptionStatus status);

    Optional<CouponRedemption> findByUserIdAndStatus(Long userId, RedemptionStatus status);
    List<CouponRedemption> findAllByUserIdOrderByRedeemedAtDesc(Long userId);

    List<CouponRedemption> findAllByUserIdAndCouponId(Long userId, Long couponId);

    boolean existsByOrderIdAndCouponId(Long orderId, Long couponId);
    Page<CouponRedemption> findAllByUserIdOrderByRedeemedAtDesc(Long userId, Pageable pageable);

    @Query("SELECT new com.shop.shopping.coupons_points.dto.coupon.UserCouponResponse( c.startsAt, c.endsAt, c.name, c.discountType, cr.status) " +
            "FROM CouponRedemption cr " +
            "JOIN cr.coupon c " +
            "WHERE cr.userId = :userId ORDER BY cr.redeemedAt DESC")
    Page<UserCouponResponse> findAllDTOByUserId(@Param("userId") Long userId, Pageable pageable);
}

