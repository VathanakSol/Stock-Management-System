package controller;

import service.ProductService;
import utils.Singleton;
import view.ProductView;

public class ProductController {
    private final ProductService productService;
    private final ProductView productView;



    public ProductController(){
        productService = Singleton.getProductServiceImp();
        productView = Singleton.getProductView();
    }

    public void display(){
        productService.displayAllProducts();
        productView.showMenu();
    }

}
