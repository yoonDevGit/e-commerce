package com.ecommerce.rooms.controller.accommodation;

import com.ecommerce.rooms.domain.Accommodation.PenstionAccommodation;
import com.ecommerce.rooms.dto.accommodation.PenstionAccommodationDto;
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
@RequestMapping("/accommodation/penstion")
public class PenstionAccommodationController {

  private final AccommodationService accommodationService;

  @GetMapping("/{accommodationId}")
  public ResponseEntity<PenstionAccommodationDto> getAccommodation(
      @PathVariable("accommodationId") @Valid Long accommodationId) {
    return new ResponseEntity<>(accommodationService.getPenstionAccommodation(accommodationId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createAccommodation(@Valid PenstionAccommodationDto penstionAccommodationDto) {
    try {
      accommodationService.createPenstionAccommodation(penstionAccommodationDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{accommodationId}")
  public ResponseEntity<Void> deleteAccommodation(@PathVariable("accommodationId") @Valid Long accommodationId) {
    try {
      accommodationService.deletePenstionAccommodation(accommodationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
