package com.Shawn.ContactsApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;

public class ContactData {
    //FIELDS
            //XML SETUP
    private static final String CONTACTS_FILE = "contacts.xml";
    private static final String CONTACT = "contact";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String NOTES = "notes";

            //CLASS FIELDS

    private static final ContactData instance = new ContactData();
    private final ObservableList<Contact> contacts =  FXCollections.observableArrayList();


    //CTORS
    public ContactData(){};


    //METHODS
                //GETTERS & SETTERS
    public static ContactData getInstance()
    {
        return instance;
    }

    public ObservableList<Contact> getContacts()
    {
        return contacts;
    }
                // ADD RECORD
    public void addContact(Contact contact)
    {
        contacts.add(contact);
    }
                //DELETE RECORD
    public void deleteContact(Contact contact){ contacts.remove(contact);}

                // READ FROM FILE
    public void loadContacts() throws IOException, XMLStreamException {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            // Setup a new eventReader
            InputStream in = new FileInputStream(CONTACTS_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            // read the XML document
            Contact contact = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    // If we have a contact item, we create a new contact
                    if (startElement.getName().getLocalPart().equals(CONTACT)) {
                        contact = new Contact();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(FIRST_NAME)) {
                            event = eventReader.nextEvent();
                            contact.setFirstName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(LAST_NAME)) {
                        event = eventReader.nextEvent();
                        contact.setLastName(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(PHONE_NUMBER)) {
                        event = eventReader.nextEvent();
                        contact.setPhoneNumber(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(NOTES)) {
                        event = eventReader.nextEvent();
                        contact.setNotes(event.asCharacters().getData());
                        continue;
                    }
                }

                // If we reach the end of a contact element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(CONTACT)) {
                        contacts.add(contact);
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (XMLStreamException e) {
                e.printStackTrace();
                }
    }

    // WRITE TO FILE
    public boolean saveContacts() throws IOException, XMLStreamException
    {
        try
        {
            //creating a factory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(CONTACTS_FILE));

            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");

            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement contactsStartElement = eventFactory.createStartElement("",
                    "", "contacts");
            eventWriter.add(contactsStartElement);
            eventWriter.add(end);

            for (Contact contact: contacts) {
                saveContact(eventWriter, eventFactory, contact);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "contacts"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();

        }
        catch(IOException e)
        {
            System.out.println("error");
            e.printStackTrace();
            return false;
        }
        catch(XMLStreamException e)
        {
            System.out.println("error");
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private void saveContact(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Contact contact) throws FileNotFoundException, XMLStreamException
    {
        XMLEvent end = eventFactory.createDTD("\n");

        // create contact open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", CONTACT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, FIRST_NAME, contact.getFirstName());
        createNode(eventWriter, LAST_NAME, contact.getLastName());
        createNode(eventWriter, PHONE_NUMBER, contact.getPhoneNumber());
        createNode(eventWriter, NOTES, contact.getNotes());

        eventWriter.add(eventFactory.createEndElement("", "", CONTACT));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}
