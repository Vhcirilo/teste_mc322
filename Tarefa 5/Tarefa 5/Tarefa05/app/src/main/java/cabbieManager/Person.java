package cabbieManager;

import javax.xml.bind.annotation.XmlSeeAlso;

import exceptions.InvalidPhoneNumberException;

@XmlSeeAlso({ Passenger.class, Cabbie.class })
public abstract class Person {
    // Adicionar os atributos da classe Person
    protected String name;
    protected String email;
    protected String phone;

    // Métodos a serem implementados da classe Person
    public abstract void register();

    public abstract void update(String field, String newValue)
            throws IllegalArgumentException, InvalidPhoneNumberException;

}