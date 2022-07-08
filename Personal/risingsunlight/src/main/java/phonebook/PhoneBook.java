package phonebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
        System.out.println("Merci d'avoir choisi notre annuaire téléphonique !");
    }

    public static int getUserChoice() {
        int userChoice = 0;
        System.out.println("Que voulez-vous faire sur l'annuaire téléphonique ? ");
        System.out.println("(1) - Rechercher un Contact");
        System.out.println("(2) - Voir les Contacts");
        System.out.println("(3) - Enregistrer un Contact");
        System.out.println("(4) - Supprimer un Contact");
        System.out.println("(5) - Modifier un Contact");
        System.out.println("(6) - Quitter l'annuaire");
        userInputScanner = new Scanner(System.in);
        try {
            userChoice = userInputScanner.nextInt();
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
    }

    public static void fetchContacts() {
    }

    public static void registerContact() {
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

    public static void deleteContact() {
    }

    public static void editContact() {
    }
}