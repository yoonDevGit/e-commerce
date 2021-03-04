package com.ecommerce.rooms.domain.Accommodation;

import com.ecommerce.rooms.dto.accommodation.PenstionAccommodationDto;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PENSTION")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PenstionAccommodation extends Accommodation {

  // 객실 정보
  private String roomInfo;

  // 펜션 서비스
  private String pensionService;

  // 바비큐 시설
  private String bbqFacilities;

  public PenstionAccommodation(PenstionAccommodationDto penstionAccommodationDto) {
    super(penstionAccommodationDto);
    this.roomInfo = penstionAccommodationDto.getRoomInfo();
    this.pensionService = penstionAccommodationDto.getPensionService();
    this.bbqFacilities = penstionAccommodationDto.getBbqFacilities();
  }
}
