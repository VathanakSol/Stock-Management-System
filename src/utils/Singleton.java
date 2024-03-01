package utils;

import controller.ProductController;
import service.ProductService;
import service.ProductServiceImp;
import view.ProductView;

public class Singleton {
    private static ProductService productService;
    private static ProductView productView;
    private static ProductController productController;
    public static ProductService getProductServiceImp() {
        if (productService == null) {
            productService = new ProductServiceImp();
        }
        return productService;
    }

    public static ProductView getProductView() {
        if (productView == null) {
            productView = new ProductView();
        }
        return productView;
    }

    public static ProductController getProductController() {
        if (productController == null) {
            productController = new ProductController();
        }
        return productController;
    }
}