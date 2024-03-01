package model;

import java.time.LocalDate;

public class Product {
    private String codeOfProduct;
<<<<<<< HEAD
    private int id;
=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
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

<<<<<<< HEAD
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
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
