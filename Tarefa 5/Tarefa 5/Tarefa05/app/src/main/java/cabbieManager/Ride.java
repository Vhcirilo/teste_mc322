package cabbieManager;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.common.base.Objects;

import exceptions.InvalidLocationException;
import exceptions.InvalidRideDistanceException;
import exceptions.NullRideStartTimeException;
import utils.LocalDateTimeAdapter;

@XmlRootElement(name = "ride")
public class Ride {

    private String rideId;
    private String passengerId;
    private String cabbieId;
    private String vehicleId;
    private String status;

    private Location pickupLocation;
    private Location dropLocation;
    private LocalDateTime startTime;
    private float distance;

    // Construtor sem parâmetros
    public Ride() {
    }

    // Construtor com o ID de passageiro
    public Ride(String passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * Requests a ride by a passenger.
     * 
     * @param pickupLocation the location where the passenger wants to be picked up
     * @param dropLocation   the location where the passenger wants to be dropped
     *                       off
     * 
     *                       The ride status is set to "REQUESTED". The startTime is
     *                       set to the current time.
     * 
     *                       Lança InvalidLocationException se o local não for
     *                       encontrado. Lança NullRideStartTimeException se o
     *                       startTime for nulo.
     */
    public void requestRide(String pickupLocation, String dropLocation) throws InvalidLocationException, NullRideStartTimeException {
        this.rideId = UUID.randomUUID().toString();

        // Valida os locais de origem e destino
        this.pickupLocation = this.returnLocation(pickupLocation);
        this.dropLocation = this.returnLocation(dropLocation);

        if (this.pickupLocation == null) {
            throw new InvalidLocationException("Local de origem inválido: " + pickupLocation);
        }
        if (this.dropLocation == null) {
            throw new InvalidLocationException("Local de destino inválido: " + dropLocation);
        }

        this.startTime = LocalDateTime.now();
        if (this.startTime == null) {
            throw new NullRideStartTimeException("O horário de início da corrida não pode ser nulo.");
        }

        System.out.println("Corrida chamada por passageiro " + this.passengerId + " de " + pickupLocation + " para "
                + dropLocation);
        this.updateRideStatus("CHAMADA", null, null);

        // Tratando a exceção que pode ser lançada ao calcular a distância
        try {
            this.distance = this.calculateDistance(); // Calcula a distância
        } catch (InvalidRideDistanceException e) {
            System.err.println("Erro ao calcular a distância: " + e.getMessage());
            this.distance = 0; // Ou tome a ação necessária em caso de erro
        }
    }

    /**
     * Returns a Location given a location name.
     * 
     * @param locationName the name of the location
     * 
     *                     If the location is not found, returns null.
     * 
     * @return a Location object or null
     * 
     * @throws InvalidLocationException se a localização for inválida
     */
    private Location returnLocation(String locationName) throws InvalidLocationException {
        return Location.valueOfName(locationName);
    }

    /**
     * Calculates the distance between the pickup and drop locations.
     * 
     * The distance is calculated as the Euclidean distance between the two points.
     * 
     * @return the calculated distance.
     * 
     *         Lança InvalidRideDistanceException se a distância for zero ou
     *         negativa.
     */
    public float calculateDistance() throws InvalidRideDistanceException {
        int x_pickup = pickupLocation.getX();
        int y_pickup = pickupLocation.getY();

        int x_drop = dropLocation.getX();
        int y_drop = dropLocation.getY();

        float distance = (float) Math.sqrt(Math.pow(x_drop - x_pickup, 2) + Math.pow(y_drop - y_pickup, 2));
        distance = Math.round(distance * 100) / 100.0f;

        if (distance < 0) {
            throw new InvalidRideDistanceException("A distância da corrida não pode ser zero ou negativa.");
        }

        System.out.println("Distância calculada: " + distance);
        return distance;
    }

    /**
     * Atualiza o status da corrida.
     * 
     * Se o status for "ACEITA", armazena o ID do motorista e do veículo que
     * aceitou a corrida.
     * 
     * @param status    o novo status da corrida
     * @param cabbieId  o ID do motorista que aceitou a corrida, se status for
     *                  "ACEITA"
     * @param vehicleId o ID do veículo que aceitou a corrida, se status for
     *                  "ACEITA"
     */
    public void updateRideStatus(String status, String cabbieId, String vehicleId) {
        this.status = status;

        if ("ACEITA".equals(status)) {
            this.cabbieId = cabbieId;
            this.vehicleId = vehicleId;
            System.out.println("Corrida aceita por motorista " + this.cabbieId);
        } else {
            System.out.println("Status da corrida: " + this.status);
        }
    }

    public void completeRide() {
        System.out.println("Corrida finalizada");
    }

    @XmlElement(name = "pickupLocation")
    public Location getPickLocation() {
        return this.pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    @XmlElement(name = "dropLocation")
    public Location getDropLocation() {
        return this.dropLocation;
    }

    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    @XmlElement(name = "rideId")
    public String getRideId() {
        return this.rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @XmlElement(name = "startTime")
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @XmlElement(name = "rideDistance")
    public float getRideDistance() {
        return this.distance;
    }

    @XmlElement(name = "passengerId")
    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    @XmlElement(name = "cabbieId")
    public String getCabbieId() {
        return cabbieId;
    }

    public void setCabbieId(String cabbieId) {
        this.cabbieId = cabbieId;
    }

    @XmlElement(name = "vehicleId")
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @XmlElement(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "distance")
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        Ride ride = (Ride) o;
        return Objects.equal(this.rideId, ride.getRideId());
    }

    @Override
    public String toString() {
        return "Ride: " + this.rideId;
    }
}
