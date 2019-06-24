package com.SE.SalesPerfomance;
        



//  @ Project : �Ұ� �౹
//  @ File Name : DBManager.java
//  @ Date : 2019-05-20
//  @ Author : ���漷

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBManager {

	private static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul";
	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB �н�����

	public DBManager() {

	}

	/** DB���� �޼ҵ� */
	public Connection getConn() {

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/* productName �� totalPrice�� �������� �޼ҵ� */
	public Vector<Vector<String>> selectProductSellRecordInfo(String productCode) {

		Vector<Vector<String>> productInfos = new Vector<Vector<String>>();

		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String query = "Select productCode, totalPrice From product_has_sell where productCode = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> productInfo = new Vector<String>();
				productInfo.add(rs.getString("productCode"));
				productInfo.add(rs.getString("totalPrice"));
				productInfos.add(productInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return productInfos;
	}
	
	/* �Ⱓ���� totalPrice�� �������� �޼ҵ� */
	public Vector<Vector<String>> selectSellRecordInfo(String sellDate1,String sellDate2) {

		Vector<Vector<String>> dateInfos = new Vector<Vector<String>>();

		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String query = "Select sellDate, sum(totalPrice) From sell where sellDate between ? and ? AND cancel= 0;";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sellDate1);
			pstmt.setString(2, sellDate2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> dateInfo = new Vector<String>();
				 dateInfo.add(rs.getString("sellDate"));
				 dateInfo.add(rs.getString("sum(totalPrice)"));
				 dateInfos.add(dateInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return  dateInfos;
	}

	
	

}

    