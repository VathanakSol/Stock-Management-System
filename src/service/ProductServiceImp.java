package service;

import model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImp implements ProductService {

    @Override
    public void displayAllProducts(List<Product> products, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Split using commas
                if (parts.length >= 4) { // Check if there are enough elements in the array
                    String id = parts[0].trim(); // Trim to remove leading/trailing whitespace
                    String name = parts[1].trim();
                    int qty = Integer.parseInt(parts[2].trim());
                    float price = Float.parseFloat(parts[3].trim());

                    // Create a new product for each line in the file
                    Product product = new Product();
                    product.setId(id);
                    product.setName(name);
                    product.setQty(qty);
                    product.setPrice(price);

                    products.add(product); // Add the product to the list

                    // Print product details
                    System.out.println(product.getId() + "\t\t" + product.getName() + "\t\t" + product.getQty() + "\t\t" + product.getPrice() + "$");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void displayByCode(String filename, Scanner input) {

    }

    @Override
    public void createNewProduct(Scanner input, List<Product> products, String fileName) {

    }

    @Override
    public void removeProduct(List<Product> products, Scanner input, String fileName) {

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
