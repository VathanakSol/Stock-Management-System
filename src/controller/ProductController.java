package controller;

import model.Product;
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

public class ProductController {
    Product product = new Product();
    Validation validation = new Validation();
    ProductServiceImp productServiceImp = new ProductServiceImp();
    private final ProductService productService;
    private final ProductView productView;




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

}
