package phonebook;

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

        Contact newContact = new Contact(lastName, firstName, phoneNumber);
        System.out.println(newContact);
    }

    public static String getUserInput(String message) {
        System.out.println(message);
        return userInputScanner.nextLine();
    }
}
