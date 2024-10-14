package utils;
import java.util.*;


public class VehicleInfoGenerator implements InfoGenerator{

    private String[] models = {"Toyota", "Honda", "Ford", "Chevrolet", "Nissan", "Hyundai", "Volkswagen", "Subaru", "Mazda", "Kia"};
    private String[] registrationNumbers = {"ABC123", "DEF456", "GHI789", "JKL012", "MNO345", "PQR678", "STU901", "VWX234", "YZA567", "BCD890"};

    private String vehicleId;
    private String registrationNumber;
    private String model;
    private int year;

    public VehicleInfoGenerator() {
        this.generateRandomInfo();
    }

    /**
     * Generates random information for a vehicle. This method assigns
     * random values to the fields model, registrationNumber, vehicleId and
     * year.
     * 
     */
    public void generateRandomInfo() {

        Random ran = new Random();
        int idx = ran.nextInt(10);

        this.model = models[idx];
        this.registrationNumber = registrationNumbers[idx];
        this.vehicleId = UUID.randomUUID().toString();
        this.year = ran.nextInt(34) + 1990;

        return;


    }

    /**
     * Gets the ID of the vehicle.
     * 
     * @return the ID of the vehicle (a UUID)
     */
    public String getVehicleId() {
        return this.vehicleId;
    }

    /**
     * Gets the registration number of the vehicle.
     * 
     * @return the registration number of the vehicle (a string of length 7, containing
     *         only uppercase letters and digits)
     */
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    /**
     * Gets the model of the vehicle.
     * 
     * @return the model of the vehicle (a string)
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Gets the year of the vehicle.
     * 
     * @return the year of the vehicle (an integer between 1990 and 2024)
     */
    public int getYear() {
        return this.year;
    }
}

