package service;

public interface ProductService {
    void displayAllProducts();
    void displayByCode(int id);
    void createNewProduct();
    void removeProduct();
    void searchByName();
    void helpMenu();
}
