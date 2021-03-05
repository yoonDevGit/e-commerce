package com.ecommerce.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.rooms.domain.Coupon;
import com.ecommerce.rooms.dto.CouponDto;
import com.ecommerce.rooms.repository.CouponRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(false)
class CouponServiceTest {

  @Autowired
  CouponRepository couponRepository;

  @Test
  public void getCoupon() throws Exception {
    //GIVEN
    CouponDto couponDto = new CouponDto(10000, "숙박 예약 시 즉시 사용가능");
    Coupon coupon = new Coupon(couponDto);

    couponRepository.save(coupon);

    long couponId = 1;

    //WHEN
    Coupon findCoupon = couponRepository.findById(couponId).orElseThrow();

    //THEN
    assertThat(findCoupon.getDiscountAmount()).isEqualTo(10000);
  }

  @Test
  public void deleteCoupon() throws Exception {
    //GIVEN
    CouponDto couponDto = new CouponDto(10000, "숙박 예약 시 즉시 사용가능");
    Coupon coupon = new Coupon(couponDto);
    couponRepository.save(coupon);

    long couponId = 1;

    //WHEN
    couponRepository.delete(coupon);

    //THEN
    assertThrows(NoSuchElementException.class,
        () -> couponRepository.findById(couponId).orElseThrow());
  }

}