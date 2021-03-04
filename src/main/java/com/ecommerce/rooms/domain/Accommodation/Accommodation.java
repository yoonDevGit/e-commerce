package com.ecommerce.rooms.domain.Accommodation;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.common.Constant.AccommodationType;
import com.ecommerce.rooms.domain.Reservation;
import com.ecommerce.rooms.domain.base.BaseTimeEntity;
import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.accommodation.AccommodationDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 숙박 업체
 * */

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Accommodation extends BaseTimeEntity {

  @Id @GeneratedValue
  @Column(name = "accommodation_id")
  private Long id;

  // 이름
  private String name;

  // 전화 번호
  private String phone;

  // 주소
  private String address;

  // 상세 주소
  private String detailedAddress;

  // 공지 사항
  private String notice;

  // 기본 정보
  private String basicInfo;

  // 인원 추가 정보
  private String addPersonnelInfo;

  // 환불 규정
  private String refundPolicy;

  // 기타 사항
  private String etc;

  // 주변 정보
  private String nearbyInfo;

  @OneToMany(mappedBy = "accommodation", cascade = ALL)
  @JsonBackReference
  private List<Room> rooms;

  @ManyToOne(fetch = LAZY, cascade = ALL)
  @JoinColumn(name = "reservation_id")
  @JsonBackReference
  private Reservation reservation;

  public Accommodation(AccommodationDto accommodationDto) {
    this.name = accommodationDto.getName();
    this.phone = accommodationDto.getPhone();
    this.address = accommodationDto.getAddress();
    this.detailedAddress = accommodationDto.getDetailedAddress();
    this.notice = accommodationDto.getNotice();
    this.basicInfo = accommodationDto.getBasicInfo();
    this.addPersonnelInfo = accommodationDto.getAddPersonnelInfo();
    this.refundPolicy = accommodationDto.getRefundPolicy();
    this.etc = accommodationDto.getEtc();
    this.nearbyInfo = accommodationDto.getNearbyInfo();
  }

}
