package com.project.Car_Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository repo;

    // ✅ Signup
    public UserData saveUser(UserData user) {
        return repo.save(user);
    }

    // ✅ Login
    public UserData login(String email, String password) {
        return repo.findByUemailAndPassword(email, password);
    }

    // ✅ Check email exists (FIXED)
    public UserData findByEmail(String email) {
        return repo.findByUemail(email);
    }
}