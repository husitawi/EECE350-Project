package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Driver2 {

	private JFrame frame;
	private JTextField PedestriansOut;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver2 window = new Driver2();
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
	public Driver2() {
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
		
		JLabel lblNewLabel = new JLabel("List of available pedestrians:");
		lblNewLabel.setFont(new Font("Courier", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 26, 328, 16);
		frame.getContentPane().add(lblNewLabel);
		
		PedestriansOut = new JTextField();
		PedestriansOut.setBounds(40, 54, 318, 162);
		frame.getContentPane().add(PedestriansOut);
		PedestriansOut.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
	
			}
		});
		btnBack.setFont(new Font("Courier", Font.PLAIN, 13));
		btnBack.setBounds(21, 228, 117, 29);
		frame.getContentPane().add(btnBack);
	}
}
