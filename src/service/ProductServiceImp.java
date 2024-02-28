package service;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImp implements ProductService {

    @Override
    public void displayAllProducts(List<Product> product, String fileName) {

    }

    @Override
    public void displayByCode(String filename, Scanner input) {

    }

    @Override
    public void createNewProduct(Scanner input, List<Product> products, String fileName) {

    }

    public void removeProduct(List<Product> products, Scanner input, String fileName) {
        System.out.println("Input ID that you want to remove: ");
        input.nextLine();
        String id = input.nextLine();
        boolean removed = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder updatedContent = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    removed = true;
                    System.out.println("Product with ID: " + id + " removed Successfully!");
                    continue;
                }
                updatedContent.append(line).append(System.lineSeparator());
            }

            if (!removed) {
                System.out.println("Product with ID: " + id + " not found.");
                return;
            }

            // Write updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(updatedContent.toString());
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    @Override
    public void searchByName(Scanner input, List<Product> products, String fileName) {

    }

    @Override
    public void updateByCode(Scanner input, List<Product> products, String fileName) {

    }

    @Override
    public void commit(String transactionFile, String backupFile) {

    }

    @Override
    public void backUp(String dir, String dataSourceFileName) {

    }

    @Override
    public void random(Scanner input, String transactionFile, String dataSource) {

    }

    @Override
    public void clearData(String dataSource) {

    }

    @Override
    public void helpMenu() {

    }
}
