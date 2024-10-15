package databaseManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import cabbieManager.Cabbie;
import cabbieManager.Passenger;
import cabbieManager.Ride;
import cabbieManager.RidePayment;
import cabbieManager.Vehicle;
// import exceptions.CabbieManagerException;
import exceptions.UnsupportedObjectTypeException;

@XmlRootElement(name = "database")
public class Database {
    private List<Cabbie> cabbies = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();
    private List<RidePayment> payments = new ArrayList<>();

    private final File file = new File("Tarefa05/app/data/database.xml");

    public Database() {
    }

    public Database(boolean load) {
        if (load) {
            this.load();
        }
    }

    @XmlElementWrapper(name = "passengers")
    @XmlElement(name = "passenger")
    public List<Passenger> getPassengers() {
        return this.passengers;
    }

    @XmlElementWrapper(name = "cabbies")
    @XmlElement(name = "cabbie")
    public List<Cabbie> getCabbies() {
        return this.cabbies;
    }

    @XmlElementWrapper(name = "rides")
    @XmlElement(name = "ride")
    public List<Ride> getRides() {
        return this.rides;
    }

    @XmlElementWrapper(name = "vehicles")
    @XmlElement(name = "vehicle")
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    @XmlElementWrapper(name = "payments")
    @XmlElement(name = "payment")
    public List<RidePayment> getPayments() {
        return this.payments;
    }

    public void insert(Object object) throws UnsupportedObjectTypeException {

            if (object instanceof Cabbie) {
                this.cabbies.add((Cabbie) object);
            } else if (object instanceof Passenger) {
                this.passengers.add((Passenger) object);
            } else if (object instanceof Vehicle) {
                this.vehicles.add((Vehicle) object);
            } else if (object instanceof Ride) {
                this.rides.add((Ride) object);
            } else if (object instanceof RidePayment) {
                this.payments.add((RidePayment) object);
            } else {
                throw new UnsupportedObjectTypeException(
                        "Trying to insert unsupported object type for database insertion");
            }

            this.save();
        
    }

    private <T> void update(T newItem, List<T> data) {
        for (int i = 0; i < data.size(); i++) {
            Object item = data.get(i);

            if (item.equals(newItem)) {
                data.set(i, newItem);
            }
        }
    }

    public void update(Object object) {
        if (object instanceof Cabbie) {
            this.update((Cabbie) object, this.cabbies);
        } else if (object instanceof Passenger) {
            this.update((Passenger) object, this.passengers);
        } else if (object instanceof Vehicle) {
            this.update((Vehicle) object, this.vehicles);
        } else if (object instanceof Ride) {
            this.update((Ride) object, this.rides);
        } else if (object instanceof RidePayment) {
            this.update((RidePayment) object, this.payments);
        } else {
            return;
        }
        this.save();
    }

    private void save() {
        try {
            JAXBContext context = JAXBContext.newInstance(Database.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputStream = new FileOutputStream(this.file);
            marshaller.marshal(this, outputStream);
            outputStream.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        if (file.exists()) {
            try {
                JAXBContext context = JAXBContext.newInstance(Database.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                InputStream inputStream = new FileInputStream(this.file);
                Database db = (Database) unmarshaller.unmarshal(inputStream);
                inputStream.close();

                this.cabbies = db.getCabbies();
                this.passengers = db.getPassengers();
                this.rides = db.getRides();
                this.vehicles = db.getVehicles();
                this.payments = db.getPayments();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}