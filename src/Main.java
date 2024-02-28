import RenderTable.TableRender;
import controller.ProductController;
<<<<<<< HEAD
import service.ProductServiceImp;
import validation.Validation;

=======
import model.Product;
import utils.Singleton;
import utils.Utils;

import java.io.File;
>>>>>>> 2645b29c2157674d81a1bf3f7321ed0b63fb5d84
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
<<<<<<< HEAD
        ProductController controller = new ProductController();
        Validation validation = new Validation();

        String opt;



        controller.display();
        System.out.println("*====================================================================================*");
        System.out.println("*====================================================================================*\n"+
                "*   ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     * \n" +
                "*  ██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    *\n" +
                "*  ██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    *\n" +
                "*  ██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    *\n" +
                "*  ╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    *\n" +
                "*   ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    *\n" +
                "*====================================================================================*");

        List<String> welcomeMenu = new ArrayList<>(List.of(
                "L) Display",
                "M) Random",
                "W) Write",
                "R) Read",
                "E) Edit",
                "D) Delete",
                "S) Search",
                "O) Set Row",
                "C) Commit",
                "K) Back Up",
                "T) Restore",
                "H) Help",
                "X) Exit"
        ));

        do {
            System.out.println(" =============================| STOCK MANAGEMENT SYSTEM |=============================");
            TableRender.renderMenu(welcomeMenu);
//          input opt;
            System.out.print("Command -> ");
           opt = input.nextLine().toLowerCase();
            switch (opt) {
//                Display
                case "l": {
                    controller.DisplayAllProduct();

                }
//                Random
                case "m":
                {

                    break;
                }
//                Write
                case "w":
                {
                    controller.CreateNewProduct();
                }

//              read
                case "r" :
                {


                    break;
                }
//                edit
                case "e" :
                {
                    int updateOpt;
                    do {
                        System.out.println("--------------| what do you want to update |---------------");
                        System.out.println("""
                                1 - Update All Product.
                                2 - Update Product name.\s
                                3 - Update Unit Price.
                                4 - Update Stock's Quantity.
                                5 - Back to Main option.\s
                                _______________________________________
                                """);
                        System.out.print("-> Choose  options above(1-5) : ");
                        updateOpt = validation.inputIntValidation();
                        switch (updateOpt) {
                            case 1 :{
                                controller.UpdateAllProduct();

                                break;
                            }

                            case 2: {
                                controller.UpdateProductNAme();
                                break;
                            }
                            case 3: {
                                controller.UpdateProductPrice();
                                break;
                            }
                            case 4: {
                                controller.UpdateProductQuantity();
                                break;
                            }
                        }

                    } while (updateOpt != 5);
                    break;


                }
//                delete
                case "d":
                {
                    controller.RemoveProduct();
                    break;
                }
//               Search
                case "s":
                {
                    controller.SearchProductByName();
                    break;
                }
//                Set row
                case "o" :
                {
                    controller.setPageSize();
                    break;
                }
//                Commit
                case "c" :
                {

                    break;
                }
//                Restore
                case "t" :
                {

                    break;
                }
//                Help menu
                case "h" :
                {
                    controller.HelpMenu();

                    break;
                }

                    default: {
                        System.out.println("Invalid option. Please select a valid option.");
                        
                    }break;
                }

        }while (!opt.equals("x"));
        System.out.println("Bye bye");
=======

        // variable for storing location of file transaction
        String transactionFile = "src/data/transaction.dat";
        // variable for storing location of file data source
        String dataSource = "src/data/data_source.dat";
        // variable for storing location of dir back up
        String dir = "src/backup";

        // checking if the user commit before exit the program
        // if forget to commit tell them to commit or not
        Utils.checkingCommit(input, transactionFile, dataSource);

        // read data from data source into transaction file
        Utils.exchangeData(dataSource, transactionFile, "Retrieving data into Transaction File: ");

        List<Product> productsList = new ArrayList<>();

        boolean running = true;
        while (running) {
            running = Singleton.getProductController().display(input, productsList, transactionFile, dataSource, dir);
        }
        System.out.println("Hello from main");
>>>>>>> 2645b29c2157674d81a1bf3f7321ed0b63fb5d84
    }
}