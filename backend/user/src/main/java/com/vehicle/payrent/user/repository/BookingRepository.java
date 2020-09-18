package com.vehicle.payrent.user.repository;

import com.vehicle.payrent.user.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetail, Integer> {
}
