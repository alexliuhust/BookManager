package com.alexSwing.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.alexSwing.dao.BookTypeDao;
import com.alexSwing.model.BookType;
import com.alexSwing.util.DbUtil;
import com.alexSwing.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManageInterFrm extends JInternalFrame {
	private JTable bookTypeTable;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private JTextField s_bookTypeNameTxt;
	private JTextField idTxt;
	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
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
	public BookTypeManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Book Type Management");
		setBounds(100, 100, 605, 575);

		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Book Type Name: ");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Table Operations", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(59)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(57, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE))))
					.addGap(56))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("ID: ");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Book Type Name: ");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		bookTypeNameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Book Type Description: ");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		bookTypeDescTxt = new JTextArea();
		bookTypeDescTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("EDIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionPerformed(e);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addGap(53)
							.addComponent(btnNewButton_2))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel_3)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addGap(3)
								.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
							.addComponent(bookTypeDescTxt)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(19))
		);
		panel.setLayout(gl_panel);

		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		
		bookTypeTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Book Type Name", "Book Type Description" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(0).setPreferredWidth(53);
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(104);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(209);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new BookType());

	}

	private void bookTypeDeleteActionPerformed(ActionEvent event) {
		String id = idTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "Please select a record that needs to delete!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "Are you sure to delete this record?");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int deleteNum = bookTypeDao.delete(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "Successfully Deleted!");
					this.resetValue();
					this.fillTable(new BookType());
				} else JOptionPane.showMessageDialog(null, "Fail to Delete");
				
			} catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Fail to Delete");
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Book Type Update Event Process
	 * @param event
	 */
	private void bookTypeUpdateActionPerformed(ActionEvent event) {
		String id = idTxt.getText();
		String bookTypeName = bookTypeNameTxt.getText();
		String bookTypeDesc = bookTypeDescTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "Please select a record that needs to update!");
			return;
		}
		if (StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "Book Type Name cannot be empty!");
			return;
		}
		
		BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int updateNum = bookTypeDao.update(con, bookType);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "Successfully Updated!");
				this.resetValue();
				this.fillTable(bookType);
			} else JOptionPane.showMessageDialog(null, "Fail to Update");
			
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail to Update");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param event
	 */
	private void bookTypeTableMousePressed(MouseEvent event) {
		int row = bookTypeTable.getSelectedRow();
		idTxt.setText((String) bookTypeTable.getValueAt(row, 0));
		bookTypeNameTxt.setText((String) bookTypeTable.getValueAt(row, 1));
		bookTypeDescTxt.setText((String) bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * Book Type Search Event Process
	 * @param e
	 */
	private void bookTypeSearchActionPerformed(ActionEvent event) {
		String s_BookTypeName = this.s_bookTypeNameTxt.getText();
		BookType bookType = new BookType();
		bookType.setBookTypeName(s_BookTypeName);
		this.fillTable(bookType);
	}

	/**
	 * Initialize Table
	 * @param bookType
	 */
	private void fillTable(BookType bookType) {
		DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(con, bookType);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
				dtm.addRow(v);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Reset Value of Table
	 */
	private void resetValue() {
		this.idTxt.setText("");
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
	
}














