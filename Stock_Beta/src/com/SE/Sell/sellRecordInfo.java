package com.SE.Sell;
//�Ǹſ� ���� ��ƼƼ
public class sellRecordInfo {
	
	private String sellCode; // �Ǹ��ڵ�
	private String customerCode;
	private String customerName; // �� �̸�
	private int paymentMethod; // ���� ��� 0:����, 1:ī��
	private String sellPersonChargeCode; // �Ǹ� ����� �ڵ�
	private String sellDate; // �Ǹų�¥
	private int totalPrice; // ��ü �Ǹ� ����
	private StringBuilder sellProductList;
	private int cancel; //��ҿ��� 0: ����, 1: ���
	private String productClassification;

	public String getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(String productClassification) {
		this.productClassification = productClassification;
	}

	public int getcancel() {
		return cancel;
	}

	public void setcancel(int cancel) {
		this.cancel = cancel;
	}

	public StringBuilder getSellProductList() {
		return sellProductList;
	}

	public void setSellProductList(StringBuilder sellProductList) {
		this.sellProductList = sellProductList;
	}

	sellRecordInfo() {}
	
	sellRecordInfo(String customerName, int paymentMethod)
	{
		this.customerName = customerName;
		this.paymentMethod = paymentMethod;
	}
	
	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getSellPersonChargeCode() {
		return sellPersonChargeCode;
	}

	public void setSellPersonChargeCode(String sellPersonChargeCode) {
		this.sellPersonChargeCode = sellPersonChargeCode;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
}
