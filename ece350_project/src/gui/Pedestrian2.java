package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Pedestrian2 {

	private JFrame frame;
	private JTextField DataOut;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedestrian2 window = new Pedestrian2();
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
	public Pedestrian2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblListOfCurrently = new JLabel("List of currently available cars:");
		lblListOfCurrently.setBounds(19, 20, 276, 16);
		lblListOfCurrently.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblListOfCurrently);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(18, 225, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		DataOut = new JTextField();
		DataOut.setBounds(29, 51, 308, 74);
		frame.getContentPane().add(DataOut);
		DataOut.setColumns(10);
	}
}
