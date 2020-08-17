package com.Shawn.ContactsApp;

import javafx.beans.property.SimpleStringProperty;

public class ContactModel {
    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty lastName = new SimpleStringProperty("");
    private final SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private final SimpleStringProperty notes = new SimpleStringProperty("");

    public ContactModel() {
        this("", "", "","");
    }

    public ContactModel(String firstName, String lastName, String phoneNumber, String notes) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setNotes(notes);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lName) {
        lastName.set(lName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phone) {
        phoneNumber.set(phone);
    }


    public String getNotes() {
        return phoneNumber.get();
    }

    public void setNotes(String notes) {
        phoneNumber.set(notes);
    }
}