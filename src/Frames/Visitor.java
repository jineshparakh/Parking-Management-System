package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Visitor extends JFrame {

	public JPanel contentPane;
	public JTabbedPane tabbedPane;
	public JPanel panel, panel_1, panel_2;
	public JComboBox comboBox;
	public JPanel p1,p2,p3;
	public JButton btnContinue,btnBookSlot;
	public JPanel panel_3;
	public JLabel lblChooseTheCompany,lblEnterTheId;
	public String[] companyList;
	public JTextField textField;
	public JTable table1;
	public JScrollPane sp1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visitor frame = new Visitor();
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
	public Visitor() {
		getContentPane().setLayout(new FlowLayout());
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 427);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		
		 tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		 tabbedPane.setBounds(0, 6, 667, 29);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Book Parking Slot", null, panel, null);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("View Booked Slots", null, panel_1, null);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Logout", null, panel_2, null);
		panel_2.setLayout(null);
		p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setBounds(0, 36, 667, 369);
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBounds(0, 36, 667, 369);
		p3 = new JPanel();
		p3.setBackground(Color.WHITE);
		p3.setBounds(0, 36, 667, 369);
//		p2.setLayout(null);
//		p3.setLayout(null);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(358, 95, 257, 27);
		
		
		lblChooseTheCompany = new JLabel("Choose the company you wish to visit:");
		lblChooseTheCompany.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblChooseTheCompany.setBounds(66, 97, 267, 21);
		btnContinue = new JButton("Continue");
		btnContinue.setBounds(274, 235, 101, 29);
		p1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(375, 262, 130, 26);
		textField.setColumns(10);
		
		lblEnterTheId = new JLabel("Enter the ID of the slot you wish to book: ");
		lblEnterTheId.setBounds(99, 262, 264, 16);
		
		
		btnBookSlot = new JButton("Book Slot");
		btnBookSlot.setBounds(268, 309, 117, 29);
		
//		p1.add(comboBox);
//		p1.add(lblChooseTheCompany);
//		p1.add(btnContinue);
	}
}
