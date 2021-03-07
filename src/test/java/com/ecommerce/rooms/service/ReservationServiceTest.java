package com.ecommerce.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ecommerce.rooms.domain.Accommodation.HotelAccommodation;
import com.ecommerce.rooms.domain.Reservation;
import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.dto.accommodation.HotelAccommodationDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import com.ecommerce.rooms.repository.ReservationRepository;
import com.ecommerce.rooms.repository.RoomRepository;
import com.ecommerce.rooms.repository.UserRepository;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(false)
class ReservationServiceTest {


  @Autowired
  private ReservationRepository reservationRepository;
  @Autowired
  private AccommodationRepository<HotelAccommodation> hotelAccommodationRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoomRepository roomRepository;

  @BeforeEach
  public void setUp() throws Exception {
    // 유저 등록
    UserDto userDto = new UserDto("dyshin", "010-0000-0000", "yoondeb93@gmail.com", 0);
    User user = new User(userDto);
    userRepository.save(user);

    // 업체 등록
    HotelAccommodationDto dto = new HotelAccommodationDto("dyshinHotel",
        "010-0000-0000", "seoul", "000-00", "notice", "basicInfo", "addPersonnelInfo",
        "refundPolicy", "etc", "nearbyInfo", "guestBenefits", "convenienceInfo",
        "cookingFacilities");

    HotelAccommodation hotelAccommodation = new HotelAccommodation(dto);
    hotelAccommodationRepository.save(hotelAccommodation);

    // 객실 등록
    RoomDto roomDto = new RoomDto("room", 10000, "0000-00-00", "1111-11-11", "basicInfo",
        "facility", "refundPolicy");
    Room room = new Room(roomDto);
    roomRepository.save(room);
  }

  @Test
  public void getReservation() throws Exception {
    //GIVEN

    long userId = 1;
    long roomId = 3;

    User findUser = userRepository.findById(userId).orElseThrow();
    Room findRoom = roomRepository.findById(roomId).orElseThrow();
    Reservation reservation = new Reservation(findUser, findRoom, null);
    reservationRepository.save(reservation);

    long reservationId = 4;

    //WHEN

    Reservation findReservation = reservationRepository.findById(reservationId).orElseThrow();

    //THEN
    assertThat(findReservation.getRoom().getPrice()).isEqualTo(10000);
    assertThat(findReservation.getRoom().getName()).isEqualTo("room");

    assertThat(findReservation.getUser().getAccount()).isEqualTo("dyshin");
    assertThat(findReservation.getUser().getPhone()).isEqualTo("010-0000-0000");

  }

  @Test
  public void deleteReservation() throws Exception {
    // GIVEN

    long reservationId = 4;
    Reservation findReservation = reservationRepository.findById(reservationId).orElseThrow();

    // WHEN
    reservationRepository.delete(findReservation);

    // THEN
    assertThrows(NoSuchElementException.class, () -> roomRepository.findById(reservationId).orElseThrow());
  }

}