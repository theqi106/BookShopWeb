/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cart;
import model.CartItem;

import model.Category;

import model.Order;
import model.Product;
import model.User;

/**
 *
 * @author THevY
 */
public class CartItemDAO {
    
    
    public int addCartItem(int quantity, String productId, int cartId){
        int result = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "INSERT INTO cartitem(quantity,productid,cartid)"
				+ "VALUES ( ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, quantity);
			st.setString(2, productId);
			st.setInt(3, cartId);
			

			result = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
    public ArrayList<CartItem> selectAllByUserId(int cartID) {
        ArrayList<CartItem> result = new ArrayList<CartItem>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM cartitem WHERE cartid=?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
                       st.setInt(1, cartID);
			ResultSet rs = st.executeQuery();
			// Bước 4:
			while (rs.next()) {
				int cartItemId = rs.getInt("cartitemid");
				int quantity = rs.getInt("quantity");
                                String productId = rs.getString("productid");
				int cartId = rs.getInt("cartid");
				

				
				Product product = (new ProductDAO().selectById(new Product(productId,"", "", 0, 0, null, "")));
                                User user = (new UserDAO().selectById(new User("", "", "", "", "", "", "", "", null, "", "", 0)));
                                Cart cart = (new CartDAO().selectById(new Cart(user, cartId)));
				CartItem ci = new CartItem(cartItemId, quantity, product, cart);
				result.add(ci);}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
        }
    public void update(String cartItemId, String quantity) {

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE cartitem " + " SET " + " quantity=?" + " WHERE cartitemid=?";

            PreparedStatement st = con.prepareStatement(sql);

            
            st.setString(1, quantity);
            st.setString(2, cartItemId);
            

            // Bước 3: thực thi câu lệnh SQL
            st.executeUpdate();

            // Bước 4:
         

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void delete(Cart c, String id) {
        
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from cartitem " + " WHERE cartid=? AND productid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, c.getCartId());
                        st.setString(2, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.executeUpdate();

			// Bước 4:
			

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }
    public void delete(Cart c) {
        
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from cartitem " + " WHERE cartid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, c.getCartId());
                        

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.executeUpdate();

			// Bước 4:
			

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }
    

}
