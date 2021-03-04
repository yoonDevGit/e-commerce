package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;

  public RoomDto getRoom(Long roomId) {
    Room room = roomRepository.findById(roomId).orElseThrow();
    return new RoomDto(room);
  }

  public void createRoom(RoomDto roomDto) {
    Room room = new Room(roomDto);
    roomRepository.save(room);
  }

  public void deleteRoom(Long roomId) {
    Room room = roomRepository.findById(roomId).orElseThrow();
    roomRepository.delete(room);
  }
}
