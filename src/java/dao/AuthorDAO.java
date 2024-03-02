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

import java.sql.Date;
import model.Author;

/**
 *
 * @author THevY
 */
public class AuthorDAO implements DAOInterface<Author>{
   
    private ArrayList<Author> data = new ArrayList<>();
    
    @Override
    public ArrayList<Author> selectAll() {
        
       ArrayList<Author> result = new ArrayList<Author>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM author";
			PreparedStatement st = con.prepareStatement(sql);
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			// Bước 4:
			while(rs.next()) {
				String authorId = rs.getString("authorid");
				String name = rs.getString("name");
				Date dayOfBirth = rs.getDate("dob");
				String bio = rs.getString("bio");
				
				Author author = new Author(authorId, name, dayOfBirth, bio);
				result.add(author);
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
    public Author selectById(Author t) {
              
       Author result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM author WHERE authorid =?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getAuthorId());
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			// Bước 4:
			while(rs.next()) {
				String authorId = rs.getString("authorid");
				String name = rs.getString("name");
				Date dayOfBirth = rs.getDate("dob");
				String bio = rs.getString("bio");
				
				result = new Author(authorId, name, dayOfBirth, bio);
				break;
			}
			
			// Bước 5:
//			DBUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
    }

//    @Override
//    public int insert(Author t) {
//        if(this.selectById(t)==null){
//        this.data.add(t);
//        return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public int insertAll(ArrayList<Author> arr) {
//        int count=0;
//        for (Author author : arr) {
//            count +=this.insert(author);
//        }
//        return count;
//    }
//
//    @Override
//    public int delete(Author t) {
//        if(this.selectById(t)!=null){
//        this.data.remove(t);
//        return 1;
//        }
//        return 0;
//    }
//    
//
//    @Override
//    public int deleteAll(ArrayList<Author> arr) {
//        int count=0;
//        for (Author author : arr) {
//            count +=this.delete(author);
//        }
//        return count;
//    }
//
//    @Override
//    public int update(Author t) {
//        if(this.selectById(t)!=null){
//        this.data.remove(t);
//        this.data.add(t);
//        return 1;
//        }
//        return 0;
//    }
//    

    @Override
    public int insert(Author t) {
       int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO author (authorid, name, dob, bio) "+
					" VALUES (?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getAuthorId());
			st.setString(2, t.getName());
			st.setDate(3, (Date) t.getDayOfBirth());
			st.setString(4, t.getBio());
			
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
    public int insertAll(ArrayList<Author> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Author t) {
       int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from author "+
					 " WHERE authorid=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getAuthorId());
			
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
    public int deleteAll(ArrayList<Author> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Author t) {
       int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE author "+
					 " SET " +
					 " name=?"+
					 ", dob=?"+
					 ", bio=?"+
					 " WHERE authorid=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setDate(2, (Date) t.getDayOfBirth());
			st.setString(3, t.getBio());
			st.setString(4, t.getAuthorId());
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
       AuthorDAO a = new AuthorDAO();
//       ArrayList<Author> rs = a.selectAll();
//        for (Author author : rs) {
//            System.out.println(author.toString());
//        }
//        Author author = a.selectById(new Author("1", "", null, ""));
//        System.out.println(author);
//        
        Author a_new = new Author("2", "lekhoai", new Date(2002-1900, 2, 2), "");
        a.delete(a_new);
        Author author = a.selectById(new Author("1", "", null, ""));
        System.out.println(author);
        author.setBio("bio had changed!!");
        a.update(author);
    }
}
