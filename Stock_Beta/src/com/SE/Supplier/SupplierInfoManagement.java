
//@ Project : �Ұ� �౹
//@ File Name : SupplierInfoManagement.java
//@ Date : 2019-06-06
//@ Author : �ڰ渰

package com.SE.Supplier;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

public class SupplierInfoManagement {
   private static int cnt = 0;
   private DBManager db;
   
   public SupplierInfoManagement() {
      db = new DBManager();
   }
   
   //�ŷ�ó �Է�
   public void insertSupplier(Vector<Vector> infos) {   
      if(infos.get(0).get(0)==null) return;
      SupplierInfo supplierInfo = null;
      for(int i=0;i<infos.size();i++) {
         if(infos.get(i).get(0)==null) return;
         int serial = db.createSupplierCode();
         String suffix = String.format("%03d", serial);
         String supplierCode = "SUPL-"+suffix;
         
         supplierInfo = new SupplierInfo();
         supplierInfo.setName((String)infos.get(i).get(0));
         supplierInfo.setEmail((String)infos.get(i).get(1));
         supplierInfo.setManager((String)infos.get(i).get(2));
         supplierInfo.setPhoneNum((String)infos.get(i).get(3));
         supplierInfo.setSupplierCode(supplierCode);
         db.insertSupplierInfo(supplierInfo);
      }
   }

   //�ŷ�ó ����
   public boolean modifySupplier(SupplierInfo supplierInfo) {      
      return db.updateSupplierInfo(supplierInfo);
   }
   
   //�ŷ�ó ����
   public boolean deleteSupplier(String supplierCode) {
      return db.deleteSupplierInfo(supplierCode);
   }
   
   //�ŷ�ó ��ȸ
   public Vector<Vector<String>> lookupSupplier(){
      Vector<Vector<String>> supplierInfo = new Vector<Vector<String>>();
      supplierInfo = db.selectSupplierInfo();
      cnt=supplierInfo.size();
      return db.selectSupplierInfo();
   }
   
   //�ŷ�ó �˻�(�ŷ�ó �ڵ�)
   public SupplierInfo lookupSupplierAsSupplierCode(String supplierCode) {
      SupplierInfo supplierInfo = db.selectSupplierInfoBySupplierCode(supplierCode);
      return supplierInfo;
   }
}