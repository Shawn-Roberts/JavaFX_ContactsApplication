package com.Shawn.ContactsApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {
    //FIELDS

    //FXML
    @FXML
    private TableView<ContactModel> contactTableView;

    @FXML
    private TableColumn<ContactModel, String> firstName;


    //INITIALIZE
    public void initialize()
    {
        ObservableList<ContactModel> contactModels = FXCollections.observableArrayList(
                new ContactModel("Jim","Bob","4444","notes")
        );

        contactTableView.setItems(contactModels);

    };



    //METHODS
}
