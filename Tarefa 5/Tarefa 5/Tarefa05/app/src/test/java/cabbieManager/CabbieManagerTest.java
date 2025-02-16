package cabbieManager;

import exceptions.InvalidPaymentMethodException;
// Import da exceção personalizada
import exceptions.InvalidRideDistanceException;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringWriter;
import java.time.LocalDateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.Assertions;

public class CabbieManagerTest {

    private Ride ride;

    @Test
    public void testCalculateDistance_SameLocations() throws InvalidRideDistanceException {
        // Arrange
        ride = new Ride("testPassengerId");
        ride.setPickupLocation(Location.AEROPORTO);
        ride.setDropLocation(Location.AEROPORTO);

        // Act
        float distance = ride.calculateDistance();

        // Assert
        assertEquals(0, distance, 0);
    }

    @Test
    public void testCalculateDistance_DifferentLocations1() throws InvalidRideDistanceException {
        // Arrange
        ride = new Ride("testPassengerId");
        ride.setPickupLocation(Location.AEROPORTO);
        ride.setDropLocation(Location.ESTADIO);

        // Act
        float distance = ride.calculateDistance();

        // Assert
        assertEquals(18.38f, distance, 0);
    }

    @Test
    public void testCalculateDistance_DifferentLocations2() throws InvalidRideDistanceException {
        // Arrange
        ride = new Ride("testPassengerId");
        ride.setPickupLocation(Location.HOSPITAL);
        ride.setDropLocation(Location.ESTACAO_DE_TREM);

        // Act
        float distance = ride.calculateDistance();

        // Assert
        assertEquals(4.24f, distance, 0);
    }

    @Test
    public void testDiurnalRideWithinRange()
            throws NullPointerException, InvalidRideDistanceException, InvalidPaymentMethodException {
        RidePayment ridePayment = new RidePayment("rideId", LocalDateTime.of(2022, 1, 1, 10, 0), 5.0f, "Dinheiro");
        Assertions.assertEquals(15.00f, ridePayment.calculateValue(), 0);
    }

    @Test
    public void testDiurnalRideWithinRange2()
            throws NullPointerException, InvalidRideDistanceException, InvalidPaymentMethodException {
        RidePayment ridePayment = new RidePayment("rideId", LocalDateTime.of(2022, 1, 1, 10, 0), 18.0f,
                "Cartão de Débito");
        Assertions.assertEquals(78f, ridePayment.calculateValue(), 0);
    }

    @Test
    public void testNocturnalRideWithinRange()
            throws NullPointerException, InvalidRideDistanceException, InvalidPaymentMethodException {
        RidePayment ridePayment = new RidePayment("rideId", LocalDateTime.of(2022, 1, 1, 20, 0), 5.0f, "Dinheiro");
        Assertions.assertEquals(18.50f, ridePayment.calculateValue(), 0);
    }

    @Test
    public void testCabbieMarshaller() {
        Cabbie cabbie = new Cabbie();
        cabbie.register();
        Assertions.assertDoesNotThrow(() -> {
            JAXBContext context = JAXBContext.newInstance(Cabbie.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(cabbie, sw);
        });
    }

    @Test
    public void testPassengerMarshaller() {
        Passenger pas = new Passenger();
        pas.register();
        Assertions.assertDoesNotThrow(() -> {
            JAXBContext context = JAXBContext.newInstance(Passenger.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(pas, sw);
        });
    }

    @Test
    public void testVehicleMarshaller() {
        Vehicle vehicle = new Vehicle();
        vehicle.registerVehicle();
        Assertions.assertDoesNotThrow(() -> {
            JAXBContext context = JAXBContext.newInstance(Vehicle.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(vehicle, sw);
        });
    }
}
