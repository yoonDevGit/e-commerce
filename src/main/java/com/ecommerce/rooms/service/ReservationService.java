package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Reservation;
import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.ReservationDto;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.repository.CouponRepository;
import com.ecommerce.rooms.repository.ReservationRepository;
import com.ecommerce.rooms.repository.RoomRepository;
import com.ecommerce.rooms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final UserRepository userRepository;
  private final RoomRepository roomRepository;
  private final CouponRepository couponRepository;

  public ReservationDto getRservation(Long reservationId) {
    Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
    return new ReservationDto(reservation);
  }

  @Transactional
  public void createReservation(Long userId, Long memberId) {
    User user = userRepository.findById(userId).orElseThrow();
    Room room = roomRepository.findById(memberId).orElseThrow();
//    List<Coupon> coupons = couponRepository;
    Reservation reservation = new Reservation(user, room, null);
    reservationRepository.save(reservation);
  }

  @Transactional
  public void deleteReservation(Long couponId) {
    Reservation reservation = reservationRepository.findById(couponId).orElseThrow();
    reservationRepository.delete(reservation);
  }

  public Page<ReservationDto> getPageAll(Pageable pageable) {
    Page<Reservation> reservations = reservationRepository.findAll(pageable);
    return reservations.map(ReservationDto::new);
  }

  public Slice<ReservationDto> getSliceAll(Pageable pageable) {
    Slice<Reservation> reservations = reservationRepository.findAll(pageable);
    return reservations.map(ReservationDto::new);
  }

}
