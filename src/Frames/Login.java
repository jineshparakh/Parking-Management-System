package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	public JPanel contentPane;
	public JTextField textField;
	public JPasswordField passwordField;
	public JButton btnIAmNew,btnLogin;
	public JComboBox comboBox;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 427);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 328, 405);
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/assets/42b5e2ae-7bc5-46fb-a18e-58606d13378c_200x200.png")));
		lblNewLabel_1.setBounds(66, 83, 200, 182);
		panel.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Company Representative", "Visitor"}));
		comboBox.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		comboBox.setBounds(363, 105, 279, 27);
		contentPane.add(comboBox);
		
		JLabel lblWelcomeBack = new JLabel("Welcome Back!!");
		lblWelcomeBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBack.setFont(new Font("Avenir Next", Font.PLAIN, 20));
		lblWelcomeBack.setBounds(390, 23, 223, 22);
		contentPane.add(lblWelcomeBack);
		
		JLabel lblNewLabel = new JLabel("Choose User Type:");
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel.setBounds(363, 77, 154, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblName.setBounds(363, 144, 61, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(363, 167, 279, 26);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(363, 224, 279, 26);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPassword.setBounds(363, 205, 86, 16);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Login");
		
		btnLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnLogin.setBounds(438, 279, 117, 29);
		contentPane.add(btnLogin);
		
		btnIAmNew = new JButton("I am New!");
		btnIAmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIAmNew.setForeground(Color.DARK_GRAY);
		btnIAmNew.setFont(new Font("Avenir Next", Font.PLAIN, 8));
		btnIAmNew.setBackground(Color.WHITE);
		btnIAmNew.setBounds(559, 372, 102, 27);
		contentPane.add(btnIAmNew);
	}
}
