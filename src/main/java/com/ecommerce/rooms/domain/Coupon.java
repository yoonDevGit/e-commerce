package com.ecommerce.rooms.domain;

import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

/** 쿠폰
 * */

@Entity
@Getter
public class Coupon {

  @Id @GeneratedValue
  @Column(name = "coupon_id")
  private Long id;

  private String description;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "reservation_id")
  @JsonManagedReference
  private Reservation reservation;

}
