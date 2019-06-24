package com.SE.product;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;


public class ProductInfoDisplay extends JFrame {

	private ProductInfoManagement pim = new ProductInfoManagement();
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInfoDisplay frame = new ProductInfoDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//���� ��ȭ����
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	JTextField textField4;
	JTextField textField5;
	JTextField textField6;

	
	public ProductInfoDisplay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		//ù��° ��
		tabbedPane.addTab("��ǰ��ȸ", null, panel, null);
		
		panel.setLayout(null);

		String[] aa = { "��ǰ�ڵ�", "�Ǿ�ǰ�з�", "��ǰ��", "������", "���Ǽ���", "�ҸŰ���" };
		// String[][] b = { { "a1", "a2", "a3" }, { "b1", "b2", "b3" }, { "c1", "c2",
		// "c3" } };
		// 1. �𵨰� �����͸� ����
		DefaultTableModel model2 = new DefaultTableModel(null, aa);
		// 2. Model�� �Ű������� ����, new JTable(b,a)�� ����������
		// ���� ������ �ϱ� ���� �ش� ����� ����մϴ�
		JTable table2 = new JTable(model2);
		table2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setAutoResizeMode(HEIGHT);
		table2.setLocation(47, 54);
		// 3. ��������δ� JScrollPane�� �߰��մϴ�.
		JScrollPane sc2 = new JScrollPane(table2);
		sc2.setSize(531, 247);
		sc2.setLocation(30, 30);
		panel.add(sc2);
		
		
		
		JButton button2 = new JButton("��ȸ");
		button2.setBounds(350, 322, 70, 23);
		panel.add(button2);
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//�о ���̺� ���� �߰�
				pim.lookupProduct();	
				DefaultTableModel m2 = (DefaultTableModel) table2.getModel();
				Vector<ProductInfo> v2 = (Vector<ProductInfo>) pim.getV();
				
