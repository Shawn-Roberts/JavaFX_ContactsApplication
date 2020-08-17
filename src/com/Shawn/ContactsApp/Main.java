package com.Shawn.ContactsApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainContacts.fxml"));
        primaryStage.setTitle("Contacts Application");
        primaryStage.setScene(new Scene(root, 800, 275));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try
        {
            ContactData.getInstance().saveContacts();
        }
        catch(IOException | XMLStreamException e)
        {
            System.out.println("Error processing Results");
        }
        finally{
            super.stop();
        }

    }

    @Override
    public void init() throws Exception {
        try
        {
            ContactData.getInstance().loadContacts();
        }
        catch(IOException | XMLStreamException e)
        {
            System.out.println("Error processing input data");
        }
        finally{
            super.init();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
