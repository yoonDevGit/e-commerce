package com.ecommerce.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.rooms.domain.Coupon;
import com.ecommerce.rooms.dto.CouponDto;
import com.ecommerce.rooms.repository.CouponRepository;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootTest
@Transactional
class CouponServiceTest {

  @Autowired
  CouponRepository couponRepository;

//  @Test
//  public void getCoupon() throws Exception {
//    //GIVEN
//    CouponDto couponDto = new CouponDto("coupon", 10000, "숙박 예약 시 즉시 사용가능");
//    Coupon coupon = new Coupon(couponDto);
//
//    couponRepository.save(coupon);
//
//    long couponId = 1;
//
//    //WHEN
//    Coupon findCoupon = couponRepository.findById(couponId).orElseThrow();
//
//    //THEN
//    assertThat(findCoupon.getDiscountAmount()).isEqualTo(10000);
//  }

  @Test
  public void deleteCoupon() throws Exception {
    //GIVEN
    CouponDto couponDto = new CouponDto("coupon", 10000, "숙박 예약 시 즉시 사용가능");
    Coupon coupon = new Coupon(couponDto);
    couponRepository.save(coupon);

    long couponId = 1;

    //WHEN
    couponRepository.delete(coupon);

    //THEN
    assertThrows(NoSuchElementException.class,
        () -> couponRepository.findById(couponId).orElseThrow());
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getCountUsers() {
    // GIVEN
    for (int i = 0; i < 100; i++) {
      CouponDto couponDto = new CouponDto("coupon" + i, 10000, "숙박 예약 시 즉시 사용가능");
      Coupon coupon = new Coupon(couponDto);
      couponRepository.save(coupon);
    }
    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

    // WHEN
    Page<Coupon> coupons = couponRepository.findAll(pageRequest);

    Page<CouponDto> couponDtos = coupons.map(CouponDto::new);

    // THEN
    assertThat(couponDtos.getTotalPages()).isEqualTo(5);
    assertThat(couponDtos.getTotalElements()).isEqualTo(100);
    assertThat(couponDtos.getNumberOfElements()).isEqualTo(20);
    assertThat(couponDtos.isFirst()).isTrue();
    assertThat(couponDtos.hasNext()).isTrue();
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getSliceUsers() {
    // GIVEN
    for (int i = 0; i < 100; i++) {
      CouponDto couponDto = new CouponDto("coupon" + i, 10000, "숙박 예약 시 즉시 사용가능");
      Coupon coupon = new Coupon(couponDto);
      couponRepository.save(coupon);
    }
    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

    // WHEN
    Slice<Coupon> coupons = couponRepository.findAll(pageRequest);

    Slice<CouponDto> couponDtos = coupons.map(CouponDto::new);

    // THEN
    assertThat(couponDtos.getNumber()).isEqualTo(0);
    assertThat(couponDtos.getNumberOfElements()).isEqualTo(20);
    assertThat(couponDtos.isFirst()).isTrue();
    assertThat(couponDtos.hasNext()).isTrue();
  }

}