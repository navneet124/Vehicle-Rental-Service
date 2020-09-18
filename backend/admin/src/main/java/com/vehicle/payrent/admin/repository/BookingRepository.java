package com.vehicle.payrent.admin.repository;

import com.vehicle.payrent.admin.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetail, Integer> {
}
