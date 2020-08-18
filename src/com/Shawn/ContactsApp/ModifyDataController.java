package com.Shawn.ContactsApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ModifyDataController {
    @FXML
   private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextArea notesTextArea;



    //CTORS


    //METHODS
    public Contact processResults()
    {
        String firstName = firstNameTextField.getText().trim();
        String lastNamee = lastNameTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        String notes = notesTextArea.getText().trim();

            Contact c = new Contact(firstName,lastNamee,phoneNumber,notes);
            ContactData.getInstance().addContact(c);
            System.out.println("CONTACT SUCESSFULLY ADDED");
            return c;

    }

    public Contact processResults(Contact oldContact)
    {
        String firstName = firstNameTextField.getText().trim();
        String lastNamee = lastNameTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        String notes = notesTextArea.getText().trim();

        Contact newContact = new Contact(firstName,lastNamee,phoneNumber,notes);
        ContactData.getInstance().editContact(oldContact, newContact);
        return newContact;
    }

    public void setDefaultText(Contact c)
    {
        firstNameTextField.setText(c.getFirstName());
        lastNameTextField.setText(c.getLastName());
        phoneNumberTextField.setText(c.getPhoneNumber());
        notesTextArea.setText(c.getNotes());
    }

}
