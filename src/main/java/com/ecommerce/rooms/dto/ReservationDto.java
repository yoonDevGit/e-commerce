package com.ecommerce.rooms.dto;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.common.Constant.ReservationStatus;
import com.ecommerce.rooms.domain.Coupon;
import com.ecommerce.rooms.domain.Reservation;
import com.ecommerce.rooms.domain.User;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 예약
 * */

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationDto {

  private ReservationStatus reservationStatus;

  private String description;

  private UserDto userDto;

  private RoomDto roomDto;

  public ReservationDto(Reservation reservation) {
    this.reservationStatus = reservation.getReservationStatus();
    this.description = reservation.getDescription();
    this.userDto = new UserDto(reservation.getUser());
    this.roomDto = new RoomDto(reservation.getRoom());
  }
}
