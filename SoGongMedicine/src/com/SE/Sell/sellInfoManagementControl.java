package com.SE.Sell;

import java.util.ArrayList;

public class sellInfoManagementControl {

	DBManager db;

	// �⺻ ������
	public sellInfoManagementControl() {

	}

	// ��ǰ�� �Ǹ��Ѵ�
	public int sellProduct(ArrayList<proudctSellRecordInfo> productRecodeInfo, sellRecordInfo sellInfo) {

		db = new DBManager();
		String sellCode = createSellCode();

		sellInfo.setSellCode(sellCode);

		for (int i = 0; i < productRecodeInfo.size(); i++) {

			productRecodeInfo.get(i).setSellcode(sellCode);
		}

		db.sellInfoInsert(sellInfo);
		db.sellProduct(productRecodeInfo);

		return 1;
	}

	// �ǸŸ� ����Ѵ�
	public int sellProudctcancel(String sellCode) {
		db = new DBManager();

		// 1. ��ǰ�Ǹſ��� ���� ���
		db.sellProudctcancel(sellCode);

		// 2. �Ǹ��������� ���
		db.sellInfocancel(sellCode);
		
		// 3. sellCode�� ��ǰ �Ǹ� ���� ��������
		ArrayList<proudctSellRecordInfo> productInfo = new ArrayList<proudctSellRecordInfo>();
		productInfo = db.getProductInfoforcancel(sellCode);
		
		// 4. ��� �����ش�.
		for(int i = 0; i < productInfo.size(); i++) {
			increaseStockQty(productInfo.get(i).getQty(), productInfo.get(i).getProductCode());
		}

		return 1;
	}

	// ��û�� �������� ����Ѵ�
	public ArrayList<StringBuilder> requestOutputReceipt(String sellCode) {

		// ��¥, ��ǰ��, ����, ����, �� �Ǹ� �ݾ�
		ArrayList<proudctSellRecordInfo> proudctInfoList = new ArrayList<proudctSellRecordInfo>();
		sellRecordInfo sellInfo = new sellRecordInfo();
		ArrayList<StringBuilder> sellInfoList = new ArrayList<StringBuilder>();

		StringBuilder productName = new StringBuilder();
		StringBuilder qty = new StringBuilder();
		StringBuilder productPrice = new StringBuilder();
		StringBuilder date = new StringBuilder();
		StringBuilder totalPrice = new StringBuilder();

		db = new DBManager();

		sellInfo = db.getSellInfo(sellCode);
		proudctInfoList = db.getsellProductList(sellCode);

		date.append(sellInfo.getSellDate());
		sellInfoList.add(date);

		totalPrice.append(sellInfo.getTotalPrice());
		sellInfoList.add(totalPrice);

		for (int i = 0; i < proudctInfoList.size(); i++) {

			productName.append(proudctInfoList.get(i).getProductName()+ "\n");
			qty.append(proudctInfoList.get(i).getQty()+ "\n");
			productPrice.append(proudctInfoList.get(i).getTotalprice()+ "\n");

		}

		sellInfoList.add(productName);
		sellInfoList.add(qty);
		sellInfoList.add(productPrice);

		for (int i = 0; i < sellInfoList.size(); i++) {
			System.out.println(sellInfoList.get(i));
		}

		return sellInfoList;

	}

	// �Ǹ� �ڵ�� �Ǹ��̷���ȸ�� �Ѵ�
	public sellRecordInfo getLookupSellRecorde(String sellCode) {

		sellRecordInfo sellInfo = new sellRecordInfo();
		db = new DBManager();

		// �Ǹ������� ������
		sellInfo = db.getSellInfo(sellCode);

		if (sellInfo == null) {
			return null;
		}

		if (sellInfo.getcancel() == 0) {
			// �Ǹ��������� ������ �� ���� ���� �ٸ� ���̺� ������
			sellInfo.setSellProductList(db.getsellProductNameList(sellCode));
			sellInfo.setCustomerName(db.getCustomerName(sellInfo.getCustomerCode()));

			return sellInfo;
		}

		return null;
	}

