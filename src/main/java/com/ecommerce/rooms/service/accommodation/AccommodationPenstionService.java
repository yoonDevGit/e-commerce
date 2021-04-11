package com.ecommerce.rooms.service.accommodation;

import com.ecommerce.rooms.domain.Accommodation.AccommodationPenstion;
import com.ecommerce.rooms.dto.accommodation.AccommodationPenstionDto;
import com.ecommerce.rooms.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccommodationPenstionService {

  private final AccommodationRepository<AccommodationPenstion> penstionAccommodationRepository;

  public AccommodationPenstionDto getPenstionAccommodation(Long accommodationId) {
    AccommodationPenstion accommodationPenstion = penstionAccommodationRepository
        .findById(accommodationId).orElseThrow();
    return new AccommodationPenstionDto(accommodationPenstion);
  }

  @Transactional
  public void createPenstionAccommodation(AccommodationPenstionDto accommodationPenstionDto) {
    AccommodationPenstion accommodationPenstion = new AccommodationPenstion(
        accommodationPenstionDto);
    penstionAccommodationRepository.save(accommodationPenstion);
  }

  @Transactional
  public void deletePenstionAccommodation(Long accommodationId) {
    AccommodationPenstion accommodationPenstion = penstionAccommodationRepository
        .findById(accommodationId).orElseThrow();
    penstionAccommodationRepository.delete(accommodationPenstion);
  }

  public Page<AccommodationPenstionDto> getPageAll(Pageable pageable) {
    Page<AccommodationPenstion> penstionAccommodations = penstionAccommodationRepository
        .findAll(pageable);
    return penstionAccommodations.map(AccommodationPenstionDto::new);
  }

  public Slice<AccommodationPenstionDto> getSliceAll(Pageable pageable) {
    Slice<AccommodationPenstion> penstionAccommodations = penstionAccommodationRepository
        .findAll(pageable);
    return penstionAccommodations.map(AccommodationPenstionDto::new);
  }
}