				m2.setRowCount(0);
				for (int i = 0; i < v2.size(); i++) {
					String[] rowData = { v2.elementAt(i).getProductCode(), v2.elementAt(i).getProductClassification(),
							v2.elementAt(i).getProductName(), v2.elementAt(i).getProductManufacturer(),
							Integer.toString(v2.elementAt(i).getAttentionLevel()),
							Integer.toString(v2.elementAt(i).getProductRetailPrice()) };
					m2.addRow(rowData);
				}
				// �߰��� ��ġ�� ������ ������ �˸��ϴ�.
				table2.updateUI();
				
			}
		});
		
		JButton button3 = new JButton("����");
		button3.setBounds(440, 322, 70, 23);
		panel.add(button3);
		
		button3.addActionListener(new ActionListener() {
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
				String productCode = (String)table2.getValueAt(selectedRow, 0);
				boolean result = pim.deleteProduct(productCode);
				
				if(result) {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
					button2.doClick();					
				}
				else {
					JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�. �ٽ� �õ����ּ���");
				}
			}
		});
		
		//����
		JButton button4 = new JButton("����");
		button4.setBounds(530, 322, 70, 23);
		panel.add(button4);
		
	
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table2.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "�����ϰ����ϴ� ���� �������ּ���");
					return;
				}
				int selectedRow = table2.getSelectedRow();
				String productCode = (String)table2.getValueAt(selectedRow, 0);
				ProductInfo ProductInfo = pim.lookupProductInfo(productCode);
						
				printProductModifyDisplay();
				textField1.setText(ProductInfo.getProductCode());
				textField2.setText(ProductInfo.getProductClassification());
				textField3.setText(ProductInfo.getProductName());
				textField4.setText(ProductInfo.getProductManufacturer());
				textField5.setText(Integer.toString(ProductInfo.getAttentionLevel()));
				textField6.setText(Integer.toString(ProductInfo.getProductRetailPrice()));

			}
		});
		
		
		
		//�ι�° ��
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("��ǰ���", null, panel_1, null);
		panel_1.setLayout(null);

		String[] a = { "��ǰ�ڵ�", "�Ǿ�ǰ�з�", "��ǰ��", "������", "���Ǽ���", "�ҸŰ���" };
		// String[][] b = { { "a1", "a2", "a3" }, { "b1", "b2", "b3" }, { "c1", "c2",
		// "c3" } };
		// 1. �𵨰� �����͸� ����
		DefaultTableModel model = new DefaultTableModel(null, a);
		// 2. Model�� �Ű������� ����, new JTable(b,a)�� ����������
		// ���� ������ �ϱ� ���� �ش� ����� ����մϴ�
		JTable table = new JTable(model);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(HEIGHT);
		table.setLocation(47, 54);
		// 3. ��������δ� JScrollPane�� �߰��մϴ�.
		JScrollPane sc = new JScrollPane(table);
		sc.setSize(531, 247);
		sc.setLocation(30, 30);
		panel_1.add(sc);

		// �߰��� ��ġ�� ������ ������ �˸��ϴ�.
		table.updateUI();
		
		JButton button = new JButton("����ã��");
		button.setBounds(363, 322, 97, 23);
		panel_1.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				// For Directory
				// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// For File
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().toString());

					String filePath = fileChooser.getSelectedFile().getAbsolutePath(); // ���ϰ��
					// ���̺� ������ �߰��ϱ�
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					Vector<ProductInfo> v = (Vector<ProductInfo>) pim.readProductList(filePath);
					
					for (int i = 0; i < v.size(); i++) {
						String[] rowData = { v.elementAt(i).getProductCode(), v.elementAt(i).getProductClassification(),
								v.elementAt(i).getProductName(), v.elementAt(i).getProductManufacturer(),
								Integer.toString(v.elementAt(i).getAttentionLevel()),
								Integer.toString(v.elementAt(i).getProductRetailPrice()) };

						m.addRow(rowData);
					}
					
					// �߰��� ��ġ�� ������ ������ �˸��ϴ�.
					table.updateUI();
				}
			}
		});

		JButton button_1 = new JButton("���Ȯ��");
		button_1.setBounds(475, 322, 97, 23);
		panel_1.add(button_1);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbm = new DBManager();
				Vector<ProductInfo> v = pim.getV();
				for (int i = 0; i < v.size(); i++) {
	
				dbm.insertDBProduct(
						v.elementAt(i).getProductCode(), v.elementAt(i).getProductClassification(), v.elementAt(i).getProductName(),
						v.elementAt(i).getProductManufacturer(),
						Integer.toString(v.elementAt(i).getAttentionLevel()), v.elementAt(i).getProductRetailPrice()
						);
				}
				JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.");


	
				pim.resetVector();
				
			}
		});
		
	}
	private void printProductModifyDisplay() {
		JFrame modifyFrame = new JFrame();
				
		modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modifyFrame.setBounds(300, 100, 405, 440);
		modifyFrame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		modifyFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("��ǰ��������");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setBounds(138, 10, 112, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("��ǰ�ڵ�");
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
		
		textField6 = new JTextField();
		textField6.setColumns(10);
		textField6.setBounds(109, 223, 245, 25);
		contentPane.add(textField6);
		
		JLabel label1 = new JLabel("�Ǿ�ǰ�з�");
		label1.setFont(new Font("����", Font.BOLD, 13));
		label1.setBounds(28, 83, 77, 25);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("��ǰ�̸�");
		label2.setFont(new Font("����", Font.BOLD, 13));
		label2.setBounds(28, 118, 77, 25);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("������");
		label3.setFont(new Font("����", Font.BOLD, 13));
		label3.setBounds(28, 153, 77, 25);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("���Ǽ���");
		label4.setFont(new Font("����", Font.BOLD, 13));
		label4.setBounds(28, 188, 77, 25);
		contentPane.add(label4);
		
		JLabel label5 = new JLabel("�ҸŰ���");
		label5.setFont(new Font("����", Font.BOLD, 13));
		label5.setBounds(28, 223, 77, 25);
		contentPane.add(label5);
		
		
		JButton btnNewButton = new JButton("���");
		btnNewButton.setBounds(294, 366, 60, 25);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				modifyFrame.setVisible(false);
				modifyFrame.dispose();
			}
		});
		
		JButton button = new JButton("����");
		button.setBounds(227, 366, 60, 25);
		contentPane.add(button);
		// ������ưŬ��
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInfo ProductInfo = new ProductInfo();
				ProductInfo.setProductCode(textField1.getText());
				ProductInfo.setProductClassification(textField2.getText());
				ProductInfo.setProductName(textField3.getText());
				ProductInfo.setProductManufacturer(textField4.getText());
				ProductInfo.setAttentionLevel(Integer.parseInt(textField5.getText()));
				ProductInfo.setProductRetailPrice(Integer.parseInt(textField5.getText()));

				boolean result = pim.modifyProduct(ProductInfo);
				
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
