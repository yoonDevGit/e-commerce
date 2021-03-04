package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
