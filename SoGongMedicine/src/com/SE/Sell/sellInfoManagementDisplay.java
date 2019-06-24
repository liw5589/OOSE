//  @ Project : �Ұ� �౹
//  @ File Name : sellInfoManagementDisplay.java
//  @ Date : 2019-06-07
//  @ Author : �̼���

package com.SE.Sell;

import java.awt.BorderLayout;


import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.SystemColor;

public class sellInfoManagementDisplay extends JFrame {

	public static final double DISCOUNT = 0.05;

	private JTable sellInfoTable;
	private JTable lookupSellRecordTable;
	private JTextField dateTextField;
	private JTextField sellCodeTextField;
	private JTextField Selling_productCode_textField;
	private JTextField Selling_productQty_textField;
	private JLabel Selling_totalPrice_labe;
	private JLabel Selling_totalPrice_view;
	JButton sellingComplete_btn;

	// ��ǰ�Ǹſ� ���� ����
	private int totalPrice;
	static ArrayList<proudctSellRecordInfo> productInfo = new ArrayList<proudctSellRecordInfo>();
	static sellRecordInfo sellInfo;
	static int totalLevel;
	
	// �Ǹ� �ϷḦ ���� �κ�
	private JPanel successSellPanel;
	private JTextField customerInfoTextField;
	private JButton successSellBtn;
	private JCheckBox noCustomerInfoCheck;
	private JButton customerInfoLBtn;
	private JLabel TotalResultLabelView;
	private JProgressBar interestedCusPrograss;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// ������ ����� ���� �κ�
	private JPanel receiptOutputPanel;
	static String receiptSellCode;
	JLabel receiptDateTextField;
	JTextArea recieptProductNameLabel;
	JTextArea recieptProductQtyLabel;
	JTextArea recieptProductPriceLabel;
	JLabel receiptSellPriceLabel;

	/*------------------------ �� �޴� ���� ------------------------*/

	public sellInfoManagementDisplay() {
		setTitle("\uC0C1\uD488\uD310\uB9E4\uAD00\uB9AC");

		setBounds(300, 50, 800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPanel, BorderLayout.CENTER);

		/*---------------��ǰ�Ǹſ� ���� �κ� �г� ����---------------*/

		JPanel productSell = new JPanel();
		tabbedPanel.addTab("��ǰ�Ǹ�", null, productSell, null);
		productSell.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 127, 700, 292);
		productSell.add(scrollPane_1);

		// ���̺� ���� �κ�
		sellInfoTable = new JTable();
		sellInfoTable.setRowHeight(25);
		scrollPane_1.setViewportView(sellInfoTable);

		// �÷� ���� ����
		Vector<String> sellingHeader = new Vector<String>();
		sellingHeader.add("��ǰ �ڵ�");
		sellingHeader.add("��ǰ��");
		sellingHeader.add("����");
		sellingHeader.add("����");

		Vector<Vector<String>> content1 = new Vector<Vector<String>>();

		DefaultTableModel model = new DefaultTableModel(content1, sellingHeader);
		sellInfoTable.setModel(model);

