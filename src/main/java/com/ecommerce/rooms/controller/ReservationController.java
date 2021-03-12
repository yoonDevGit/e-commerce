package com.ecommerce.rooms.controller;

import com.ecommerce.rooms.dto.ReservationDto;
import com.ecommerce.rooms.service.ReservationService;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @GetMapping("/reservation/{reservationId}")
  public ResponseEntity<ReservationDto> getReservation(
      @PathVariable("reservationId") @Valid Long reservationId) {
    return new ResponseEntity<>(reservationService.getRservation(reservationId), HttpStatus.OK);
  }

  @GetMapping("/reservations-page")
  @ResponseStatus
  public Page<ReservationDto> getPageAll(
      @PageableDefault(size = 5, sort = "username") Pageable pageable) {
    return reservationService.getPageAll(pageable);
  }

  @GetMapping("/reservation-slice")
  public Slice<ReservationDto> getSliceAll(@PageableDefault(size = 5, sort = "username") Pageable pageable) {
    return reservationService.getSliceAll(pageable);
  }

  @PostMapping("/reservation")
  public ResponseEntity<Void> createReservation(@RequestParam("userId") @Valid Long userId,
      @RequestParam("roomId") @Valid Long roomId) {

    try {
      reservationService.createReservation(userId, roomId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/reservation/{reservationId}")
  public ResponseEntity<Void> deleteReservation(@PathVariable("reservationId") @Valid Long reservationId) {
    try {
      reservationService.deleteReservation(reservationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
