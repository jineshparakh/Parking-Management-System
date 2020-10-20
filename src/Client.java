import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DateFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Frames.Login;
import Frames.Signup;
import Frames.Visitor;
import Frames.Representative.DateLabelFormatter;
import Frames.Representative;
public class Client {

	 /*
	 * 
	 *  CREATE TABLE parking(
    -> cname VARCHAR(50) NOT NULL,
    -> slot_start TIME,
    -> slot_end TIME,
    -> dateGiven DATE,
    -> vname VARCHAR(50) DEFAULT 'NO',
    -> FOREIGN KEY(cname) REFERENCES register_representative(cname) ON DELETE CASCADE,
    -> FOREIGN KEY(vname) REFERENCES register_visitor(vname) ON DELETE CASCADE,
    -> PRIMARY KEY(cname, slot_start, slot_end,dateGiven,vname)
    -> );
    
    CREATE TABLE register_visitor(
    -> vname VARCHAR(50) NOT NULL,
    -> password VARCHAR(50) NOT NULL,
    -> PRIMARY KEY(vname, password)
    -> );
    
     CREATE TABLE register_representative(
    -> cname VARCHAR(50) NOT NULL,
    -> password VARCHAR(50) NOT NULL,
    -> PRIMARY KEY(cname, password)
    -> );
	 */
	static String givenName="";
	static int cc=0;
	static String name="";
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 8888);
//		Scanner scanner = new Scanner(System. in);
		BufferedReader scanner  = new BufferedReader( new InputStreamReader(System.in));
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
		int choice = 0;
		
		Signup signup=new Signup();
		Login login=new Login();
		Visitor visitor=new Visitor();
		Representative representative=new Representative();
		signup.setVisible(true);
		signup.btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type=signup.comboBox.getSelectedItem().toString();
				
				String name=signup.textField.getText();
				String password=signup.passwordField.getText();
				String confirmPassword=signup.passwordField_1.getText();
				if(name.contentEquals("")||password.contentEquals("")|| confirmPassword.equals("")) {
					JOptionPane.showMessageDialog(signup, "Some fields are blank!\n Try Again","Input Error", JOptionPane.ERROR_MESSAGE);
				}
				if(!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(signup, "Passwords do not match!\n Try Again","Password Error", JOptionPane.ERROR_MESSAGE);
					signup.passwordField.setText("");
					signup.passwordField_1.setText("");
				}
				if(password.contentEquals(confirmPassword) && !name.contentEquals("") && !password.contentEquals("") && !confirmPassword.equals("")) {
					System.out.println(type);
					System.out.println(name);
					System.out.println(password);
					System.out.println(confirmPassword);
					output.println("Signup");
					output.println(type);
					output.println(name);
					output.println(password);
					int valid=0;
					try {
						valid = Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(valid==1) {
						JOptionPane.showMessageDialog(signup, "User is now Registered!\n You can login","Signup Successful", JOptionPane.INFORMATION_MESSAGE);
						signup.passwordField.setText("");
						signup.textField.setText("");
						signup.passwordField_1.setText("");

					}
					else {
						JOptionPane.showMessageDialog(signup, "User is already registered!\nPlease try to login!","Signup Error", JOptionPane.ERROR_MESSAGE);
						signup.passwordField.setText("");
						signup.textField.setText("");
						signup.passwordField_1.setText("");
						
					}
				}
				
			}
		});
		login.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type=login.comboBox.getSelectedItem().toString();
				String name=login.textField.getText();
				givenName=name;
				String password=login.passwordField.getText();
				if(name.contentEquals("") || password.contentEquals("")) {
					JOptionPane.showMessageDialog(login, "Some fields are blank!\n Try Again","Input Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					output.println("Login");
					output.println(type);
					output.println(name);
					output.println(password);
					int valid=-1;
					try {
						valid=Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(valid==0) {
						JOptionPane.showMessageDialog(signup, "This user is not registered!\n Plese register first!","Registration Error", JOptionPane.ERROR_MESSAGE);
						
					}
					else if(valid==1) {
						JOptionPane.showMessageDialog(signup, "Password is Incorrect!\n Try Again","Password Error", JOptionPane.ERROR_MESSAGE);
						login.passwordField.setText("");
						
					}
					else {
						if(type.equals("Visitor")) {
							JOptionPane.showMessageDialog(visitor, "Login Successful","Login Successful", JOptionPane.INFORMATION_MESSAGE);
							login.setVisible(false);
							visitor.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(representative, "Login Successful","Login Successsful", JOptionPane.INFORMATION_MESSAGE);
							login.setVisible(false);
							representative.setVisible(true);
						}
						
					}
				}
			}
					
		});
		signup.btnIHaveAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup.setVisible(false);
