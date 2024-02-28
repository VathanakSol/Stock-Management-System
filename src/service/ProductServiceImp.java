package service;

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import validation.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProductServiceImp implements ProductService {
    Validation validation = new Validation();
    Scanner input = new Scanner(System.in);
    private List<Product> productList = new ArrayList<>(); // Store stock data in a list
    // Display all product
    public List<Product> getDisplayAllProduct(int page) {
        // Simply return the stock list
        return productList;
    }

    @Override
    public void displayAllProducts() {
        System.out.println("Stock Management System");

    }

    @Override
    public void displayByCode(int id) {

    }

    @Override
    public void createNewProduct() {
        System.out.println("Write name of product to stock : ");
        String name = input.nextLine();
        System.out.println("Write price of product to stock : ");
        float unitPrice = Float.parseFloat(input.nextLine());
        System.out.println("Write quantity of product to stock : ");
        int qty = validation.inputIntValidation();

        LocalDate currentDate = LocalDate.now(); // Initialize currentDate
        String nextCode = String.valueOf(productList.size() + 1);
        productList.add(new Product(nextCode, name, unitPrice, qty,  currentDate ));

        System.out.println("Insert stock successfully!");
    }


    @Override
    public void removeProduct() {
        System.out.print("Input Stock's codeOfProduct: ");
        String codeOfProduct = input.nextLine();

        Product productToDelete = findProductByCodeOfProduct(codeOfProduct);

        if (productToDelete != null) {
            String productName = productToDelete.getName();
            productList.remove(productToDelete);
            System.out.println("Deleting Stock: " + productName);
            System.out.println("Delete Stock codeOfProduct: " + codeOfProduct + " Successfully!");
        } else {
            System.out.println("There is no Stock codeOfProduct: " + codeOfProduct);
        }

    }

    @Override
    public void searchByName() {
        System.out.print("Enter the CodeOfProduct of the product you want to read: ");
        String codeOfProduct = input.nextLine();

        Product productToRead = findProductByCodeOfProduct(codeOfProduct);

        if (productToRead != null) {
            System.out.println("Product ID: " + productToRead.getCodeOfProduct());
            System.out.println("Product Name: " + productToRead.getName());
            System.out.println("Unit Price: " + productToRead.getPrice());
            System.out.println("Quantity: " + productToRead.getQty());
            System.out.println("Imported Date: " + productToRead.getCurrentDate());
        } else {
            System.out.println("Product with codeOfProduct " + codeOfProduct + " not found.");
        }

    }

    @Override
    public void helpMenu() {
        System.out.println("""                
                +-----------------------------------------------------------------+
                !  1.    Press     l : Display product as table.                  !
                !  2.    Press     w : Create a new product.                      !
                !  3.    Press     r : View Product detail by code.               !
                !  4.    Press     e : Edit existing product by code.             !
                !  5.    Press     d : Deleting an existing product by name.      !
                !  6.    Press     s : Search an existing product by name.        !
                !  7.    Press     c : Commit transactional data.                 !
                !  8.    Press     k : Back data.                                 !
                !  9.    Press     t : Restore Data.                              !
                !  10.   Press     f : Navigate pagination to the first page.     !
                !  11.   Press     p : Navigate pagination to the previous page.  !
                !  12.   Press     n : Navigate pagination to the next page.      !
                !  13.   Press     l : Navigate pagination to the last page.      !
                !  14.   Press     h : Help.                                      !
                !  15.   Press     b : Step back by the application.              !
                !  16.   Press     x : Exit the application.                      !
                +-----------------------------------------------------------------+
                """);
    }
    public void updateStockProductAll() {
        System.out.println("-> Input new stock's product name : ");
        String name = input.nextLine();
        System.out.println("-> Input new stock's product quantity : ");
        int qty = Integer.parseInt(input.nextLine());
        System.out.println("-> Input new stock's product price : ");
        float unitPrice = Float.parseFloat(input.nextLine());
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();
        LocalDate currentDate = LocalDate.now(); // Initialize currentDate

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);
        if (stockToUpdate != null) {
            stockToUpdate.setName(name);
            stockToUpdate.setQty(qty);
            stockToUpdate.setPrice(unitPrice);
            stockToUpdate.setCurrentDate(currentDate); // Set the current date
            System.out.println("Update stock's product all successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }

    public void updateStockProductName() {
        System.out.print("-> Input new stock's product name: ");
        String product = input.nextLine();
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);

        if (stockToUpdate != null) {
            stockToUpdate.setName(product);
            System.out.println("Update stock's product name successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }

    public void updateStockQty() {
        System.out.print("-> Input new stock's quantity: ");
        int qty = validation.inputIntValidation();
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);

        if (stockToUpdate != null) {
            stockToUpdate.setQty(qty);
            System.out.println("Update stock's quantity successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }
    public void updateStockPrice() {
        System.out.println("-> Input new stock's product price : ");
        float unitPrice = Float.parseFloat(input.nextLine());
        System.out.print("-> Input stock's codeOfProduct to update: ");
        String codeOfProduct = input.nextLine();

        Product stockToUpdate = findProductByCodeOfProduct(codeOfProduct);

        if (stockToUpdate != null) {
            stockToUpdate.setPrice(unitPrice);
            System.out.println("Update stock's price successfully!");
        } else {
            System.out.println("Stock with codeOfProduct " + codeOfProduct + " not found.");
        }
    }

    // Helper method to find a stock by its ID
    private Product findProductByCodeOfProduct(String codeOfProduct) {
        for (Product product : productList) {
            if (Objects.equals(product.getCodeOfProduct(), codeOfProduct)) {
                return product;
            }
        }
        return null; // Stock with the given ID not found
    }
}
