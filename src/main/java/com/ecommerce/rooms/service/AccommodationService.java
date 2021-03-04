package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.Accommodation.HotelAccommodation;
import com.ecommerce.rooms.domain.Accommodation.PenstionAccommodation;
import com.ecommerce.rooms.dto.accommodation.AccommodationDto;
import com.ecommerce.rooms.dto.accommodation.HotelAccommodationDto;
import com.ecommerce.rooms.dto.accommodation.PenstionAccommodationDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

  private final AccommodationRepository<HotelAccommodation> hotelAccommodationRepository;
  private final AccommodationRepository<PenstionAccommodation> penstionAccommodationRepository;

  public AccommodationDto getHotelAccommodation(Long accommodationId) {
    HotelAccommodation hotelAccommodation = hotelAccommodationRepository.findById(accommodationId)
        .orElseThrow();
    return new HotelAccommodationDto(hotelAccommodation);
  }

  public PenstionAccommodationDto getPenstionAccommodation(Long accommodationId) {
    PenstionAccommodation penstionAccommodation = penstionAccommodationRepository
        .findById(accommodationId).orElseThrow();
    return new PenstionAccommodationDto(penstionAccommodation);
  }

  public void createHotelAccommodation(HotelAccommodationDto hotelAccommodationDto) {
    HotelAccommodation hotelAccommodation = new HotelAccommodation(hotelAccommodationDto);
    hotelAccommodationRepository.save(hotelAccommodation);
  }

  public void createPenstionAccommodation(PenstionAccommodationDto penstionAccommodationDto) {
    PenstionAccommodation penstionAccommodation = new PenstionAccommodation(
        penstionAccommodationDto);
    penstionAccommodationRepository.save(penstionAccommodation);
  }

  public void deleteHotelAccommodation(Long accommodationId) {
    HotelAccommodation hotelAccommodation = hotelAccommodationRepository.findById(accommodationId)
        .orElseThrow();
    hotelAccommodationRepository.delete(hotelAccommodation);
  }

  public void deletePenstionAccommodation(Long accommodationId) {
    PenstionAccommodation penstionAccommodation = penstionAccommodationRepository
        .findById(accommodationId).orElseThrow();
    penstionAccommodationRepository.delete(penstionAccommodation);
  }
}
