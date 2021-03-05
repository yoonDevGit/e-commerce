package com.ecommerce.rooms.dto;

import com.ecommerce.rooms.domain.Coupon;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponDto {

  // 할인 금액
  private int discountAmount;

  private String description;

  public CouponDto(Coupon coupon) {
    this.discountAmount = coupon.getDiscountAmount();
    this.description = coupon.getDescription();
  }
}
