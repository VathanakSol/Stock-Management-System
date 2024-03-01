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
                System.out.println("=========================|Random Product Here|==========================");
                System.out.println("Writing-Random from list into file...");
                productService.random(input,products,transactionFile);
                System.out.println("Reading-Random from file into list...");
                Utils.readFileToList(transactionFile, "");
                input.nextLine();
            }

            // user want to write product or create product
            case "w", "W" -> {
                System.out.println("=========================|Create Product Here|==========================");
                productService.write(input, products, transactionFile);
                input.nextLine();
            }

            // user want to read (display by code)
            case "r", "R" -> {
                System.out.println("===============| Display by Code |===============");

                productService.read(products, input);
                input.nextLine();
            }

            // user want to update or edit
            case "e", "E" -> {
                System.out.println("===============| Update product stock All |===============");
                productService.edit(input, products, transactionFile);
            }

            // user want to delete or remove
            case "d", "D" -> {
                System.out.println("=========================|Delete Product Here|==========================");
                productService.delete(products, input, transactionFile);

            }

            // user want to search
            case "s", "S" -> {
                System.out.println("=========================|Search Product Here|==========================");
                productService.search(input, products);
                input.nextLine();
            }

            // set row
            case "o", "O" -> {
                System.out.println("=========================|Set Row Here|==========================");
                productService.setRow(input);
            }

            // commit
            case "c", "C" -> {
                System.out.println("=========================|Commit Product Here|==========================");
                productService.commit(transactionFile, dataSource, input);
            }

            // back up option
            case "k", "K" -> {
                System.out.println("=========================|Back Up Product Here|==========================");
                // make sure to commit first before back up to new file
//                Utils.checkingCommit(input, transactionFile, dataSource);
                productService.backUp(dir, transactionFile);
                System.out.println("You are Backing up file...");
                System.out.println("Press any key to finish");
                input.nextLine();
            }

            // restore option
            case "t", "T" -> {
                System.out.println("=========================|Restore Product Here|==========================");
                productService.restore(dir, transactionFile, input);
            }

            case "h", "H" -> {
                System.out.println("===========================|Help  Menu|============================");
                productService.helpMenu();
                System.out.println("Press any key...");
                input.nextLine();
            }

            // exit
            case "x", "X" -> {
                System.out.println("===========================|Exit Here|==========================");
                // ask you want to commit before exit the program\
                System.out.println("Enter any key");
                input.nextLine();
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid Input!!!");
            }
        }
        return isTrue;
    }
}