package com.alexSwing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.alexSwing.model.BookType;

/**
 * Book Type DAO
 * @author liu.zehu
 *
 */
public class BookTypeDao {

	/**
	 * Add a book type
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
}
