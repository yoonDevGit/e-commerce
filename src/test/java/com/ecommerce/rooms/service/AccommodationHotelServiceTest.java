package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.accommodation.AccommodationHotel;
import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.dto.accommodation.AccommodationHotelDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import com.ecommerce.rooms.repository.RoomRepository;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccommodationHotelServiceTest {

  @Autowired
  AccommodationRepository<AccommodationHotel> hotelAccommodationRepository;
  @Autowired
  RoomRepository roomRepository;

  @Test
  public void getHotelAccommodation() throws Exception {
    // GIVEN
    for (int i = 0; i < 10; i++) {
      AccommodationHotelDto accommodationHotelDto = new AccommodationHotelDto("dyshinHotel" + i,
          "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
          "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
          "cookingFacilities");
      AccommodationHotel accommodationHotel = new AccommodationHotel(accommodationHotelDto);
      hotelAccommodationRepository.save(accommodationHotel);
      for (int j = 0; j < 10; j++) {
        RoomDto roomDto = new RoomDto("room" + j, 10000, "0000-00-00", "1111-11-11", "basicInfo",
            "facility", "refundPolicy");
        Room room = new Room(roomDto);
        room.changeAccommodation(accommodationHotel);
        roomRepository.save(room);
      }
    }

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

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getCountUsers() {
    // GIVEN
    for (int i = 0; i < 10; i++) {
      AccommodationHotelDto accommodationHotelDto = new AccommodationHotelDto("dyshinHotel" + i,
          "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
          "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
          "cookingFacilities");
      AccommodationHotel accommodationHotel = new AccommodationHotel(accommodationHotelDto);
      hotelAccommodationRepository.save(accommodationHotel);
      for (int j = 0; j < 10; j++) {
        RoomDto roomDto = new RoomDto("room" + j, 10000, "0000-00-00", "1111-11-11", "basicInfo",
            "facility", "refundPolicy");
        Room room = new Room(roomDto);
        room.changeAccommodation(accommodationHotel);
        roomRepository.save(room);
      }
    }

    PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "name"));

    // WHEN
    Page<AccommodationHotel> accommodationHotels = hotelAccommodationRepository.findPageAll(pageRequest);

    Page<AccommodationHotelDto> accommodationHotelDtos = accommodationHotels.map(AccommodationHotelDto::new);

    // THEN
    assertThat(accommodationHotelDtos.getTotalPages()).isEqualTo(2);
    assertThat(accommodationHotelDtos.getTotalElements()).isEqualTo(10);
    assertThat(accommodationHotelDtos.getNumberOfElements()).isEqualTo(5);
    assertThat(accommodationHotelDtos.isFirst()).isTrue();
    assertThat(accommodationHotelDtos.hasNext()).isTrue();
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getSliceUsers() {
    // GIVEN
    for (int i = 0; i < 2; i++) {
      AccommodationHotelDto accommodationHotelDto = new AccommodationHotelDto("dyshinHotel" + i,
          "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
          "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
          "cookingFacilities");
      AccommodationHotel accommodationHotel = new AccommodationHotel(accommodationHotelDto);
      hotelAccommodationRepository.save(accommodationHotel);
      for (int j = 0; j < 10; j++) {
        RoomDto roomDto = new RoomDto("room" + j, 10000, "0000-00-00", "1111-11-11", "basicInfo",
            "facility", "refundPolicy");
        Room room = new Room(roomDto);
        room.changeAccommodation(accommodationHotel);
        roomRepository.save(room);
      }
    }

    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

    // WHEN
    Slice<AccommodationHotel> accommodationHotels = hotelAccommodationRepository.findPageAll(pageRequest);

    Slice<AccommodationHotelDto> accommodationHotelDtos = accommodationHotels.map(AccommodationHotelDto::new);

    // THEN
    assertThat(accommodationHotelDtos.getNumber()).isEqualTo(0);
    assertThat(accommodationHotelDtos.getNumberOfElements()).isEqualTo(20);
    assertThat(accommodationHotelDtos.isFirst()).isTrue();
    assertThat(accommodationHotelDtos.hasNext()).isFalse();
  }
  
}