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
public class CartItem {
    private int cartItemId;
    private int quantity;
    private Product productId;
    private Cart cartId;

    public CartItem() {
    }

    public CartItem(int cartItemId, int quantity, Product productId, Cart cartId) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
        this.productId = productId;
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "CartItem{" + "cartItemId=" + cartItemId + ", quantity=" + quantity + ", productId=" + productId + ", cartId=" + cartId + '}';
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Cart getCartId() {
        return cartId;
    }

    public void setCartId(Cart cartId) {
        this.cartId = cartId;
    }
    
    
}
