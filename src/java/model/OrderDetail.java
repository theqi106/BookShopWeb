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
public class OrderDetail {
    private int orderDetailId;
    private Order order;
    private Product product;
    private int quantity;
    private double giaGoc;
    private double discount;
    private double giaBan;
    private double vat;
    private double totalPayment;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, Order order, Product product, int quantity, double giaGoc, double discount, double giaBan, double vat, double totalPayment) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.giaGoc = giaGoc;
        this.discount = discount;
        this.giaBan = giaBan;
        this.vat = vat;
        this.totalPayment = totalPayment;
    }

    public OrderDetail(Order order, Product product, int quantity, double giaBan, double totalPayment) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.giaBan = giaBan;
        this.totalPayment = totalPayment;
    }
    

     public OrderDetail(Product product, int quantity, double total) {
        this.product = product;
        this.quantity = quantity;
        this.totalPayment = total;
    }

    public OrderDetail(Product product, int quantity, double giaBan, double totalPayment) {
        this.product = product;
        this.quantity = quantity;
        this.giaBan = giaBan;
        this.totalPayment = totalPayment;
    }
     

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
     
    

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return giaGoc;
    }

    public void setCost(double cost) {
        this.giaGoc = giaGoc;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return giaBan;
    }

    public void setPrice(double price) {
        this.giaBan = giaBan;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    
}
