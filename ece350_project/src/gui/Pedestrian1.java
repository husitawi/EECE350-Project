package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Pedestrian1 {

	private JFrame frame;
	private JTextField CurrentLocation;
	private JTextField Destination;
	boolean ns=false, np=false, nk =false, qr=false;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedestrian1 window = new Pedestrian1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Pedestrian1() throws ClassNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Courier", Font.PLAIN, 13));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CurrentLocation = new JTextField();
		CurrentLocation.setBounds(229, 4, 130, 26);
		frame.getContentPane().add(CurrentLocation);
		CurrentLocation.setColumns(10);
		
		Destination = new JTextField();
		Destination.setBounds(229, 46, 130, 26);
		frame.getContentPane().add(Destination);
		Destination.setColumns(10);
		
		
		String location =CurrentLocation.getText();
		String destination = Destination.getText();
	
		
		if(location.length() != 0 && destination.length() != 0  ) {
		String[] location_array = location.split(",");
		String xLocation = location_array[0];
		String yLocation = location_array[1];
		
		String[] destination_array = destination.split(",");
		String xDestination= destination_array[0];
		String yDestination = destination_array[1];
		
		}
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("No smoking");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					ns = true;
				else 
					ns = false;	
			}
		});
		chckbxNewCheckBox.setBounds(123, 98, 196, 23);
		chckbxNewCheckBox.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("No pets");
		chckbxNewCheckBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					np = true;
				else 
					np = false;	
				
			}
		});
		chckbxNewCheckBox_1.setBounds(123, 122, 141, 23);
		chckbxNewCheckBox_1.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("No kids");
		chckbxNewCheckBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					nk = true;
				else 
					nk = false;	
			}
		});
		chckbxNewCheckBox_2.setBounds(123, 145, 128, 23);
		chckbxNewCheckBox_2.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Quiet ride");
		chckbxNewCheckBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					qr = true;
				else 
					qr = false;	
			}
		});
		chckbxNewCheckBox_3.setBounds(123, 169, 155, 23);
		chckbxNewCheckBox_3.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNewCheckBox_3);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(309, 228, 117, 29);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pedestrian2 nw = new Pedestrian2();
				nw.NewScreen();
			}
		});
		btnNext.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(16, 228, 117, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			} 
		});
		btnBack.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Current Location (X,Y):");
		lblNewLabel.setBounds(17, 11, 184, 16);
		lblNewLabel.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDesiredDestinationxy = new JLabel("Desired Destination (X,Y):");
		lblDesiredDestinationxy.setBounds(17, 53, 208, 16);
		lblDesiredDestinationxy.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblDesiredDestinationxy);
		
		JLabel lblPreferences = new JLabel("Preferences: ");
		lblPreferences.setBounds(16, 102, 104, 16);
		lblPreferences.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblPreferences);
	}
}
