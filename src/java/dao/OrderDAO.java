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
import java.util.ArrayList;
import model.Order;
import model.User;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.OrderDetail;

/**
 *
 * @author THevY
 */
public class OrderDAO implements DAOInterface<Order> {

    private ArrayList<Order> data = new ArrayList<>();

    @Override
    public ArrayList<Order> selectAll() {
        ArrayList<Order> result = new ArrayList<Order>();
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM orders";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                String userId = rs.getString(2);
                String addressBuy = rs.getString(3);
                String addressReceive = rs.getString(4);
                String status = rs.getString(5);
                double moneyRemain = rs.getDouble(6);

                User user = new UserDAO()
                        .selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", 0));
                Order od = new Order(orderId, user, addressBuy, addressReceive, status, moneyRemain);
                result.add(od);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Order selectById(Order t) {
        Order result = null;
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM order WHERE orderid = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getOrderId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String orderId = rs.getString(1);
                String userId = rs.getString(2);
                String addressBuy = rs.getString(3);
                String addressReceive = rs.getString(4);
                String status = rs.getString(5);
                String pay = rs.getString(6);
                double moneyPayed = rs.getDouble(7);
                double moneyRemain = rs.getDouble(8);
                Date dayOfOrder = rs.getDate(9);
                Date dayOfDeli = rs.getDate(10);

                User user = new UserDAO()
                        .selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", 0));
//				Order order = new Order(orderId, user, addressBuy, addressReceive, status, pay, status, moneyPayed, moneyRemain, dayOfOrder, dayOfDeli);

//				result = order;
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public Order selectByOrderId(int t) {
        Order result = null;
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM orders WHERE orderid = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String orderId = rs.getString(1);
                String userId = rs.getString(2);
                String addressBuy = rs.getString(3);
                String addressReceive = rs.getString(4);
                String status = rs.getString(5);

                double moneyRemain = rs.getDouble(6);

                User user = new UserDAO()
                        .selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", 0));
                result = new Order(t, user, addressBuy, addressReceive, status, moneyRemain);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public List<Order> selectByUserId(User u) {
        List<Order> result = new ArrayList<>();
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM orders WHERE userid = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, u.getUserId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                String userId = rs.getString(2);
                String addressBuy = rs.getString(3);
                String addressReceive = rs.getString(4);
                String status = rs.getString(5);
                double moneyRemain = rs.getDouble(6);

//                User user = new UserDAO()
//                        .selectById(new User(userId, "", "", "", "", "", "", "", null, "", "", 0));
                Order order = new Order(orderId, u, addressBuy, addressReceive, status, moneyRemain);
                result.add(order);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public Order insert(Cart t, User u, double total) throws SQLException {

        Order result = new Order();
        Connection con = JDBCUtil.getConnection();
        String sql = "INSERT INTO orders (userid,addressbuy,addressreceive,statuspay,moneyremain) VALUES (?,?,?,?,?)";
        try (
                PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, t.getUserId().getUserId());
            st.setString(2, u.getAddress());
            st.setString(3, u.getAddressBuying());
            st.setString(4, "pending");
            st.setDouble(5, total);

            // Execute the INSERT statement
            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                // Get the generated keys (including orderid)
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Retrieve the generated orderid
                        int generatedOrderId = rs.getInt(1);

                        // Now you can use the generatedOrderId as needed
                        System.out.println("Generated Order ID: " + generatedOrderId);

                        // Create an Order object if needed
                        result = new Order(t.getUserId(), u.getAddress(), u.getAddressReiceive(), "pending", total);
                        result.setOrderId(generatedOrderId); // Assuming there's a setter for orderid in the Order class

                        // Additional logic or handling if necessary
                    } else {
                        System.out.println("Failed to retrieve the generated Order ID.");
                        // Handle the case where no orderid is returned
                    }
                }
            } else {
                System.out.println("Failed to insert the order.");
                // Handle the case where the insert operation failed
            }
            return result;
        }

    }

//    @Override
//    public int insertAll(ArrayList<Order> arr) {
//        int count=0;
//        for (Order Order : arr) {
//            count +=this.insert(Order);
//        }
//        return count;
//    }
    @Override
    public int delete(Order t) {
        int result = 0;
        Connection con = JDBCUtil.getConnection();
        String sql = "DELETE FROM order WHERE orderid = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getOrderId());
            result = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteById(int t) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = " DELETE FROM orders WHERE orderid =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t);
            st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int deleteAll(ArrayList<Order> arr) {
        int count = 0;
        for (Order Order : arr) {
            count += this.delete(Order);
        }
        return count;
    }

    @Override
    public int update(Order t) {
        int result = 0;
        Connection con = JDBCUtil.getConnection();

        String sql = "UPDATE order" + " SET " + "userid=?" + ", addressbuy=?" + ",addressreceive=?"
                + ",status=?" + ",pay=?" + ",moneypayed=?" + ",moneyremain=?" + ",dayoforder=?"
                + ",dayofdeli=?" + " WHERE orderid=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUserId().getUserId());
            st.setString(2, t.getAddress());
            st.setString(3, t.getAddressReiceive());
            st.setString(4, t.getStatus());
            st.setString(5, t.getPaying());
            st.setDouble(6, t.getMoneyPayed());
            st.setDouble(7, t.getMoneyRemain());
            st.setDate(8, (Date) t.getDayOfOrder());
            st.setDate(9, (Date) t.getDayOfDeli());
            st.setInt(10, t.getOrderId());

            result = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int updateById(int id,String status) {
        int result = 0;
        Connection con = JDBCUtil.getConnection();

        String sql = "UPDATE orders" + " SET " + "statuspay=?" 
                + " WHERE orderid=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, status);
            
            st.setInt(2,id);
            

            result = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertAll(ArrayList<Order> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        OrderDAO oDAO = new OrderDAO();
        oDAO.deleteById(8);
    }

}
