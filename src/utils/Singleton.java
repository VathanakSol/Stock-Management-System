package utils;

import service.ProductService;
import service.ProductServiceImp;
import view.ProductView;

public class Singleton {
    private static ProductService productService;
    private static ProductView productView;

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
}
