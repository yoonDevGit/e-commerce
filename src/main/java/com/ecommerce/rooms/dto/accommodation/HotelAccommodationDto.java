package com.ecommerce.rooms.dto.accommodation;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import com.ecommerce.rooms.domain.Accommodation.HotelAccommodation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HotelAccommodationDto extends AccommodationDto {

  // 투숙객 혜택
  private String guestBenefits;

  // 부대시설 정보
  private String convenienceInfo;

  // 취사 시설
  private String cookingFacilities;


  public HotelAccommodationDto(HotelAccommodation hotelAccommodation) {
    super(hotelAccommodation);
    this.guestBenefits = hotelAccommodation.getGuestBenefits();
    this.convenienceInfo = hotelAccommodation.getConvenienceInfo();
    this.cookingFacilities = hotelAccommodation.getCookingFacilities();
  }
}
