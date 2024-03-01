package model;

import java.time.LocalDate;

public class Product {
<<<<<<< HEAD
    private String codeOfProduct;
<<<<<<< HEAD
    private int id;
=======
>>>>>>> aa0b1a4d6f2dce684f65effe5cc4d3e8861ce6cc
=======
    private String id;
>>>>>>> 358575109c7aa64dfc83c74b187dc99c64e86bb6
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return
                id + "\t\t\t" + name + "\t\t\t" + qty + "\t\t\t" + price;
    }
}
