/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author THevY
 */
public class Cart {
    private User userId;
    private int  cartId;

    public Cart(User userId, int cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }

    public Cart() {
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Cart{" + "userId=" + userId + ", cartId=" + cartId + '}';
    }
    
}
