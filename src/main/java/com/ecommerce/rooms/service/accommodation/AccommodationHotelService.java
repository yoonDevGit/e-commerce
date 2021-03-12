package com.ecommerce.rooms.service.accommodation;

import com.ecommerce.rooms.domain.Accommodation.AccommodationHotel;
import com.ecommerce.rooms.dto.accommodation.AccommodationHotelDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationHotelService {

  private final AccommodationRepository<AccommodationHotel> accommodationHotelRepository;

  public AccommodationHotelDto getHotelAccommodation(Long accommodationId) {
    AccommodationHotel accommodationHotel = accommodationHotelRepository.findById(accommodationId)
        .orElseThrow();
    return new AccommodationHotelDto(accommodationHotel);
  }


  public void createHotelAccommodation(AccommodationHotelDto accommodationHotelDto) {
    AccommodationHotel accommodationHotel = new AccommodationHotel(accommodationHotelDto);
    accommodationHotelRepository.save(accommodationHotel);
  }


  public void deleteHotelAccommodation(Long accommodationId) {
    AccommodationHotel accommodationHotel = accommodationHotelRepository.findById(accommodationId)
        .orElseThrow();
    accommodationHotelRepository.delete(accommodationHotel);
  }


  public Page<AccommodationHotelDto> getPageAll(Pageable pageable) {
    Page<AccommodationHotel> users = accommodationHotelRepository.findPageAll(pageable);
    return users.map(AccommodationHotelDto::new);
  }

  public Slice<AccommodationHotelDto> getSliceAll(Pageable pageable) {
    Slice<AccommodationHotel> hotelAccommodations = accommodationHotelRepository.findSliceAll(pageable);
    return hotelAccommodations.map(AccommodationHotelDto::new);
  }
}
