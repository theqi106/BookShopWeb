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
import model.Author;
import model.Category;
import model.Product;

/**
 *
 * @author THevY
 */
public class ProductDAO implements DAOInterface<Product> {

    private ArrayList<Product> data = new ArrayList<>();

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<Product> result = new ArrayList<Product>();
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM product";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String productid = rs.getString("productid");
                String productname = rs.getString("productname");
                String image = rs.getString("image");
                double price = rs.getDouble("giaban");
                int quantity = (int) rs.getDouble("quantity");
                String categoryid = rs.getString("categoryid");
                String describe = rs.getString("describ");

                Category category = (new CategoryDAO().selectById(new Category(categoryid, "")));

                Product p = new Product(productid, productname, image, price, quantity,
                        category, describe);
                result.add(p);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Product selectById(Product t) {
        Product result = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM product WHERE productid=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getProductId());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productid = rs.getString("productid");
                String productname = rs.getString("productname");

                String image = rs.getString("image");
                double price = rs.getDouble("giaban");
                int quantity = (int) rs.getDouble("quantity");
                String categoryid = rs.getString("categoryid");
                String describe = rs.getString("describ");

                Category category = (new CategoryDAO().selectById(new Category(categoryid, "")));

                result = new Product(productid, productname, image, price, quantity,
                        category, describe);
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

    public ArrayList<Product> selectProductByCategoryId(String cid) {
        ArrayList<Product> result = new ArrayList<Product>();
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM product WHERE categoryid=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cid);
            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String productid = rs.getString("productid");
                String productname = rs.getString("productname");
                String image = rs.getString("image");
                double price = rs.getDouble("giaban");
                int quantity = (int) rs.getDouble("quantity");
                String categoryid = rs.getString("categoryid");
                String describe = rs.getString("describ");

                Category category = (new CategoryDAO().selectById(new Category(categoryid, "")));

                Product p = new Product(productid, productname, image, price, quantity,
                        category, describe);
                result.add(p);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<Product> searchProductByName(String txtSearch) {
        ArrayList<Product> result = new ArrayList<Product>();
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM product\n"
                    + "WHERE productname LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String productid = rs.getString("productid");
                String productname = rs.getString("productname");

                String image = rs.getString("image");

                double price = rs.getDouble("giaban");
                int quantity = (int) rs.getDouble("quantity");
                String categoryid = rs.getString("categoryid");

                String describe = rs.getString("describ");

                Category category = (new CategoryDAO().selectById(new Category(categoryid, "")));

                Product p = new Product(productid, productname, image, price, quantity,
                        category, describe);
                result.add(p);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(Product t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO product (productid,productname,image,"
                    + " giaban, quantity, categoryid, describ) "
                    + " VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getProductId());
            st.setString(2, t.getProductName());
            st.setString(3, t.getImage());
            st.setDouble(4, t.getPrice());
            st.setInt(5, t.getQuantity());
            st.setString(6, t.getTypeOfBook().getCategoryId());
            st.setString(7, t.getDescripe());

            // Bước 3: thực thi câu lệnh SQL
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
    public int insertAll(ArrayList<Product> arr) {
        int count = 0;
        for (Product Product : arr) {
            count += this.insert(Product);
        }
        return count;
    }

    @Override
    public int delete(Product t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE from product " + " WHERE productid=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getProductId());

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
    public int deleteAll(ArrayList<Product> arr) {
        int count = 0;
        for (Product Product : arr) {
            count += this.delete(Product);
        }
        return count;
    }

    @Override
    public int update(Product t) {
        int result = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE product " + " SET " + "productname=?, image=?,  "
                    + "giaban=?, quantity=?, categoryid=?,  describ=?" + " WHERE productid=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getProductName());
            st.setString(2, t.getImage());
            st.setDouble(3, t.getPrice());
            st.setInt(4, t.getQuantity());
            st.setString(5, t.getTypeOfBook().getCategoryId());

            st.setString(6, t.getDescripe());
            st.setString(7, t.getProductId());
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

    public Product getById(String id) {
        Product result = new Product();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM product WHERE productid=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productid = rs.getString("productid");
                String productname = rs.getString("productname");
                String image = rs.getString("image");
                double price = rs.getDouble("giaban");
                int quantity = (int) rs.getDouble("quantity");
                String categoryid = rs.getString("categoryid");
                String describe = rs.getString("describ");

                Category category = (new CategoryDAO().selectById(new Category(categoryid, "")));

                result = new Product(productid, productname, image, price, quantity,
                        category, describe);
                return result;

            }
            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public void delete(String id) {

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE FROM product WHERE productid =? ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);

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

    public void insert(String id, String name, String image, int price, int quantity, String category, String description) {

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO product(productid,productname,image,giaban,quantity,categoryid,describ) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, image);
            st.setDouble(4, price);
            st.setInt(5, quantity);
            st.setString(6, category);
            st.setString(7, description);

            // Bước 3: thực thi câu lệnh SQL
            st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void edit(String id, String name, String image, int price, int quantity, String category, String description) {

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE product " + " SET " + "productname=?, image=?,  "
                    + "giaban=?, quantity=?, categoryid=?,  describ=?" + " WHERE productid=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, image);
            st.setDouble(3, price);
            st.setInt(4, quantity);
            st.setString(5, category);
            st.setString(6, description);
            st.setString(7, id);

            // Bước 3: thực thi câu lệnh SQL
            st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getTotalProduct() {

        String sql = "SELECT COUNT(*) FROM product";
        try {
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Product> pagingProduct(int index) {
        ArrayList<Product> result = new ArrayList<>();
        String sql = "SELECT * FROM product \n"
                + "ORDER BY productid \n"
                + "LIMIT 4 OFFSET ?";
        try {
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, (index - 1) * 4);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productid = rs.getString("productid");
                String productname = rs.getString("productname");
                String image = rs.getString("image");
                double price = rs.getDouble("giaban");
                int quantity = (int) rs.getDouble("quantity");
                String categoryid = rs.getString("categoryid");
                String describe = rs.getString("describ");

                Category category = (new CategoryDAO().selectById(new Category(categoryid, "")));
                Product p = new Product(productid, productname, image, price, quantity,
                        category, describe);
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        ProductDAO pDAO = new ProductDAO();
//        pDAO.delete("12");
        pDAO.delete("12");
//        List<Product> p = pDAO.selectProductByCategoryId("1");
//        for (Product product : p) {
//            System.out.println(product);
//        }

    }
}
