package com.SE.product;

import java.sql.*;
import java.util.Vector;

public class DBManager {
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // ����̹�
	private final String DB_URL = "jdbc:mysql://localhost/mydb?&serverTimezone=UTC"; // ������ DB ����
	private final String USER_NAME = "root"; // DB�� ������ ����� �̸��� ����� ����
	private final String PASSWORD = "1234"; // ������� ��й�ȣ�� ����� ����

	// ������
	public DBManager() {
	}
	
	//��� ��ǰ������ ����
	public Vector<ProductInfo> selectProductInfos() {

		Vector<ProductInfo> productInfos = new Vector<ProductInfo>();
		Connection conn = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			// ����̹� �ε�
			Class.forName(JDBC_DRIVER);
			// ����
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

			String sql = "select * from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

						
						
			while (rs.next()) {
				ProductInfo pi = new ProductInfo(rs.getString("productCode"),rs.getString("productClassification")
						,rs.getString("productName"),rs.getString("productManufacturer")
						,Integer.parseInt(rs.getString("attentionLevel")),Integer.parseInt(rs.getString("productRetailPrice")));
				productInfos.add(pi);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return productInfos;
	}

	
	//�ڵ��ȣ�� �Ķ���ͷ� �ϳ��� ��ǰ������ ����
		public ProductInfo selectProductInfo(String productCode) {

			Connection conn = null; // ����
			PreparedStatement pstmt = null; // ���
			ResultSet rs = null; // ���
			ProductInfo pi = new ProductInfo();
			
			try {
				// ����̹� �ε�
				Class.forName(JDBC_DRIVER);
				// ����
				conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

				String sql = "select * from product where productCode = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productCode);
				rs = pstmt.executeQuery();

							
							
				while (rs.next()) {
					pi = new ProductInfo(rs.getString("productCode"),rs.getString("productClassification")
							,rs.getString("productName"),rs.getString("productManufacturer")
							,Integer.parseInt(rs.getString("attentionLevel")),Integer.parseInt(rs.getString("productRetailPrice")));
				}

			} catch (ClassNotFoundException e) {
				System.out.println("����̹� �ε� ����");
			} catch (SQLException e) {
				System.out.println("���� " + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return pi;
		}
		
		
	//����
		public boolean updateProductInfo(ProductInfo productInfo) {
			
			Connection conn = null; // ����
			PreparedStatement pstmt = null; // ���
			ResultSet rs = null; // ���

			try {
				// ����̹� �ε�
				Class.forName(JDBC_DRIVER);
				// ����
				conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

				String query = "update product set productClassification=?, productName=?, "
						+ "productManufacturer=?, attentionLevel=?, productRetailPrice=?"
						+ " where productCode=?";
				
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, productInfo.getProductClassification());
				pstmt.setString(2, productInfo.getProductName());
				pstmt.setString(3, productInfo.getProductManufacturer());
				pstmt.setString(4,  Integer.toString(productInfo.getAttentionLevel()));
				pstmt.setInt(5,  productInfo.getProductRetailPrice());
				pstmt.setString(6,  productInfo.getProductCode());

				int result = pstmt.executeUpdate();
				if(result>0) return true;
				else return false;
				
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
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

			return true;
		}
		
		
		
	public void insertDBProduct(String productCode, String productClassification, String productName,
			String productManufacturer, String attentionLevel, int productRetailPrice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// ����̹� �ε�
			Class.forName(JDBC_DRIVER);
			// ����
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

			String sql = "INSERT INTO product VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// ������ binding
			pstmt.setString(1, productCode);
			pstmt.setString(2, productClassification);
			pstmt.setString(3, productName);
			pstmt.setString(4, productManufacturer);
			pstmt.setString(5, attentionLevel);
			pstmt.setInt(6, productRetailPrice);

			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("������ �Է� ����");
			} else {
				System.out.println("������ �Է� ����");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean deleteProductInfo(String productCode) {
		Connection conn = null; // ����
		PreparedStatement pstmt = null; // ���
		ResultSet rs = null; // ���

		try {
			// ����̹� �ε�
			Class.forName(JDBC_DRIVER);
			// ����
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			String query = "delete from product where productCode=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, productCode);
			
			int result = pstmt.executeUpdate();
	
			if(result>0) return true;
			else return false;
			
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
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return true;
	}
	
	
}