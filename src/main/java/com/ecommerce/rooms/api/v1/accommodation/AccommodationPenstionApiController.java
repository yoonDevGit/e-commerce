package com.ecommerce.rooms.api.v1.accommodation;

import com.ecommerce.rooms.dto.accommodation.AccommodationPenstionDto;
import com.ecommerce.rooms.service.accommodation.AccommodationPenstionService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accommodation/penstion")
@Api(value = "AccommodationPenstionApiController v1")
public class AccommodationPenstionApiController {

  private final AccommodationPenstionService accommodationPenstionService;

  @GetMapping("/{accommodationId}")
  @ApiOperation(value="펜션 상세")
  public ResponseEntity<AccommodationPenstionDto> getAccommodation(
      @PathVariable("accommodationId") @Valid Long accommodationId) {
    return new ResponseEntity<>(
        accommodationPenstionService.getPenstionAccommodation(accommodationId), HttpStatus.OK);
  }

  @GetMapping("/page")
  @ApiOperation(value="펜션 목록 페이지")
  public Page<AccommodationPenstionDto> getPageAll(
      @PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return accommodationPenstionService.getPageAll(pageable);
  }

  @GetMapping("/slice")
  @ApiOperation(value="펜션 목록 슬라이스")
  public Slice<AccommodationPenstionDto> getSliceAll(
      @PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return accommodationPenstionService.getSliceAll(pageable);
  }

  @PostMapping
  @ApiOperation(value="펜션 추가")
  public ResponseEntity<Void> createAccommodation(
      @RequestBody @Valid AccommodationPenstionDto accommodationPenstionDto) {
    try {
      accommodationPenstionService.createPenstionAccommodation(accommodationPenstionDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{accommodationId}")
  @ApiOperation(value="펜션 삭제")
  public ResponseEntity<Void> deleteAccommodation(
      @PathVariable("accommodationId") @Valid Long accommodationId) {
    try {
      accommodationPenstionService.deletePenstionAccommodation(accommodationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

}
