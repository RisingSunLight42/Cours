package phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Basé sur la vidéo tuto https://www.youtube.com/watch?v=DTYjbHC0rj8
 *
 */
public class PhoneBook {

    public static final String PHONE_BOOK_FILE_PATH = "Personal\\risingsunlight\\src\\main\\java\\phonebook\\phonebook.txt";
    public static Scanner userInputScanner = null;

    public static void main(String[] args) {
        int userChoice = 0;
        userInputScanner = new Scanner(System.in);

        while (userChoice != 6) {
            userChoice = getUserChoice();

            switch (userChoice) {
                case 1:
                    getContact();
                    break;
                case 2:
                    fetchContacts();
                    break;
                case 3:
                    registerContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    editContact();
            }
        }
        userInputScanner.close();
        System.out.println("Merci d'avoir choisi notre annuaire téléphonique !");
    }

    /**
     * Show the possibles choices for the user and return the user choice
     * 
     * @return the user choice
     */
    public static int getUserChoice() {
        int userChoice = 0;
        System.out.println("Que voulez-vous faire sur l'annuaire téléphonique ? ");
        System.out.println("(1) - Rechercher un Contact");
        System.out.println("(2) - Voir les Contacts");
        System.out.println("(3) - Enregistrer un Contact");
        System.out.println("(4) - Supprimer un Contact");
        System.out.println("(5) - Modifier un Contact");
        System.out.println("(6) - Quitter l'annuaire");
        try {
            userChoice = Integer.parseInt(userInputScanner.nextLine());
        } catch (InputMismatchException e) {
            System.out
                    .println("Tu ne m'as pas donné un nombre ! Choisis bien un nombre associé à l'action souhaitée !");
            return getUserChoice();
        }
        if (userChoice < 1 || userChoice > 6) {
            System.out.println("Le choix que tu m'as donné est incorrect !");
            return getUserChoice();
        }
        return userChoice;
    }

    public static void getContact() {
        List<Contact> contactsArray = readPhoneBookFile();
        Iterator<Contact> contactsIterator = contactsArray.iterator();
        String recherche = getUserInput("Quel contact recherchez-vous ?");
        while (contactsIterator.hasNext()) {
            Contact contact = contactsIterator.next();
            if (contact.correspond(recherche))
                System.out.println(contact);
        }
    }

    /**
     * Read the file containing all the informations of the phonebook and return it
     * 
     * @return a List of Contact
     */
    public static List<Contact> readPhoneBookFile() {
        File phoneBookFile = getOrCreatePhoneBookFile(PHONE_BOOK_FILE_PATH);
        List<Contact> contactsArray = new ArrayList<Contact>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(phoneBookFile))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                String[] contactInfoStrings = line.split(" ");
                contactsArray.add(new Contact(contactInfoStrings[0], contactInfoStrings[1],
                        contactInfoStrings[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactsArray;
    }

    /**
     * Fetch the Contacts' List and show it
     */
    public static void fetchContacts() {
        List<Contact> contactsArray = readPhoneBookFile();
        Iterator<Contact> contactsIterator = contactsArray.iterator();
        while (contactsIterator.hasNext()) {
            System.out.println(contactsIterator.next());
        }
    }

    /**
     * Register a new {@link Contact} with the lastName, firstName and the
     * phoneNumber
     */
    public static void registerContact() {
        // * Récupère les infos utilisateurs
        String lastName = getUserInput("Entrez le nom de famille :");
        String firstName = getUserInput("Entrez le prénom :");
        String phoneNumber = getPhoneNumber("Entrez le numéro de téléphone :");

        Contact newContact = new Contact(lastName, firstName, phoneNumber); // Crée un nouveau contact

        File phoneBookFile = getOrCreatePhoneBookFile(PHONE_BOOK_FILE_PATH); // Récupère le fichier phonebook

        updatePhoneBook(phoneBookFile, newContact); // Met à jour le phonebook
    }

    /**
     * Get the user input for a specified message.
     * 
     * @param message - the message to show to the user
     * @return the user input
     */
    public static String getUserInput(String message) {
        System.out.println(message);
        return userInputScanner.nextLine();
    }

    public static String getPhoneNumber(String message) {
        String phoneNumber = getUserInput(message);
        if (phoneNumber.length() != 10) {
            System.out.println("La taille du numéro de téléphone donné est incorrecte ! Elle doit faire 10 numéros !");
            return getPhoneNumber(message);
        } else if (!checkOnlyDigit(phoneNumber)) {
            System.out.println("Le numéro de téléphone que tu m'as donné contient des lettres ! Il est invalide");
            return getPhoneNumber(message);
        }
        return phoneNumber;
    }

    public static boolean checkOnlyDigit(String stringToCheck) {
        char[] charArrayToCheck = stringToCheck.toCharArray();
        for (char character : charArrayToCheck) {
            if (!Character.isDigit(character))
                return false;
        }
        return true;
    }

    /**
     * Get or Create the phonebook's file and return it
     * 
     * @param phoneBookFilePath - the supposed path of the file
     * @return The phonebook's file
     */
    public static File getOrCreatePhoneBookFile(String phoneBookFilePath) {
        File phoneBookFile = new File(phoneBookFilePath);

        if (phoneBookFile.exists()) {
            return phoneBookFile;
        }

        try {
            phoneBookFile.createNewFile();
            System.out.println("Le fichier a bien été créé à l'emplacement donné (" + phoneBookFilePath + ")");
            return phoneBookFile;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Le fichier n'existe pas et je n'ai pas pu le créer.");
        }

        return null;
    }

    /**
     * Update the phonebook with the new Contact
     * 
     * @param phoneBookFile - the phonebook's file
     * @param newContact    - the new {@link Contact} to add
     */
    public static void updatePhoneBook(File phoneBookFile, Contact newContact) {
        // Déclarer le fileWriter dans le try permet de le fermer automatiquement après
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(phoneBookFile, true))) {
            fileWriter.append(newContact.toString());
            fileWriter.append(System.lineSeparator()); // Permet d'avoir la bonne séparation de ligne suivant l'OS
            System.out.println("Contact ajouté !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact() {
    }

    public static void editContact() {
    }
}