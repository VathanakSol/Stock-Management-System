package service;

import model.Product;

import java.util.List;
import java.util.Scanner;

public interface ProductService {
    void displayAllProducts(List<Product> product, String fileName);
    void displayByCode(String filename, Scanner input);
    void createNewProduct(Scanner input, List<Product> products, String fileName);
    void removeProduct(List<Product> products, Scanner input, String fileName);
    void searchByName(Scanner input, List<Product> products, String fileName);
    void updateByCode(Scanner input, List<Product> products, String fileName);
    void commit(String transactionFile, String backupFile);
    void backUp(String dir, String dataSourceFileName);
    void random(Scanner input, String transactionFile, String dataSource);
    void clearData(String dataSource);
    void helpMenu();
}
