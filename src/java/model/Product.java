/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author THevY
 */
public class Product {
    private String productId;
    private String productName;
    private String image;
    private double price;
    private int quantity;
    private Category typeOfBook;
    private String descripe;

    public Product() {
    }

    public Product(String productId, String productName, String image, double price, int quantity, Category typeOfBook, String descripe) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.typeOfBook = typeOfBook;
        this.descripe = descripe;
    }

    public Product(String productId) {
        this.productId = productId;
    }

  

   

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getTypeOfBook() {
        return typeOfBook;
    }

    public void setTypeOfBook(Category typeOfBook) {
        this.typeOfBook = typeOfBook;
    }

   

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", image=" + image + ", price=" + price + ", quantity=" + quantity + ", typeOfBook=" + typeOfBook + ", descripe=" + descripe + '}';
    }

}