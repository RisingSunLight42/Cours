package calculator;

import java.util.Scanner;

/**
 * Calculator, do what a calculator do
 */
public final class Calculator {

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Donnez-moi le premier nombre de l'opération.");
        int firstNumber = Integer.parseInt(userInputScanner.nextLine());
        System.out.println("Donnez-moi le second nombre de l'opération.");
        int secondNumber = Integer.parseInt(userInputScanner.nextLine());
        System.out.println("Donnez-moi le signe de l'opération à effectuer");
        String operationSign = userInputScanner.nextLine();
        userInputScanner.close();

        int result = simpleOperation(firstNumber, secondNumber, operationSign);
        System.out.println(
                "Le résultat de l'opération " + firstNumber + operationSign + secondNumber + " est :\n" + result);
    }

    private static int simpleOperation(int firstNumber, int secondNumber, String operationSign) {
        int result = 0;
        switch (operationSign) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }
        return result;
    }
}
