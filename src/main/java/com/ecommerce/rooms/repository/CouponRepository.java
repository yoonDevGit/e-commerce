package com.ecommerce.rooms.repository;

import com.ecommerce.rooms.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
