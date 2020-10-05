package com.alexSwing.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.alexSwing.dao.BookDao;
import com.alexSwing.dao.BookTypeDao;
import com.alexSwing.model.Book;
import com.alexSwing.model.BookType;
import com.alexSwing.util.DbUtil;

public class BookManageInterFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JComboBox s_bookTypeNameJcb;
	
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField priceTxt;
	private JRadioButton maleJrb;
	private JRadioButton femaleJrb;
	private JComboBox bookTypeNameJcb;
	private JTextArea bookDescTxt;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

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
		setBounds(100, 100, 697, 714);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(24))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Title");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Author");
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gender");
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		maleJrb = new JRadioButton("M");
		buttonGroup.add(maleJrb);
		maleJrb.setSelected(true);
		maleJrb.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		femaleJrb = new JRadioButton("F");
		buttonGroup.add(femaleJrb);
		femaleJrb.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JLabel lblNewLabel_7 = new JLabel("Price");
		lblNewLabel_7.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("$");
		lblNewLabel_8.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JLabel lblNewLabel_9 = new JLabel("Type");
		lblNewLabel_9.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		bookTypeNameJcb = new JComboBox();
		
		JLabel lblNewLabel_10 = new JLabel("Description");
		lblNewLabel_10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		bookDescTxt = new JTextArea();
		
		JButton btnNewButton_1 = new JButton("EDIT");
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addGap(28)
							.addComponent(btnNewButton_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_10)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(maleJrb)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(femaleJrb)
									.addGap(18)
									.addComponent(lblNewLabel_9))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(18)
									.addComponent(lblNewLabel_7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_8))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(4)
									.addComponent(bookTypeNameJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(28))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_9)
						.addComponent(bookTypeNameJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(femaleJrb)
						.addComponent(maleJrb)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_10)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(23))
		);
		panel_1.setLayout(gl_panel_1);

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
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				bookTableMousePressed(me);
			}
		});
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
		this.fillBookType("edit");
		this.fillTable(new Book());
		

	}

	/**
	 * Book table mouse pressed event process
	 * @param me
	 */
	private void bookTableMousePressed(MouseEvent me) {
		int row = this.bookTable.getSelectedRow();
		this.idTxt.setText((String)this.bookTable.getValueAt(row, 0));
		this.bookNameTxt.setText((String)this.bookTable.getValueAt(row, 1));
		this.authorTxt.setText((String)this.bookTable.getValueAt(row, 2));
		
		String sex = (String) this.bookTable.getValueAt(row, 3);
		if (sex.equals("Male")) this.maleJrb.setSelected(true); 
		else  this.femaleJrb.setSelected(true);
		
		this.priceTxt.setText((Float)this.bookTable.getValueAt(row, 4) + "");
		this.bookDescTxt.setText((String)this.bookTable.getValueAt(row, 5));
		String bookTypeName = (String) this.bookTable.getValueAt(row, 6);
		int itemCount = this.bookTypeNameJcb.getItemCount();
		for (int i = 0; i < itemCount; i++) {
			BookType bookType = (BookType) this.bookTypeNameJcb.getItemAt(i);
			if (bookTypeName.equals(bookType.getBookTypeName()))
				this.bookTypeNameJcb.setSelectedIndex(i);
		}
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
					this.bookTypeNameJcb.addItem(bookType);
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
