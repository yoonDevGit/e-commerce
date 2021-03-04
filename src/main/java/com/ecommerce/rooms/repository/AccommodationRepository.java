package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository<T extends Accommodation> extends JpaRepository<T, Long> {

}
