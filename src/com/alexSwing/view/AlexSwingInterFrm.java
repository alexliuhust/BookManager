package com.alexSwing.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AlexSwingInterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlexSwingInterFrm frame = new AlexSwingInterFrm();
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
	public AlexSwingInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("About AlexSwing");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("Alexander's Swing Exercise ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 27));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);

	}

}
