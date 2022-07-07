package phonebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Basé sur la vidéo tuto https://www.youtube.com/watch?v=DTYjbHC0rj8
 *
 */
public class PhoneBook {

    public static final String PHONE_BOOK_FILE_PATH = "Personal\\risingsunlight\\src\\main\\java\\phonebook\\phonebook.txt";
    public static Scanner userInputScanner = null;

    public static void main(String[] args) {

        userInputScanner = new Scanner(System.in);

        // * Récupère les infos utilisateurs
        String lastName = getUserInput("Entrez le nom de famille :");
        String firstName = getUserInput("Entrez le prénom :");
        String phoneNumber = getUserInput("Entrez le numéro de téléphone :");

        userInputScanner.close(); // Ferme le scanner, doit être fermé pour des soucis de perfs

        Contact newContact = new Contact(lastName, firstName, phoneNumber); // Crée un nouveau contact

        File phoneBookFile = getOrCreatePhoneBookFile(PHONE_BOOK_FILE_PATH); // Récupère le fichier phonebook

        updatePhoneBook(phoneBookFile, newContact); // Met à jour le phonebook
    }

    public static String getUserInput(String message) {
        System.out.println(message);
        return userInputScanner.nextLine();
    }

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
}