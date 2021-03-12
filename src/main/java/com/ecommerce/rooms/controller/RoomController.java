package com.ecommerce.rooms.controller;

import com.ecommerce.rooms.common.vaildation.ValidationAccommodation;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.service.RoomService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;
  private final ValidationAccommodation validationAccommodation;

  @GetMapping("/accommodation/{accommodationType}/{accommodationId}/room/{roomId}")
  public ResponseEntity<RoomDto> getRoom(
      @PathVariable("accommodationType") @Valid String accommodationType,
      @PathVariable("accommodationId") @Valid Long accommodationId,
      @PathVariable("roomId") @Valid Long roomId) {
    validationAccommodation.type(accommodationType);
    return new ResponseEntity<>(roomService.getRoom(roomId),
        HttpStatus.OK);
  }

  @PostMapping("/accommodation/{accommodationType}/{accommodationId}/room")
  public ResponseEntity<Void> createRoom(@RequestBody @Valid RoomDto roomDto,
      @PathVariable("accommodationType") @Valid String accommodationType,
      @PathVariable("accommodationId") @Valid Long accommodationId) {
    try {
      validationAccommodation.type(accommodationType);
      roomService.createRoom(roomDto, accommodationType, accommodationId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @GetMapping("/accommodation/{accommodationType}/{accommodationId}/room-page")
  @ResponseStatus
  public Page<RoomDto> getPageAll(
      @PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return roomService.getPageAll(pageable);
  }

  @GetMapping("/accommodation/{accommodationType}/{accommodationId}/room-slice")
  public Slice<RoomDto> getSliceAll(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
    return roomService.getSliceAll(pageable);
  }

  @DeleteMapping("/accommodation/{accommodationType}/{accommodationId}/room/{roomId}")
  public ResponseEntity<Void> deleteRoom (
      @PathVariable("accommodationType") @Valid String accommodationType,
      @PathVariable("accommodationId") @Valid Long accommodationId,
      @PathVariable("roomId") @Valid Long roomId) {
    try {
      validationAccommodation.type(accommodationType);
      roomService.deleteRoom(roomId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
}
