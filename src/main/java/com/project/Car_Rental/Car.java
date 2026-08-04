package com.project.Car_Rental;

public class Car {

    private int id;
    private String name;
    private String sub;
    private String cls;      // vehicle class: Sport, Luxury, SUV, Sedan, Electric...
    private String emoji;
    private String img;
    private int seats;
    private String fuel;
    private String trans;
    private double daily;    // daily rate in INR

    public Car() {
    }

    public Car(int id, String name, String sub, String cls, String emoji, String img,
               int seats, String fuel, String trans, double daily) {
        this.id = id;
        this.name = name;
        this.sub = sub;
        this.cls = cls;
        this.emoji = emoji;
        this.img = img;
        this.seats = seats;
        this.fuel = fuel;
        this.trans = trans;
        this.daily = daily;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSub() { return sub; }
    public void setSub(String sub) { this.sub = sub; }

    public String getCls() { return cls; }
    public void setCls(String cls) { this.cls = cls; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public String getFuel() { return fuel; }
    public void setFuel(String fuel) { this.fuel = fuel; }

    public String getTrans() { return trans; }
    public void setTrans(String trans) { this.trans = trans; }

    public double getDaily() { return daily; }
    public void setDaily(double daily) { this.daily = daily; }
}
