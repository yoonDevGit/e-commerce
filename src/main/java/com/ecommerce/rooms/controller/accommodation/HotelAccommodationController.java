package com.ecommerce.rooms.controller.accommodation;

import com.ecommerce.rooms.dto.accommodation.HotelAccommodationDto;
import com.ecommerce.rooms.service.AccommodationService;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodation/hotel")
public class HotelAccommodationController {

  private final AccommodationService accommodationService;

  @GetMapping("/{accommodation-id}")
  public ResponseEntity<HotelAccommodationDto> getAccommodation(
      @PathVariable("accommodation-id") @Valid Long accommodationId) {
    return new ResponseEntity(accommodationService.getHotelAccommodation(accommodationId),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createAccommodation(
      @Valid HotelAccommodationDto hotelAccommodationDto) {
    try {
      accommodationService.createHotelAccommodation(hotelAccommodationDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{accommodation-id}")
  public ResponseEntity<Void> deleteAccommodation(
      @PathVariable("accommodation-id") @Valid Long accommodationId) {
    try {
      accommodationService.deleteHotelAccommodation(accommodationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
