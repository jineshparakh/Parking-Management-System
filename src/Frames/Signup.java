package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	public JButton btnSignup,btnIHaveAn;
	public JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
//		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
//	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
//	    setLocation(x, y);
//	    setLocationRelativeTo(null);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 427);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 328, 405);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Signup.class.getResource("/assets/42b5e2ae-7bc5-46fb-a18e-58606d13378c_200x200.png")));
		lblNewLabel_1.setBounds(66, 83, 200, 182);
		panel.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Company Representative", "Visitor"}));
		comboBox.setBounds(363, 105, 279, 27);
		contentPane.add(comboBox);
		
		JLabel lblYouSeemNew = new JLabel("You Seem New Here!!");
		lblYouSeemNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouSeemNew.setFont(new Font("Avenir Next", Font.PLAIN, 20));
		lblYouSeemNew.setBounds(390, 23, 223, 22);
		contentPane.add(lblYouSeemNew);
		
		JLabel lblNewLabel = new JLabel("Choose User Type:");
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel.setBounds(363, 77, 154, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblName.setBounds(363, 144, 61, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(363, 167, 279, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPassword.setBounds(363, 205, 86, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(363, 224, 279, 26);
		contentPane.add(passwordField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblConfirmPassword.setBounds(363, 262, 154, 16);
		contentPane.add(lblConfirmPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(363, 284, 279, 26);
		contentPane.add(passwordField_1);
		
		btnSignup = new JButton("SignUp");
		btnSignup.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSignup.setBounds(430, 350, 117, 29);
		contentPane.add(btnSignup);
		
		btnIHaveAn = new JButton("I have an account!");
		btnIHaveAn.setForeground(Color.DARK_GRAY);
		btnIHaveAn.setBackground(Color.WHITE);
		btnIHaveAn.setFont(new Font("Avenir Next", Font.PLAIN, 8));
		btnIHaveAn.setBounds(559, 378, 102, 27);
		contentPane.add(btnIHaveAn);
	}
}
