package com.Shawn.ContactsApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
    //FIELDS

    //FXML
    @FXML
    private TableView<Contact> contactTableView;


    //INITIALIZE
    public void initialize()
    {
        contactTableView.setItems(ContactData.getInstance().getContacts());

    }


    //METHODS
}
