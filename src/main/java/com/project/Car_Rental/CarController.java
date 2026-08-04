package com.project.Car_Rental;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class CarController {

    @Autowired
    private SignupService service;
    
    @Autowired
    private BookingService bookingService;

    // pages
    @GetMapping("/home")
    public String home() {
        return "home";   // ❌ remove .
    }

    @GetMapping("/home2")
    public String home2() {
        return "home2";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/cars")
    public String cars() {
        return "cars";
    }

    @GetMapping("/availability")
    public String availability() {
        return "availability";
    }

    @GetMapping("/mybookings")
    public String mybooking(Model model) {

        model.addAttribute(
                "bookings",
                bookingService.getAllBookings()
        );

        return "mybooking";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    
 

    // ✅ SIGNUP (WITH VERIFICATION)
    @PostMapping("/signup")
    public String saveUser(
    		
            @RequestParam String fname,
            @RequestParam String lname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone
    ) {

        // 🔥 check email already exists
        if (service.findByEmail(email) != null) {
            return "redirect:/signup?error";  // ❌ duplicate email
        }

        UserData user = new UserData();
        user.setUfname(fname);
        user.setUlname(lname);
        user.setUemail(email);
        user.setPassword(password);
        user.setPhonenumber(phone);

        service.saveUser(user);

        return "redirect:/signin?success";  // ✅ success
    }

    // ✅ LOGIN (VERIFICATION)
    @PostMapping("/signin")
    public String loginUser(

            @RequestParam String email,
            @RequestParam String password,

            HttpSession session
    ) {

        UserData user = service.login(email, password);

        if (user != null) {

            // ✅ SAVE USER IN SESSION
            session.setAttribute("loggedUser", user);

            return "redirect:/home2";

        } else {

            return "redirect:/signin?error";
        }
    }
}