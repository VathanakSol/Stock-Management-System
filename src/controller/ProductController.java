package controller;

import model.Product;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
import service.ProductService;
import utils.Singleton;
import utils.Utils;
import view.ProductView;

import java.io.File;
<<<<<<< HEAD

=======
import java.util.ArrayList;
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;
    private final ProductView productView;

<<<<<<< HEAD
=======
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import service.ProductService;
import service.ProductServiceImp;
import utils.Singleton;
import validation.Validation;
import view.ProductView;

import java.util.List;

import static java.lang.System.exit;

public class ProductController {
    Product product = new Product();
    Validation validation = new Validation();
    ProductServiceImp productServiceImp = new ProductServiceImp();
    private final ProductService productService;
    private final ProductView productView;




>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
    public ProductController(){
        productService = Singleton.getProductServiceImp();
        productView = Singleton.getProductView();
    }
<<<<<<< HEAD

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
=======
    int pageSize = 2 ;
    // Set the number of rows per page
    public void setPageSize() {
        System.out.print("Enter the number of rows per page: ");
        int newSize = validation.inputIntValidation();
        if (newSize > 0) {
            pageSize = newSize;
            System.out.println("Page size set to " + newSize + " rows.");
        } else {
            System.out.println("Invalid page size. Please enter a positive number.");
        }
    }
    public void DisplayAllProduct(){
        System.out.println("===============| Display all product |==============");

        int page = 1;
        while (true){
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            CellStyle cellStyle1 = new CellStyle(CellStyle.HorizontalAlign.left);
            Table table = new Table(5, BorderStyle.UNICODE_HEAVY_BOX_WIDE, ShownBorders.ALL);
            table.setColumnWidth(0, 20, 11);
            table.setColumnWidth(1, 20, 25);
            table.setColumnWidth(2, 20, 25);
            table.setColumnWidth(3, 20, 25);
            table.setColumnWidth(4, 25, 30);
            // Title Table / header table
            List<Product> stockList = productServiceImp.getDisplayAllProduct(page);
            System.out.println("====================| Current Page: |" +page);
            table.addCell("CODE", cellStyle);
            table.addCell("NAME", cellStyle);
            table.addCell("UNIT-PRICE", cellStyle);
            table.addCell("QUANTITY", cellStyle);
            table.addCell("IMPORTED DATE", cellStyle);

            int startRow = (page - 1) * pageSize;
            int endRow = Math.min(startRow + pageSize, stockList.size());

            for (int i = startRow; i < endRow; i++){
                table.addCell(stockList.get(i).getCodeOfProduct()+"", cellStyle1);
                table.addCell(stockList.get(i).getName()+"", cellStyle1);
                table.addCell(stockList.get(i).getPrice()+"", cellStyle1);
                table.addCell(stockList.get(i).getQty()+"", cellStyle1);
                table.addCell(stockList.get(i).getCurrentDate()+"", cellStyle1);
            }
            System.out.println(table.render());
            int totalRecords = stockList.size();
            int maxPage1 = (totalRecords + pageSize - 1) / pageSize;
            System.out.println(" Page : " +page + " of " +maxPage1+"                                                                                        Total record : "+ totalRecords);
            System.out.println(" >>>Page Navigation :                                            (f)First  (p)Previous  (n)Next  (l)Last  (g)GoTo   (b)Back");
            System.out.print(" -> Please input your choice : ");
            String opt = validation.inputStringValidation();
            switch (opt){
                case "f":
                    page = 1;
                    break;
                case "p":
                    if (page > 1) {
                        page--;
                    }
                    break;

                case "n":
                    int nextPage = page + 1;
                    int maxPage = (stockList.size() + pageSize - 1) / pageSize; // Calculate the maximum page
                    if (nextPage <= maxPage) {
                        page = nextPage;
                    } else {
                        System.out.println("No more pages available.");
                    }
                    break;
                case "l":
                    // Set the page to the last page
                    page = (stockList.size() + pageSize - 1) / pageSize;
                    break;
                case "g":
                    System.out.print("Enter the page number: ");
                    int pageNumber = validation.inputIntValidation();
                    if (pageNumber >= 1) {
                        page = pageNumber;
                    } else {
                        System.out.println("Invalid page number. Please enter a positive number.");
                    }
                    break;
                case "b":
                    System.out.println("Back to the main menu...");
                    return;
                default:
                    System.out.print("Input must be a valid string. Try again!! \n");
            }
//            if (opt > 5 || opt < 1) {
//                break;
//            }
        }

    }
    public void DisplayProductByCode(){

    }
    public void CreateNewProduct(){
        System.out.println("=========================|Create Product Here|==========================");
        productServiceImp.createNewProduct();;
    }
    public void UpdateAnExistingProduct(){}
    public void RemoveProduct(){
        productServiceImp.removeProduct();
    }
    public void SearchProductByName(){
      productServiceImp.searchByName();
    }
    public void HelpMenu(){
        System.out.println("================ | Here are our process system | ==================");
        productServiceImp.helpMenu();

    }

<<<<<<< HEAD
    public void display(){
        productService.displayAllProducts();
        productView.showMenu();
    }
    public void UpdateAllProduct(){
        System.out.println("===============| Update product stock All |===============");
        productServiceImp.updateStockProductAll();
    }
    public void UpdateProductNAme(){
        System.out.println("===============| Update product stock As name |===============");
        productServiceImp.updateStockProductName();
    }
    public void UpdateProductQuantity(){
        System.out.println("===============| Update product stock As Quantity |===============");
        productServiceImp.updateStockQty();
    }
    public void UpdateProductPrice(){
        System.out.println("===============| Update product stock As Price |===============");
        productServiceImp.updateStockPrice();
    }
=======
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6

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
                productView.invalidInput();
            }
        }
        return isTrue;
    }
}
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
