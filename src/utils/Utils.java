package utils;

import model.Product;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Utils {
    // exchange data from one file to other file
    // make sure do not confused
    // when transfer data from transaction to data source following order of the param
    // when transfer data from data source to transaction file reverse the following order of param
    public static void exchangeData(String transactionFile, String backupFile, String msg) {
        System.out.println(msg);
        ExecutorService executor = Executors.newFixedThreadPool(2); // Create a thread pool with 2 threads

        executor.execute(() -> { // Execute reading and writing tasks in a separate thread
//            loading(msg);
            try (BufferedReader reader = new BufferedReader(new FileReader(transactionFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile, true))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine(); // Add newline after each line for readability
                }
                clearTransactionFile(transactionFile); // Clear transaction file after sending data to backup file
            } catch (IOException e) {
                System.err.println("Error exchanging data: " + e.getMessage());
            }
        });
        executor.shutdown();
    }
    // for this method we want to check is the data that user just modify is already commit to data source
    public static void checkingCommit(Scanner input, String transactionFile, String dataSource) {
        File file = new File(transactionFile);
        if (file.length() > 0) {
            // we ask if they want to commit data from the current file or transaction file to data source
            System.out.println("Do you want to commit? (Y/N)");
            String answer = input.nextLine();
            switch (answer) {
                // if yes then we call the method commit to perform the commit action
                case "Y", "y" ->{
                    Singleton.getProductServiceImp().commit(transactionFile, dataSource);
                }
                // if no just print a line and clear all the data in transaction file
                // this mean that all the modifying not change
                case "n", "N" -> {
                    System.out.println("You are canceled your modifying!");
                }
            }
        }
    }
    // for clearing data in the transaction file
    private static void clearTransactionFile(String transactionFile) {
        try (PrintWriter writer = new PrintWriter(transactionFile)) {
            // Clear transaction file by writing an empty string to it
            writer.print("");
        } catch (IOException e) {
            System.err.println("Error clearing transaction file: " + e.getMessage());
        }
    }

    public static void validation() {}

    public static void loading() {}
}
