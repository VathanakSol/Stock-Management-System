package service;

import model.Product;

import java.util.List;
import java.util.Scanner;

public interface ProductService {
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
    void helpMenu();
}
