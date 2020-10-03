package com.alexSwing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alexSwing.model.BookType;
import com.alexSwing.util.StringUtil;

/**
 * Book Type DAO
 * @author liu.zehu
 *
 */
public class BookTypeDao {

	/**
	 * Add a Book Type
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, BookType bookType) throws Exception{
		String sql = "insert into t_booktype values(null, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * Search Book Types
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, BookType bookType) throws Exception{
		StringBuilder sb = new StringBuilder("select * from t_bookType");
		if (StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" and bookTypeName like '%" + bookType.getBookTypeName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * Delete a Book Type
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String id) throws Exception {
		String sql = "delete from t_bookType where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * Update a Book Type
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, BookType bookType) throws Exception {
		String sql = "update t_bookType set bookTypeName = ?, bookTypeDesc = ? where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		pstmt.setInt(3, bookType.getId());
		return pstmt.executeUpdate();
	}
	
	
	
	
	
	
	
}













