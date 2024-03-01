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
    public static void exchangeData(String transactionFile, String dataSource) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // Create a thread pool with 10 threads

        executor.execute(() -> { // Execute reading and writing tasks in a separate thread
            try (BufferedReader reader = new BufferedReader(new FileReader(transactionFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(dataSource))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.flush(); // Flush the writer to ensure all data is written
            } catch (IOException e) {
                System.err.println("Error committing data: " + e.getMessage());
            }
        });
        executor.shutdown();
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
    // method for validation user input
    // method read data from file into list
    public static ArrayList<Product> readFileToList(String transactionFile, String msg) {
        ArrayList<Product> products = new ArrayList<>();
        // set the time of starting
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String id = parts[0].trim();
                String name = parts[1].trim();
                int qty = Integer.parseInt(parts[2].trim());
                float price = Float.parseFloat(parts[3].trim());

                Product product = new Product();
                product.setName(name);
                product.setId(id);
                product.setPrice(price);
                product.setQty(qty);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        syncWithTransactionFile(products,transactionFile);
        long end = System.currentTimeMillis();
        Utils.findDuration(start, end);
        System.out.println(msg);
        System.out.println();
        return products;
    }

    // this method is make sure the list data file transaction has the same data
    // it synchronizes
    public static void syncWithTransactionFile(List<Product> products, String transaction) {
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

    // method for find duration
    // need parameters start and end time
    public static long findDuration(long start, long end) {
        long duration = end - start;
        System.out.print("Take time " + duration + " millisecond(>_<) ");
        return duration;
    }

    public static void displayAnimation(long duration) {
        // Set the sequences for the animation
        String[] animationFrames = new String[] {">0<", "-0-", ">_<", "-_-", ">,<", "-,-", "-X-"};

        // Get the current time
        long startTime = System.currentTimeMillis();

        // Start the animation loop
        while (System.currentTimeMillis() - startTime < duration) {
            for (String frame : animationFrames) {
                // Print the animation frame
                System.out.print("\r" + frame);

                // Sleep for a short duration to control the animation speed
                try {
                    Thread.sleep(0); // Adjust the sleep duration for the desired speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int countLines(String filePath) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                count++;
            }
        }
        return count;
    }

    public static void clearFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            // FileWriter is opened in overwrite mode and closed immediately,
            // which clears the file without writing anything.
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }
}
