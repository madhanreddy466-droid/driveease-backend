package com.project.Car_Rental;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private SignupService service;

    public static class SignupRequest {
        public String fname;
        public String lname;
        public String email;
        public String password;
        public String phone;
    }

    public static class SigninRequest {
        public String email;
        public String password;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req, HttpSession session) {

        if (req.email == null || req.email.isBlank() || req.password == null || req.password.length() < 8) {
            return ResponseEntity.badRequest().body(error("Please provide a valid email and a password of at least 8 characters."));
        }

        if (service.findByEmail(req.email) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error("An account with this email already exists."));
        }

        UserData user = new UserData();
        user.setUfname(req.fname);
        user.setUlname(req.lname);
        user.setUemail(req.email);
        user.setPassword(req.password);
        user.setPhonenumber(req.phone);

        service.saveUser(user);

        // auto sign-in after signup
        session.setAttribute("loggedUser", user);

        return ResponseEntity.ok(toPublicUser(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest req, HttpSession session) {

        UserData user = service.login(req.email, req.password);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error("Invalid email or password."));
        }

        session.setAttribute("loggedUser", user);

        return ResponseEntity.ok(toPublicUser(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {

        UserData user = (UserData) session.getAttribute("loggedUser");

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error("Not signed in."));
        }

        return ResponseEntity.ok(toPublicUser(user));
    }

    private Map<String, Object> toPublicUser(UserData user) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getUemail());
        map.put("fname", user.getUfname());
        map.put("lname", user.getUlname());
        map.put("phone", user.getPhonenumber());
        // never expose the password
        return map;
    }

    private Map<String, String> error(String message) {
        return Map.of("error", message);
    }
}
