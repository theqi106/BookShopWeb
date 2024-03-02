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
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Order;

import model.OrderDetail;
import model.Product;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author THevY
 */
public class OrderDetailDAO implements DAOInterface<OrderDetail> {

    private ArrayList<OrderDetail> data = new ArrayList<>();

    @Override
    public ArrayList<OrderDetail> selectAll() {
        ArrayList<OrderDetail> ketQua = new ArrayList<OrderDetail>();

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM chitietdonhang";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String orderDetailId = rs.getString("orderdetailid");
                String orderId = rs.getString("orderid");
                String productId = rs.getString("productId");
                int quantity = rs.getInt("quantity");
                double giagoc = rs.getDouble("giagoc");
                double giamgia = rs.getDouble("giamgia");
                double giaban = rs.getDouble("giaban");
                double vat = rs.getDouble("vat");
                double total = rs.getDouble("total");

                //			Order order = new OrderDAO().selectById(new Order(orderId, null, "", "", "", "","", 0, 0, null, null));
//				Product product = new ProductDAO().selectById(new Product("", "", null, 0, 0, 0, 0, 0, null, "", ""));
//				OrderDetail odd = new OrderDetail(orderDetailId, order, product, quantity, giagoc, giamgia, giaban,
//						vat, total);
//				ketQua.add(odd);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    public List<OrderDetail> selectByOrderId(int t) {
        List<OrderDetail> result = new ArrayList<OrderDetail>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM orderdetail WHERE orderid=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int orderDetailId = rs.getInt("orderdetailid");
                int orderId = rs.getInt("orderid");
                String productId = rs.getString("productid");
                int quantity = rs.getInt("quantity");
                double giaban = rs.getDouble("giaban");
                double total = rs.getDouble("total");
                Product product = new ProductDAO().selectById(new Product(productId));

                OrderDetail odd = new OrderDetail(product, quantity, giaban, total);
                result.add(odd);
            }
            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public List<OrderDetail> insert(ArrayList<CartItem> t, int orderId) {
        List<OrderDetail> orderList = new ArrayList<>();
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();
            for (CartItem cDetail : t) {

                // Bước 2: tạo ra đối tượng statement
                String sql = "INSERT INTO orderdetail (productid, quantity ,giaban,total ,orderid) "
                        + " VALUES (?,?,?,?,?)";

                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, cDetail.getProductId().getProductId());
                st.setInt(2, cDetail.getQuantity());
                st.setDouble(3, cDetail.getProductId().getPrice());
                st.setDouble(4, cDetail.getQuantity() * cDetail.getProductId().getPrice());
                st.setInt(5, orderId);

                // Bước 3: thực thi câu lệnh SQL
                st.executeUpdate();
                orderList.add(convertCartToOrder(cDetail));
            }
            // Bước 4:

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return orderList;
    }

    public OrderDetail convertCartToOrder(CartItem c) {
        return new OrderDetail(c.getProductId(), c.getQuantity(), c.getProductId().getPrice() * c.getQuantity());
    }

    @Override
    public int insertAll(ArrayList<OrderDetail> arr) {
        int count = 0;
        for (OrderDetail OrderDetail : arr) {
            count += this.insert(OrderDetail);
        }
        return count;
    }

    @Override
    public int delete(OrderDetail t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE from orderdetail " + " WHERE orderdetailid=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getOrderDetailId());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            result = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + result + " dòng bị thay đổi!");

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<OrderDetail> arr) {
        int count = 0;
        for (OrderDetail OrderDetail : arr) {
            count += this.delete(OrderDetail);
        }
        return count;
    }

    public int deleteAll(Order order) {
        int count = 0;
        for (OrderDetail OrderDetail : data) {
            if (OrderDetail.getOrder().equals(order)) {
                this.delete(OrderDetail);
            }
        }
        return count;
    }

    @Override
    public int update(OrderDetail t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE orderdetail SET orderid=?, productid=?, quantity=?, giagoc=?, discount=?, giaban=?, vat=?, total=?"
                    + " WHERE machitietdonhang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getOrder().getOrderId());
            st.setString(2, t.getProduct().getProductId());
            st.setInt(3, t.getQuantity());
            st.setDouble(4, t.getCost());
            st.setDouble(5, t.getDiscount());
            st.setDouble(6, t.getPrice());
            st.setDouble(7, t.getVat());
            st.setDouble(8, t.getTotalPayment());
            st.setInt(9, t.getOrderDetailId());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(OrderDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderDetail selectById(OrderDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
