package utils;
import java.util.*;


public class CabbieInfoGenerator implements InfoGenerator{

    private String[] names = {"Tiago", "Sofia", "Felipe", "Isabela", "Rodrigo", "Carolina", "Henrique", "Fernanda", "Mateus", "Gabriela"};
    private String[] emails = {"tiago.silva@email.com", "sofia.almeida@email.com", "felipe.oliveira@email.com", "isabela.ferreira@email.com", "rodrigo.santos@email.com", "carolina.martins@email.com", "henrique.costa@email.com", "fernanda.lima@email.com", "mateus.pereira@email.com", "gabriela.souza@email.com"};
    private String[] phones = {"+55 13 91234-5678", "+55 27 92345-6789", "+55 85 93456-7890", "+55 47 94567-8901", "+55 62 95678-9012", "+55 98 96789-0123", "+55 95 97890-1234", "+55 67 98901-2345", "+55 84 99012-3456", "+55 12 90123-4567"};
    private String[] licenseNumbers = {"1A2bC3dE", "FgH4IjK5", "L6mNopQr", "StUvWxYz", "A7bC8dEf", "GhI9JkLm", "NoPqRsTu", "VwXyZ123", "456AbCdE", "FgH7IjKl"};

    private String name;
    private String email;
    private String phone;
    private String cabbieId;
    private float rate;
    private String licenseNumber;

    public CabbieInfoGenerator() {
        this.generateRandomInfo();
    }

    /**
     * Generates random information for the cabbie. This method assigns
     * random values to the fields name, email, phone, cabbieId, rate and
     * licenseNumber.
     * 
     */
    public void generateRandomInfo() {

        Random ran = new Random();
        int idx = ran.nextInt(10);
        String cabbieId = UUID.randomUUID().toString();
        float rate  = idx/2.0f;


        this.name = names[idx];
        this.email = emails[idx];
        this.phone = phones[idx];
        this.cabbieId = cabbieId;
        this.rate = rate;
        this.licenseNumber = licenseNumbers[idx];



        return;


    }

    /**
     * Gets the name of the cabbie.
     * 
     * @return the name of the cabbie
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the email of the cabbie.
     * 
     * @return the email of the cabbie
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the phone number of the cabbie.
     * 
     * @return the phone number of the cabbie
     */
    public String getPhone() {
        return this.phone;
    }


    /**
     * Gets the ID of the cabbie.
     * 
     * @return the ID of the cabbie (a UUID)
     */
    public String getCabbieId() {
        return this.cabbieId;
    }


    /**
     * Gets the rating of the cabbie.
     * 
     * @return the rating of the cabbie (a float between 0.0 and 5.0)
     */
    public float getRate() {    
        return this.rate;
    }


    /**
     * Gets the license number of the cabbie.
     * 
     * @return the license number of the cabbie (a string of length 7, containing
     *         only uppercase letters and digits)
     */
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
}
