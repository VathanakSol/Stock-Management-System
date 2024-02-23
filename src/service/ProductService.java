package service;

public interface ProductService {
    void displayAllProducts();
    void displayByCode(int id);
    void createNewProduct();
    void removeProduct(int id);
    void searchByName(String name);
    void helpMenu();
}
