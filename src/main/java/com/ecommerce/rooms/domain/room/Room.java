package com.ecommerce.rooms.domain.room;

import static javax.persistence.FetchType.LAZY;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import com.ecommerce.rooms.domain.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

/** 숙박업체 방
 * */

@Entity
@Getter
public class Room extends BaseTimeEntity {

  @Id @GeneratedValue
  @Column(name = "room_id")
  private Long id;

  // 이름
  private String name;

  // 가격
  private int price;

  private String description;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "accommodation_id")
  @JsonManagedReference
  private Accommodation accommodation;


}
