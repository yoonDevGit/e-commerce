package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Coupon;
import com.ecommerce.rooms.dto.CouponDto;
import com.ecommerce.rooms.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {

  private final CouponRepository couponRepository;

  public CouponDto getCoupon(Long couponId) {
    Coupon coupon = couponRepository.findById(couponId).orElseThrow();
    return new CouponDto(coupon);
  }

  @Transactional
  public void createCoupon(CouponDto couponDto) {
    Coupon coupon = new Coupon(couponDto);
    couponRepository.save(coupon);
  }

  @Transactional
  public void deleteCoupon(Long couponId) {
    Coupon coupon = couponRepository.findById(couponId).orElseThrow();
    couponRepository.delete(coupon);
  }

  public Page<CouponDto> getPageAll(Pageable pageable) {
    Page<Coupon> users = couponRepository.findAll(pageable);
    return users.map(CouponDto::new);
  }

  public Slice<CouponDto> getSliceAll(Pageable pageable) {
    Slice<Coupon> users = couponRepository.findAll(pageable);
    return users.map(CouponDto::new);
  }
}
