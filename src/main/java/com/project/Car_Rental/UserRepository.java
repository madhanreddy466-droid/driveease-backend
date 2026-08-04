package com.project.Car_Rental;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, String> {

    // ✅ login check

    // ✅ check email exists
    UserData findByUemail(String uemail);

	UserData findByUemailAndPassword(String email, String password);
}