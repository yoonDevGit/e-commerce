package com.ecommerce.rooms.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.common.Constant.ReservationStatus;
import com.ecommerce.rooms.domain.base.BaseEntity;
import com.ecommerce.rooms.domain.room.Room;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 예약
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reservation extends BaseEntity {

  @Id @GeneratedValue
  @Column(name = "reservation_id")
  private Long id;

  @Enumerated(value = STRING)
  private ReservationStatus reservationStatus;

  private String description;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  @JsonManagedReference
  private User user;

  @OneToOne(fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "room_id")
  @JsonManagedReference
  private Room room;


  @OneToMany(mappedBy = "reservation", cascade = ALL)
  @JsonBackReference
  private List<Coupon> coupons = new ArrayList<>();


  public Reservation(User user, Room room, List<Coupon> coupons) {
    this.user = user;
    this.room = room;
    this.coupons = coupons;
    this.reservationStatus = ReservationStatus.COMPLETE;
  }
}
