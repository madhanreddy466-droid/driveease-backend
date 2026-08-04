package com.project.Car_Rental;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByPhone(String phone);

    List<Booking> findByName(String name);

    List<Booking> findByPickupLocation(String pickupLocation);

    List<Booking> findByPickupDate(LocalDate pickupDate);

    List<Booking> findByReturnDate(LocalDate returnDate);

    List<Booking> findByPhoneAndPickupDate(String phone, LocalDate pickupDate);

    List<Booking> findByNameIgnoreCase(String name);
    
    List<Booking> findByUserEmail(String userEmail);

    List<Booking> findByCarId(Integer carId);
}