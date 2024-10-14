package cabbieManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

import utils.PassengerInfoGenerator;

@XmlRootElement(name="passenger")
public class Passenger extends Person{
    private String passengerId;
    private String email;
    private String name;
    private String phone;

    public Passenger() {
    }

    /**
     * Registers a passenger by generating random information.
     * This method assigns a random email, name, phone number, and user ID to the passenger.
     * 
     */
    @Override
    public void register() {

        PassengerInfoGenerator pass = new PassengerInfoGenerator();
        this.email = pass.getEmail();
        this.name = pass.getName();
        this.phone = pass.getPhone();
        this.passengerId = pass.getPassengerId();
        System.out.println("Pessoa passageira " + this.passengerId + " (" + this.name + ") criada com sucesso");
    
    }

    /**
     * Updates a field of the passenger.
     * 
     * @param field The field to be updated.
     * @param newValue The new value for the field.
     * 
     * The valid fields are:
     * <ul>
     * <li>name</li>
     * <li>email</li>
     * <li>phone</li>
     * <li>passengerId</li>
     * </ul>
     * 
     * If the field is not valid, a message is printed and the field is not updated.
     */
    @Override
    public void update(String field, String newValue){

        boolean validField = true;

        switch (field) {
            case "name":
                this.name = newValue;
                break;
            case "email":
                this.email = newValue;
                break;
            case "phone":
                this.phone = newValue;
                break;
            case "passengerId":
                this.passengerId = newValue;
                break;
            default:
                System.out.println("Campo inválido");
                validField = false;       
        }

        if (validField) {
            System.out.println("Campo " + field + " atualizado com sucesso!");
        }

        return;
    
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the ID of the passenger.
     * 
     * @return the ID of the passenger (a UUID)
     */
    @XmlElement(name = "passengerId")
    public String getPassengerId() {
        return this.passengerId;
    }
    public void setPassengerId(String passengerId){
        this.passengerId = passengerId;
    }

    /**
     * Returns a string representation of the object.
     * 
     * The format is: "email name phone passengerId"
     * 
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Passenger: " + this.email + this.name + this.phone + this.passengerId;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        
        Passenger pas = (Passenger) o;
        return Objects.equal(this.passengerId, pas.getPassengerId());
    }
}