//				signup.getContentPane().removeAll();
//				signup.dispose();
//				signup.setContentPane(login.contentPane);
				login.setVisible(true);
			}
		});
		login.btnIAmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
//				login.dispose();
//				login.getContentPane().removeAll();
//				login.setContentPane(signup.contentPane);
				signup.setVisible(true);
			}
		});
		representative.tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = representative.tabbedPane.getSelectedIndex();
				System.out.println(selectedIndex);
				representative.p1.removeAll();
				representative.p1.revalidate();
				representative.p1.repaint();
				representative.p1.updateUI();
				representative.p2.removeAll();
				representative.p2.revalidate();
				representative.p2.repaint();
				representative.p2.updateUI();
	        	representative.panel.removeAll();
				representative.panel.revalidate();
				representative.panel.repaint();
				representative.panel.updateUI();
		        if(selectedIndex==0) {
		        	representative.p1.setVisible(false);
		        	representative.p2.setVisible(false);
		        	representative.p3.setVisible(false);
		        	
		        	
		        	UtilDateModel model = new UtilDateModel();
		        	Properties p = new Properties();
		        	p.put("text.today", "Today");
		        	p.put("text.month", "Month");
		        	p.put("text.year", "Year");
		        	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		        	// Don't know about the formatter, but there it is...
		        	representative.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		        	representative.datePicker.setBounds(257, 83, 202, 29);
		        	Calendar calendar = Calendar.getInstance();
		            calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
		            calendar.set(Calendar.MINUTE, 0);
		            calendar.set(Calendar.SECOND, 0);

		            SpinnerDateModel modell = new SpinnerDateModel();
		            modell.setValue(calendar.getTime());

		            representative.spinner = new JSpinner(modell);
		            representative.spinner.setBounds(318, 145, 86, 20);

		            JSpinner.DateEditor editor = new JSpinner.DateEditor(representative.spinner, "HH:mm:ss");
		            editor.setBounds(100,100,30,20);
		            DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
		            formatter.setAllowsInvalid(false); // this makes what you want
		            formatter.setOverwriteMode(true);

		            representative.spinner.setEditor(editor);
		        	
		       	
		            representative.calendar1 = Calendar.getInstance();
		            representative.calendar1.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
		            representative.calendar1.set(Calendar.MINUTE, 0);
		            representative.calendar1.set(Calendar.SECOND, 0);

		            SpinnerDateModel modelll = new SpinnerDateModel();
		            modelll.setValue(representative.calendar1.getTime());
		            representative.spinner2 = new JSpinner(modelll);
		            representative.spinner2.setBounds(318, 194, 86, 20);
		            JSpinner.DateEditor editor2 = new JSpinner.DateEditor(representative.spinner2, "HH:mm:ss");
		            editor2.setBounds(200,100,30,20);
		            DateFormatter formatter2 = (DateFormatter)editor2.getTextField().getFormatter();
		           formatter2.setAllowsInvalid(false); // this makes what you want
		            formatter2.setOverwriteMode(true);
		            representative.spinner2.setEditor(editor2);
		                    
		            representative.panel.setLayout(null);
		            representative.panel.add(representative.spinner);
		            representative.panel.add(representative.spinner2);
		            representative.panel.add(representative.datePicker);
		            
		            representative.lblChooseDate = new JLabel("Choose Date");
		            representative.lblChooseDate.setBounds(147, 94, 98, 16);
		            representative.panel.add(representative.lblChooseDate);
		            
		            representative.lblStartTime = new JLabel("Start TIme");
		            representative.lblStartTime.setBounds(188, 147, 98, 16);
		            representative.panel.add(representative.lblStartTime);
		            
		            representative.lblEndTime = new JLabel("End Time");
		            representative.lblEndTime.setBounds(188, 206, 78, 16);
		            representative.panel.add(representative.lblEndTime);
		            
		            representative.lblYouCanAdd = new JLabel("You can add free parking slots for your Company here!");
		            representative.lblYouCanAdd.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		            representative.lblYouCanAdd.setHorizontalAlignment(SwingConstants.CENTER);
		            representative.lblYouCanAdd.setBounds(6, 22, 655, 39);
		            representative.panel.add(representative.lblYouCanAdd);
		            
		            representative.btnAddSlot = new JButton("Add Slot");
		            representative.btnAddSlot.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {
		            		if(!representative.datePicker.getJFormattedTextField().getText().equals("")) {
		            			output.println("Add Free Slot");
		            			Date date = (Date)representative.spinner.getValue();
		                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		                        String time1 = format.format(date);
		                        date=(Date) representative.spinner2.getValue();
		                        String time2=format.format(date);
		                        output.println(givenName);
		                        output.println(time1);
		                        output.println(time2);
		                        output.println(representative.datePicker.getJFormattedTextField().getText());
		            			System.out.println(time1);
		            			System.out.println(time2);
		            	    	System.out.println(representative.datePicker.getJFormattedTextField().getText());
		            	    	String verdict = null;
								try {
									verdict = input.readLine();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		            	    	if(verdict.equals("Done")) {
			            			JOptionPane.showMessageDialog(representative, "Slot addition successful!","Slot addition Success", JOptionPane.INFORMATION_MESSAGE);

		            	    	}
		            	    	else {
			            			JOptionPane.showMessageDialog(representative, "There was an error during the addtion of the free slot! Please try again!","Slot addition error", JOptionPane.ERROR_MESSAGE);

		            	    	}
		            	    	
		            			
		            		}
		            		else {
		            			JOptionPane.showMessageDialog(representative, "Looks like the date is not selected! Please choose a date first","Slot addition error", JOptionPane.ERROR_MESSAGE);
		            		}
		            		  
		            	}
		            });
		            representative.btnAddSlot.setBounds(189, 281, 215, 29);
		            representative.panel.add(representative.btnAddSlot);
		            representative.panel.setVisible(true);
		                    
		        	
		        }
		        if(selectedIndex==1) {
		        	representative.panel.setVisible(false);
		        	representative.p2.setVisible(false);
		        	representative.p3.setVisible(false);

		        	output.println("View Free Slots Representative");
		        	output.println(givenName);
		        	int count=0;
		        	try {
						count=Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	String column[]={"ID","Start Time","End Time","Date"};         
		        	String[][] data=new String[count][4];
		        	for(int i=0;i<count;i++) {
		        		String s = null;
						try {
							s = input.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String[] d=new String[3];
						d=s.split("#");
						System.out.println(d);
		        		data[i][0]=Integer.toString(i+1);
		        		data[i][1]=d[0];
		        		data[i][2]=d[1];
		        		data[i][3]=d[2];
		        		System.out.println(s);
		        	}
		        	JTable table1=new JTable(data,column);
		        	JScrollPane sp1=new JScrollPane(table1); 
		        	 sp1.setPreferredSize(new Dimension(500,100));  
		        	 representative.p1.add(sp1);
		        	 representative.contentPane.add(representative.p1);
//		        	representative.table=new JTable(data,column);
//		        	 representative.sp=new JScrollPane(representative.table); 
//		        	 representative.sp.setPreferredSize(new Dimension(500,100));  
//		        	 representative.panel.add(representative.sp);
//		        	 representative.panel.add(table1);
		        	 representative.p1.setVisible(true);
		        	
		        	
		        }
		        if(selectedIndex==2) {
		        	representative.panel.setVisible(false);
		        	representative.p1.setVisible(false);
		        	representative.p3.setVisible(false);
		        	output.println("View Booked Slots");
		        	output.println(givenName);
		        	int count=0;
		        	try {
						count=Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	String column[]={"ID","Start Time","End Time","Visitor", "Date"};         
		        	String[][] data=new String[count][5];
		        	for(int i=0;i<count;i++) {
		        		String s=null;
		        		try {
							 s=input.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        		String a[]=s.split("#");
		        		data[i][0]=Integer.toString(i+1);
		        		data[i][1]=a[0];
		        		data[i][2]=a[1];
		        		data[i][3]=a[3];
		        		data[i][4]=a[2];
		        		
		        		
		        	}
		        	JTable table1=new JTable(data,column);
		        	JScrollPane sp1=new JScrollPane(table1); 
		        	 sp1.setPreferredSize(new Dimension(500,100));  
		        	 representative.p2.add(sp1);
		        	 representative.contentPane.add(representative.p2);
		        	representative.p2.setVisible(true);
		        	
		        }
		        if(selectedIndex==3) {
		        	JOptionPane.showMessageDialog(representative, "Thank you for spending time with us! We look forward to helping you again","Logout Successsful", JOptionPane.INFORMATION_MESSAGE);
		        	representative.setVisible(false);
		        	signup.setVisible(true);
		        }
			}
		});
		visitor.btnBookSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=Integer.parseInt(visitor.textField.getText());
				if(index<1 && index>cc) {
		        	JOptionPane.showMessageDialog(visitor, "Invalid ID selection!\n Please select a valid ID","Invalid Request", JOptionPane.ERROR_MESSAGE);

					
				}
				else {
					output.println("Book Free Slot");
					output.println(givenName);
					output.println(name);
					output.println(index);
					int valid=0;
					try {
						 valid=Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(valid==1) {
			        	JOptionPane.showMessageDialog(visitor, "Your parking slot was booked successfully!","Successful Slot Booking", JOptionPane.INFORMATION_MESSAGE);
			        	visitor.p2.setVisible(false);
			        	visitor.p1.setVisible(true);

						
					}
					else {
			        	JOptionPane.showMessageDialog(visitor, "Your slot could not be booked!\n Please try again later","Error While Booking", JOptionPane.ERROR_MESSAGE);

						
					}
				}
				
				
			}
			});
		visitor.btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cname=visitor.comboBox.getSelectedItem().toString();
				output.println("Get Free Slots in the selected company");
				output.println(cname);
				name=cname;
				output.println(givenName);
				int count=0;
				try {
					count=Integer.parseInt(input.readLine());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cc=count;
				if(count==0) {
		        	JOptionPane.showMessageDialog(visitor, "It seems like this company has no free parking slots!","No Free Slots", JOptionPane.INFORMATION_MESSAGE);
		        	visitor.p1.setVisible(true);
		        	

				}
				else {
					String column[]={"ID","Start Time","End Time", "Date"};         
		        	String[][] data=new String[count][4];
		        	for(int i=0;i<count;i++) {
		        		String s=null;
		        		try {
							s=input.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        		String a[]=s.split("#");
		        		data[i][0]=Integer.toString(i+1);
		        		data[i][1]=a[0];
		        		data[i][2]=a[1];
		        		data[i][3]=a[2];
		        		
		        	}
		        	visitor.table1=new JTable(data,column);
		        	 visitor.sp1=new JScrollPane(visitor.table1); 
		        	 visitor.sp1.setPreferredSize(new Dimension(600,100));  
		        	 visitor.p2.add(visitor.sp1);
		        	 
		        	 visitor.p2.add(visitor.lblEnterTheId);
		        	 visitor.p2.add(visitor.textField);
		        	 visitor.p2.add(visitor.btnBookSlot);
		        	 visitor.p1.setVisible(false);
		        	 visitor.p3.setVisible(false);
		        	 visitor.panel.setVisible(false);
		        	visitor.p2.setVisible(true);
		        	
		        	
				}
				
			}
		});
		
		visitor.tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
