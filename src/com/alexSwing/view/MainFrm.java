package com.alexSwing.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("Book Manager Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Main Menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Category Management");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeAddInterFrm bookTypeAddInterFrm = new BookTypeAddInterFrm();
				bookTypeAddInterFrm.setVisible(true);
				table.add(bookTypeAddInterFrm);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Maintain");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManageInterFrm bookTypeManageInterFrm = new BookTypeManageInterFrm();
				bookTypeManageInterFrm.setVisible(true);
				table.add(bookTypeManageInterFrm);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Book Management");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddInterFrm bookAddInterFrm = new BookAddInterFrm();
				bookAddInterFrm.setVisible(true);
				table.add(bookAddInterFrm);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Maintain");
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Log Out");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Do you want to log out?");
				if (result == 0) dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("About Us");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("AlexSwingExercise");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlexSwingInterFrm alexSwingInterFrm = new AlexSwingInterFrm();
				alexSwingInterFrm.setVisible(true);
				table.add(alexSwingInterFrm);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JDesktopPane();
		contentPane.add(table, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
