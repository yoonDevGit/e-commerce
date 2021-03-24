package com.ecommerce.rooms.api;

import com.ecommerce.rooms.dto.CouponDto;
import com.ecommerce.rooms.service.CouponService;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponApiController {

  private final CouponService couponService;

  @GetMapping("/coupon/{couponId}")
  public ResponseEntity<CouponDto> getCoupon(@PathVariable("couponId") @Valid Long couponId) {
    return new ResponseEntity<>(couponService.getCoupon(couponId), HttpStatus.OK);
  }

  @GetMapping("/coupons-page")
  @ResponseStatus
  public Page<CouponDto> getPageAll(
      @PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return couponService.getPageAll(pageable);
  }

  @GetMapping("/coupons-slice")
  public Slice<CouponDto> getSliceAll(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return couponService.getSliceAll(pageable);
  }

  @PostMapping("/coupon")
  public ResponseEntity<Void> createCoupon(@Valid CouponDto couponDto) {
    try {
      couponService.createCoupon(couponDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/coupon/{couponId}")
  public ResponseEntity<Void> deleteCoupon(@PathVariable("couponId") @Valid Long couponId) {
    try {
      couponService.deleteCoupon(couponId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
