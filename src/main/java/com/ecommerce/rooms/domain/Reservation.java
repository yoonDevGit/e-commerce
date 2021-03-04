package com.ecommerce.rooms.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.common.Constant.ReservationStatus;
import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import com.ecommerce.rooms.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;

/** 예약
 * */

@Entity
@Getter
public class Reservation extends BaseEntity {

  @Id @GeneratedValue
  @Column(name = "reservation_id")
  private Long id;

  private String checkIn;

  private String checkOut;

  @Enumerated(value = STRING)
  private ReservationStatus reservationStatus;

  private String description;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  @JsonManagedReference
  private User user;

  @OneToMany(mappedBy = "reservation", cascade = ALL)
  @JsonManagedReference
  private List<Accommodation> accommodation;


  @OneToMany(mappedBy = "reservation", cascade = ALL)
  @JsonBackReference
  private List<Coupon> coupons = new ArrayList<>();

  @OneToMany(mappedBy = "reservation", cascade = ALL)
  @JsonBackReference
  private List<Point> points = new ArrayList<>();
}