		JLabel label2 = new JLabel("\uD310\uB9E4\uAD00\uB9AC");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("�������", Font.BOLD, 20));
		label2.setBounds(37, 23, 99, 46);
		productSell.add(label2);

		JLabel label_1 = new JLabel("\uC0C1\uD488 \uD310\uB9E4");
		label_1.setFont(new Font("�������", Font.PLAIN, 14));
		label_1.setBounds(135, 34, 62, 28);
		productSell.add(label_1);

		Selling_productCode_textField = new JTextField();
		Selling_productCode_textField.setBounds(127, 85, 191, 21);
		productSell.add(Selling_productCode_textField);
		Selling_productCode_textField.setColumns(10);

		JLabel Selling_productCode_label = new JLabel("\uC0C1\uD488 \uCF54\uB4DC");
		Selling_productCode_label.setBounds(50, 88, 65, 15);
		productSell.add(Selling_productCode_label);

		JLabel Selling_productQty_label = new JLabel("\uC218\uB7C9");
		Selling_productQty_label.setBounds(356, 88, 65, 15);
		productSell.add(Selling_productQty_label);

		Selling_productQty_textField = new JTextField();
		Selling_productQty_textField.setText("1");
		Selling_productQty_textField.setColumns(10);
		Selling_productQty_textField.setBounds(396, 85, 41, 21);
		productSell.add(Selling_productQty_textField);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(50, 429, 700, 43);
		productSell.add(panel);
		panel.setLayout(null);

		Selling_totalPrice_labe = new JLabel("\uCD1D \uD569\uACC4 \uAE08\uC561");
		Selling_totalPrice_labe.setBounds(453, 15, 86, 18);
		Selling_totalPrice_labe.setFont(new Font("�������", Font.BOLD, 15));
		panel.add(Selling_totalPrice_labe);

		// �� ���� ���� �� �κ�
		Selling_totalPrice_view = new JLabel();
		Selling_totalPrice_view.setFont(new Font("�������", Font.PLAIN, 14));
		Selling_totalPrice_view.setBounds(551, 17, 126, 15);
		panel.add(Selling_totalPrice_view);

		sellInfo = new sellRecordInfo();

		// ��ǰ �߰� ��ư�� ������ ���̺� ������ ��ǰ ������ ǥ�õȴ�
		JButton Selling_Plus_btn = new JButton("\uCD94\uAC00");
		Selling_Plus_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ��ǰ�ڵ�, ��ǰ��, ����, ����
				String productCode = Selling_productCode_textField.getText();
				int productQty = Integer.parseInt((String) Selling_productQty_textField.getText());

				// ��Ʈ��
				sellInfoManagementControl control = new sellInfoManagementControl();
				proudctSellRecordInfo temp = control.getProductInfo(productCode);

				if (temp == null) {
					JOptionPane.showMessageDialog(null, "��ǰ�� �������� �ʽ��ϴ�! ��ǰ �ڵ� Ȯ��!");
					return;
				} else {
					int price = temp.getProductPrice();
					String productName = temp.getProductName();

					// ��ǰ ���� ����
					temp.setQty(productQty);
					temp.setProductCode(productCode);
					temp.setTotalprice(price * productQty);
					temp.setProductName(productName);

					// ���� ����
					totalPrice += price * productQty;
					sellInfo.setTotalPrice(totalPrice);

					// ���̺� �÷� �߰�
					Vector<String> userRow = new Vector<String>();

					userRow.addElement(temp.getProductCode());
					userRow.addElement(temp.getProductName());
					userRow.addElement(Integer.toString(temp.getQty()));
					userRow.addElement(Integer.toString(temp.getProductPrice()));

					model.addRow(userRow);

					// ���� �����ֱ�
					Selling_totalPrice_view.setText(Integer.toString(totalPrice));

					// �Ǹ� ��ǰ �߰�
					productInfo.add(temp);
				}

			}
		});

		Selling_Plus_btn.setBounds(478, 84, 62, 23);
		productSell.add(Selling_Plus_btn);

		// �ǸſϷḦ ���� �������� �κ�
		sellingComplete_btn = new JButton("\uD310\uB9E4\uC644\uB8CC");
		sellingComplete_btn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {

				int count = sellInfoTable.getModel().getRowCount();
						
				if(count == 0)
				{ 
					JOptionPane.showMessageDialog(null, "�Ǹ� �� ��ǰ ������ �Է� �� �ּ���!");
					return;
				} else {
					sellSuccessDisplay();
				}
				
				int totalPrice = sellInfo.getTotalPrice();

				TotalResultLabelView.setText(Integer.toString(totalPrice));
			}
		});

		sellingComplete_btn.setFont(new Font("�������", Font.BOLD, 15));
		sellingComplete_btn.setBounds(639, 482, 111, 34);
		productSell.add(sellingComplete_btn);

		/*---------------��ǰ�Ǹſ� ���� �κ� �г� ��---------------*/

		/*---------------�Ǹ��̷���ȸ�� ���� �κ� �г�---------------*/

		JPanel lookupSellRecord = new JPanel();
		tabbedPanel.addTab("�Ǹ��̷���ȸ", null, lookupSellRecord, null);
		lookupSellRecord.setLayout(null);

		JScrollPane lookUpTable = new JScrollPane();
		lookUpTable.setBounds(49, 170, 700, 305);
		lookupSellRecord.add(lookUpTable);

		lookupSellRecordTable = new JTable();
		lookupSellRecordTable.setRowHeight(25);
		lookUpTable.setViewportView(lookupSellRecordTable);

		Vector<String> lookUpHeader = new Vector<String>();
		Vector<Vector<String>> lookUpContent = new Vector<Vector<String>>();

		lookUpHeader.add("���ų�¥");
		lookUpHeader.add("�Ǹ��ڵ�");
		lookUpHeader.add("�������");
		lookUpHeader.add("������ǰ");
		lookUpHeader.add("�� �ǸŰ���");
		lookUpHeader.add("������");

		DefaultTableModel lookupModel = new DefaultTableModel(lookUpContent, lookUpHeader);
		lookupSellRecordTable.setModel(lookupModel);

		JLabel label3 = new JLabel("\uD310\uB9E4\uC774\uB825\uC870\uD68C");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("�������", Font.BOLD, 20));
		label3.setBounds(130, 10, 523, 50);
		lookupSellRecord.add(label3);

		// ��ȸ��ư Ŭ����
		JButton lookupBtn = new JButton("\uC870\uD68C\uD558\uAE30");
		lookupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// ��Ʈ�� Ŭ���� ����
				sellInfoManagementControl control = new sellInfoManagementControl();

				// ��ȸ�� ���� ���� ����
				ArrayList<sellRecordInfo> sellInfoList = new ArrayList<sellRecordInfo>();
				sellRecordInfo lookupSellInfo = new sellRecordInfo();
				String sellDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";

				// ��ȸ
				String date = dateTextField.getText();
			

				// ���������� ����
				while (lookupModel.getRowCount() != 0) {
					lookupModel.removeRow(0);
				}

				// ���̺� �÷� �߰�
				Vector<String> userRow = new Vector<String>();

				// ��ȸ�� ���� �б⹮
				if (sellCodeTextField.getText().equals("") && dateTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�˻� ������ �Է����ּ���");
					return;
				}

				// ��ǰ�� ��ȸ
				else if (!sellCodeTextField.getText().equals("") && dateTextField.getText().equals("")) {

					lookupSellInfo = control.getLookupSellRecorde(sellCodeTextField.getText());

					if (lookupSellInfo == null) {
						JOptionPane.showMessageDialog(null, "��ȸ�� �� ���� �����Դϴ�!(���ڵ常 �Է°���)");
						return;

					} else {
						// �Ǹ��ڵ忡 ���� ��ȸ���
						userRow.addElement(lookupSellInfo.getSellDate());
						userRow.addElement(lookupSellInfo.getSellCode());

						// ���� ��� 0:����, 1:ī��
						if (lookupSellInfo.getPaymentMethod() == 0) {
							userRow.addElement("����");
						} else {
							userRow.addElement("ī��");
						}

						userRow.addElement(lookupSellInfo.getSellProductList().toString());
						userRow.addElement(Integer.toString(lookupSellInfo.getTotalPrice()));
						userRow.addElement(lookupSellInfo.getCustomerName());

						lookupModel.addRow(userRow);
					}

				}

				// ��¥ ���� Ȯ��
				else if (!date.matches(sellDateFormat)) {
					JOptionPane.showMessageDialog(null, "��¥������ Ȯ�����ּ���!(yyyy-mm-dd)");
					return;
				}

				// ��¥�� ��ȸ
				else if (sellCodeTextField.getText().equals("") && !dateTextField.getText().equals("")) {

					sellInfoList = control.getSellDateLookupRecorde(dateTextField.getText());

					if (sellInfoList == null) {
						JOptionPane.showMessageDialog(null, "���� �ŷ��� ������ �������� �ʽ��ϴ�!");
						return;
					}

					for (int i = 0; i < sellInfoList.size(); i++) {

						String sellDateInfo = sellInfoList.get(i).getSellDate();
						String sellCodeInfo = sellInfoList.get(i).getSellCode();
						String paymentMethodInfo;
						String sellInfoListInfo = sellInfoList.get(i).getSellProductList().toString();
						String totalPriceInfo = Integer.toString(sellInfoList.get(i).getTotalPrice());
						String customerName = sellInfoList.get(i).getCustomerName();

						// ���� ��� 0:����, 1:ī��
						if (sellInfoList.get(i).getPaymentMethod() == 0) {
							paymentMethodInfo = "����";
						} else {
							paymentMethodInfo = "ī��";
						}

						Object[] data = { sellDateInfo, sellCodeInfo, paymentMethodInfo, sellInfoListInfo,
								totalPriceInfo, customerName };

						lookupModel.addRow(data);
					}

				}
			}
		});

		lookupBtn.setFont(new Font("�������", Font.BOLD, 15));
		lookupBtn.setBounds(550, 70, 111, 80);
		lookupSellRecord.add(lookupBtn);

		dateTextField = new JTextField();
		dateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		dateTextField.setColumns(10);
		dateTextField.setBounds(223, 116, 297, 34);
		lookupSellRecord.add(dateTextField);

		sellCodeTextField = new JTextField();
		sellCodeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		sellCodeTextField.setColumns(10);
		sellCodeTextField.setBounds(223, 70, 297, 34);
		lookupSellRecord.add(sellCodeTextField);

		JLabel sellCodeInputLabel = new JLabel("\uD310\uB9E4\uCF54\uB4DC\uC785\uB825");
		sellCodeInputLabel.setFont(new Font("�������", Font.BOLD, 14));
		sellCodeInputLabel.setBounds(110, 70, 101, 34);
		lookupSellRecord.add(sellCodeInputLabel);

		JLabel dataInputLabel = new JLabel("\uB0A0\uC9DC\uC785\uB825");
		dataInputLabel.setFont(new Font("�������", Font.BOLD, 14));
		dataInputLabel.setBounds(138, 116, 58, 34);
		lookupSellRecord.add(dataInputLabel);

		// ������ ��� ��ư
		JButton receiptBtn = new JButton("\uC601\uC218\uC99D \uCD9C\uB825");
		receiptBtn.addActionListener(new ActionListener() {
			// ������ ����ϱ�
			public void actionPerformed(ActionEvent e) {
				
				// ������ ����� ���� �κ�
				if (lookupSellRecordTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "�������� ����ϱ� ���� �ŷ��� �������ּ���!");
					return;
				}

				// ����� ������ ���� ���̾�α�
				int cancel = JOptionPane.showConfirmDialog(null, "�� �ŷ��� ���� �������� ����Ͻðڽ��ϱ�?", "���", JOptionPane.YES_NO_OPTION);

				if (cancel != JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "�ش� �ŷ��� ���� ������ ����� ��ҵǾ����ϴ�!");
					return;
				} else {
					receiptOutputDisplay();
				}

			}
		});
		receiptBtn.setFont(new Font("�������", Font.BOLD, 13));
		receiptBtn.setBounds(632, 485, 117, 36);
		lookupSellRecord.add(receiptBtn);

		// �Ǹ� ��� ��ư
		JButton cancelBtn = new JButton("\uD310\uB9E4\uCDE8\uC18C");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ��Ʈ�� Ŭ���� ����
				sellInfoManagementControl control = new sellInfoManagementControl();

				if (lookupSellRecordTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "�����ϰ����ϴ� ���� �������ּ���");
					return;
				}

				// ������ ������ ���� ���̾�α�
				int delete = JOptionPane.showConfirmDialog(null, "���� ����Ͻðڽ��ϱ�?", "���", JOptionPane.YES_NO_OPTION);

				if (delete != JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "�Ǹ���� ��û�� ��ҵǾ����ϴ�!");
					return;
				}

				// ���õ� ���ڵ�
				int selectedRow = lookupSellRecordTable.getSelectedRow();
				String sellCode = (String) lookupSellRecordTable.getValueAt(selectedRow, 1);
				int result = control.sellProudctcancel(sellCode);

				if (result == 1) {
					if (delete != JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "�Ǹ���ҵǾ����ϴ�!");
						return;
					}
				}

			}
		});

		cancelBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		cancelBtn.setBounds(504, 485, 117, 36);
		lookupSellRecord.add(cancelBtn);

		// �� ��� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm1 = sellInfoTable.getColumnModel();
		TableColumnModel tcm2 = lookupSellRecordTable.getColumnModel();

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

	/*------------------------ �� �޴� �� ------------------------*/

	/*------------------------ ���� �ǸſϷ� â ------------------------*/

	public void sellSuccessDisplay() {

		JFrame sellSuccessDisplay = new JFrame();

		sellSuccessDisplay.setTitle(
				"\uC0C1\uD488\uD310\uB9E4-\uACE0\uAC1D\uC815\uBCF4 \uD655\uC778 \uBC0F \uD310\uB9E4\uC644\uB8CC");
		sellSuccessDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sellSuccessDisplay.setBounds(100, 100, 699, 400);
		sellSuccessDisplay.setVisible(true);

		successSellPanel = new JPanel();
		successSellPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sellSuccessDisplay.setContentPane(successSellPanel);
		successSellPanel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 69, 661, 15);
		successSellPanel.add(separator);

		JLabel customerInfoLabel = new JLabel("\uACE0\uAC1D \uC815\uBCF4 \uD655\uC778");
		customerInfoLabel.setFont(new Font("�������", Font.BOLD, 14));
		customerInfoLabel.setBounds(43, 112, 96, 15);
		successSellPanel.add(customerInfoLabel);

		customerInfoTextField = new JTextField();

		// �� �̸� �޾ƿ��� �ؽ�Ʈ �ʵ�
		customerInfoTextField.setBounds(159, 106, 262, 27);
		successSellPanel.add(customerInfoTextField);
		customerInfoTextField.setColumns(10);

		// �� �̸� �Է� �� ��ư���� ���� Ȯ��
		customerInfoLBtn = new JButton("\uD655\uC778");
		customerInfoLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sellInfoManagementControl control = new sellInfoManagementControl();

				String name = customerInfoTextField.getText();
				String returnCustomerCode = control.customerCheck(name);
				
				//���赵 ��������
				int level = control.getInterestedCustomer(name);
				System.out.println("�̰��������̴ٴٴٴٴ�"+level);
				
				totalLevel = level;
				
				if(level >= 80) {
					JOptionPane.showMessageDialog(null, "�ݵ�� ���������� �ؾ��ϴ� ����Դϴ�!");
					return;
				}
				
				interestedCusPrograss.setValue(level);
				
				if (returnCustomerCode == "��ȸ��") {
					JOptionPane.showMessageDialog(null, "�ش� �����ڸ� ã�� �� �����ϴ�!");
					String temp = control.getNoCustomerCode();
					sellInfo.setCustomerCode(temp);
					returnCustomerCode = " ";

				} else {
					// �������� ���� �� ���ô�
					int totalPrice = sellInfo.getTotalPrice();
					double totalTemp = DISCOUNT * totalPrice;

					totalPrice = (int) (totalPrice - totalTemp);
					sellInfo.setTotalPrice(totalPrice);
					sellInfo.setCustomerCode(returnCustomerCode);

					TotalResultLabelView.setText(Integer.toString(totalPrice));
				}

			}
		});

		customerInfoLBtn.setFont(new Font("�������", Font.PLAIN, 12));
		customerInfoLBtn.setBounds(444, 105, 91, 28);
		successSellPanel.add(customerInfoLBtn);

		// ����̸� üũ�ϵ��� �ϱ�
		noCustomerInfoCheck = new JCheckBox("\uC678\uBD80\uACE0\uAC1D");
		noCustomerInfoCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				sellInfoManagementControl control = new sellInfoManagementControl();

				String returnCode = control.getNoCustomerCode();

				if (e.getStateChange() == ItemEvent.SELECTED) {

					int result = JOptionPane.showConfirmDialog(null, "�� �ƴ��� üũ�˴ϴ�!", "Confirm",
							JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.CLOSED_OPTION) {
					} else if (result == JOptionPane.YES_OPTION) {
						sellInfo.setCustomerCode(returnCode);
					}
				}

			}

		});

		JCheckBox paymentCheckboxCard = new JCheckBox("\uCE74\uB4DC");
		paymentCheckboxCard.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sellInfo.setPaymentMethod(1);
			}
		});
		buttonGroup.add(paymentCheckboxCard);
		paymentCheckboxCard.setBounds(38, 230, 62, 23);
		successSellPanel.add(paymentCheckboxCard);
		
		JCheckBox paymentCheckboxCash = new JCheckBox("\uD604\uAE08");
		paymentCheckboxCash.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sellInfo.setPaymentMethod(1);
			}
		});
		
		buttonGroup.add(paymentCheckboxCash);
		paymentCheckboxCash.setBounds(104, 230, 62, 23);
		successSellPanel.add(paymentCheckboxCash);
		
		noCustomerInfoCheck.setFont(new Font("�������", Font.PLAIN, 12));
		noCustomerInfoCheck.setBounds(566, 108, 91, 23);
		successSellPanel.add(noCustomerInfoCheck);

		JLabel successSellTitleLabel = new JLabel("\uC0C1\uD488\uD310\uB9E4");
		successSellTitleLabel.setFont(new Font("�������", Font.BOLD, 18));
		successSellTitleLabel.setBounds(27, 27, 96, 33);
		successSellPanel.add(successSellTitleLabel);

		interestedCusPrograss = new JProgressBar();
		interestedCusPrograss.setToolTipText("");
		interestedCusPrograss.setValue(0);
		interestedCusPrograss.setBounds(43, 183, 593, 27);
		successSellPanel.add(interestedCusPrograss);

		JLabel interestedCusLabel = new JLabel("\uACE0\uAC1D \uC704\uD5D8 \uC218\uC900");
		interestedCusLabel.setFont(new Font("�������", Font.BOLD, 14));
		interestedCusLabel.setBounds(43, 158, 96, 15);
		successSellPanel.add(interestedCusLabel);

		JLabel TotalResultLabel = new JLabel("\uCD1D \uAC00\uACA9");
		TotalResultLabel.setFont(new Font("�������", Font.BOLD, 16));
		TotalResultLabel.setBounds(491, 241, 50, 27);
		successSellPanel.add(TotalResultLabel);

		// ���� ���� �����ִ� ��
		TotalResultLabelView = new JLabel("");
		TotalResultLabelView.setFont(new Font("�������", Font.PLAIN, 15));
		TotalResultLabelView.setBounds(549, 243, 87, 23);
		successSellPanel.add(TotalResultLabelView);

		// ���������� �ǸſϷḦ �ϴ� ��ư
		successSellBtn = new JButton("\uD310\uB9E4\uC644\uB8CC");
		successSellBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�ǸſϷ��߽��ϴ�!", "����", JOptionPane.INFORMATION_MESSAGE);
				sellInfoManagementControl control = new sellInfoManagementControl();
				control.sellProduct(productInfo, sellInfo);
				control.setAttentionCustomer(totalLevel, sellInfo.getCustomerCode());
				
				for(int i = 0; i < productInfo.size(); i++) {
					control.decreaseStockQty(productInfo.get(i).getQty(), productInfo.get(i).getProductCode());
				}
				
				sellSuccessDisplay.setVisible(false);
			}
		});

		successSellBtn.setBounds(272, 308, 139, 41);
		successSellPanel.add(successSellBtn);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 283, 661, 15);
		successSellPanel.add(separator_1);
	}

	
	/*------------------------ ������ ����� ���� â ------------------------*/
	
	public void receiptOutputDisplay() {
	
		JFrame receiptOutputDisplay = new JFrame();
		receiptOutputDisplay.setVisible(true);

		receiptOutputDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		receiptOutputDisplay.setBounds(100, 100, 448, 568);
		receiptOutputPanel = new JPanel();
		receiptOutputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		receiptOutputDisplay.setContentPane(receiptOutputPanel);
		receiptOutputPanel.setLayout(null);

		JLabel receiptTitleLabel = new JLabel("\uC601\uC218\uC99D");
		receiptTitleLabel.setFont(new Font("����", Font.BOLD, 24));
		receiptTitleLabel.setBounds(166, 27, 79, 51);
		receiptOutputPanel.add(receiptTitleLabel);

		JLabel basic_label1 = new JLabel("\uC0C1\uD638 : \uC18C\uACF5\uC57D\uAD6D");
		basic_label1.setFont(new Font("����", Font.PLAIN, 12));
		basic_label1.setBounds(30, 86, 118, 18);
		receiptOutputPanel.add(basic_label1);

		JLabel basic_label2 = new JLabel("\uC0AC\uC5C5\uC790\uBC88\uD638 : SW-TWO-PEOPLE-09");
		basic_label2.setFont(new Font("����", Font.PLAIN, 12));
		basic_label2.setBounds(30, 107, 268, 18);
		receiptOutputPanel.add(basic_label2);

		JLabel basic_label3 = new JLabel("\uC804\uD654\uBC88\uD638 : 054-478-7114");
		basic_label3.setFont(new Font("����", Font.PLAIN, 12));
		basic_label3.setBounds(30, 127, 268, 18);
		receiptOutputPanel.add(basic_label3);

		JLabel basic_label4 = new JLabel(
				"\uC8FC\uC18C : (39253) \uACBD\uBD81 \uAD6C\uBBF8\uC2DC \uAD6C\uBBF8\uB300\uB85C 350-27 (\uC2E0\uD3C9\uB3D9\uCEA0\uD37C\uC2A4)");
		basic_label4.setFont(new Font("����", Font.PLAIN, 12));
		basic_label4.setBounds(30, 147, 321, 18);
		receiptOutputPanel.add(basic_label4);

		receiptDateTextField = new JLabel("date");
		receiptDateTextField.setFont(new Font("����", Font.PLAIN, 12));
		receiptDateTextField.setBounds(30, 196, 321, 18);
		receiptOutputPanel.add(receiptDateTextField);

		JLabel label = new JLabel("================================================================");
		label.setBounds(24, 224, 410, 15);
		receiptOutputPanel.add(label);

		JLabel label_1 = new JLabel("================================================================");
		label_1.setBounds(24, 256, 410, 15);
		receiptOutputPanel.add(label_1);

		JLabel label_2 = new JLabel("\uC0C1\uD488\uBA85");
		label_2.setFont(new Font("����", Font.PLAIN, 12));
		label_2.setBounds(81, 238, 48, 18);
		receiptOutputPanel.add(label_2);

		JLabel label_3 = new JLabel("\uC218\uB7C9");
		label_3.setFont(new Font("����", Font.PLAIN, 12));
		label_3.setBounds(218, 239, 48, 18);
		receiptOutputPanel.add(label_3);

		JLabel label_4 = new JLabel("\uAC00\uACA9");
		label_4.setFont(new Font("����", Font.PLAIN, 12));
		label_4.setBounds(328, 238, 48, 18);
		receiptOutputPanel.add(label_4);

		JLabel label_5 = new JLabel("================================================================");
		label_5.setBounds(24, 455, 410, 15);
		receiptOutputPanel.add(label_5);

		JLabel receiptSellPriceLabelTitle = new JLabel("\uD310\uB9E4\uAE08\uC561");
		receiptSellPriceLabelTitle.setFont(new Font("����", Font.BOLD, 15));
		receiptSellPriceLabelTitle.setBounds(251, 480, 79, 31);
		receiptOutputPanel.add(receiptSellPriceLabelTitle);

		//���� ���� ��Ÿ���� �κ�
		receiptSellPriceLabel = new JLabel();
		receiptSellPriceLabel.setBounds(328, 488, 79, 15);
		receiptOutputPanel.add(receiptSellPriceLabel);

		// ��ǰ �̸� ��Ÿ���� ��
		recieptProductNameLabel = new JTextArea();
		recieptProductNameLabel.setEditable(false);
		recieptProductNameLabel.setLineWrap(true);
		recieptProductNameLabel.setOpaque(false);
		recieptProductNameLabel.setBounds(24, 281, 163, 164);
		receiptOutputPanel.add(recieptProductNameLabel);

		//���� ��Ÿ���� ��
		recieptProductQtyLabel = new JTextArea();
		recieptProductQtyLabel.setEditable(false);
		recieptProductQtyLabel.setLineWrap(true);
		recieptProductQtyLabel.setOpaque(false);
		recieptProductQtyLabel.setBounds(207, 282, 70, 164);
		receiptOutputPanel.add(recieptProductQtyLabel);

		//���� ��Ÿ���� ��
		recieptProductPriceLabel = new JTextArea();
		recieptProductPriceLabel.setEditable(false);
		recieptProductPriceLabel.setLineWrap(true);
		recieptProductPriceLabel.setOpaque(false);
		recieptProductPriceLabel.setBounds(305, 281, 95, 164);
		receiptOutputPanel.add(recieptProductPriceLabel);	
		
		//������ ����� ���� �κ�
		selectedColum();
		ArrayList<StringBuilder> proudctInfoList = new ArrayList<StringBuilder>();
		sellInfoManagementControl control = new sellInfoManagementControl();
		proudctInfoList = control.requestOutputReceipt(receiptSellCode);
		
		receiptDateTextField.setText(proudctInfoList.get(0).toString());
		recieptProductNameLabel.setText(proudctInfoList.get(2).toString());
		recieptProductQtyLabel.setText(proudctInfoList.get(3).toString());
		recieptProductPriceLabel.setText(proudctInfoList.get(4).toString());	
		receiptSellPriceLabel.setText(proudctInfoList.get(1).toString());

	}

	// �÷� ���� �� �Ǹ��ڵ� ����
	public void selectedColum() {
		
		

		// ���õ� ���ڵ�
		int selectedRow = lookupSellRecordTable.getSelectedRow();
		receiptSellCode = (String) lookupSellRecordTable.getValueAt(selectedRow, 1);		
		
	}


}
