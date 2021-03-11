package com.ecommerce.rooms.controller;

import com.ecommerce.rooms.dto.CouponDto;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.service.CouponService;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

  private final CouponService couponService;

  @GetMapping("/{couponId}")
  public ResponseEntity<CouponDto> getCoupon(@PathVariable("couponId") @Valid Long couponId) {
    return new ResponseEntity<>(couponService.getCoupon(couponId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createCoupon(@Valid CouponDto couponDto) {
    try {
      couponService.createCoupon(couponDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{couponId}")
  public ResponseEntity<Void> deleteCoupon(@PathVariable("couponId") @Valid Long couponId) {
    try {
      couponService.deleteCoupon(couponId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
