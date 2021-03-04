package com.ecommerce.rooms.dto.accommodation;

import com.ecommerce.rooms.domain.Accommodation.PenstionAccommodation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PenstionAccommodationDto extends AccommodationDto {

  // 객실 정보
  private String roomInfo;

  // 펜션 서비스
  private String pensionService;

  // 바비큐 시설
  private String bbqFacilities;

  public PenstionAccommodationDto(PenstionAccommodation penstionAccommodation) {
    super(penstionAccommodation);
    this.roomInfo = penstionAccommodation.getRoomInfo();
    this.pensionService = penstionAccommodation.getPensionService();
    this.bbqFacilities = penstionAccommodation.getBbqFacilities();
  }
}
