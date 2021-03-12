package com.ecommerce.rooms.domain;

import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.dto.CouponDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 쿠폰
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coupon {

  @Id @GeneratedValue
  @Column(name = "coupon_id")
  private Long id;

  private String name;

  // 할인 금액
  private int discountAmount;

  private String description;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "reservation_id")
  @JsonManagedReference
  private Reservation reservation;

  public Coupon(String name, int discountAmount, String description) {
    this.name = name;
    this.discountAmount = discountAmount;
    this.description = description;
  }

  public Coupon(CouponDto couponDto) {
    this.name = couponDto.getName();
    this.discountAmount = couponDto.getDiscountAmount();
    this.description = couponDto.getDescription();
  }

}
