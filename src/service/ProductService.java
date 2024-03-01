package service;

import model.Product;

import java.util.List;
import java.util.Scanner;

public interface ProductService {
<<<<<<< HEAD
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
=======
    void display(List<Product> product, Scanner input);
    void read(List<Product> products, Scanner input);
    void write(Scanner input, List<Product> products, String transaction);
    void delete(List<Product> products, Scanner input, String transaction);
    void search(Scanner input, List<Product> products);
    void edit(Scanner input, List<Product> products,String transactionFile);
    void commit(String transactionFile, String dataSource, Scanner input);
    void backUp(String dir, String dataSourceFileName);
    void random(Scanner input, List<Product> products, String transaction);
    void clearData(String dataSource);
    void restore(String backUpPath, String transaction, Scanner input);
    void setRow(Scanner input);
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
    void helpMenu();
}
