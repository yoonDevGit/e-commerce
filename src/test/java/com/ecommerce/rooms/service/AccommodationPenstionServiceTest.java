package com.ecommerce.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.rooms.domain.accommodation.AccommodationHotel;
import com.ecommerce.rooms.domain.accommodation.AccommodationPenstion;
import com.ecommerce.rooms.dto.accommodation.AccommodationPenstionDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(false)
class AccommodationPenstionServiceTest {

  @Autowired
  AccommodationRepository<AccommodationHotel> hotelAccommodationRepository;

  @Autowired
  AccommodationRepository<AccommodationPenstion> penstionAccommodationRepository;

  @Test
  public void getPenstionAccommodation() throws Exception {
    // GIVEN
    AccommodationPenstionDto dto = new AccommodationPenstionDto("dyshinPenstion",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "roomInfo", "pensionService", "bbqFacilities");

    AccommodationPenstion accommodationPenstion = new AccommodationPenstion(dto);
    penstionAccommodationRepository.save(accommodationPenstion);

    long penstionAccommodationId = 1;

    // WHEN
    AccommodationPenstion findAccommodationPenstion = penstionAccommodationRepository
        .findById(penstionAccommodationId).orElseThrow();

    // THEN
    assertThat(findAccommodationPenstion.getNearbyInfo()).isEqualTo("nearbyInfo");
    assertThat(findAccommodationPenstion.getRoomInfo()).isEqualTo("roomInfo");
    assertThat(findAccommodationPenstion.getPensionService()).isEqualTo("pensionService");
    assertThat(findAccommodationPenstion.getBbqFacilities()).isEqualTo("bbqFacilities");

  }

  @Test
  public void deletePenstionAccommodation() throws Exception {
    // GIVEN
    AccommodationPenstionDto dto = new AccommodationPenstionDto("dyshinPenstion",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "roomInfo", "pensionService", "bbqFacilities");

    AccommodationPenstion accommodationPenstion = new AccommodationPenstion(dto);
    penstionAccommodationRepository.save(accommodationPenstion);

    long penstionAccommodationId = 1;

    // WHEN
    penstionAccommodationRepository.delete(accommodationPenstion);

    // THEN
    assertThrows(NoSuchElementException.class,
        () -> penstionAccommodationRepository.findById(penstionAccommodationId).orElseThrow());
  }
}