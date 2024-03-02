/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cart;
import model.CartItem;
import model.Category;
import model.Product;
import model.User;

/**
 *
 * @author THevY
 */
public class CartDAO {
    public void addToCart(String pID, Cart cart){
        new CartItemDAO().addCartItem(1, pID, cart.getCartId());
    }
    
    public int createNewCart(String userId){
        int result = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "INSERT INTO cart(userid)"
				+ "VALUES (?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userId);
			result = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
    public Cart selectById(Cart c) {
       Cart result = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "SELECT * FROM cart WHERE cartid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, c.getCartId());

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int cartId = rs.getInt("cartid");
				String userId = rs.getString("userid");

				
				User user = (new UserDAO().selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", 0)));

				 result  =  new Cart(user, cartId);
				break;
                        }
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
    }
}
