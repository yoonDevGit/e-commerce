package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
