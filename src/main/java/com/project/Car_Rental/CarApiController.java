package com.project.Car_Rental;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Fleet catalog. There's no `cars` table in the DB (the original app kept the
 * fleet as a static list), so this simply exposes that same catalog as JSON
 * for the React frontend to consume.
 */
@RestController
@RequestMapping("/api/cars")
public class CarApiController {

    private static final List<Car> CARS = buildCatalog();

    @GetMapping
    public List<Car> getCars() {
        return CARS;
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return CARS.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private static List<Car> buildCatalog() {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1, "Ferrari 488 GTB", "2023 • V8 Turbo", "Sport", "🏎️", "https://images.unsplash.com/photo-1592198084033-aade902d1aae?w=600&h=280&fit=crop&q=80", 2, "Petrol", "Auto", 8500));
        list.add(new Car(2, "Mercedes S-Class", "2024 • AMG Line", "Luxury", "🚘", "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=600&h=280&fit=crop&q=80", 5, "Diesel", "Auto", 5500));
        list.add(new Car(3, "Porsche Cayenne", "2023 • Turbo S", "SUV", "🚙", "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 6500));
        list.add(new Car(4, "BMW M4 Competition", "2023 • Twin Turbo", "Sport", "🚗", "https://images.unsplash.com/photo-1555215695-3004980ad54e?w=600&h=280&fit=crop&q=80", 4, "Petrol", "Manual", 7200));
        list.add(new Car(5, "Tesla Model S", "2024 • Plaid", "Electric", "⚡", "https://images.unsplash.com/photo-1560958089-b8a1929cea89?w=600&h=280&fit=crop&q=80", 5, "Electric", "Auto", 5000));
        list.add(new Car(6, "Range Rover Sport", "2024 • HSE", "SUV", "🏔️", "https://images.unsplash.com/photo-1606016159991-dfe4f2746ad5?w=600&h=280&fit=crop&q=80", 7, "Diesel", "Auto", 7800));
        list.add(new Car(7, "Audi A8 L", "2023 • TDI Quattro", "Luxury", "🎩", "https://images.unsplash.com/photo-1606152421802-db97b9c7a11b?w=600&h=280&fit=crop&q=80", 5, "Diesel", "Auto", 4800));
        list.add(new Car(8, "Lamborghini Urus", "2024 • Performante", "SUV", "🦁", "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 12000));
        list.add(new Car(9, "Hyundai Creta", "2024 • SX Turbo", "SUV", "🌟", "https://images.unsplash.com/photo-1609521263047-f8f205293f24?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 1800));
        list.add(new Car(10, "Toyota Camry", "2024 • Hybrid", "Sedan", "🍃", "https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=600&h=280&fit=crop&q=80", 5, "Hybrid", "Auto", 2400));
        list.add(new Car(11, "Tata Nexon EV", "2024 • Max Long Range", "Electric", "🔋", "https://images.pexels.com/photos/9031387/pexels-photo-9031387.jpeg", 5, "Electric", "Auto", 1600));
        list.add(new Car(12, "Honda City", "2024 • ZX CVT", "Sedan", "🚕", "https://images.unsplash.com/photo-1617469767053-d3b523a0b982?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 1400));
        list.add(new Car(13, "Rolls-Royce Ghost", "2024 • Extended Wheelbase", "Luxury", "👑", "https://images.unsplash.com/photo-1631295868223-63265b40d9e4?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 28000));
        list.add(new Car(14, "Bentley Continental GT", "2023 • W12 Speed", "Luxury", "🦅", "https://images.unsplash.com/photo-1580414057403-c5f451f30e1c?w=600&h=280&fit=crop&q=80", 4, "Petrol", "Auto", 22000));
        list.add(new Car(15, "McLaren 720S", "2023 • Spider", "Sport", "🦋", "https://images.pexels.com/photos/29918150/pexels-photo-29918150.jpeg", 2, "Petrol", "Auto", 16500));
        list.add(new Car(16, "Bugatti Chiron", "2024 • Sport", "Sport", "🐝", "https://images.pexels.com/photos/12351384/pexels-photo-12351384.jpeg", 2, "Petrol", "Auto", 45000));
        list.add(new Car(17, "Aston Martin DBX", "2023 • 707", "SUV", "🏰", "https://images.unsplash.com/photo-1580273916550-e323be2ae537?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 14000));
        list.add(new Car(18, "Porsche 911 GT3", "2024 • RS Touring", "Sport", "🐎", "https://images.unsplash.com/photo-1614162692292-7ac56d7f7f1e?w=600&h=280&fit=crop&q=80", 2, "Petrol", "Manual", 13500));
        list.add(new Car(19, "Tesla Model X", "2024 • Plaid AWD", "Electric", "🛸", "https://images.pexels.com/photos/28576666/pexels-photo-28576666.jpeg", 7, "Electric", "Auto", 6200));
        list.add(new Car(20, "Rivian R1T", "2024 • Adventure", "SUV", "⛺", "https://images.pexels.com/photos/35557255/pexels-photo-35557255.jpeg", 5, "Electric", "Auto", 5800));
        list.add(new Car(21, "Jeep Wrangler", "2024 • Rubicon 4xe", "SUV", "🏕️", "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=600&h=280&fit=crop&q=80", 5, "Hybrid", "Auto", 4200));
        list.add(new Car(22, "Ford Mustang GT500", "2023 • Shelby", "Sport", "🐴", "https://images.unsplash.com/photo-1547744152-14d985cb937f?w=600&h=280&fit=crop&q=80", 4, "Petrol", "Manual", 9800));
        list.add(new Car(23, "Volkswagen Tiguan", "2024 • R-Line", "SUV", "🐯", "https://images.unsplash.com/photo-1504215680853-026ed2a45def?w=600&h=280&fit=crop&q=80", 7, "Diesel", "Auto", 3200));
        list.add(new Car(24, "Kia EV6 GT", "2024 • AWD", "Electric", "⚡", "https://images.unsplash.com/photo-1629897048514-3dd7414fe72a?w=600&h=280&fit=crop&q=80", 5, "Electric", "Auto", 3800));
        list.add(new Car(25, "Maserati GranTurismo", "2024 • Folgore", "Luxury", "🔱", "https://images.unsplash.com/photo-1563720223185-11003d516935?w=600&h=280&fit=crop&q=80", 4, "Electric", "Auto", 18000));
        list.add(new Car(26, "Chevrolet Corvette", "2024 • Z06 Convertible", "Sport", "🌊", "https://images.unsplash.com/photo-1600712242805-5f78671b24da?w=600&h=280&fit=crop&q=80", 2, "Petrol", "Auto", 11000));
        list.add(new Car(27, "Land Rover Defender", "2024 • 130 X", "SUV", "🦁", "https://images.unsplash.com/photo-1616422285623-13ff0162193c?w=600&h=280&fit=crop&q=80", 8, "Diesel", "Auto", 8200));
        list.add(new Car(28, "Toyota Land Cruiser", "2024 • GR Sport", "SUV", "🏔️", "https://images.pexels.com/photos/18029607/pexels-photo-18029607.jpeg", 7, "Diesel", "Auto", 6800));
        list.add(new Car(29, "BMW i7", "2024 • M70 xDrive", "Electric", "🌀", "https://images.pexels.com/photos/12365935/pexels-photo-12365935.jpeg", 5, "Electric", "Auto", 9500));
        list.add(new Car(30, "Mercedes EQS AMG", "2024 • 53 4MATIC+", "Electric", "💎", "https://images.pexels.com/photos/29779238/pexels-photo-29779238.jpeg", 5, "Electric", "Auto", 8800));
        list.add(new Car(31, "Maruti Suzuki Baleno", "2024 • Alpha CVT", "Sedan", "🌸", "https://images.unsplash.com/photo-1609521263047-f8f205293f24?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 1100));
        list.add(new Car(32, "Mahindra Thar", "2024 • LX 4WD Hard Top", "SUV", "🌵", "https://images.pexels.com/photos/34940284/pexels-photo-34940284.jpeg", 4, "Diesel", "Manual", 2800));
        list.add(new Car(33, "Lamborghini Huracan", "2024 • STO", "Sport", "🔥", "https://images.pexels.com/photos/29884349/pexels-photo-29884349.jpeg", 2, "Petrol", "Auto", 19000));
        list.add(new Car(34, "Jaguar F-Type", "2024 • R Coupe", "Sport", "🐆", "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?w=600&h=280&fit=crop&q=80", 2, "Petrol", "Auto", 10500));
        list.add(new Car(35, "Lexus LX 600", "2024 • Ultra Luxury", "SUV", "🍁", "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=600&h=280&fit=crop&q=80", 7, "Petrol", "Auto", 9200));
        list.add(new Car(36, "Audi e-tron GT", "2024 • RS Quattro", "Electric", "🌩️", "https://images.pexels.com/photos/9452202/pexels-photo-9452202.jpeg", 5, "Electric", "Auto", 7400));
        list.add(new Car(37, "Volvo XC90 Recharge", "2024 • Ultimate", "SUV", "❄️", "https://images.unsplash.com/photo-1612544448445-b8232cff3b6c?w=600&h=280&fit=crop&q=80", 7, "Hybrid", "Auto", 5200));
        list.add(new Car(38, "Genesis G90", "2024 • 3.5T AWD", "Luxury", "🌙", "https://images.pexels.com/photos/12506835/pexels-photo-12506835.jpeg", 5, "Petrol", "Auto", 6800));
        list.add(new Car(39, "Skoda Octavia RS", "2024 • RS 245", "Sedan", "🎯", "https://images.unsplash.com/photo-1541899481282-d53bffe3c35d?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 2200));
        list.add(new Car(40, "Porsche Taycan", "2024 • Turbo S Cross Turismo", "Electric", "⚡", "https://images.pexels.com/photos/35849577/pexels-photo-35849577.jpeg", 5, "Electric", "Auto", 11500));
        list.add(new Car(41, "Ferrari Roma", "2024 • V8 GT", "Sport", "🌹", "https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=600&h=280&fit=crop&q=80", 2, "Petrol", "Auto", 21000));
        list.add(new Car(42, "Rolls-Royce Cullinan", "2024 • Black Badge", "SUV", "🖤", "https://images.unsplash.com/photo-1632245889029-e406faaa34cd?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 35000));
        list.add(new Car(43, "Hyundai Ioniq 6", "2024 • AWD Premium", "Electric", "🔵", "https://images.pexels.com/photos/33125984/pexels-photo-33125984.jpeg", 5, "Electric", "Auto", 3200));
        list.add(new Car(44, "Toyota GR Supra", "2024 • A90 3.0T", "Sport", "🟡", "https://images.pexels.com/photos/10978972/pexels-photo-10978972.jpeg", 2, "Petrol", "Auto", 7800));
        list.add(new Car(45, "Audi Q8 e-tron", "2024 • S-Line Quattro", "SUV", "🔷", "https://images.unsplash.com/photo-1563720223185-11003d516935?w=600&h=280&fit=crop&q=80", 5, "Electric", "Auto", 6400));
        list.add(new Car(46, "Mercedes AMG GT", "2024 • 63 S 4-Door", "Sport", "⭐", "https://images.unsplash.com/photo-1609521263047-f8f205293f24?w=600&h=280&fit=crop&q=80", 4, "Petrol", "Auto", 14500));
        list.add(new Car(47, "Maruti Swift", "2024 • ZXi Plus AMT", "Sedan", "🌈", "https://images.unsplash.com/photo-1541899481282-d53bffe3c35d?w=600&h=280&fit=crop&q=80", 5, "Petrol", "Auto", 900));
        list.add(new Car(48, "Lamborghini Revuelto", "2024 • V12 Hybrid", "Sport", "🌋", "https://images.unsplash.com/photo-1526726538690-5cbf956ae2fd?w=600&h=280&fit=crop&q=80", 2, "Hybrid", "Auto", 55000));        return list;
    }
}
