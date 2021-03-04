package com.ecommerce.rooms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.repository.RoomRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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
}