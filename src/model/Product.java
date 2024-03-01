package model;

public class Product {
    private String id;
    private String name;
    private int qty;
    private float price;

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
