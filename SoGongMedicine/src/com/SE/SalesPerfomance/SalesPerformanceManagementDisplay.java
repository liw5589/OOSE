package com.SE.SalesPerfomance;

import java.awt.BorderLayout;




import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class SalesPerformanceManagementDisplay extends JFrame{

   private JPanel contentPane;
   private JTextField productTextField;
   private JTable productTable;
   private JTextField dateTextField;
   private JTable dateTable;
   private JTextField dateTextField2;
   private JButton productButton;

   private SalesPerformanceManagement spm;
   
   private JButton dateButton;
   private JTextField classificationTextField;
   private JTable classificationTable;
   private JButton classificationButton;   
   
   
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               SalesPerformanceManagementDisplay frame = new SalesPerformanceManagementDisplay();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   
   public SalesPerformanceManagementDisplay() {
      spm = new SalesPerformanceManagement();
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 484, 373);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      
      // �� �г�
      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(0, 0, 436, 287);
      contentPane.add(tabbedPane);
      // �� �г�
      
      // �Ⱓ�� ���� �г�
      JPanel datePanel = new JPanel();
      tabbedPane.addTab("\uAE30\uAC04\uBCC4 \uB9E4\uCD9C\uC2E4\uC801 \uC870\uD68C", null, datePanel, null);
      datePanel.setLayout(null);
      // �Ⱓ�� ���� �г�
      
      // �Ⱓ�� ���� ����
      JLabel label = new JLabel("\uAE30\uAC04\uBCC4 \uB9E4\uCD9C\uC2E4\uC801 \uC870\uD68C");
      label.setFont(new Font("����", Font.PLAIN, 21));
      label.setBounds(105, 10, 208, 37);
      datePanel.add(label);
      // �Ⱓ�� ���� ����
      
      // �Ⱓ�� ���� �˸���
      JLabel label_1 = new JLabel("\uB0A0\uC9DC");
      label_1.setFont(new Font("����", Font.PLAIN, 15));
      label_1.setBounds(12, 67, 48, 28);
      datePanel.add(label_1);
      // �Ⱓ�� ���� �˸���
      
      // �Ⱓ�� ���� �ؽ�Ʈâ1
      dateTextField = new JTextField();
      dateTextField.setColumns(10);
      dateTextField.setBounds(52, 71, 104, 21);
      datePanel.add(dateTextField);
      // �Ⱓ�� ���� �ؽ�Ʈâ1
      
      // �ؽ�Ʈâ ���� ����
      JLabel lblNewLabel_2 = new JLabel("~");
      lblNewLabel_2.setBounds(168, 74, 26, 15);
      datePanel.add(lblNewLabel_2);
      // �ؽ�Ʈâ ���� ����
      
      // �Ⱓ�� ���� �ؽ�Ʈâ2
      dateTextField2 = new JTextField();
      dateTextField2.setColumns(10);
      dateTextField2.setBounds(188, 71, 110, 21);
      datePanel.add(dateTextField2);
      // �Ⱓ�� ���� �ؽ�Ʈâ2
      
      // �Ⱓ�� ���� ��ư
      dateButton = new JButton("\uC870\uD68C");
      dateButton.setBounds(310, 70, 97, 23);
      datePanel.add(dateButton);
      // �Ⱓ�� ���� ��ư
      
      // �Ⱓ�� ���� ��ũ��
      JScrollPane dateScroll = new JScrollPane();
      dateScroll.setBounds(12, 105, 407, 143);
      datePanel.add(dateScroll);
      // �Ⱓ�� ���� ��ũ��
      
      // �Ⱓ�� ���� ���̺�
      dateTable = new JTable();
      dateScroll.setViewportView(dateTable);
      // �Ⱓ�� ���� ���̺�
      
      
      Vector<Vector<String>> checkNull2 = new Vector<Vector<String>>(); 
      Vector<String> header2 = new Vector<String>();
      header2.add("�Ⱓ");
      header2.add("�Ѹ���");
      
      Vector<Vector<String>> content2 = new Vector<Vector<String>>();
      Vector<String> dateinfo = new Vector<String>();
      
      content2.add(dateinfo);
      DefaultTableModel model2 = new DefaultTableModel(content2,header2);
      dateTable.setModel(model2);
      
      dateButton.addActionListener(new ActionListener() {
         /*�Ⱓ �� �Ѹ��� �׼� ������*/
         public void actionPerformed(ActionEvent e) {
            Vector<Vector<String>> dateInfos;
            
            dateInfos = spm.lookupSalesPerformanceAsDate(dateTextField.getText(),dateTextField2.getText());
            String regExp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";
            if(!(dateTextField.getText().matches(regExp))) {
               JOptionPane.showMessageDialog(null,"�������ڸ� �߸��Է��ϼ˽��ϴ�. �ٽ� �ѹ� Ȯ���� �ּ��� ��¥ ���� : yyyy-mm-dd");
            }
            else if(!(dateTextField.getText().matches(regExp))) {
               JOptionPane.showMessageDialog(null,"������ ���ڸ� �߸��Է��ϼ˽��ϴ�. �ٽ� �ѹ� Ȯ���� �ּ��� ��¥ ���� : yyyy-mm-dd");
            }
            
            else if(dateInfos.equals(checkNull2)) {
               JOptionPane.showMessageDialog(null,"��¥�� �ش��ϴ� ������ �����ϴ�.");
            }
            
            else {
            while(model2.getRowCount() != 0) {
               model2.removeRow(0);
            }
            
            for(int i = 0; i < dateInfos.size(); i++) {
               model2.addRow(dateInfos.get(i));
            }
            }
         
         }
      });
         
      
      //------------------------------------------------------------------------------------------------------------
      
      
      // ��ǰ�� ���� �г�
      JPanel productPanel = new JPanel();
      tabbedPane.addTab("\uC0C1\uD488\uCF54\uB4DC\uBCC4 \uB9E4\uCD9C\uC2E4\uC801 \uC870\uD68C", null, productPanel, null);
      productPanel.setLayout(null);
      // ��ǰ�� ���� �г�
      
      // ��ǰ�� ���� ����
      JLabel lblNewLabel = new JLabel("\uC0C1\uD488\uCF54\uB4DC\uBCC4 \uB9E4\uCD9C\uC2E4\uC801 \uC870\uD68C");
      lblNewLabel.setFont(new Font("����", Font.PLAIN, 21));
      lblNewLabel.setBounds(94, 10, 234, 37);
      productPanel.add(lblNewLabel);
      // ��ǰ�� ���� ����
      
      // ��ǰ�� ���� �˸���
      JLabel lblNewLabel_1 = new JLabel("\uC0C1\uD488\uCF54\uB4DC");
      lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
      lblNewLabel_1.setBounds(12, 70, 63, 28);
      productPanel.add(lblNewLabel_1);
      // ��ǰ�� ���� �˸���
      
      // ��ǰ�� ���� �ؽ�Ʈâ
      productTextField = new JTextField();
      productTextField.setBounds(72, 74, 245, 21);
      productPanel.add(productTextField);
      productTextField.setColumns(10);
      // ��ǰ�� ���� �ؽ�Ʈâ
      
      // ��ǰ�� ���� ��ư
      productButton = new JButton("\uC870\uD68C");
      productButton.setBounds(329, 73, 97, 23);
      productPanel.add(productButton);
      // ��ǰ�� ���� ��ư
   
      
      // ��ǰ�� ���� ��ũ��
      JScrollPane productScroll = new JScrollPane();
      productScroll.setBounds(12, 108, 407, 140);
      productPanel.add(productScroll);
      // ��ǰ�� ���� ��ũ��
      
      // ��ǰ�� ���� ���̺�
      productTable = new JTable();
      productScroll.setViewportView(productTable);
      // ��ǰ�� ���� ���̺�
      
      Vector<Vector<String>> checkNull = new Vector<Vector<String>>(); 
      Vector<String> header = new Vector<String>();
      header.add("��ǰ�ڵ�");
      header.add("�Ѹ���");
      
      Vector<Vector<String>> content = new Vector<Vector<String>>();
      Vector<String> productinfo = new Vector<String>();
      
      content.add(productinfo);
      DefaultTableModel model = new DefaultTableModel(content,header);
      productTable.setModel(model);
      
      productButton.addActionListener(new ActionListener() {
          /*��ǰ�� �Ѹ��� �׼� ������*/
          public void actionPerformed(ActionEvent e) {
             Vector<Vector<String>> productInfos;
             productInfos = spm.lookupSalesPerformanceAsProductCode(productTextField.getText());
             if(productInfos.equals(checkNull)) {
                JOptionPane.showMessageDialog(null,"��ϵǾ� ���� ���� ��ǰ�ڵ��Դϴ�. �ٽ� �ѹ� Ȯ���� �ּ���");
             }
             while(model.getRowCount() != 0) {
                model.removeRow(0);
             }
             
             for(int i = 0; i < productInfos.size(); i++) {
                model.addRow(productInfos.get(i));
             }
          }
       });
      
         
   
   }

}