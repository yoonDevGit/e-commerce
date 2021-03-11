package com.ecommerce.rooms.dto;

import com.ecommerce.rooms.domain.room.Room;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomDto {

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

  public RoomDto(Room room) {
    this.name = room.getName();
    this.price = room.getPrice();
    this.checkIn = room.getCheckIn();
    this.checkOut = room.getCheckOut();
    this.basicInfo = room.getBasicInfo();
    this.facility = room.getFacility();
    this.refundPolicy = room.getRefundPolicy();
  }

  public static List<RoomDto> toList(List<Room> rooms) {
    return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
  }
}
