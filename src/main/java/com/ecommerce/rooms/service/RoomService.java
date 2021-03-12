package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Accommodation.AccommodationHotel;
import com.ecommerce.rooms.domain.Accommodation.AccommodationPenstion;
import com.ecommerce.rooms.domain.room.Room;
import com.ecommerce.rooms.dto.RoomDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import com.ecommerce.rooms.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final AccommodationRepository<AccommodationHotel> accommodationHotelRepository;
  private final AccommodationRepository<AccommodationPenstion> accommodationPenstionRepository;


  public RoomDto getRoom(Long roomId) {
    Room room = roomRepository.findById(roomId).orElseThrow();
    room.getAccommodation();
    return new RoomDto(room);
  }

  public void createRoom(RoomDto roomDto, String accommodationType, Long accommodationId) {

    Room room = new Room(roomDto);

    if (accommodationType.equals("hotel")) {
      AccommodationHotel accommodation = accommodationHotelRepository.findById(accommodationId)
          .orElseThrow();
      room.changeAccommodation(accommodation);
    } else if (accommodationType.equals("penstion")) {
      AccommodationPenstion accommodation = accommodationPenstionRepository
          .findById(accommodationId).orElseThrow();
      room.changeAccommodation(accommodation);
    }

    roomRepository.save(room);
  }

  public void deleteRoom(Long roomId) {
    Room room = roomRepository.findById(roomId).orElseThrow();
    roomRepository.delete(room);
  }

  public Page<RoomDto> getPageAll(Pageable pageable) {
    Page<Room> rooms = roomRepository.findAll(pageable);
    return rooms.map(RoomDto::new);
  }

  public Slice<RoomDto> getSliceAll(Pageable pageable) {
    Slice<Room> rooms = roomRepository.findAll(pageable);
    return rooms.map(RoomDto::new);
  }
}
