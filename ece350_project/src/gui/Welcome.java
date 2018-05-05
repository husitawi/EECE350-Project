package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class Welcome {

	private JFrame frame;
	private JButton btnNewButton_1;
	private JLabel lblWelcome;
	private JLabel lblPleaseSelectWhich;
	private JButton btnExit;
	private JLabel lblOr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Pedestrian");
		btnNewButton.setBounds(19, 164, 117, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pedestrian1 nw = null;
				try {
					nw = new Pedestrian1();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nw.NewScreen();
			} 
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Driver");
		btnNewButton_1.setBounds(167, 164, 117, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Driver1 nw = new Driver1();
				nw.NewScreen();
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 92, 383, 12);
		separator.setForeground(Color.GRAY);
		frame.getContentPane().add(separator);
		
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Courier", Font.PLAIN, 24));
		lblWelcome.setBounds(167, 34, 141, 46);
		frame.getContentPane().add(lblWelcome);
		
		lblPleaseSelectWhich = new JLabel("Please select which applies:");
		lblPleaseSelectWhich.setFont(new Font("Courier", Font.PLAIN, 13));
		lblPleaseSelectWhich.setBounds(31, 136, 277, 16);
		frame.getContentPane().add(lblPleaseSelectWhich);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Courier", Font.PLAIN, 13));
		btnExit.setBounds(19, 234, 117, 29);
		frame.getContentPane().add(btnExit);
		
		lblOr = new JLabel("Or:");
		lblOr.setFont(new Font("Courier", Font.PLAIN, 13));
		lblOr.setBounds(29, 206, 61, 16);
		frame.getContentPane().add(lblOr);
	}
}
