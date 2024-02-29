package model;

import java.time.LocalDate;

public class Product {
    private String codeOfProduct;
    private int id;
    private String name;
    private int qty;
    private float price;
    LocalDate currentDate;
    public Product(){}
    public Product(String codeOfProduct,String name, float price,int qty,  LocalDate currentDate){

        this.codeOfProduct = codeOfProduct;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.currentDate = currentDate;


    }

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public void setCodeOfProduct(String codeOfProduct) {
        codeOfProduct = codeOfProduct;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
