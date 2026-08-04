package com.project.Car_Rental;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping("/mybooking")
    public String mybooking(
            HttpSession session,
            org.springframework.ui.Model model) {

        // ✅ logged user
        UserData user =
                (UserData) session.getAttribute("loggedUser");

        // ✅ get only logged user bookings
        java.util.List<Booking> bookings =
                service.getBookingsByUser(
                        user.getUemail());

        // ✅ total bookings
        int totalBookings = bookings.size();

        // ✅ total spent
        double totalSpent = 0;

        for (Booking b : bookings) {
            totalSpent += b.getPrice();
        }

        // ✅ upcoming bookings
        int upcoming = 0;

        LocalDate today = LocalDate.now();

        for (Booking b : bookings) {

            if (b.getPickupDate()
                    .isAfter(today)) {

                upcoming++;
            }
        }

        // ✅ send data to HTML
        model.addAttribute("bookings", bookings);

        model.addAttribute(
                "totalBookings",
                totalBookings);

        model.addAttribute(
                "totalSpent",
                totalSpent);

        model.addAttribute(
                "upcoming",
                upcoming);

        return "mybooking";
    }
    
    @PostMapping("/booking")
    public String saveBooking(

            HttpSession session,

            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("pickupDate") String pickupDate,
            @RequestParam("returnDate") String returnDate,
            @RequestParam("pickupLocation") String pickupLocation,
            @RequestParam("price") double price) {

        Booking booking = new Booking();

        booking.setName(name);

        booking.setPhone(phone);

        // ✅ get logged-in user
        UserData user =
                (UserData) session.getAttribute("loggedUser");

        // ✅ save user email
        booking.setUserEmail(user.getUemail());

        // Convert String → LocalDate
        booking.setPickupDate(LocalDate.parse(pickupDate));

        booking.setReturnDate(LocalDate.parse(returnDate));

        booking.setPickupLocation(pickupLocation);

        booking.setPrice(price);

        service.saveBooking(booking);

        return "redirect:/mybooking?success";
    }
    
    @GetMapping("/api/bookings")
    @ResponseBody
    public org.springframework.http.ResponseEntity<?> getBookings(
            HttpSession session) {

        // ✅ get logged-in user
        UserData user =
                (UserData) session.getAttribute("loggedUser");

        if (user == null) {
            return org.springframework.http.ResponseEntity
                    .status(401)
                    .body(java.util.Map.of("error", "Not signed in."));
        }

        // ✅ return only this user's bookings
        return org.springframework.http.ResponseEntity.ok(
                service.getBookingsByUser(user.getUemail())
        );
    }

    // JSON booking creation for the React frontend
    public static class BookingRequest {
        public String name;
        public String phone;
        public String pickupDate;
        public String returnDate;
        public String pickupLocation;
        public double price;
        public Integer carId;
        public String carName;
    }

    @GetMapping("/api/cars/{carId}/availability")
    @ResponseBody
    public org.springframework.http.ResponseEntity<?> checkAvailability(
            @PathVariable Integer carId,
            @RequestParam String pickupDate,
            @RequestParam String returnDate) {

        boolean available = service.isCarAvailable(carId, LocalDate.parse(pickupDate), LocalDate.parse(returnDate));

        return org.springframework.http.ResponseEntity.ok(java.util.Map.of("available", available));
    }

    @PostMapping("/api/bookings")
    @ResponseBody
    public org.springframework.http.ResponseEntity<?> createBookingJson(
            @RequestBody BookingRequest req,
            HttpSession session) {

        UserData user = (UserData) session.getAttribute("loggedUser");

        if (user == null) {
            return org.springframework.http.ResponseEntity
                    .status(401)
                    .body(java.util.Map.of("error", "Please sign in to book a car."));
        }

        Booking booking = new Booking();
        booking.setName(req.name);
        booking.setPhone(req.phone);
        booking.setUserEmail(user.getUemail());
        booking.setPickupDate(LocalDate.parse(req.pickupDate));
        booking.setReturnDate(LocalDate.parse(req.returnDate));
        booking.setPickupLocation(req.pickupLocation);
        booking.setPrice(req.price);
        booking.setCarId(req.carId);
        booking.setCarName(req.carName);

        Booking saved = service.bookCarIfAvailable(booking);

        if (saved == null) {
            return org.springframework.http.ResponseEntity
                    .status(409)
                    .body(java.util.Map.of("error",
                            "Sorry, " + (req.carName != null ? req.carName : "this car")
                                    + " is already booked for the selected dates. Please choose different dates or another car."));
        }

        return org.springframework.http.ResponseEntity.ok(saved);
    }

    @DeleteMapping("/api/bookings/{id}")
    @ResponseBody
    public org.springframework.http.ResponseEntity<?> cancelBooking(
            @PathVariable int id,
            HttpSession session) {

        UserData user = (UserData) session.getAttribute("loggedUser");

        if (user == null) {
            return org.springframework.http.ResponseEntity
                    .status(401)
                    .body(java.util.Map.of("error", "Please sign in."));
        }

        Booking booking;
        try {
            booking = service.getBookingById(id);
        } catch (RuntimeException ex) {
            return org.springframework.http.ResponseEntity
                    .status(404)
                    .body(java.util.Map.of("error", "Booking not found."));
        }

        if (!booking.getUserEmail().equals(user.getUemail())) {
            return org.springframework.http.ResponseEntity
                    .status(403)
                    .body(java.util.Map.of("error", "You can only cancel your own bookings."));
        }

        service.deleteBooking(id);

        return org.springframework.http.ResponseEntity.ok(java.util.Map.of("message", "Booking cancelled"));
    }



    @GetMapping("/api/admin/stats")
    @ResponseBody
    public java.util.Map<String, Object> adminStats() {

        java.util.List<Booking> bookings =
                service.getAllBookings();

        int totalBookings = bookings.size();

        double totalRevenue = 0;

        for (Booking b : bookings) {

            totalRevenue += b.getPrice();
        }

        // Fleet size (matches the catalog in CarApiController)
        int totalCars = 48;

        int currentlyBooked = bookings.size();

        int availableCars =
                totalCars - currentlyBooked;

        java.util.Map<String, Object> map =
                new java.util.HashMap<>();

        map.put("revenue", totalRevenue);

        map.put("bookings", totalBookings);

        map.put("available", availableCars);

        map.put("booked", currentlyBooked);

        map.put("fleet", totalCars);

        return map;
    }
    
    
    @GetMapping("/api/admin/bookings")
    @ResponseBody
    public java.util.List<Booking> getAdminBookings() {

        return service.getAllBookings();
    }

   
}