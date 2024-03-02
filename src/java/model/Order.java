/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author THevY
 */
public class Order {
    private int orderId;
    private User userId;
    private String address;
    private String addressReiceive;
    private String status;
    private String paying;
    private String statusPaying;
    private double moneyPayed;
    private double moneyRemain;
    private Date dayOfOrder;
    private Date dayOfDeli;
    private List<OrderDetail> orderList;

    public Order() {
    }

    public List<OrderDetail> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDetail> orderList) {
        this.orderList = orderList;
    }

    public Order(User userId, String address, String addressReiceive, String status, double moneyRemain) {
        this.userId = userId;
        this.address = address;
        this.addressReiceive = addressReiceive;
        this.status = status;
        this.moneyRemain = moneyRemain;
    }
    public Order(int orderId, User userId, String address, String addressReiceive, String status, double moneyRemain) {
        this.orderId = orderId;
        this.userId = userId;
        this.address = address;
        this.addressReiceive = addressReiceive;
        this.status = status;
        this.moneyRemain = moneyRemain;
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressReiceive() {
        return addressReiceive;
    }

    public void setAddressReiceive(String addressReiceive) {
        this.addressReiceive = addressReiceive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaying() {
        return paying;
    }

    public void setPaying(String paying) {
        this.paying = paying;
    }

    public String getStatusPaying() {
        return statusPaying;
    }

    public void setStatusPaying(String statusPaying) {
        this.statusPaying = statusPaying;
    }

    public double getMoneyPayed() {
        return moneyPayed;
    }

    public void setMoneyPayed(double moneyPayed) {
        this.moneyPayed = moneyPayed;
    }

    public double getMoneyRemain() {
        return moneyRemain;
    }

    public void setMoneyRemain(double moneyRemain) {
        this.moneyRemain = moneyRemain;
    }

    public Date getDayOfOrder() {
        return dayOfOrder;
    }

    public void setDayOfOrder(Date dayOfOrder) {
        this.dayOfOrder = dayOfOrder;
    }

    public Date getDayOfDeli() {
        return dayOfDeli;
    }

    public void setDayOfDeli(Date dayOfDeli) {
        this.dayOfDeli = dayOfDeli;
    }

   
@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(orderId, other.orderId);
	}
}