package utils;

<<<<<<< HEAD
<<<<<<< HEAD
import controller.ProductController;
=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
import controller.ProductController;
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
import service.ProductService;
import service.ProductServiceImp;
import view.ProductView;

public class Singleton {
    private static ProductService productService;
    private static ProductView productView;
<<<<<<< HEAD
<<<<<<< HEAD
    private static ProductController productController;
=======

>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
    private static ProductController productController;
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
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
<<<<<<< HEAD
=======
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6

    public static ProductController getProductController() {
        if (productController == null) {
            productController = new ProductController();
        }
        return productController;
    }
<<<<<<< HEAD
=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
}
