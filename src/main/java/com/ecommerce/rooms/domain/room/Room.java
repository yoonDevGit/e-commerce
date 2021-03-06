package com.ecommerce.rooms.domain.room;

import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.domain.accommodation.Accommodation;
import com.ecommerce.rooms.domain.Reservation;
import com.ecommerce.rooms.domain.base.BaseTimeEntity;
import com.ecommerce.rooms.dto.RoomDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 숙박업체 방
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Room extends BaseTimeEntity {

  @Id @GeneratedValue
  @Column(name = "room_id")
  private Long id;

  // 이름
  private String name;

  // 가격
  private int price;

  // 입실
  private String checkIn;

  // 퇴실
  private String checkOut;

  // 기본 정보
  private String basicInfo;

  // 편의 시설
  private String facility;

  // 환불 규정
  private String refundPolicy;

  @OneToOne(mappedBy = "room",fetch = LAZY, cascade = CascadeType.ALL)
  @JsonBackReference
  private Reservation reservation;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "accommodation_id")
  @JsonManagedReference
  private Accommodation accommodation;

  public Room(RoomDto roomDto) {
    this.name = roomDto.getName();
    this.price = roomDto.getPrice();
    this.checkIn = roomDto.getCheckIn();
    this.checkOut = roomDto.getCheckOut();
    this.basicInfo = roomDto.getBasicInfo();
    this.facility = roomDto.getFacility();
    this.refundPolicy = roomDto.getRefundPolicy();
  }

  public void changeAccommodation(Accommodation accommodation) {
    this.accommodation = accommodation;
  }

}
