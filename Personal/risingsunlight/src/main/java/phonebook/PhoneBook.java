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

    public static Scanner userInputScanner = null;

    public static void main(String[] args) {

        userInputScanner = new Scanner(System.in);

        // * Récupère les infos utilisateurs
        String lastName = getUserInput("Entrez le nom de famille :");
        String firstName = getUserInput("Entrez le prénom :");
        String phoneNumber = getUserInput("Entrez le numéro de téléphone :");

        userInputScanner.close(); // Ferme le scanner, doit être fermé pour des soucis de perfs

        Contact newContact = new Contact(lastName, firstName, phoneNumber); // Crée un nouveau contact

        // Récupère le fichier du phonebook
        File phoneBookFile = new File(
                "Personal\\risingsunlight\\src\\main\\java\\phonebook\\phonebook.txt");

        if (phoneBookFile.exists()) {
            try {
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(phoneBookFile, true));
                fileWriter.append(newContact.toString());
                System.out.println("Contact ajouté !");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                phoneBookFile.createNewFile();
                System.out.println("Le fichier a bien été créé.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Le fichier n'existe pas et je n'ai pas pu le créer.");
            }
        }
    }

    public static String getUserInput(String message) {
        System.out.println(message);
        return userInputScanner.nextLine();
    }
}
