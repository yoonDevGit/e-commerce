package com.ecommerce.rooms.api.v1;

import com.ecommerce.rooms.dto.ReservationDto;
import com.ecommerce.rooms.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
@Api(value = "ReservationApiController v1")
public class ReservationApiController {

  private final ReservationService reservationService;

  @GetMapping("/{reservationId}")
  @ApiOperation(value="예약 상세")
  public ResponseEntity<ReservationDto> getReservation(
      @PathVariable("reservationId") @Valid Long reservationId) {
    return new ResponseEntity<>(reservationService.getRservation(reservationId), HttpStatus.OK);
  }

  @GetMapping("/page")
  @ApiOperation(value="예약 목록 페이지")
  public Page<ReservationDto> getPageAll(
      @PageableDefault(size = 5, sort = "username") Pageable pageable) {
    return reservationService.getPageAll(pageable);
  }

  @GetMapping("/slice")
  @ApiOperation(value="예약 목록 슬라이스")
  public Slice<ReservationDto> getSliceAll(@PageableDefault(size = 5, sort = "username") Pageable pageable) {
    return reservationService.getSliceAll(pageable);
  }

  @PostMapping
  @ApiOperation(value="예약 생성")
  public ResponseEntity<Void> createReservation(@RequestParam("userId") @Valid Long userId,
      @RequestParam("roomId") @Valid Long roomId) {

    try {
      reservationService.createReservation(userId, roomId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{reservationId}")
  @ApiOperation(value="예약 삭제")
  public ResponseEntity<Void> deleteReservation(@PathVariable("reservationId") @Valid Long reservationId) {
    try {
      reservationService.deleteReservation(reservationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
