package com.project.Car_Rental;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository repo;

    // One lock object per car so two people booking DIFFERENT cars never
    // block each other, but two people racing for the SAME car do.
    private final ConcurrentMap<Integer, Object> carLocks = new ConcurrentHashMap<>();

    // Constructor Injection (Best Practice)
    public BookingService(BookingRepository repo) {
        this.repo = repo;
    }

    // Save booking
    public Booking saveBooking(Booking booking) {
        return repo.save(booking);
    }

    /**
     * True if no existing booking for this car overlaps the requested date range.
     * Two ranges overlap when each starts before the other ends.
     */
    public boolean isCarAvailable(Integer carId, LocalDate pickupDate, LocalDate returnDate) {
        if (carId == null) return true;

        List<Booking> existing = repo.findByCarId(carId);
        for (Booking b : existing) {
            boolean overlaps = pickupDate.isBefore(b.getReturnDate()) && b.getPickupDate().isBefore(returnDate);
            if (overlaps) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks availability and saves atomically per-car, so two people
     * booking the same car at (nearly) the same instant can't both succeed.
     * Returns null if the car is no longer available for those dates.
     */
    public Booking bookCarIfAvailable(Booking booking) {
        Integer carId = booking.getCarId();
        if (carId == null) {
            return repo.save(booking);
        }

        Object lock = carLocks.computeIfAbsent(carId, k -> new Object());
        synchronized (lock) {
            if (!isCarAvailable(carId, booking.getPickupDate(), booking.getReturnDate())) {
                return null;
            }
            return repo.save(booking);
        }
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return repo.findAll();
    }

    // Get booking by ID
    public Booking getBookingById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    // Delete booking
    public void deleteBooking(int id) {
        repo.deleteById(id);
    }

    // Update booking
    public Booking updateBooking(int id, Booking updatedBooking) {
        Booking existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existing.setName(updatedBooking.getName());
        existing.setPhone(updatedBooking.getPhone());
        existing.setPickupLocation(updatedBooking.getPickupLocation());
        existing.setPickupDate(updatedBooking.getPickupDate());
        existing.setReturnDate(updatedBooking.getReturnDate());
        existing.setPrice(updatedBooking.getPrice());

        return repo.save(existing);
    }

    // Search by phone
    public List<Booking> getBookingsByPhone(String phone) {
        return repo.findByPhone(phone);
    }

    // Search by name
    public List<Booking> getBookingsByName(String name) {
        return repo.findByName(name);
    }
    
    public List<Booking> getBookingsByUser(String email) {

        return repo.findByUserEmail(email);
    }
}