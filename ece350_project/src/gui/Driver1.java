package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Driver1 {

	private JFrame frame;
	private JTextField StartLocation;
	private JTextField Destination;
	private JTextField Seats;
	private JTextField Cost;
	boolean ns = false, np = false, nk = false, qr = false;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver1 window = new Driver1();
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
	public Driver1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Courier", Font.PLAIN, 13));
		frame.setBounds(100, 100, 500, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(82, 35, 1, 16);
		frame.getContentPane().add(textPane);
		
		JLabel lblStartingLocationxy = new JLabel("Starting location (X,Y):");
		lblStartingLocationxy.setBounds(22, 19, 197, 16);
		lblStartingLocationxy.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblStartingLocationxy);
		
		StartLocation = new JTextField();
		StartLocation.setBounds(251, 12, 130, 26);
		frame.getContentPane().add(StartLocation);
		StartLocation.setColumns(10);
		
		JLabel lblDesiredDestinationxy = new JLabel("Desired Destination (X,Y):");
		lblDesiredDestinationxy.setBounds(22, 57, 216, 16);
		lblDesiredDestinationxy.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblDesiredDestinationxy);
		
		Destination = new JTextField();
		Destination.setBounds(251, 50, 130, 26);
		frame.getContentPane().add(Destination);
		Destination.setColumns(10);
		
		String location = StartLocation.getText();
		String destination = Destination.getText();
		
	
		
		if(location.length() != 0 && destination.length() != 0  )
		{
		String[] location_array = location.split(",");
		String xLocation = location_array[0];
		String yLocation = location_array[1];
		
		String[] destination_array = destination.split(",");
		String xDestination= destination_array[0];
		String yDestination = destination_array[1];
		}
		
		JLabel lblRegulations = new JLabel("Regulations:");
		lblRegulations.setBounds(22, 177, 104, 16);
		lblRegulations.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblRegulations);
		
		JCheckBox chckbxNoSmoking = new JCheckBox("No smoking");
		chckbxNoSmoking.setBounds(138, 177, 128, 23);
		chckbxNoSmoking.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					ns = true;
				else 
					ns = false;	
			}
		});
		chckbxNoSmoking.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNoSmoking);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("No pets");
		chckbxNewCheckBox.setBounds(138, 199, 128, 23);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					np = true;
				else 
					np = false;	
			}
		});
		chckbxNewCheckBox.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNoKids = new JCheckBox("No kids");
		chckbxNoKids.setBounds(138, 222, 128, 23);
		chckbxNoKids.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					nk = true;
				else 
					nk = false;	
			}
		});
		chckbxNoKids.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxNoKids);
		
		JCheckBox chckbxQuietRide = new JCheckBox("Quiet ride");
		chckbxQuietRide.setBounds(138, 246, 128, 23);
		chckbxQuietRide.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED)
					qr = true;
				else 
					qr = false;	
			}
		});
		chckbxQuietRide.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(chckbxQuietRide);
		
		JLabel lblNumberOfSeats = new JLabel("Number of seats available:");
		lblNumberOfSeats.setBounds(22, 95, 216, 16);
		lblNumberOfSeats.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblNumberOfSeats);
		
		Seats = new JTextField();
		Seats.setBounds(251, 88, 130, 26);
		frame.getContentPane().add(Seats);
		Seats.setColumns(10);

		String seats = Seats.getText();

		JLabel lblCostPerKilometer = new JLabel("Cost per kilometer:");
		lblCostPerKilometer.setBounds(22, 134, 197, 16);
		lblCostPerKilometer.setFont(new Font("Courier", Font.PLAIN, 13));
		frame.getContentPane().add(lblCostPerKilometer);
		
		Cost = new JTextField();
		Cost.setBounds(251, 126, 130, 26);
		frame.getContentPane().add(Cost);
		Cost.setColumns(10);
		
		String cost = Cost.getText();
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBounds(366, 285, 117, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Driver2 nw = new Driver2();
				nw.NewScreen();
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(22, 285, 117, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton_1);
	}
}
