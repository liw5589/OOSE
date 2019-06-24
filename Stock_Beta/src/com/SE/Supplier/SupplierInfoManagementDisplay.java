package com.SE.Supplier;

//  @ Project : �Ұ� �౹
//  @ File Name : SupplierInfoManagementDisplay.java
//  @ Date : 2019-06-22
//  @ Author : �ڰ渰

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.SE.BaseData.SettingInfo;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SupplierInfoManagementDisplay extends JFrame {
	private JTable table1;
	private JTable table2;
	private SupplierInfoManagement simg;
	
	// modifyFrame
	JPanel contentPane;
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	JTextField textField4;
	JTextField textField5;

	public SupplierInfoManagementDisplay() {
		simg = new SupplierInfoManagement();
		setBounds(300, 50, 800, 600);
		
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel1 = new JPanel();
		tabbedPane.addTab("\uAC70\uB798\uCC98\uC815\uBCF4\uB4F1\uB85D", null, panel1, null);
		panel1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 80, 700, 383);
		panel1.add(scrollPane_1);

		table1 = new JTable(); // �� ���̺��� ����ϴ����� : ������û�� , �Աݳ��� , �� ���� �ܰ�(����� ��û�ڸ����ȸ)
		table1.setRowHeight(25);
		scrollPane_1.setViewportView(table1);

		Vector<String> header = new Vector<String>();
		header.add("�ŷ�ó�̸�");
		header.add("�̸����ּ�");
		header.add("������");
		header.add("����ó");

		Vector<Vector<String>> content1 = new Vector<Vector<String>>();
		Vector<String> supplierInfo = new Vector<String>();

		content1.add(supplierInfo);
		DefaultTableModel model = new DefaultTableModel(content1, header);
		table1.setModel(model);

		JLabel label2 = new JLabel("\uAC70\uB798\uCC98\uC815\uBCF4\uB4F1\uB85D");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("����", Font.BOLD, 20));
		label2.setBounds(126, 10, 523, 50);
		panel1.add(label2);

		JButton button2 = new JButton("\uB4F1\uB85D\uD558\uAE30");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Vector> infos = new Vector<Vector>();
				infos = model.getDataVector();
				simg.insertSupplier(infos);//�̷��� ���ͷ� �޵���, �̰� ���ö���Ŭ������ �ִ���
				JOptionPane.showMessageDialog(null,"����� �Ϸ� �Ǿ����ϴ�.");
			}
		});
		button2.setFont(new Font("����", Font.BOLD, 15));
		button2.setBounds(639, 482, 111, 34);
		panel1.add(button2);

		JButton button4 = new JButton("\uD589\uCD94\uAC00");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String> info = new Vector<String>();
				model.addRow(info);

			}
		});
		button4.setFont(new Font("����", Font.BOLD, 15));
		button4.setBounds(516, 482, 111, 34);
		panel1.add(button4);

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("\uAC70\uB798\uCC98\uC815\uBCF4\uC870\uD68C.\uC218\uC815", null, panel2, null);
		panel2.setLayout(null);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(49, 170, 700, 305);
		panel2.add(scrollPane2);

		table2 = new JTable();
		table2.setRowHeight(25);
		scrollPane2.setViewportView(table2);
		Vector<String> header2 = new Vector<String>();
		Vector<Vector<String>> content2 = new Vector<Vector<String>>();

		header2.add("�ŷ�ó�ڵ�");
		header2.add("�ŷ�ó�̸�");
		header2.add("�̸����ּ�");
		header2.add("������");
		header2.add("����ó");

		DefaultTableModel model2 = new DefaultTableModel(content2, header2);
		table2.setModel(model2);

		JLabel label3 = new JLabel("\uAC70\uB798\uCC98\uC815\uBCF4\uC870\uD68C.\uC218\uC815");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("����", Font.BOLD, 20));
		label3.setBounds(130, 10, 523, 50);
		panel2.add(label3);

		// ��ȸ��ư Ŭ����
		JButton button3 = new JButton("\uC870\uD68C\uD558\uAE30");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String regExp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";

				Vector<Vector<String>> supplierInfos;
				supplierInfos = simg.lookupSupplier();
				// ���������� ����
				while (model2.getRowCount() != 0) {
					model2.removeRow(0);
				}
				// ��ȸ������ ����
				for (int i = 0; i < supplierInfos.size(); i++) {
					model2.addRow(supplierInfos.get(i));
				}
			}
		});
		button3.setFont(new Font("����", Font.BOLD, 15));
		button3.setBounds(550, 70, 111, 80);
		panel2.add(button3);

		JButton btnNewButton = new JButton("\uC218\uC815");
		btnNewButton.setFont(new Font("����", Font.BOLD, 13));
		// ���� Ŭ����
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table2.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "�����ϰ����ϴ� ���� �������ּ���");
					return;
				}
				int selectedRow = table2.getSelectedRow();
				String supplierCode = (String)table2.getValueAt(selectedRow, 0);
				SupplierInfo supplierInfo = simg.lookupSupplierAsSupplierCode(supplierCode);
				
				printSupplierModifyDisplay();
				textField1.setText(supplierInfo.getSupplierCode());
				textField2.setText(supplierInfo.getName());
				textField3.setText(supplierInfo.getEmail());
				textField4.setText(supplierInfo.getManager());
				textField5.setText(supplierInfo.getPhoneNum());
			}
		});
		btnNewButton.setBounds(501, 486, 74, 36);
		panel2.add(btnNewButton);

		JButton button = new JButton("\uC0AD\uC81C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table2.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "�����ϰ����ϴ� ���� �������ּ���");
					return;
				}
				int delete = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?","����",JOptionPane.YES_NO_OPTION);
				if (delete != JOptionPane.OK_OPTION){
					JOptionPane.showMessageDialog(null, "������ ����Ͽ����ϴ�.");
					return;
	            }
				int selectedRow = table2.getSelectedRow();
				String supplierCode = (String)table2.getValueAt(selectedRow, 0);
				boolean result = simg.deleteSupplier(supplierCode);
				
				if(result) {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
					button3.doClick();					
				}
				else {
					JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�. �ٽ� �õ����ּ���");
				}
			}
		});
		button.setFont(new Font("����", Font.BOLD, 13));
		button.setBounds(587, 486, 74, 36);
		panel2.add(button);
		
		JButton button1 = new JButton("\uCD9C\uB825");
		button1.setFont(new Font("����", Font.BOLD, 13));
		button1.setBounds(675, 485, 74, 36);
		panel2.add(button1);

		// �� ��� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = table1.getColumnModel();
		TableColumnModel tcm2 = table2.getColumnModel();
		// ��ü �� ����
		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		}
		// ��ü �� ����
		for (int i = 0; i < tcm2.getColumnCount(); i++) {
			tcm2.getColumn(i).setCellRenderer(dtcr);
		}

		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //���α׷�����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose(); //����â�� �ݴ´�.
	}


	private void printSupplierModifyDisplay() {
		JFrame modifyFrame = new JFrame();
				
		modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modifyFrame.setBounds(300, 100, 405, 440);
		modifyFrame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		modifyFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<�ŷ�ó��������>");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setBounds(138, 10, 112, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("�ŷ�ó�ڵ�");
		lblNewLabel1.setFont(new Font("����", Font.BOLD, 13));
		lblNewLabel1.setBounds(28, 48, 77, 25);
		contentPane.add(lblNewLabel1);
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setBounds(109, 48, 245, 25);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(109, 83, 245, 25);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(109, 118, 245, 25);
		contentPane.add(textField3);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(109, 153, 245, 25);
		contentPane.add(textField4);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(109, 188, 245, 25);
		contentPane.add(textField5);
		
		JLabel label1 = new JLabel("�ŷ�ó�̸�");
		label1.setFont(new Font("����", Font.BOLD, 13));
		label1.setBounds(28, 83, 77, 25);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("�̸���");
		label2.setFont(new Font("����", Font.BOLD, 13));
		label2.setBounds(28, 118, 77, 25);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("�����");
		label3.setFont(new Font("����", Font.BOLD, 13));
		label3.setBounds(28, 153, 77, 25);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("����ó");
		label4.setFont(new Font("����", Font.BOLD, 13));
		label4.setBounds(28, 188, 77, 25);
		contentPane.add(label4);
		
		JButton btnNewButton = new JButton("\uCDE8\uC18C");
		btnNewButton.setBounds(294, 366, 60, 25);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				modifyFrame.setVisible(false);
				modifyFrame.dispose();
			}
		});
		
		JButton button = new JButton("\uC218\uC815");
		button.setBounds(227, 366, 60, 25);
		contentPane.add(button);
		// ������ưŬ��
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SupplierInfo supplierInfo = new SupplierInfo();
				supplierInfo.setSupplierCode(textField1.getText());
				supplierInfo.setName(textField2.getText());
				supplierInfo.setEmail(textField3.getText());
				supplierInfo.setManager(textField4.getText());
				supplierInfo.setPhoneNum(textField5.getText());
				boolean result = simg.modifySupplier(supplierInfo);
				
				if(result) {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
				}
				else {
					JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�. �Է������� Ȯ�����ּ���");
				}
			}
		});
	}
	
}