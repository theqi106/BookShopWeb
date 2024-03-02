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
import model.Category;

/**
 *
 * @author THevY
 */
public class CategoryDAO implements DAOInterface<Category>{
   
    private ArrayList<Category> data = new ArrayList<>();
    @Override
    public ArrayList<Category> selectAll() {
        ArrayList<Category> result = new ArrayList<Category>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM category";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String categoryId = rs.getString("categoryid");
				String categoryName = rs.getString("categoryname");

				Category category = new Category(categoryId, categoryName);
				result.add(category);
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
    public Category selectById(Category t) {
        Category result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM category WHERE categoryid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCategoryId());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String categoryId = rs.getString("categoryid");
				String categoryName = rs.getString("categoryname");

				result = new Category(categoryId, categoryName);
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

    @Override
    public int insert(Category t) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO category (categoryid, categoryname) "+
					" VALUES (?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCategoryId());
			st.setString(2, t.getCategoryName());
			
			// Bước 3: thực thi câu lệnh SQL
			result = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ result+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
    }

    @Override
    public int insertAll(ArrayList<Category> arr) {
        int count=0;
        for (Category Category : arr) {
            count +=this.insert(Category);
        }
        return count;
    }

    @Override
    public int delete(Category t) {
       int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "SET FOREIGN_KEY_CHECKS=0;\n"
                                + "DELETE from product WHERE productid =?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCategoryId());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			result = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ result+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
    }
    

    @Override
    public int deleteAll(ArrayList<Category> arr) {
        int count=0;
        for (Category Category : arr) {
            count +=this.delete(Category);
        }
        return count;
    }

    @Override
    public int update(Category t) {
       int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE category "+
					 " SET " +
					 " categoryname=?"+
					 " WHERE categoryid=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCategoryName());
			st.setString(2, t.getCategoryId());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			result = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ result+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

    
    public static void main(String[] args) {
        CategoryDAO cDAO = new CategoryDAO();
        ArrayList<Category> list = cDAO.selectAll();
        for (Category category : list) {
            
        
        System.out.println(category);
    }
    }
}
