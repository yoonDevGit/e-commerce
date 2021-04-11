package com.ecommerce.rooms.domain.accommodation;

import com.ecommerce.rooms.dto.accommodation.AccommodationPenstionDto;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("PENSTION")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccommodationPenstion extends Accommodation {

  // 객실 정보
  private String roomInfo;

  // 펜션 서비스
  private String pensionService;

  // 바비큐 시설
  private String bbqFacilities;

  public AccommodationPenstion(AccommodationPenstionDto accommodationPenstionDto) {
    super(accommodationPenstionDto);
    this.roomInfo = accommodationPenstionDto.getRoomInfo();
    this.pensionService = accommodationPenstionDto.getPensionService();
    this.bbqFacilities = accommodationPenstionDto.getBbqFacilities();
  }
}
