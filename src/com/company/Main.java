package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static IbanHandler ibanHandler = new IbanHandler();
    private static FileHandler fileHandler = new FileHandler(ibanHandler);


    public static void main(String[] args) {

        String menuOutput = "\n------------ MENU ------------ \n\n" +
                "1. Interactive IBAN validation \n" +
                "2. IBAN validation using files \n\n" +
                "0. Shut down\n";

        showMainMenu(menuOutput);

    }


    private static void showMainMenu(String menuOutput) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(menuOutput);
            String option = scanner.next();

            switch (option) {
                case "0":
                    System.out.println("\nShutting down...\n");
                    return;

                case "1":
                    String iban = getIbanFromUI(scanner);
                    boolean isIbanValid = ibanHandler.isIbanValid(iban);
                    printIbanValidity(iban, isIbanValid);
                    break;

                case "2":
                    String filePath = getFilePathFromUI(scanner);
                    ArrayList<String> ibanList = fileHandler.getIbanListFromFile(filePath);
                    fileHandler.writeIbanListToFile(ibanList, filePath);
                    break;

                default:
                    System.out.println("\nThere is no such option. Try again...\n");
            }
        }
    }


    private static String getIbanFromUI(Scanner scanner) {

        System.out.println("\n--- INTERACTIVE VALIDATION ---\n");
        System.out.println("Put your IBAN below:\n");

        return scanner.next();
    }

    private static String getFilePathFromUI(Scanner scanner) {

        System.out.println("\n------- FILE VALIDATION ------\n");
        System.out.println("Put your path to .txt file below:\n");

        return scanner.next();
    }

    private static void printIbanValidity(String iban, boolean isValid) {

        if (isValid)
            System.out.println("\nIBAN " + iban + " is valid!\n");
        else
            System.out.println("\nIBAN " + iban + " is NOT valid!\n");
    }

}