package com.SE.Sell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//�Ǹ� �̷��� ���� DB ������ ���� DAO �κ�.
public class DBManager {

	private static final String URL = "jdbc:mysql://localhost:3306/mydb?&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root"; // DB ID
	private static final String PASS = "1234"; // DB �н�����
	private Connection connection;
	private ResultSet resultSet;

	public DBManager() {

	}

	/*--------------DB���� �κ�--------------*/

	public Connection getConn() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	/*--------------��¥�� ������ �κ�--------------*/

	public String getDate() {

		PreparedStatement pstmt;
		String SQL = "SELECT NOW()";

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "\n ERROR : ���� �ð��� ������ �� �����ϴ� \n"; // �����ͺ��̽� ����
	}

	/*--------------�ε����� ������ �κ�--------------*/

	public int getIndex() {

		PreparedStatement pstmt;
		String SQL = "SELECT COUNT(*) FROM sell";

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1) + 1;
			} else {
				return 1;// ù ��° �÷��� ���
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �ڵ� ���� ����\n");
		return 0;
	}

	/*--------------�մ� �ڵ�� �մ� ���� �޾ƿ��� �κ�--------------*/

	public String getCustomerName(String coustomerCode) {

		// SQL ���� ����
		String SQL;
		PreparedStatement pstmt;

		String customer = " ";

		// �� �̸� �޾ƿ���
		SQL = "SELECT customerName FROM customer WHERE customerCode=?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, coustomerCode);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {

				customer = resultSet.getString("customerName");
				return customer;

			} else {
				return "��ȸ��";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �մ� �̸� �޾ƿ��� �Ұ���");
		return null;
	}

	/*--------------�� �ڵ带 �������� �κ�--------------*/

	public String getCustomerCode(String name) {

		// SQL ���� ����
		String SQL;
		PreparedStatement pstmt;

		String customer = " ";

		// �� �̸� �޾ƿ���
		SQL = "SELECT customerCode FROM customer WHERE customerName=?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, name);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				customer = resultSet.getString("customerCode");
				return customer;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �մ� ���� �޾ƿ��� �Ұ��� \n");
		return null;
	}

	/*--------------����� ���ϴ� �κ�--------------*/

	public int StockUpdateIncrease(int value, String productCode) {

		// SQL ���� ����
		String SQL;
		PreparedStatement pstmt;

		SQL = "UPDATE stock SET qty = qty + ? WHERE productCode = ?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, productCode);

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: ��� ��ȸ �Ұ��� \n");
		return -1;
	}

	/*--------------����� ���̴� �κ�--------------*/

	public int StockUpdateDecrase(int value, String productCode) {

		// SQL ���� ����
		String SQL;
		PreparedStatement pstmt;

		SQL = "UPDATE stock SET qty = qty - ? WHERE productCode = ?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, productCode);

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: ��� ��ȸ �Ұ��� \n");
		return -1;
	}
	
	/*--------------�� ���赵 �����ϱ� --------------*/

	public int customerAttentionLevel(int value, String customerCode) {

		// SQL ���� ����
		String SQL;
		PreparedStatement pstmt;

		SQL = "UPDATE customer SET interestedCustomer = ? WHERE customerCode  = ?";

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, customerCode);

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: �� ���� ������Ʈ �Ұ��� \n");
		return -1;
	}

	/*--------------��ǰ������ �������� �κ�--------------*/

	public proudctSellRecordInfo getProductInfo(String productCode) {

		// SQL ���� ����
		PreparedStatement pstmt;
		String SQL = "select productName, attentionLevel, productRetailPrice from product where productCode=?";

		// ��ǰ��, ��ǰ ����, ���� ������ �����´�
		proudctSellRecordInfo info = new proudctSellRecordInfo();

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, productCode);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				info.setProductName(resultSet.getString("productName"));
				info.setAttentionLevel(resultSet.getString("attentionLevel"));
				info.setProductPrice(resultSet.getInt("productRetailPrice"));
				info.setProductCode(productCode);
				return info;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("ERROR: ��ǰ������ �޾ƿ�������");
		return null;
	}

	/*--------------�Ǹ������� ���̺� �߰��Ѵ�--------------*/

	public int sellInfoInsert(sellRecordInfo sellInfo) {

		// SQL ���� ����
		String SQL = "INSERT INTO sell VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt;

		// ������ ��� ���� �ӽ� ������
		String sellCode = sellInfo.getSellCode();
		String customerCode = sellInfo.getCustomerCode();
		int paymentMethod = sellInfo.getPaymentMethod();
		// String sellPersonChargeCode = sellInfo.getSellPersonChargeCode();
		int totalPrice = sellInfo.getTotalPrice();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);

			pstmt.setString(1, sellCode); //
			pstmt.setString(2, customerCode); //
			pstmt.setInt(3, paymentMethod);
			pstmt.setString(4, "SELM-001"); // getSellPersonChargeName(); ���߿� ������ �� �ִ� ���
			pstmt.setString(5, getDate()); //
			pstmt.setInt(6, totalPrice); // �Ѱ���
			pstmt.setInt(7, 0); // 0�̸� �Ǹ� �� ���� 1�̸� �Ǹ� ��� �� ������

			pstmt.executeUpdate();

			return 0;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: �Ǹ������� �߰��� �� ���� \n");
		return -1; // �����ͺ��̽� ����
	}

	/*--------------��ǰ�� ���� ������ ���̺� �߰��Ѵ�--------------*/

	public int sellProduct(ArrayList<proudctSellRecordInfo> productRecodeInfo) {

		// SQL ���� ����
		String SQL;
		PreparedStatement pstmt;

		SQL = "INSERT INTO product_has_sell VALUES(?,?,?,?,?,?)";

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);

			for (int i = 0; i < productRecodeInfo.size(); i++) {
				pstmt.setString(1, productRecodeInfo.get(i).getProductCode()); // ��ǰ�ڵ�
				pstmt.setString(2, productRecodeInfo.get(i).getSellcode()); // �Ǹ��ڵ�
				pstmt.setInt(3, productRecodeInfo.get(i).getQty()); // �Ǹ� ����
				pstmt.setInt(4, productRecodeInfo.get(i).getTotalprice()); // ��ü����
				pstmt.setInt(5, 0); // ������� 0:���� / 1: ī��
				pstmt.setString(6, productRecodeInfo.get(i).getProductName()); // ��ǰ�̸�

				pstmt.executeUpdate();
			}

			return 0;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: ��ǰ �Ǹ� ������ ����� �� ����\n");
		return -1; // �����ͺ��̽� ����
	}

	/*--------------�Ǹſ� ���� ��� ����Ʈ�� �����´�--------------*/

	public ArrayList<sellRecordInfo> getSellList(String sellCode) {

		// SQL ���� ����
		String SQL = "SELECT * FROM sell WHERE cancel = 0 AND sellCode = ?";
		PreparedStatement pstmt;

		ArrayList<sellRecordInfo> list = new ArrayList<sellRecordInfo>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				sellRecordInfo sellInfo = new sellRecordInfo();

				// ���ų�¥, �Ǹ��ڵ�, �������, �� �ǸŰ���, �������ڵ�
				sellInfo.setSellCode(resultSet.getString("sellCode"));
				sellInfo.setCustomerCode(resultSet.getString("customerCode"));
				sellInfo.setPaymentMethod(resultSet.getInt("paymentMethod"));
				sellInfo.setTotalPrice(resultSet.getInt("totalPrice"));

				list.add(sellInfo);

			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹ� ������ ������ �� ���� \n");
		return null; // �����ͺ��̽� ����
	}

	/*--------------�Ǹ��ڵ忡 ���� ������ �����´�--------------*/

	public sellRecordInfo getSellInfo(String sellCode) {

		// SQL ���� ����
		String SQL = "SELECT * FROM sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		sellRecordInfo sellInfo = new sellRecordInfo();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {

				// ���ų�¥, �Ǹ��ڵ�, �������, �� �ǸŰ���, �������ڵ�
				sellInfo.setSellCode(resultSet.getString("sellCode"));
				sellInfo.setCustomerCode(resultSet.getString("customerCode"));
				sellInfo.setPaymentMethod(resultSet.getInt("paymentMethod"));
				sellInfo.setSellDate(resultSet.getString("sellDate"));
				sellInfo.setTotalPrice(resultSet.getInt("totalPrice"));
				sellInfo.setcancel(resultSet.getInt("cancel"));

				return sellInfo;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹ� ������ ������ �� ���� \n");
		return null; // �����ͺ��̽� ����
	}

	/*--------------���� �Ǹ��ڵ带 ���� ��ǰ�ǸŸ���Ʈ �̸� ��������--------------*/

	public StringBuilder getsellProductNameList(String sellCode) {

		StringBuilder strBuilder = new StringBuilder();

		// SQL ���� ����
		String SQL = "SELECT productName FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				strBuilder.append(resultSet.getString("productName") + ",");
			}

			return strBuilder;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹŵ� ��ǰ �̸� �������� �Ұ� \n");
		return null;
	}
	
	/*--------------�Ǹ��ڵ�� ��ǰ ������ ���� ��������--------------*/
	
	public ArrayList<proudctSellRecordInfo> getProductInfoforcancel(String sellCode) {

		ArrayList<proudctSellRecordInfo> productInfo = new ArrayList<proudctSellRecordInfo>();

		// SQL ���� ����
		String SQL = "SELECT productCode, qty FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				proudctSellRecordInfo product = new proudctSellRecordInfo();
				
				product.setProductCode(resultSet.getString("productCode"));
				product.setQty(resultSet.getInt("qty"));
				
				productInfo.add(product);
			}

			return productInfo;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹŵ� ��ǰ �̸� �������� �Ұ� \n");
		return null;
	}


	/*--------------��¥�� ���� �Ǹ� ��ȸ--------------*/

	public ArrayList<sellRecordInfo> getSellInfoFromDate(String sellDate) {

		// SQL ���� ����
		String SQL = "SELECT * FROM sell WHERE cancel = 0 AND date_format(sellDate,'%Y-%m-%d')=?";
		PreparedStatement pstmt;
		ArrayList<sellRecordInfo> sellInfoList = new ArrayList<sellRecordInfo>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellDate);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				sellRecordInfo sellInfo = new sellRecordInfo();

				// ���ų�¥, �Ǹ��ڵ�, �������, �� �ǸŰ���, �������ڵ�
				sellInfo.setSellCode(resultSet.getString("sellCode"));
				sellInfo.setCustomerCode(resultSet.getString("customerCode"));
				sellInfo.setPaymentMethod(resultSet.getInt("paymentMethod"));
				sellInfo.setSellDate(resultSet.getString("sellDate"));
				sellInfo.setTotalPrice(resultSet.getInt("totalPrice"));
				sellInfo.setcancel(resultSet.getInt("cancel"));

				sellInfoList.add(sellInfo);
			}

			return sellInfoList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹ� ������ ������ �� ���� \n");
		return null; // �����ͺ��̽� ����
	}

	/*--------------��ǰ��ǰ�ǸŸ� ����ϴ� �κ�--------------*/

	public int sellProudctcancel(String sellCode) {
		System.out.println(sellCode);
		// SQL ���� ����
		String SQL = "UPDATE product_has_sell SET cancel='1' WHERE sellCode = ? ";
		PreparedStatement pstmt;

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			pstmt.executeUpdate();

			return 1;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: ��ǰ�Ǹ���ҿ� �����߽��ϴ� \n");
		return -1;
	}

	/*--------------��ǰ�ǸŸ� ����ϴ� �κ�--------------*/

	public int sellInfocancel(String sellCode) {

		// SQL ���� ����
		String SQL = "UPDATE sell SET cancel='1' WHERE sellCode = ? ";
		PreparedStatement pstmt;

		try {
			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			pstmt.executeUpdate();

			return 1;

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println("\nERROR: �Ǹ�������ҿ� �����߽��ϴ� \n");
		return -1;
	}

	// �Ǹ��ڵ�� ��ǰ ���� �޾ƿ���
	public ArrayList<proudctSellRecordInfo> getsellProductList(String sellCode) {

		// ��¥, ��ǰ��, ����, ����, �� �Ǹ� �ݾ�

		ArrayList<proudctSellRecordInfo> proudctInfoList = new ArrayList<proudctSellRecordInfo>();

		// SQL ���� ����
		String SQL = "SELECT qty, totalPrice, productName FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				proudctSellRecordInfo productInfo = new proudctSellRecordInfo();

				productInfo.setQty(Integer.parseInt(resultSet.getString("qty")));
				productInfo.setTotalprice(resultSet.getInt("totalPrice"));
				productInfo.setProductName(resultSet.getString("productName"));

				proudctInfoList.add(productInfo);

			}

			return proudctInfoList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: ��ǰ ���� �޾ƿ��� �Ұ� \n");
		return null;
	}

	// �Ǹ��ڵ忡 ���� ��ǰ ����Ʈ ��������
	public ArrayList<String> getProductCodeList(String sellCode) {

		// SQL ���� ����
		String SQL = "SELECT productCode FROM product_has_sell WHERE sellCode = ?";
		PreparedStatement pstmt;
		ArrayList<String> productCodeList = new ArrayList<String>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, sellCode);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String productCode;

				productCode = resultSet.getString("productCode");
				productCodeList.add(productCode);
			}

			return productCodeList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹ� ������ ������ �� ���� \n");
		return null; // �����ͺ��̽� ����
	}

	// �� ������ sellCode �˾ƿ���
	public ArrayList<String> getSellCodeList(String customerCode) {

		// SQL ���� ����
		String SQL = "SELECT sellCode FROM sell WHERE customerCode = ?";
		PreparedStatement pstmt;
		ArrayList<String> sellCodeList = new ArrayList<String>();

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, customerCode);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				String sellCode;

				sellCode = resultSet.getString("sellCode");
				sellCodeList.add(sellCode);
			}

			return sellCodeList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹ� ������ ������ �� ���� \n");
		return null; // �����ͺ��̽� ����
	}

	// ��ǰ�ڵ�� ���� ���� �˾ƿ���
	public int getAttentionLevel(String productCode) {

		// SQL ���� ����
		String SQL = "SELECT attentionLevel FROM product WHERE productCode = ?";
		PreparedStatement pstmt;
		int attentionLevel = 0;

		try {

			connection = getConn();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, productCode);

			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {

				attentionLevel = resultSet.getInt("attentionLevel");
			}

			return attentionLevel;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nERROR: �Ǹ� ������ ������ �� ���� \n");
		return -1; // �����ͺ��̽� ����
	}
	
	

}
