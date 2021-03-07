package com.ecommerce.rooms.controller;

import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.service.RoomService;
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
@RequestMapping("/room")
public class RoomController {

  private final RoomService roomService;

  @GetMapping("/{roomId}")
  public ResponseEntity<RoomDto> getRoom(@PathVariable("roomId") @Valid Long roomId) {
    return new ResponseEntity<>(roomService.getRoom(roomId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createRoom(@Valid RoomDto roomDto) {
    try {
      roomService.createRoom(roomDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") @Valid Long roomId) {
    try {
      roomService.deleteRoom(roomId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
}
