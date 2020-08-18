package com.Shawn.ContactsApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainController {
    //FIELDS

    //FXML
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TableView<Contact> contactTableView;

    @FXML
    private Button addButton;


    @FXML
    private Button deleteButton;


    //INITIALIZE
    public void initialize()
    {
        // ADD BUTTON PRESSED
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showNewItemDialog(addButton, "Add New Item", "AddContact.fxml");

            }
        });

        //DELETE BUTTON ENABLED
        contactTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observableValue, Contact contact, Contact t1) {
                deleteButton.setDisable(false);

            }
        });

        //DELETE BUTTON PRESSED
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteContact(contactTableView.getSelectionModel().getSelectedItem());
            }
        });



        // LOAD DATA INTO FORM
        contactTableView.setItems(ContactData.getInstance().getContacts());

    }


    //METHODS
    public void showNewItemDialog(Button b, String titleText, String location)
    {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle(titleText);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(location));
        try
        {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        }
        catch(IOException e)
        {
            System.out.println("Error, cant load dialoge error");
            e.printStackTrace();
            return;

        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            ModifyDataController controller = fxmlLoader.getController();
            Contact c = controller.processResults();

        }
    }

    public void deleteContact(Contact contact)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Delete contact: " + contact.getFirstName()+","+contact.getLastName());
        alert.setContentText("Are you sure? Press Okay to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
            ContactData.getInstance().deleteContact(contact);
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Cancelled");
            alert.setHeaderText("No Record Deleted");
            alert.show();
        }

    }
}
