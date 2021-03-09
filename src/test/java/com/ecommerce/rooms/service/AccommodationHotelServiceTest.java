package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Accommodation.AccommodationHotel;
import com.ecommerce.rooms.domain.Accommodation.AccommodationPenstion;
import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.dto.accommodation.AccommodationHotelDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
class AccommodationHotelServiceTest {

  @Autowired
  AccommodationRepository<AccommodationHotel> hotelAccommodationRepository;

  @Autowired
  AccommodationRepository<AccommodationPenstion> penstionAccommodationRepository;

  @Test
  public void getHotelAccommodation() throws Exception {
    // GIVEN
    AccommodationHotelDto dto = new AccommodationHotelDto("dyshinHotel",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
        "cookingFacilities");

    AccommodationHotel accommodationHotel = new AccommodationHotel(dto);
    hotelAccommodationRepository.save(accommodationHotel);

    long hotelAccommodationId = 1;

    // WHEN
    AccommodationHotel findAccommodationHotel = hotelAccommodationRepository
        .findById(hotelAccommodationId).orElseThrow();

    // THEN
    assertThat(findAccommodationHotel.getNearbyInfo()).isEqualTo("nearbyInfo");
    assertThat(findAccommodationHotel.getGuestBenefits()).isEqualTo("guestBenefits");
    assertThat(findAccommodationHotel.getConvenienceInfo()).isEqualTo("convenienceInfo");

  }

  @Test
  public void deleteHotelAccommodation() throws Exception {
    // GIVEN
    AccommodationHotelDto dto = new AccommodationHotelDto("dyshinHotel",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
        "cookingFacilities");

    AccommodationHotel accommodationHotel = new AccommodationHotel(dto);
    hotelAccommodationRepository.save(accommodationHotel);

    long hotelAccommodationId = 1;

    // WHEN
    hotelAccommodationRepository.delete(accommodationHotel);

    // THEN
    assertThrows(NoSuchElementException.class,
        () -> hotelAccommodationRepository.findById(hotelAccommodationId).orElseThrow());
  }
  
}