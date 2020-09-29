package com.alexSwing.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alexSwing.dao.BookTypeDao;
import com.alexSwing.model.BookType;
import com.alexSwing.util.DbUtil;
import com.alexSwing.util.StringUtil;

public class BookTypeAddInterFrm extends JInternalFrame {

	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrm frame = new BookTypeAddInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeAddInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setTitle("Add Book Type");
		setBounds(100, 100, 450, 300);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);

		bookTypeDescTxt = new JTextArea();
		bookTypeDescTxt.setFont(new Font("Courier New", Font.PLAIN, 12));

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeAddActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));

		JButton btnNewButton_1 = new JButton("RESET");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(46)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
										.addComponent(lblNewLabel_1))
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(bookTypeDescTxt)
										.addComponent(bookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
								.addContainerGap(66, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(99).addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
								.addComponent(btnNewButton_1).addGap(107)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(31)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(bookTypeNameTxt,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(32)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
				.addGap(26)));
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * Add Book Type Event Process
	 * 
	 * @param event
	 */
	protected void bookTypeAddActionPerformed(ActionEvent event) {
		String bookTypeName = this.bookTypeNameTxt.getText();
		String bookTypeDesc = this.bookTypeDescTxt.getText();
		if (StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "Book Type Name Cannot Be Empty!");
		}
		BookType bookType = new BookType(bookTypeName, bookTypeDesc);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int n = bookTypeDao.add(con, bookType);
			if (n == 1) {
				JOptionPane.showMessageDialog(null, "Successfully Added!");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "Failed to add...");
				resetValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to add...");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Reset Event Process
	 * 
	 * @param event
	 */
	private void resetValueActionPerformed(ActionEvent event) {
		this.resetValue();
	}

	/**
	 * Reset Table
	 */
	private void resetValue() {
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}

}
