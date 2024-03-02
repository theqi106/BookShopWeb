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
import model.Cart;


import model.User;

/**
 *
 * @author THevY
 */
public class UserDAO implements DAOInterface<User>{
   
    private ArrayList<User> data = new ArrayList<>();
    @Override
    public ArrayList<User> selectAll() {
       ArrayList<User> result = new ArrayList<User>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String userId = rs.getString("userid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String addressReceive = rs.getString("addressreceive");
				String addressBuy = rs.getString("addressbuy");
				Date dob = rs.getDate("dob");
				String telNum = rs.getString("telnum");
				String email = rs.getString("email");
				int isAdmin = rs.getInt("isadmin");

				User user = new User(userId, username, password, name, gender, address,
						addressReceive, addressBuy, dob, telNum, email, isAdmin);
				result.add(user);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
        }

    
    public Cart getCart(User user){
        Cart result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM cart WHERE userid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUserId());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int cartid = rs.getInt("cartid");
				String userid = rs.getString("userid");

				result = new Cart(user, cartid);
			}
                        
                        if (result == null){
                            new CartDAO().createNewCart(user.getUserId());
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
    public User selectById(User t) {
       User result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE userid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserId());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String userId = rs.getString("userid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String addressReceive = rs.getString("addressreceive");
				String addressBuy = rs.getString("addressbuy");
				Date dob = rs.getDate("dob");
				String telNum = rs.getString("telnum");
				String email = rs.getString("email");
				int isAdmin = rs.getInt("isadmin");

				result = new User(userId, username, password, name, gender, address,
						addressReceive, addressBuy, dob, telNum, email, isAdmin);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
    }
    public User selectByUsernameAndPassword(User t) {
       User result = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE username=? and password =?";
			PreparedStatement st = con.prepareStatement(sql);
                        
                        System.out.println(t.getUsername()+"/"+t.getPassword());
			st.setString(1, t.getUsername());
			st.setString(2, t.getPassword());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String userId = rs.getString("userid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				String addressReceive = rs.getString("addressreceive");
				String addressBuy = rs.getString("addressbuy");
				Date dob = rs.getDate("dob");
				String telNum = rs.getString("telnum");
				String email = rs.getString("email");
				int isAdmin = rs.getInt("isadmin");

				result = new User(userId, username, password, name, gender, address,
						addressReceive, addressBuy, dob, telNum, email, isAdmin);
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
    public int insert(User t) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO user (userid, username, password, name, gender, address, addressreceive, addressbuy, dob, telnum, email, isadmin) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserId());
			st.setString(2, t.getUsername());
			st.setString(3, t.getPassword());
			st.setString(4, t.getName());
			st.setString(5, t.getGender());
			st.setString(6, t.getAddress());
			st.setString(7, t.getAddressReiceive());
			st.setString(8, t.getAddressBuying());
			st.setDate(9, (Date) t.getBirth());
			st.setString(10, t.getTelNum());
			st.setString(11, t.getEmail());
			st.setInt(12, t.getIsAdmin());

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
    public int insertAll(ArrayList<User> arr) {
        int count=0;
        for (User User : arr) {
            count +=this.insert(User);
        }
        return count;
    }

    @Override
    public int delete(User t) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from user " + " WHERE userid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUserId());

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
     public int deleteById(String id ) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from user " + " WHERE userid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			result = st.executeUpdate();

			// Bước 4:
			

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
    }
    

    @Override
    public int deleteAll(ArrayList<User> arr) {
        int count=0;
        for (User User : arr) {
            count +=this.delete(User);
        }
        return count;
    }

    @Override
    public int update(User t) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE user " + " SET " + " username=?" + ", password=?" + ", name=?" + ", gender=?"
					+ ", address=?" + ", addressreceive=?" + ", addressbuy=?" + ", dob=?" + ", telnum=?"
					+ ", email=?" + ", isadmin=?" + " WHERE userid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUsername());
			st.setString(2, t.getPassword());
			st.setString(3, t.getName());
			st.setString(4, t.getGender());
			st.setString(5, t.getAddress());
			st.setString(6, t.getAddressReiceive());
			st.setString(7, t.getAddressBuying());
			st.setDate(8, (Date) t.getBirth());
			st.setString(9, t.getTelNum());
			st.setString(10, t.getEmail());
			st.setInt(11, t.getIsAdmin());
			st.setString(12, t.getUserId());
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
    
    public boolean checkUsername(String  username) {
       boolean result = false;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE username=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				result =  true;
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
    }
    public boolean changePassword(User t) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE user " + " SET "  + " password=?"  + " WHERE userid=?";

			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, t.getPassword());
			
			st.setString(2, t.getUserId());
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

		return result>0;
	}
    public int updateInfor(User t) {
        int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE user " + " SET " + " name=?" + ", gender=?"
					+ ", address=?" + ", addressreceive=?" + ", addressbuy=?" + ", dob=?" + ", telnum=?"
					+ ", email=?" + ", isadmin=?" + " WHERE userid=?";

			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, t.getName());
			st.setString(2, t.getGender());
			st.setString(3, t.getAddress());
			st.setString(4, t.getAddressReiceive());
			st.setString(5, t.getAddressBuying());
			st.setDate(6, (Date) t.getBirth());
			st.setString(7, t.getTelNum());
			st.setString(8, t.getEmail());
			st.setInt(9, t.getIsAdmin());
			st.setString(10, t.getUserId());
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
    
}

