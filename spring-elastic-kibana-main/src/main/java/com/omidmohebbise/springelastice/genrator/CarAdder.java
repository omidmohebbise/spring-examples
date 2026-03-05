package com.omidmohebbise.springelastice.genrator;

import com.omidmohebbise.springelastice.model.CarModel;
import com.omidmohebbise.springelastice.service.CarSearchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CarAdder {
    private static final String[] BRANDS = {
        "Tesla", "Toyota", "Ford", "BMW", "Audi", "Honda", "Chevrolet", "Hyundai", "Kia", "Volkswagen",
        "Nissan", "Mazda", "Subaru", "Lexus", "Infiniti", "Acura", "Mitsubishi", "Suzuki", "Daihatsu", "Isuzu",
        "Jeep", "Dodge", "Chrysler", "Ram", "Buick", "Cadillac", "GMC", "Lincoln", "Mercury", "Pontiac",
        "Saturn", "Hummer", "Oldsmobile", "Plymouth", "Fiat", "Alfa Romeo", "Lancia", "Ferrari", "Maserati", "Lamborghini",
        "Peugeot", "Renault", "Citroen", "DS", "Opel", "Vauxhall", "SEAT", "Skoda", "Saab", "Volvo",
        "Mini", "Land Rover", "Jaguar", "Bentley", "Rolls-Royce", "Aston Martin", "McLaren", "Bugatti", "Pagani", "Koenigsegg",
        "Smart", "Dacia", "Tata", "Mahindra", "Proton", "Perodua", "Geely", "Chery", "BYD", "Great Wall",
        "FAW", "Dongfeng", "BAIC", "Changan", "SAIC", "JAC", "Haval", "Lifan", "Zotye", "NIO",
        "Lucid", "Rivian", "Polestar", "Genesis", "SsangYong", "Daewoo", "Scion", "Holden", "HSV", "UAZ",
        "GAZ", "Moskvitch", "ZAZ", "Tatra", "Wuling", "Baojun", "Hongqi", "VinFast", "Fisker", "Aptera"
    };
    private static final String[] MODELS = {
        "Model S", "Corolla", "F-150", "3 Series", "A4", "Civic", "Impala", "Elantra", "Soul", "Golf",
        "Camry", "Accord", "Mustang", "Explorer", "Escape", "Focus", "Altima", "Sentra", "Rogue", "Leaf",
        "CX-5", "CX-9", "Mazda3", "Mazda6", "Outback", "Forester", "Legacy", "Impreza", "BRZ", "RX",
        "ES", "IS", "GS", "Q50", "QX60", "TLX", "MDX", "RDX", "ILX", "Eclipse",
        "Lancer", "Swift", "Vitara", "Jimny", "Mira", "Terios", "Trooper", "Wrangler", "Cherokee", "Compass",
        "Durango", "Charger", "300", "Pacifica", "Enclave", "Encore", "Escalade", "Sierra", "Yukon", "Navigator",
        "Grand Marquis", "G8", "Aura", "H2", "Astra", "500", "Giulia", "Stelvio", "Delta", "Stratos",
        "California", "Quattroporte", "Aventador", "Huracan", "208", "308", "Clio", "Megane", "C4", "DS3",
        "Astra", "Insignia", "Corsa", "Mokka", "Astra GTC", "Ibiza", "Leon", "Octavia", "Superb", "900",
        "S60", "XC90", "Countryman", "Evoque", "F-Pace", "Continental", "Phantom", "DB11", "720S", "Chiron"
    };
    private static final int MIN_YEAR = 1990;
    private static final int MAX_YEAR = 2025;
    private static final double MIN_PRICE = 5000.0;
    private static final double MAX_PRICE = 150000.0;
    private static final int DATASET_SIZE = 1000;
    private static final Logger logger = Logger.getLogger(CarAdder.class.getName());
    private final Random random = new Random();
    private final CarSearchService carSearchService;

    public CarAdder(CarSearchService carSearchService) {
        this.carSearchService = carSearchService;
    }

    @Scheduled(fixedRate = 2000) // Add a new batch every 60 seconds
    public void addCars() {
        for (int i = 0; i < DATASET_SIZE; i++) {
            CarModel car = new CarModel();
            car.setId(UUID.randomUUID().toString());
            car.setModel(MODELS[random.nextInt(MODELS.length)]);
            car.setBrand(BRANDS[random.nextInt(BRANDS.length)]);
            car.setYear(random.nextInt(MAX_YEAR - MIN_YEAR + 1) + MIN_YEAR);
            car.setPrice(MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble());

            carSearchService.add(car);
        }
        logger.info("Generated " + DATASET_SIZE + " random cars in this batch.");
    }
}
