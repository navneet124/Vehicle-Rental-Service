package com.vehicle.payrent.vendor.repository;

import com.vehicle.payrent.vendor.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetail, Integer> {
}
