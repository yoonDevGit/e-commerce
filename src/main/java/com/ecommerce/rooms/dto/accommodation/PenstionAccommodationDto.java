package com.ecommerce.rooms.dto.accommodation;

import com.ecommerce.rooms.domain.Accommodation.PenstionAccommodation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PenstionAccommodationDto extends AccommodationDto {

  // 객실 정보
  private String roomInfo;

  // 펜션 서비스
  private String pensionService;

  // 바비큐 시설
  private String bbqFacilities;

  public PenstionAccommodationDto(String name, String phone, String address,
      String detailedAddress, String notice, String basicInfo, String addPersonnelInfo,
      String refundPolicy, String etc, String nearbyInfo, String roomInfo,
      String pensionService, String bbqFacilities) {
    super(name, phone, address, detailedAddress, notice, basicInfo, addPersonnelInfo, refundPolicy,
        etc, nearbyInfo);
    this.roomInfo = roomInfo;
    this.pensionService = pensionService;
    this.bbqFacilities = bbqFacilities;
  }

  public PenstionAccommodationDto(PenstionAccommodation penstionAccommodation) {
    super(penstionAccommodation);
    this.roomInfo = penstionAccommodation.getRoomInfo();
    this.pensionService = penstionAccommodation.getPensionService();
    this.bbqFacilities = penstionAccommodation.getBbqFacilities();
  }
}
