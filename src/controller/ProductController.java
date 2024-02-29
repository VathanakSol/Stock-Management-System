package controller;

import model.Product;
import service.ProductService;
import utils.Singleton;
import utils.Utils;
import view.ProductView;

import java.io.File;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;
    private final ProductView productView;

    public ProductController(){
        productService = Singleton.getProductServiceImp();
        productView = Singleton.getProductView();
    }

    public boolean display(Scanner input, List<Product> products, String transactionFile, String dataSource, String dir){
        boolean isTrue = true;

        // show menu
        // declare variable to get the value of option user input
        int choice = productView.showMenu(input);
        switch (choice) {
            // user want to display all products
            case 1 -> {
                File file = new File(transactionFile);
                // as we know after we commit all the data in the transaction file we send to new data source file
                // all the data in the transaction file we clear it all
                // we need to check if user display after commit we will show the data in the data file
                // cuz in the transaction is nothing to display
                System.out.println("All Products");
                System.out.println("ID\t\tName\t\tQuantity\tPrice");
                if (file.length() == 0) {
                    productService.displayAllProducts(products, dataSource);
                }
                productService.displayAllProducts(products, transactionFile);
            }
            // user want to create a new product
            case 2 -> {
                productService.createNewProduct(input, products, transactionFile);
            }
            // user want to remove product by id
            case 3 -> {
                productService.removeProduct(products, input, transactionFile);

            }
            // user want to update
            case 4 -> {
                productService.updateByCode(input, products, transactionFile);
            }
            // user want to search
            case 5 -> {
                productService.searchByName(input, products, transactionFile);
            }
            // user commit
            case 6 -> {
                productService.commit(transactionFile, dataSource);
            }
            case 7 -> {
                // make sure to commit first before back up to new file
                Utils.checkingCommit(input, transactionFile, dataSource);
                productService.backUp(dir, dataSource);
            }
            case 8 -> {
                productService.random(input, transactionFile, dataSource);
            }
            case 9 -> {
                productService.clearData(transactionFile);
            }
            case 10 -> {
                isTrue = false;
            }
            default -> {
                System.out.println("Invalid input");
            }
        }
        return isTrue;
    }
}