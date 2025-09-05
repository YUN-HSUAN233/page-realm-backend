package com.shop.shopping.coupons_points.controller;

import com.shop.shopping.coupons_points.dto.points.UserPointsResponse;
import com.shop.shopping.coupons_points.service.UserPointsService;
import com.shop.shopping.pagerealm.security.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/points")
public class UserPointsController {
    private UserPointsService userPointsService;

    public UserPointsController(UserPointsService userPointsService) {
        this.userPointsService = userPointsService;
    }

    /**
     * 查詢點數紀錄
     */
    @GetMapping("/view")
    public ResponseEntity<List<UserPointsResponse>> getCoupon(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<UserPointsResponse> responses = userPointsService.getPoints(userDetails.getId());
        return ResponseEntity.ok(responses);
    }
}
