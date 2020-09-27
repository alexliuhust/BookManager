package com.alexSwing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alexSwing.model.User;

/**
 * User DAO
 * @author liu.zehu
 *
 */
public class UserDao {
	
	/**
	 * Login Authentication
	 * @param con: Connection
	 * @param user: User
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception {
		User resultUser = null;
		String sql = "select * from t_user where userName = ? and password= ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getPassword());
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		
		return resultUser;
	}
}






