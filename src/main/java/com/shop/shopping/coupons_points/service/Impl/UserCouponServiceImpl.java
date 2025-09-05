package com.shop.shopping.coupons_points.service.Impl;

import com.shop.shopping.coupons_points.dto.coupon.UserAddCouponRequest;
import com.shop.shopping.coupons_points.dto.coupon.UserCouponResponse;
import com.shop.shopping.coupons_points.entity.Coupon;
import com.shop.shopping.coupons_points.entity.CouponRedemption;
import com.shop.shopping.coupons_points.repository.CouponRedemptionRepository;
import com.shop.shopping.coupons_points.repository.CouponRepository;
import com.shop.shopping.coupons_points.service.UserCouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCouponServiceImpl implements UserCouponService {
    private CouponRepository couponRepository;
    private CouponRedemptionRepository couponRedemptionRepository;

    public UserCouponServiceImpl(CouponRepository couponRepository,CouponRedemptionRepository couponRedemptionRepository) {
        this.couponRepository = couponRepository;
        this.couponRedemptionRepository = couponRedemptionRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addCoupon(Long userId, UserAddCouponRequest request) {
        String couponCode = request.getCouponCode();
        Optional<Coupon> couponpt = couponRepository.findByGenericCodeIgnoreCase(couponCode);
        couponpt.ifPresent(coupon -> {
            CouponRedemption couponRedemption = new CouponRedemption();
            couponRedemption.setCouponId(coupon.getId());
            couponRedemption.setUserId(userId);
            couponRedemption.setAmountDiscounted(0);
            couponRedemption.setStatus(CouponRedemption.RedemptionStatus.HOLD);
            couponRedemptionRepository.save(couponRedemption);
        });
    }

    @Override
    public List<UserCouponResponse> getCoupon(Long userId) {
        List<CouponRedemption> couponRedemption = couponRedemptionRepository.findAllByUserIdOrderByRedeemedAtDesc(userId);
        List<Long> couponIds = couponRedemption.stream()
                .map(CouponRedemption::getCouponId)
                .toList();

        List<UserCouponResponse> response = new ArrayList<>();
        for (Long couponId : couponIds) {
            Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
            if (optionalCoupon.isEmpty()) {
                continue;
            }
            Coupon coupon = optionalCoupon.get();
            List<CouponRedemption> redemptions =
                    couponRedemptionRepository.findAllByUserIdAndCouponId(userId, couponId);

            for (CouponRedemption redemption : redemptions) {
                UserCouponResponse responseDto = new UserCouponResponse();
                responseDto.setStart(coupon.getCreatedAt().toLocalDate());
                responseDto.setEnd(coupon.getEndsAt().toLocalDate());
                responseDto.setName(coupon.getName());
                responseDto.setDiscountType(coupon.getDiscountType().toString());
                responseDto.setStatus(redemption.getStatus().toString());

                response.add(responseDto);
            }
        }
        return response;
    }
}
