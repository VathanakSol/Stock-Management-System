package utils;

import model.Product;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    // write data in transaction file into data source file and clear the list
    public static void exchangeData(String originalFile, String destinationFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush(); // Flush the writer to ensure all data is written
        } catch (IOException e) {
            System.err.println("Error committing data: " + e.getMessage());
        }
    }
    // checking first is it user already commit before close the program
    // if no commit before close program ask them
    // y commit the data from transaction just modify into data source file
    // no just clear the data in file transaction
    public static void checkingCommit(Scanner input, String transactionFile, String dataSource) {
        File file = new File(transactionFile);
        if (file.length() > 0) {
            // We ask if they want to commit data from the current file or transaction file to data source
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Do you want to commit? (Y/N)");
                String answer = input.nextLine();
                switch (answer.toLowerCase()) { // Convert to lowercase for case-insensitive comparison
                    // If yes then we call the method commit to perform the commit action
                    case "y" -> {
                        exchangeData(transactionFile, dataSource);
                        validInput = true; // Valid input provided, exit the loop
                    }
                    // If no just print a line and clear all the data in transaction file
                    // This means that all the modifying has not changed
                    case "n" -> {
                        System.out.println("You are canceling...");
                        clearTransactionFile(transactionFile, "");
                        validInput = true; // Valid input provided, exit the loop
                    }
                    default -> System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }
        }
    }
    // for clearing data in the transaction file
    public static void clearTransactionFile(String transactionFile, String msg) {
        try (PrintWriter writer = new PrintWriter(transactionFile)) {
            // Clear transaction file by writing an empty string to it
            writer.print("");
        } catch (IOException e) {
            System.err.println("Error clearing transaction file: " + e.getMessage());
        }
    }
    /***
     * method for validation user input
     * method read data from file into list
     */
    public static ArrayList<Product> readFileToList(String dataSource) {
        ArrayList<Product> products = new ArrayList<>();
        long start = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(dataSource))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Ensure that the line is correctly formatted
                    Product product = new Product();
                    product.setId(parts[0]);
                    product.setName(parts[1]);
                    product.setQty(Integer.parseInt(parts[2]));
                    product.setPrice(Float.parseFloat(parts[3]));
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Reading duration: " + duration + " ms");
        return products;
    }

    // this method is make sure the list data file transaction has the same data
    // it synchronizes
    public static void listToFile(List<Product> products, String transaction) {
        long start = System.currentTimeMillis();
        // Write data to transaction file using BufferedWriter for efficient buffering
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transaction))) {
            for (Product product : products) {
                // Assuming productToString(product) correctly formats the product
                writer.write(productToString(product));
                writer.newLine(); // Ensures each product is on a new line
            }
            long end = System.currentTimeMillis();
            long duration = end - start;
            // loading animation write from file into list
            displayAnimation(duration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // convert each product separate by (,) comma easy to dealing with it
    public static String productToString(Product product) {
        return product.getId() + "," + product.getName() + "," + product.getQty() + "," + product.getPrice();
    }

    /*** method for find duration
     need parameters start and end time*/
    public static long findDuration(long start, long end) {
        long duration = end - start;
        return duration;
    }

    public static void displayAnimation(long duration) {
        // Set the sequences for the animation
        String[] animationFrames = new String[] {"(👉ﾟヮﾟ)👉", "👈(⌒▽⌒)👉", "👈(ﾟヮﾟ👈)"};

        // Get the current time
        long startTime = System.currentTimeMillis();

        // Start the animation loop
        while (System.currentTimeMillis() - startTime < duration) {
            for (int i = 0; i < animationFrames.length; i++) {
                // Print the animation frame
                System.out.print("\r" + animationFrames[i % animationFrames.length]);

                // Sleep for a short duration to control the animation speed
                try {
                    Thread.sleep(300); // Adjust the sleep duration for the desired speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }    public static boolean confirm(String msg, Scanner input) {
        while (true) {
            System.out.println("Are you sure you want to " + msg + "?(Y/N)");

            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid input! Please enter 'y' for yes or 'n' for no.");
            }
        }
    }
}
