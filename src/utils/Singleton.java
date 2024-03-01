package utils;

<<<<<<< HEAD
import controller.ProductController;
=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
import service.ProductService;
import service.ProductServiceImp;
import view.ProductView;

public class Singleton {
    private static ProductService productService;
    private static ProductView productView;
<<<<<<< HEAD
    private static ProductController productController;
=======

>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
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
<<<<<<< HEAD

    public static ProductController getProductController() {
        if (productController == null) {
            productController = new ProductController();
        }
        return productController;
    }
=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
}
