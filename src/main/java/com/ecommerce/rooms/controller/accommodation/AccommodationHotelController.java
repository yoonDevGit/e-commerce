package com.ecommerce.rooms.controller.accommodation;

import com.ecommerce.rooms.dto.accommodation.AccommodationHotelDto;
import com.ecommerce.rooms.service.accommodation.AccommodationHotelService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodation")
public class AccommodationHotelController {

  private final AccommodationHotelService accommodationHotelService;

  @GetMapping("/hotel/{accommodationId}")
  public ResponseEntity<AccommodationHotelDto> getAccommodation(
      @PathVariable("accommodationId") @Valid Long accommodationId) {
    return new ResponseEntity(accommodationHotelService.getHotelAccommodation(accommodationId),
        HttpStatus.OK);
  }

  @GetMapping("/hotels/page")
  @ResponseStatus
  public Page<AccommodationHotelDto> getPageAll(
      @PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return accommodationHotelService.getPageAll(pageable);
  }

  @GetMapping("/hotels/slice")
  public Slice<AccommodationHotelDto> getSliceAll(
      @PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return accommodationHotelService.getSliceAll(pageable);
  }

  @PostMapping
  public ResponseEntity<Void> createAccommodation(
      @RequestBody @Valid AccommodationHotelDto accommodationHotelDto) {
    try {
      accommodationHotelService.createHotelAccommodation(accommodationHotelDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/hotel/{accommodationId}")
  public ResponseEntity<Void> deleteAccommodation(
      @PathVariable("accommodationId") @Valid Long accommodationId) {
    try {
      accommodationHotelService.deleteHotelAccommodation(accommodationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