//				visitor.p1.setVisible(false);
//				visitor.p2.setVisible(false);
//				visitor.p3.setVisible(false);
				visitor.p1.removeAll();
				visitor.p1.revalidate();
				visitor.p1.repaint();
				visitor.p1.updateUI();
				visitor.p2.removeAll();
				visitor.p2.revalidate();
				visitor.p2.repaint();
				visitor.p2.updateUI();
				visitor.p3.removeAll();
				visitor.p3.revalidate();
				visitor.p3.repaint();
				visitor.p3.updateUI();
				int selectedIndex = visitor.tabbedPane.getSelectedIndex();
				System.out.println(selectedIndex);
				if(selectedIndex==0) {
					output.println("Get Company List");
					int count=0;
					try {
						count=Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					visitor.companyList=new String[count];
					for(int i=0;i<count;i++) {
						String s=null;
						try {
							 s=input.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						visitor.companyList[i]=s;
					}
					
					visitor.comboBox.setModel(new DefaultComboBoxModel(visitor.companyList));
					visitor.p1.add(visitor.comboBox);
					visitor.p1.add(visitor.lblChooseTheCompany);
					visitor.p1.add(visitor.btnContinue);
					visitor.p2.setVisible(false);
					visitor.p3.setVisible(false);
					visitor.p1.setVisible(true);
					
					
				}
				else if(selectedIndex==1) {
					output.println("Check Booked Slots");
					output.println(givenName);
					int count=0;
					try {
						count=Integer.parseInt(input.readLine());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cc=count;
					if(count==0) {
			        	JOptionPane.showMessageDialog(visitor, "It seems like you have not booked any slots!","No Slots Booked", JOptionPane.INFORMATION_MESSAGE);
			        	

					}
					else {
						String column[]={"ID","Start Time","End Time", "Date"};         
			        	String[][] data=new String[count][4];
			        	for(int i=0;i<count;i++) {
			        		String s=null;
			        		try {
								s=input.readLine();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			        		String a[]=s.split("#");
			        		data[i][0]=Integer.toString(i+1);
			        		data[i][1]=a[0];
			        		data[i][2]=a[1];
			        		data[i][3]=a[2];
			        		
			        	}
			        	JTable table1=new JTable(data,column);
			        	JScrollPane sp1=new JScrollPane(table1); 
			        	 sp1.setPreferredSize(new Dimension(500,100));  
			        	 visitor.p3.add(sp1);
			        	 visitor.p3.setVisible(false);
			        	 visitor.p1.setVisible(false);
			        	 visitor.p2.setVisible(false);
			        	visitor.p3.setVisible(true);
			        	
			        	
					}
					
				}
				else {
					JOptionPane.showMessageDialog(visitor, "Thank you for spending time with us! We look forward to helping you again","Logout Successsful", JOptionPane.INFORMATION_MESSAGE);
		        	visitor.setVisible(false);
		        	signup.setVisible(true);
				}
				
			}
		});
		
		
//		do {
//			for (int i = 0; i < 8; i++) {
//				String menu = input.readLine();
//				System.out.println(menu);
//			}
//
//			System.out.println("Enter your choice:");
//			choice = Integer.parseInt(scanner.readLine());
//
//
//			System.out.println("Your choice is: " + choice);
//			output.println(choice);
//			switch (choice) {
//			case 1:
//				String cname, password;
//				String forName = input.readLine();
//				System.out.println(forName);
//				cname = scanner.readLine();
//				output.println(cname);
//				String forPassword = input.readLine();
//				System.out.println(forPassword);
//				password = scanner.readLine();
//				output.println(password);
//				String verdictC = input.readLine();
//				System.out.println(verdictC);
//				break;
//			case 2:
//				String vname, passwordd;
//				String forVname=input.readLine();
//				System.out.println(forVname);
//				vname=scanner.readLine();
//				output.println(vname);
//				String forPasswordd=input.readLine();
//				System.out.println(forPasswordd);
//				passwordd=scanner.readLine();
//				output.println(passwordd);
//				String verdictV=input.readLine();
//				System.out.println(verdictV);
//				break;
//			case 3:
//				String ccname, passworddd;
//				String forCCname=input.readLine();
//				System.out.println(forCCname);
//				ccname=scanner.readLine();
//				output.println(ccname);
//				String forPassworddd=input.readLine();
//				System.out.println(forPassworddd);
//				passworddd=scanner.readLine();
//				output.println(passworddd);
//				String loginVerdictC=input.readLine();
//				System.out.println(loginVerdictC);
//				if(loginVerdictC.equals("[SERVER] You have successfully logged in!!!")) {
//					int choice1=0;
//					do {
//						for (int i = 0; i < 4; i++) {
//							String menu = input.readLine();
//							System.out.println(menu);
//						}
//						choice1 = Integer.parseInt(scanner.readLine());
//						output.println(choice1);
//						switch(choice1) {
//						case 1 :
//							String numberOfSlots=input.readLine();
//							System.out.println(numberOfSlots);
//							int numberOfFreeSlots=Integer.parseInt(scanner.readLine());
//							output.println(numberOfFreeSlots);
//							String message=input.readLine();
//							System.out.println(message);
//							for(int i=0;i<numberOfFreeSlots;i++) {
//								String m=input.readLine();
//								System.out.println(m);
//								String time=scanner.readLine();
//								output.println(time);
//								
//							}
//							String added=input.readLine();
//							System.out.println(added);
//							
//							break;
//						case 2:
//							for (int i = 0; i < 3; i++) {
//								String menu = input.readLine();
//								System.out.println(menu);
//							}
//							int number=Integer.parseInt(input.readLine());
//							for(int i=0;i<number;i++) {
//								String data=input.readLine();
//								System.out.println(data);
//								
//							}
//							String m=input.readLine();
//							System.out.println(m);
//							break;
//						case 3:
//							break;
//							
//						}
//						
//					}while(choice1<3);
//				}
//				break;
//			case 4:
//				String vvname, ppassword;
//				String forvvname=input.readLine();
//				System.out.println(forvvname);
//				vvname=scanner.readLine();
//				output.println(vvname);
//				String forppasword=input.readLine();
//				System.out.println(forppasword);
//				ppassword=scanner.readLine();
//				output.println(ppassword);
//				String visVerdict=input.readLine();
//				System.out.println(visVerdict);
//				if(visVerdict.equals("[SERVER] You have successfully logged in!!!")){
//					String s=input.readLine();
//					System.out.println(s);
//					String ans=scanner.readLine();
//					output.println(ans);
//					String verdict2=input.readLine();
//					System.out.println(verdict2);
//					if(verdict2.equals("[SERVER] The company exists in the Database.. Gathering Info..")) {
//						for (int i = 0; i < 5; i++) {
//							String menu = input.readLine();
//							System.out.println(menu);
//						}
//						int number=Integer.parseInt(input.readLine());
//						for(int i=0;i<number;i++) {
//							String time=input.readLine();
//							System.out.println(time);
//						}
//						String tp=input.readLine();
//						System.out.println(tp);
//						int j=0;
//						String mm=input.readLine();
//						System.out.println(mm);
//						String ss=scanner.readLine();
//						System.out.println(ss);
//						j=Integer.parseInt(ss);
//						
//						output.println(ss);
//						if(j>=0&& j<number) {
//							String mess=input.readLine();
//							System.out.println(mess);
//						}
//						if(j<0 || j<number) {
//							String m=input.readLine();
//							System.out.println(m);
//						}
//					}
//				}
//				break;
//			}
//
//
//
//		} while (choice != 5);
//		scanner.close();
//		input.close();
//		socket.close();

	}

}
