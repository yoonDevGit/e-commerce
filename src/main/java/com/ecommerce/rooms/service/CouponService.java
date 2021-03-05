package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Coupon;
import com.ecommerce.rooms.dto.CouponDto;
import com.ecommerce.rooms.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

  private final CouponRepository couponRepository;

  public CouponDto getCoupon(Long couponId) {
    Coupon coupon = couponRepository.findById(couponId).orElseThrow();
    return new CouponDto(coupon);
  }

  public void createCoupon(CouponDto couponDto) {
    Coupon coupon = new Coupon(couponDto);
    couponRepository.save(coupon);
  }

  public void deleteCoupon(Long couponId) {
    Coupon coupon = couponRepository.findById(couponId).orElseThrow();
    couponRepository.delete(coupon);
  }
}
