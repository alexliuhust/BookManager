package com.alexSwing.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.alexSwing.dao.BookDao;
import com.alexSwing.dao.BookTypeDao;
import com.alexSwing.model.Book;
import com.alexSwing.model.BookType;
import com.alexSwing.util.DbUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookManageInterFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JComboBox s_bookTypeNameJcb;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrm frame = new BookManageInterFrm();
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
	public BookManageInterFrm() {
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setTitle("Book Management");
		setBounds(100, 100, 697, 569);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(31)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
				.addGap(24)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(152, Short.MAX_VALUE)));

		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Author");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));

		s_bookTypeNameJcb = new JComboBox();

		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel).addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addComponent(s_bookNameTxt)
								.addComponent(s_authorTxt).addComponent(s_bookTypeNameJcb, 0, 379, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE).addComponent(btnNewButton)
						.addGap(24)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton)
								.addComponent(lblNewLabel_2).addComponent(s_bookTypeNameJcb, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(41, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		bookTable = new JTable();
		scrollPane.setViewportView(bookTable);
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Title", "Author", "Gender", "Price", "Desc", "Type"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getContentPane().setLayout(groupLayout);
		
		this.fillBookType("search");
		this.fillTable(new Book());
		

	}

	/**
	 * Book Search Event Process
	 * @param event
	 */
	private void bookSearchActionPerformed(ActionEvent event) {
		String bookName = this.s_bookNameTxt.getText();
		String author = this.s_authorTxt.getText();
		BookType bookType = (BookType) this.s_bookTypeNameJcb.getSelectedItem();
		
		Book book = new Book(bookName, author, bookType.getId());
		this.fillTable(book);
		
	}

	/**
	 * Fill up the book type scroll down menu; 
	 * if in the search pane, show "-Select-"; 
	 * if in the edit pane, show the first book type.
	 * 
	 * @param Type
	 */
	private void fillBookType(String type) {
		Connection con = null;
		BookType bookType = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookTypeDao.list(con, new BookType());
			if (type.equals("search")) {
				bookType = new BookType();
				bookType.setBookTypeName("-Select-");
				bookType.setId(-1);
				this.s_bookTypeNameJcb.addItem(bookType);
			}
			while (rs.next()) {
				bookType = new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if (type.equals("search")) {
					this.s_bookTypeNameJcb.addItem(bookType);
				} else {
					// this.bookTypeNameJcb.addItem(bookType);
				}
			}
		} catch (Exception e) {
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
	 * Initialize Table
	 * @param bookType
	 */
	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookDao.list(con, book);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
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

}
