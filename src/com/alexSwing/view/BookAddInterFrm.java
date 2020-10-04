package com.alexSwing.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alexSwing.dao.BookDao;
import com.alexSwing.dao.BookTypeDao;
import com.alexSwing.model.Book;
import com.alexSwing.model.BookType;
import com.alexSwing.util.DbUtil;
import com.alexSwing.util.StringUtil;

public class BookAddInterFrm extends JInternalFrame {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField priceTxt;
	private JComboBox bookTypeJcb;
	private JTextArea bookDescTxt;
	private JRadioButton maleJrb;
	private JRadioButton femaleJrb;

	private DbUtil dbutil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
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
	public BookAddInterFrm() {
		setTitle("Add Book");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 570, 604);

		JLabel lblNewLabel = new JLabel("Gender");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		maleJrb = new JRadioButton("Male");
		maleJrb.setFont(new Font("Arial", Font.BOLD, 12));
		buttonGroup.add(maleJrb);
		maleJrb.setSelected(true);

		femaleJrb = new JRadioButton("Female");
		femaleJrb.setFont(new Font("Arial", Font.BOLD, 13));
		buttonGroup.add(femaleJrb);

		JLabel lblNewLabel_1 = new JLabel("Book Title");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		bookNameTxt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		authorTxt = new JTextField();
		authorTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		authorTxt.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		priceTxt = new JTextField();
		priceTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		priceTxt.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Book Description");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

		bookDescTxt = new JTextArea();
		bookDescTxt.setFont(new Font("Arial", Font.PLAIN, 13));

		JButton addButton = new JButton("ADD");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}
		});
		addButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));

		JButton resetButton = new JButton("RESET");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		resetButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));

		JLabel lblNewLabel_5 = new JLabel("Book Type");
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		bookTypeJcb = new JComboBox();
		
		JLabel lblNewLabel_6 = new JLabel("$");
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_4)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(maleJrb)
									.addGap(18)
									.addComponent(femaleJrb)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addComponent(bookDescTxt))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(maleJrb)
						.addComponent(lblNewLabel)
						.addComponent(femaleJrb)
						.addComponent(lblNewLabel_3)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(80)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(resetButton))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		fillBookType();

	}

	/**
	 * Book Add Event Process
	 * 
	 * @param event
	 */
	private void bookAddActionPerformed(ActionEvent event) {
		String bookName = this.bookNameTxt.getText();
		String author =   this.authorTxt.getText();
		String price =    this.priceTxt.getText();
		String bookDesc = this.bookDescTxt.getText();
		if (StringUtil.isEmpty(bookName)) {
			JOptionPane.showMessageDialog(null, "Book Name Cannot Be Empty!");
			return;
		} else if (StringUtil.isEmpty(author)) {
			JOptionPane.showMessageDialog(null, "Author Cannot Be Empty!");
			return;
		} else if (StringUtil.isEmpty(price)) {
			JOptionPane.showMessageDialog(null, "Price Cannot Be Empty!");
			return;
		} else if (StringUtil.isEmpty(bookDesc)) {
			JOptionPane.showMessageDialog(null, "Book Description Cannot Be Empty!");
			return;
		}

		String sex = this.maleJrb.isSelected() ? "Male" : "Female";
		BookType bookType = (BookType) this.bookTypeJcb.getSelectedItem();
		Book book = new Book(bookName, author, sex, Float.parseFloat(price), bookType.getId(), bookDesc);

		Connection con = null;
		try {
			con = dbutil.getCon();
			int num = bookDao.add(con, book);
			if (num == 1) {
				JOptionPane.showMessageDialog(null, "Successfully Added One Book!");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "Failed to Add");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to Add");
		} finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Reset the table
	 */
	private void resetValue() {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.bookDescTxt.setText("");
		this.maleJrb.setSelected(true);
		
		if (this.bookTypeJcb.getItemCount() > 0) {
			this.bookTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * Initialize the Book Type scroll down menu
	 */
	private void fillBookType() {
		Connection con = null;
		BookType bookType = null;
		try {
			con = dbutil.getCon();
			ResultSet rs = bookTypeDao.list(con, new BookType());
			while (rs.next()) {
				bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				// Not directly addItem(bookType.getBookTypeName()),
				// because the IDs of book types are needed later on.
				// We need to add an object, not just a book type name, into the combo.
				this.bookTypeJcb.addItem(bookType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
