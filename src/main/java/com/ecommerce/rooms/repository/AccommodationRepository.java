package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.accommodation.Accommodation;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository<T extends Accommodation> extends JpaRepository<T, Long> {

  @Query("select a from  Accommodation a join fetch a.rooms r where a.id = :accommodationId")
  Optional<T> findById(@Param("accommodationId") Long accommodationId);

  @Query(value = "select a from  Accommodation a join fetch a.rooms r", countQuery = "select  count(a) from Accommodation a")
  Page<T> findPageAll(Pageable pageable);

  @Query(value = "select a from Accommodation  a join fetch a.rooms r")
  Slice<T> findSliceAll(Pageable pageable);
}
