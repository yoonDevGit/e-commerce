package com.ecommerce.rooms.domain.Accommodation;

import com.ecommerce.rooms.dto.accommodation.HotelAccommodationDto;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("HOTEL")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelAccommodation extends Accommodation {

  // 투숙객 혜택
  private String guestBenefits;

  // 부대시설 정보
  private String convenienceInfo;

  // 취사 시설
  private String cookingFacilities;

  public HotelAccommodation(HotelAccommodationDto hotelAccommodationDto) {
    super(hotelAccommodationDto);
    this.guestBenefits = hotelAccommodationDto.getGuestBenefits();
    this.convenienceInfo = hotelAccommodationDto.getConvenienceInfo();
    this.cookingFacilities = hotelAccommodationDto.getCookingFacilities();
  }
}
