package com.ecommerce.rooms.dto.accommodation;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import com.ecommerce.rooms.domain.Accommodation.HotelAccommodation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelAccommodationDto extends AccommodationDto {

  // 투숙객 혜택
  private String guestBenefits;

  // 부대시설 정보
  private String convenienceInfo;

  // 취사 시설
  private String cookingFacilities;

  public HotelAccommodationDto(String name, String phone, String address, String detailedAddress,
      String notice, String basicInfo, String addPersonnelInfo, String refundPolicy, String etc,
      String nearbyInfo, String guestBenefits, String convenienceInfo, String cookingFacilities) {
    super(name, phone, address, detailedAddress, notice, basicInfo, addPersonnelInfo, refundPolicy,
        etc, nearbyInfo);
    this.guestBenefits = guestBenefits;
    this.convenienceInfo = convenienceInfo;
    this.cookingFacilities = cookingFacilities;
  }

  public HotelAccommodationDto(HotelAccommodation hotelAccommodation) {
    super(hotelAccommodation);
    this.guestBenefits = hotelAccommodation.getGuestBenefits();
    this.convenienceInfo = hotelAccommodation.getConvenienceInfo();
    this.cookingFacilities = hotelAccommodation.getCookingFacilities();
  }
}
