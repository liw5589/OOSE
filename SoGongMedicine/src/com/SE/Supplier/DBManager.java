
//  @ Project : �Ұ� �౹
//  @ File Name : DBManager.java
//  @ Date : 2019-06-06
//  @ Author : �ڰ渰

package com.SE.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBManager {

   private static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Seoul&useSSL=false";
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
   
   /* �ŷ�ó ���� �Է�  */
   public boolean insertSupplierInfo(SupplierInfo supplierInfo) {
      Connection con = null; //����
      PreparedStatement pstmt = null; //���
      
      try {
         con = getConn();
         
         String query = "insert into supplier values (?,?,?,?,?,?)";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, supplierInfo.getSupplierCode());
         pstmt.setString(2, supplierInfo.getName());
         pstmt.setString(3, supplierInfo.getEmail());
         pstmt.setString(4, supplierInfo.getManager());
         pstmt.setString(5, supplierInfo.getPhoneNum());
         pstmt.setString(6, "0");
         
         pstmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      } finally {
         if(pstmt != null)
            try {
               pstmt.close();
            } catch(SQLException e1) {
               e1.printStackTrace();
            }
         if(con != null)
            try {
               con.close();
            } catch(SQLException e2) {
               e2.printStackTrace();
            }
      }      
      return true;
   }
   
   //supplier�� Ʃ�� ������ �������� �޼ҵ�
   public int createSupplierCode() {
      Connection con = null; //����
      PreparedStatement pstmt = null; //���
      ResultSet rs = null; //���
      
      int count = 0; //Ʃ�� ������ �����ϴ� ����
      
      try {
         con = getConn();
         
         String query = "select count(*) from supplier";
         pstmt = con.prepareStatement(query);         
         rs = pstmt.executeQuery();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if(rs != null)
            try {
               while(rs.next()) {
                  count = rs.getInt(1);                  
               }
               rs.close();
               count++;
            } catch(SQLException e1) {
               e1.printStackTrace();
            }
         if(pstmt != null)
            try {
               pstmt.close();
            } catch(SQLException e2) {
               e2.printStackTrace();
            }
         if(con != null)
            try {
               con.close();
            } catch(SQLException e3) {
               e3.printStackTrace();
            }
      }      
      return count;
   }
   
   /* �ŷ�ó ���� ��ȸ */
   public Vector<Vector<String>> selectSupplierInfo(){
      
      Vector<Vector<String>> supplierInfos = new Vector<Vector<String>>();
      
      Connection con = null; // ����
      PreparedStatement pstmt = null; // ���
      ResultSet rs = null; // ���
      
      try {
         con = getConn();
         String query = "select * from supplier where cancel = ?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, "0");
         rs = pstmt.executeQuery();

         while (rs.next()) {
            Vector<String> supplierInfo = new Vector<String>();
            supplierInfo.add(rs.getString("supplierCode"));
            supplierInfo.add(rs.getString("name"));
            supplierInfo.add(rs.getString("email"));
            supplierInfo.add(rs.getString("manager"));
            supplierInfo.add(rs.getString("phoneNum"));
            supplierInfos.add(supplierInfo);
         }

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         if (pstmt != null)
            try {
               pstmt.close();
            } catch (SQLException e2) {
               // TODO Auto-generated catch block
               e2.printStackTrace();
            }
         if (con != null)
            try {
               con.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
      }
      return supplierInfos;      
   }
   
   /* �ŷ�ó ���� ���� */
   public boolean deleteSupplierInfo(String supplierCode) {
      Connection con = null; // ����
      PreparedStatement pstmt = null; // ���
      ResultSet rs = null; // ���
      
      try {
         con = getConn();
         String query = "update supplier set cancel = ? where supplierCode=?";
         pstmt = con.prepareStatement(query);

         pstmt.setString(1, "1");
         pstmt.setString(2, supplierCode);
         
         int result = pstmt.executeUpdate();
   
         if(result>0) return true;
         else return false;
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         if (pstmt != null)
            try {
               pstmt.close();
            } catch (SQLException e2) {
               // TODO Auto-generated catch block
               e2.printStackTrace();
            }
         if (con != null)
            try {
               con.close();
            } catch (SQLException e3) {
               // TODO Auto-generated catch block
               e3.printStackTrace();
            }
      }
      return true;
   }
   
   /* �ŷ�ó ���� ���� */
   public boolean updateSupplierInfo(SupplierInfo supplierInfo) {
      Connection con = null; // ����
      PreparedStatement pstmt = null; // ���
      ResultSet rs = null; // ���
      
      try {
         con = getConn();
         String query = "update supplier set name=?, email=?, manager=?,phoneNum=? where supplierCode=?";
         pstmt = con.prepareStatement(query);

         pstmt.setString(1, supplierInfo.getName());
         pstmt.setString(2, supplierInfo.getEmail());
         pstmt.setString(3, supplierInfo.getManager());
         pstmt.setString(4, supplierInfo.getPhoneNum());
         pstmt.setString(5, supplierInfo.getSupplierCode());
         
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

   /*�ŷ�ó ���� �˻�(�ŷ�ó �ڵ�)*/
   public SupplierInfo selectSupplierInfoBySupplierCode(String supplierCode) {
      
      SupplierInfo supplierInfo = new SupplierInfo();
      Connection con = null; // ����
      PreparedStatement pstmt = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String query = "select * from supplier where supplierCode=?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, supplierCode);rs = pstmt.executeQuery();

         while (rs.next()) {
            supplierInfo.setSupplierCode(rs.getString("supplierCode"));
            supplierInfo.setName(rs.getString("name"));
            supplierInfo.setEmail(rs.getString("email"));
            supplierInfo.setManager(rs.getString("manager"));
            supplierInfo.setPhoneNum(rs.getString("phoneNum"));
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
   return supplierInfo;
   }
}