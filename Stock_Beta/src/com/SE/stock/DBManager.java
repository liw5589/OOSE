package com.SE.stock;

import com.SE.Warehousing.*;
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

	/*�԰�ÿ� ��� ������ ��� ����� �� ��� �ö�*/
	public boolean insertStockInfo(WarehousingInfo warehousinginfo) {
		

		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		try {
			String expidate = warehousinginfo.getWarehousingDate();
			int year = Integer.parseInt(expidate.substring(0,4)) + 3;
			String mmdd = expidate.substring(5,10);
			String full =  year + "-"+ mmdd;
			System.out.println(warehousinginfo.getProductCode());
			con = getConn();
			String query = "insert into stock values (?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, warehousinginfo.getProductCode());
			pstmt.setString(2,warehousinginfo.getProductName());
			int result = (warehousinginfo.getQty() * Integer.parseInt(warehousinginfo.getUnit()));
			pstmt.setInt(3,result);
			pstmt.setInt(4,0);
			pstmt.setString(5,full);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
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

		return true;
	}
	
	/** ��������� �޼ҵ� */
	public boolean updateApproStock(String productCode,String approStock) {
		StockInfo data = new StockInfo();
		
		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���

		try {

			con = getConn();
			String query = "update stock set approStock = ? where productCode = ?;";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, approStock);
			System.out.println(pstmt);
			System.out.println("�����?");
			pstmt.executeUpdate();
			System.out.println("�����? ����");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
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

		return true;
	}

	/*TotalStockInfo�� ��� �޼ҵ� */
	public Vector<Vector<String>> selectTotalStock() {

		Vector<Vector<String>> stockInfos = new Vector<Vector<String>>();

		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String query = "select * from stock";
			pstmt= con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> stockInfo = new Vector<String>();
				stockInfo.add(rs.getString("productCode"));
				stockInfo.add(rs.getString("productName"));
				stockInfo.add(rs.getString("qty"));
				stockInfo.add(rs.getString("approStock"));
				stockInfo.add(rs.getString("expirationDate"));
				stockInfos.add(stockInfo);
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

		return stockInfos;
	}

	/* productCode �� Stockinfo�� �������� �޼ҵ� */
	public Vector<Vector<String>> selectStockInfoAsproductCode(String productCode) {

		Vector<Vector<String>> stockInfos = new Vector<Vector<String>>();

		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String query = "select * from stock where productCode=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> stockInfo = new Vector<String>();
				stockInfo.add(rs.getString("productCode"));
				stockInfo.add(rs.getString("productName"));
				stockInfo.add(rs.getString("qty"));
				stockInfo.add(rs.getString("approStock"));
				stockInfo.add(rs.getString("expirationDate"));
				stockInfos.add(stockInfo);
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

		return stockInfos;
	}
	
	/* ����������� Stockinfo�� �������� �޼ҵ� */
	public Vector<Vector<String>> selectStockInfoAsexpirationDate(String expirationDate_1,String expirationDate_2) {

		Vector<Vector<String>> stockInfos = new Vector<Vector<String>>();

		Connection con = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String query = "select * from stock where expirationDate between ? and ?;";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, expirationDate_1);
			pstmt.setString(2, expirationDate_2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> stockInfo = new Vector<String>();
				stockInfo.add(rs.getString("productCode"));
				stockInfo.add(rs.getString("productName"));
				stockInfo.add(rs.getString("qty"));
				stockInfo.add(rs.getString("approStock"));
				stockInfo.add(rs.getString("expirationDate"));
				stockInfos.add(stockInfo);
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

		return stockInfos;
	}

}