	// ��¥�� �Ǹ��̷���ȸ�� �Ѵ�
	public ArrayList<sellRecordInfo> getSellDateLookupRecorde(String sellDate) {

		ArrayList<sellRecordInfo> sellInfoList = new ArrayList<sellRecordInfo>();
		db = new DBManager();

		sellInfoList = db.getSellInfoFromDate(sellDate);

		for (int i = 0; i < sellInfoList.size(); i++) {

			sellInfoList.get(i).setSellProductList(db.getsellProductNameList(sellInfoList.get(i).getSellCode()));
			sellInfoList.get(i).setCustomerName(db.getCustomerName(sellInfoList.get(i).getCustomerCode()));
		}

		return sellInfoList;
	}

	// ����� ���Ѵ�
	public void increaseStockQty(int value, String productCode) {
		db = new DBManager();

		// ��� �� �Է�
		db.StockUpdateIncrease(value, productCode);
	}

	// ����� ���δ�
	public void decreaseStockQty(int value, String productCode) {
		DBManager db = new DBManager();

		// ��� �� �Է�
		db.StockUpdateDecrase(value, productCode);
	}

	// ���������� Ȯ���Ѵ�
	public String customerCheck(String name) {

		db = new DBManager();
		String code = db.getCustomerCode(name);

		if (code == null) {
			return "��ȸ��";
		} else {
			return code;
		}
	}

	// ��ȸ�� �ڵ� ��������
	public String getNoCustomerCode() {

		db = new DBManager();
		String code = db.getCustomerCode("��ȸ��");

		return code;
	}

	// ȸ�� �ڵ� ��������
	public String getCustomerCode(String name) {

		db = new DBManager();
		String code = db.getCustomerCode(name);

		return code;
	}

	// ȸ�� �̸� ��������
	public String getCustomerName(String customerCode) {

		db = new DBManager();
		String customerName = db.getCustomerCode(customerCode);

		return customerCode;
	}

	// �������� �����Ѵ�
	public int setCustomerDiscount(int totalPrice) {

		return totalPrice * 5 / 100;
	}

	// �Ǹ��ڵ带 �����Ѵ�
	public String createSellCode() {

		db = new DBManager();
		int serial = db.getIndex();

		String suffix = String.format("%03d", serial);
		String sellCode = "SELL-" + suffix;

		System.out.println(sellCode + "�Ǹ��ڵ� ������");

		return sellCode;
	}

	// ��ǰ�� ���� ������ �޾ƿ´�
	public proudctSellRecordInfo getProductInfo(String productCode) {
		DBManager db = new DBManager();

		if (db.getProductInfo(productCode) == null) {
			return null;
		} else {
			return db.getProductInfo(productCode);
		}
	}

	// �� ���� ���� ��������
	public int getInterestedCustomer(String customerName) {
		DBManager db = new DBManager();
		int level = 0;

		// ���̸����� ���ڵ� ��������
		String CusomerCode = db.getCustomerCode(customerName);

		// ���ڵ�� �Ǹ����� ��������
		ArrayList<String> sellCodeList = new ArrayList<String>();
		sellCodeList = db.getSellCodeList(CusomerCode);

		// �Ǹ��ڵ�� ��ǰ�ڵ� �޾ƿ���
		ArrayList<String> productCodeList = new ArrayList<String>();
		for (int i = 0; i < sellCodeList.size(); i++) {
			productCodeList.addAll(db.getProductCodeList(sellCodeList.get(i)));
			System.out.println(productCodeList.get(i));
		}

		// ��ǰ�ڵ�� ���跹�� ��������
		for (int i = 0; i < productCodeList.size(); i++) {
			
			int attention = db.getAttentionLevel(productCodeList.get(i));
			System.out.println(attention);
			if (attention == 0) {
				level += 10;
			} else if (attention == 1) {
				level += 5;
			} else {
				level += 1;
			}
		}
		
		return level;
	}
	
	public void setAttentionCustomer(int level, String customerCode) {
		DBManager db = new DBManager();

		int result = db.customerAttentionLevel(level, customerCode);
		
		if(result == -1) { System.out.println("������Ʈ ������");}
		else {
			System.out.println("������Ʈ ������");
		}
	}
}
