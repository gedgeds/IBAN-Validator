package com.company;

import java.io.*;
import java.util.ArrayList;

class FileHandler {

    private IbanHandler ibanHandler;


    FileHandler(IbanHandler ibanHandler) {

        this.ibanHandler = ibanHandler;
    }


    ArrayList<String> getIbanListFromFile(String pathName) {

        ArrayList<String> ibans = new ArrayList<>();
        String line;

        try {
            // Read every line from file
            BufferedReader in = new BufferedReader(new FileReader(pathName));
            while ((line = in.readLine()) != null) {
                ibans.add(line);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("\nThere was a problem reading the file");
            System.out.println(e + "\n");
        }

        return ibans;
    }

    void writeIbanListToFile(ArrayList<String> ibans, String pathName) {

        // Check every iban and write result to file
        StringBuilder result = new StringBuilder();
        for (String str : ibans) {
            result.append(str)
                    .append(";")
                    .append(ibanHandler.isIbanValid(str))
                    .append("\n");
        }
        printResultToFile(pathName, result);
    }


    private String getFileName(String pathName) {

        File path = new File(pathName);
        String fileName = path.getName();

        int pos = fileName.lastIndexOf(".");
        if (pos > 0)
            fileName = fileName.substring(0, pos) + ".out";

        return fileName;
    }

    private void printResultToFile(String path, StringBuilder result) {

        try (FileWriter writer = new FileWriter(getFileName(path));
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(result.toString());

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}