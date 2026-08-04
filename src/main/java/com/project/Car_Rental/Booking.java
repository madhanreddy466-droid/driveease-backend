package com.project.Car_Rental;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;          // Customer Name
    private String phone;         // Phone Number
    private String pickupLocation;// Location
    private LocalDate pickupDate; // Pickup Date
    private LocalDate returnDate; // Return Date
    private double price;         // Total Price
    private Integer carId;        // Reference into the fleet catalog
    private String carName;       // Denormalized for easy display

    // ===== Getters & Setters =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getPrice() {
        return price;
    }

	public void setPrice(double price) {
        this.price = price;
    }
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(int id, String name, String phone, String pickupLocation, LocalDate pickupDate, LocalDate returnDate,
			double price,String userEmail) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.pickupLocation = pickupLocation;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.price = price;
		this.userEmail = userEmail;
	}
	
	private String userEmail;
	
	public String getUserEmail() {
	    return userEmail;
	}

	public void setUserEmail(String userEmail) {
	    this.userEmail = userEmail;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
}


