package controller;

import model.Product;
<<<<<<< HEAD
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
=======
>>>>>>> 2645b29c2157674d81a1bf3f7321ed0b63fb5d84
import service.ProductService;
import service.ProductServiceImp;
import utils.Singleton;
<<<<<<< HEAD
import validation.Validation;
import view.ProductView;

import java.util.List;
=======
import utils.Utils;
import view.ProductView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
>>>>>>> 2645b29c2157674d81a1bf3f7321ed0b63fb5d84

public class ProductController {
    Product product = new Product();
    Validation validation = new Validation();
    ProductServiceImp productServiceImp = new ProductServiceImp();
    private final ProductService productService;
    private final ProductView productView;

<<<<<<< HEAD



=======
>>>>>>> 2645b29c2157674d81a1bf3f7321ed0b63fb5d84
    public ProductController(){
        productService = Singleton.getProductServiceImp();
        productView = Singleton.getProductView();
    }
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
            System.out.println(" >>>Page Navigation :                                                 (1)First  (2)Previous  (3)Next  (4)Last  (5)GoTo");
            System.out.print(" -> Press any key(Integer) to exit : ");
            int opt = validation.inputIntValidation();
            switch (opt){
                case 1:
                    page = 1;
                    break;
                case 2:
                    if (page > 1) {
                        page--;
                    }
                    break;

                case 3:
                    int nextPage = page + 1;
                    int maxPage = (stockList.size() + pageSize - 1) / pageSize; // Calculate the maximum page
                    if (nextPage <= maxPage) {
                        page = nextPage;
                    } else {
                        System.out.println("No more pages available.");
                    }
                    break;
                case 4:
                    // Set the page to the last page
                    page = (stockList.size() + pageSize - 1) / pageSize;
                    break;
                case 5:
                    System.out.print("Enter the page number: ");
                    int pageNumber = validation.inputIntValidation();
                    if (pageNumber >= 1) {
                        page = pageNumber;
                    } else {
                        System.out.println("Invalid page number. Please enter a positive number.");
                    }
                    break;
                default:
                    System.out.println("Back to the main menu...");
            }
            if (opt > 5 || opt < 1){
                break;
            }
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
    public boolean display(Scanner input, List<Product> products, String transactionFile, String dataSource, String dir){
        boolean isTrue = true;
>>>>>>> 2645b29c2157674d81a1bf3f7321ed0b63fb5d84

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
