package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.Accommodation.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository<T extends Accommodation> extends JpaRepository<T, Long> {

  @Query ("select a from  Accommodation a join fetch a.rooms where a.id = :accommodationId")
  T findRoomAll(@Param("accommodationId") Long accommodationId);
}
