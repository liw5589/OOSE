package com.SE.product;

import java.io.*;
import java.util.*;

public class ProductInfoManagement {
	
	private Vector<ProductInfo> v;
	
	ProductInfoManagement()
	{
		v = new Vector<ProductInfo>();
	}
	
	public Vector<ProductInfo> getV() {
		return v;
	}
	
	public void resetVector() {
		v = null;
		v = new Vector<ProductInfo>();
	}
	
	private DBManager dbm = new DBManager();
	
	
	public boolean modifyProduct(ProductInfo productInfo) {
	
		return dbm.updateProductInfo(productInfo);
	}
	
	public boolean deleteProduct(String productCode) {
		return dbm.deleteProductInfo(productCode);
	}

	public void lookupProduct() {
		v = dbm.selectProductInfos();
		
	}
	
	public ProductInfo lookupProductInfo(String productCode) {
		return dbm.selectProductInfo(productCode);
	}
	
	public void outputBarcode(String productCode) {
	
	}
	
	public void uploadProductList(String fileAddress) {
		 
	}
	public Vector<?> readProductList(String fileAddress) {
		v = new Vector<ProductInfo>();
		
		 try{
	            //���� ��ü ����
	            File file = new File(fileAddress);
	            //��ĳ�ʷ� ���� �б�
	            Scanner s = new Scanner(file);
	            while(s.hasNextLine()){
	                String tmpLine = s.nextLine();
	                String argu[] = tmpLine.split(",");
	                //argu[0]�� ��Ϲ�ȣ �̹Ƿ� ������.
	                //��ü �����ؼ� ����Ʈ�� �ֱ�
	                ProductInfo pi = new ProductInfo(argu[0],argu[1],argu[2],argu[3],Integer.parseInt(argu[4]),Integer.parseInt(argu[5]));
	                v.add(pi);
	                //dbm.insertDBProduct(productCode, productClassification, productName, productManufacturer, attentionLevel, productRetailPrice);
	            }
	            
	        }catch (FileNotFoundException e) {
	           System.out.println("���Ͼ���");
	        }
		 
		return v;
	    
	}
}
