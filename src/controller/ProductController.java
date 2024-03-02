package controller;

import model.Product;
import service.ProductService;
import utils.Singleton;
import utils.Utils;
import view.ProductView;

import java.io.File;
import java.util.ArrayList;
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
        productView.showMenu();
        String userInput = input.nextLine();
        switch (userInput) {
            // user want to display all products
            case "l", "L" -> {
                productService.display(products,input);
                input.nextLine();
            }

            // user want to random create new product
            case "m", "M" -> {
                productService.random(input,products,transactionFile);
            }

            // user want to write product or create product
            case "w", "W" -> {
                productService.write(input, products, transactionFile);
                input.nextLine();
            }

            // user want to read (display by code)
            case "r", "R" -> {
                productService.read(products, input);
                input.nextLine();
            }

            // user want to update or edit
            case "e", "E" -> {
                productService.edit(input, products, transactionFile);
            }

            // user want to delete or remove
            case "d", "D" -> {
                productService.delete(products, input, transactionFile);

            }

            // user want to search
            case "s", "S" -> {
                productService.search(input, products);
                input.nextLine();
            }

            // set row
            case "o", "O" -> {
                productService.setRow(input);
            }

            // commit
            case "c", "C" -> {
                productService.commit(transactionFile, dataSource);
            }

            // back up option
            case "k", "K" -> {
                // make sure to commit first before back up to new file
//                Utils.checkingCommit(input, transactionFile, dataSource);
                productService.backUp(dir, transactionFile);
                System.out.println("You are Backing up file...");
                System.out.println("Press any key to finish");
                input.nextLine();
            }

            // restore option
            case "t", "T" -> {
                productService.restore(dir, transactionFile, input);
                System.out.println("Press any key");
                input.nextLine();
            }

            case "h", "H" -> {
                productService.helpMenu();
                System.out.println("Press any key...");
                input.nextLine();
            }

            // exit
            case "x", "X" -> {
                // ask you want to commit before exit the program\
                System.out.println("Enter any key");
                input.nextLine();
                System.exit(0);
            }
            default -> {
                productView.invalidInput();
            }
        }
        return isTrue;
    }
}
