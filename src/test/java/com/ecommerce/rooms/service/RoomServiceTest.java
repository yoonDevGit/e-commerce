package com.ecommerce.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.repository.RoomRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootTest
@Rollback(false)
class RoomServiceTest {

  @Autowired
  RoomRepository roomRepository;

  @Test
  public void getRoom() throws Exception {
    // GIVEN
    RoomDto roomDto = new RoomDto("room", 10000, "0000-00-00", "1111-11-11", "basicInfo",
        "facility", "refundPolicy");
    Room room = new Room(roomDto);

    roomRepository.save(room);

    long roomId = 1;

    // WHEN
    Room findRoom = roomRepository.findById(roomId).orElseThrow();

    // THEN
    assertThat(findRoom.getPrice()).isEqualTo(10000);

  }

  @Test
  public void deleteRoom() throws Exception {
    // GIVEN
    RoomDto roomDto = new RoomDto("room", 10000, "0000-00-00", "1111-11-11", "basicInfo",
        "facility", "refundPolicy");
    Room room = new Room(roomDto);
    roomRepository.save(room);

    long roomId = 1;
    Room findRoom = roomRepository.findById(roomId).orElseThrow();

    // WHEN
    roomRepository.delete(findRoom);

    // THEN
    assertThrows(NoSuchElementException.class, () -> roomRepository.findById(roomId).orElseThrow());
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getCountUsers() {
    // GIVEN
    for (int i = 0; i < 100; i++) {
      RoomDto roomDto = new RoomDto("room" + i, 10000, "0000-00-00", "1111-11-11", "basicInfo",
          "facility", "refundPolicy");
      Room room = new Room(roomDto);
      roomRepository.save(room);
    }
    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

    // WHEN
    Page<Room> room = roomRepository.findAll(pageRequest);

    Page<RoomDto> roomDtos = room.map(RoomDto::new);

    // THEN
    assertThat(roomDtos.getTotalPages()).isEqualTo(5);
    assertThat(roomDtos.getTotalElements()).isEqualTo(100);
    assertThat(roomDtos.getNumberOfElements()).isEqualTo(20);
    assertThat(roomDtos.isFirst()).isTrue();
    assertThat(roomDtos.hasNext()).isTrue();
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getSliceUsers() {
    // GIVEN
    for (int i = 0; i < 100; i++) {
      RoomDto roomDto = new RoomDto("room" + i, 10000, "0000-00-00", "1111-11-11", "basicInfo",
          "facility", "refundPolicy");
      Room room = new Room(roomDto);
      roomRepository.save(room);
    }
    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));

    // WHEN
    Slice<Room> room = roomRepository.findAll(pageRequest);

    Slice<RoomDto> roomDtos = room.map(RoomDto::new);

    // THEN
    assertThat(roomDtos.getNumber()).isEqualTo(0);
    assertThat(roomDtos.getNumberOfElements()).isEqualTo(20);
    assertThat(roomDtos.isFirst()).isTrue();
    assertThat(roomDtos.hasNext()).isTrue();
  }
}