package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import com.ecommerce.rooms.domain.Accommodation.HotelAccommodation;
import com.ecommerce.rooms.domain.Accommodation.PenstionAccommodation;
import com.ecommerce.rooms.dto.accommodation.HotelAccommodationDto;
import com.ecommerce.rooms.dto.accommodation.PenstionAccommodationDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import java.util.NoSuchElementException;
import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
class AccommodationServiceTest {

  @Autowired
  AccommodationRepository<HotelAccommodation> hotelAccommodationRepository;

  @Autowired
  AccommodationRepository<PenstionAccommodation> penstionAccommodationRepository;

  @Test
  public void getHotelAccommodation() throws Exception {
    // GIVEN
    HotelAccommodationDto dto = new HotelAccommodationDto("dyshinHotel",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
        "cookingFacilities");

    HotelAccommodation hotelAccommodation = new HotelAccommodation(dto);
    hotelAccommodationRepository.save(hotelAccommodation);

    long hotelAccommodationId = 1;

    // WHEN
    HotelAccommodation findHotelAccommodation = hotelAccommodationRepository
        .findById(hotelAccommodationId).orElseThrow();

    // THEN
    assertThat(findHotelAccommodation.getNearbyInfo()).isEqualTo("nearbyInfo");
    assertThat(findHotelAccommodation.getGuestBenefits()).isEqualTo("guestBenefits");
    assertThat(findHotelAccommodation.getConvenienceInfo()).isEqualTo("convenienceInfo");

  }

  @Test
  public void getPenstionAccommodation() throws Exception {
    // GIVEN
    PenstionAccommodationDto dto = new PenstionAccommodationDto("dyshinPenstion",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "roomInfo", "pensionService", "bbqFacilities");

    PenstionAccommodation penstionAccommodation = new PenstionAccommodation(dto);
    penstionAccommodationRepository.save(penstionAccommodation);

    long penstionAccommodationId = 1;

    // WHEN
    PenstionAccommodation findPenstionAccommodation = penstionAccommodationRepository
        .findById(penstionAccommodationId).orElseThrow();

    // THEN
    assertThat(findPenstionAccommodation.getNearbyInfo()).isEqualTo("nearbyInfo");
    assertThat(findPenstionAccommodation.getRoomInfo()).isEqualTo("roomInfo");
    assertThat(findPenstionAccommodation.getPensionService()).isEqualTo("pensionService");
    assertThat(findPenstionAccommodation.getBbqFacilities()).isEqualTo("bbqFacilities");

  }

  @Test
  public void deleteHotelAccommodation() throws Exception {
    // GIVEN
    HotelAccommodationDto dto = new HotelAccommodationDto("dyshinHotel",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
        "cookingFacilities");

    HotelAccommodation hotelAccommodation = new HotelAccommodation(dto);
    hotelAccommodationRepository.save(hotelAccommodation);

    long hotelAccommodationId = 1;

    // WHEN
    hotelAccommodationRepository.delete(hotelAccommodation);

    // THEN
    assertThrows(NoSuchElementException.class,
        () -> hotelAccommodationRepository.findById(hotelAccommodationId).orElseThrow());
  }

  @Test
  public void deletePenstionAccommodation() throws Exception {
    // GIVEN
    PenstionAccommodationDto dto = new PenstionAccommodationDto("dyshinPenstion",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "roomInfo", "pensionService", "bbqFacilities");

    PenstionAccommodation penstionAccommodation = new PenstionAccommodation(dto);
    penstionAccommodationRepository.save(penstionAccommodation);

    long penstionAccommodationId = 1;

    // WHEN
    penstionAccommodationRepository.delete(penstionAccommodation);

    // THEN
    assertThrows(NoSuchElementException.class,
        () -> penstionAccommodationRepository.findById(penstionAccommodationId).orElseThrow());
  }
}