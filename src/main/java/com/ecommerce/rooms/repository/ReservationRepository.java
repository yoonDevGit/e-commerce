package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
