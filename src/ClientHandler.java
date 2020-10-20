import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler implements Runnable{

	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	public ClientHandler (Socket clientSocket) throws IOException {
		this.socket=clientSocket;
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(socket.getOutputStream(), true);
	}
	@Override
	public void run() {
		try {
			while(true) {
			String request=input.readLine();
			if(request.equals("Signup")) {
				int valid=0;
				String type=input.readLine();
				String name=input.readLine();
				String password=input.readLine();
				if(type.equals("Visitor")) {
					try {
						Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
						Statement myStmt = MyConn.createStatement();
						try {
							myStmt.executeUpdate("INSERT INTO register_visitor VALUES('"+name+"','"+password+"')");
							System.out.println("[SERVER] Your have now Registered, Please Login!!");
							valid=1;
						}
						catch(Exception Exe) {
							 System.out.println("[SERVER] You are already registered, Please Login!!");
						}
					}
					catch(Exception Exe){
						System.out.println(Exe);
					}
					
				}
				else {
					try {
						Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
						Statement myStmt = MyConn.createStatement();
						try {
							myStmt.executeUpdate("INSERT INTO register_representative VALUES('"+name+"','"+password+"')");
							System.out.println("[SERVER] Your Company is Now Registered, Please Login!!");
							valid=1;
						}
						catch(Exception Exe) {
							 System.out.println("[SERVER] Company is already registered, Please Login!!");
						}
					}
					catch(Exception Exe){
						System.out.println(Exe);
					}
				}
				output.println(valid);
			}
			else if(request.equals("Login")) {
				System.out.println("Login");
				String type=input.readLine();
				String name=input.readLine();
				String password=input.readLine();
				int valid=-1;
				if(!type.contentEquals("Visitor")) {
					try {
						Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
						Statement myStmt = MyConn.createStatement();
						ResultSet myRs=myStmt.executeQuery("SELECT * from register_representative WHERE cname='"+name+"'");
						System.out.println("SELECT * from register_representative WHERE cname='"+name+"'");
						if(!myRs.isBeforeFirst()) {
							valid=0;
		                    System.out.println("[SERVER] The Company Name is not registered! Please Register first");
						}
						else {
							myRs=myStmt.executeQuery("SELECT * from register_representative WHERE cname='"+name+"' AND password='"+password+"'");
							System.out.println("SELECT * from register_representative WHERE cname='"+name+"' AND password='"+password+"'");
							if(!myRs.isBeforeFirst()) {
								valid=1;
								System.out.println("[SERVER] Password is incorrect!!! Please try again");
							}
							else {
								valid=2;
								System.out.println("[SERVER] You have successfully logged in!!!");
							}
						}
					}catch(Exception Exe){
						System.out.println(Exe);
					}
		
				}
				else {
					try {
	                	Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
						Statement myStmt = MyConn.createStatement();
						ResultSet myRs=myStmt.executeQuery("SELECT * from register_visitor WHERE vname='"+name+"'");
						System.out.println("SELECT * from register_representative WHERE vname='"+name+"'");
						if(!myRs.isBeforeFirst()) {
							valid=0;
		                    System.out.println("[SERVER] You are  not registered! Please Register first");
						}
						else {
							myRs=myStmt.executeQuery("SELECT * from register_visitor WHERE vname='"+name+"' AND password='"+password+"'");
							System.out.println("SELECT * from register_representative WHERE vname='"+name+"' AND password='"+password+"'");
							if(!myRs.isBeforeFirst()) {
								valid=1;
								System.out.println("[SERVER] Password is incorrect!!! Please try again");
							}
							else {
								valid=2;
								System.out.println("[SERVER] You have successfully logged in!!!");
							}
				}
				
			}catch(Exception Exe){
				System.out.println(Exe);
			}
					
			}
				output.println(valid);
		}
			else if(request.equals("View Free Slots Representative")) {
				String companyName=input.readLine();
				System.out.println(companyName);
				Connection MyConn = null;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ResultSet myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname='No'");
					int count = 0;
                    int q = 0;
                    while(myRs.next()){
                      
                        count++;
                    }
                    String random=Integer.toString(count);
                    output.println(random);
                    myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname='No'");
                    int i=0;
                    while(myRs.next()) {
                    	String tpp=myRs.getString("slot_start")+"#"+myRs.getString("slot_end")+"#"+myRs.getDate("dateGiven");
                    	System.out.println("[SERVER] sent free slot to client: "+ tpp);
                        output.println(tpp);
                        i++;
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(request.equals("Add Free Slot")) {
				String ccname=input.readLine();
				String time1=input.readLine();
				String time2=input.readLine();
				String date=input.readLine();
//				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            	Date date = new Date();
				Connection MyConn = null;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	System.out.println("INSERT INTO parking VALUES('"+ccname+"','"+time1+"','"+time2+"','"+date+"','No')");
            	try {
					myStmt.executeUpdate("INSERT INTO parking VALUES('"+ccname+"','"+time1+"','"+time2+"','"+date+"','No')");
					output.println("Done");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					output.println("Not Done");
				}
			}
			else if(request.equals("View Booked Slots")) {
				String companyName=input.readLine();
				System.out.println(companyName);
				Connection MyConn = null;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ResultSet myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname!='No'");
					int count = 0;
                    int q = 0;
                    while(myRs.next()){
                      
                        count++;
                    }
                    String random=Integer.toString(count);
                    output.println(random);
                    myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname!='No'");
                    int i=0;
                    while(myRs.next()) {
                    	String tpp=myRs.getString("slot_start")+"#"+myRs.getString("slot_end")+"#"+myRs.getDate("dateGiven")+"#"+myRs.getString("vname");
                    	System.out.println("[SERVER] sent free slot to client: "+ tpp);
                        output.println(tpp);
                        i++;
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			else if(request.equals("Get Company List")) {
				
				Connection MyConn = null;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ResultSet myRs=myStmt.executeQuery("SELECT cname FROM register_representative");
					int count = 0;
                    int q = 0;
                    while(myRs.next()){
                      
                        count++;
                    }
                    String random=Integer.toString(count);
                    output.println(random);
                    myRs=myStmt.executeQuery("SELECT cname FROM register_representative");
                    int i=0;
                    while(myRs.next()) {
                    	String tpp=myRs.getString("cname");
                    	System.out.println("[SERVER] sent free slot to client: "+ tpp);
                        output.println(tpp);
                        i++;
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(request.equals("Get Free Slots in the selected company")) {
				String companyName=input.readLine();
				String vname=input.readLine();
				Connection MyConn = null;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ResultSet myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname='No'");
					int count = 0;
                    int q = 0;
                    while(myRs.next()){
                      
                        count++;
                    }
                    String random=Integer.toString(count);
                    output.println(random);
                    myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname='No'");
                    int i=0;
                    while(myRs.next()) {
                    	String tpp=myRs.getString("slot_start")+"#"+myRs.getString("slot_end")+"#"+myRs.getDate("dateGiven");
                    	System.out.println("[SERVER] sent free slot to client: "+ tpp);
                        output.println(tpp);
                        i++;
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(request.equals("Book Free Slot")) {
				String vname=input.readLine();
				String companyName=input.readLine();
				int index=Integer.parseInt(input.readLine());
				Connection MyConn=null;
				int valid=0;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ResultSet myRs=myStmt.executeQuery("SELECT * FROM parking WHERE cname='"+companyName+"' AND vname='No'");
					int count = 0;
                    int q = 0;
                    String slot_start = null, slot_end = null, date;
                    while(myRs.next()){
                    	if(count+1==index) {
                    		slot_start=myRs.getString("slot_start");
                    		slot_end=myRs.getString("slot_end");
                    		date=myRs.getString("dateGiven");
                    		break;
                    	}
                        count++;
                    }

                    myStmt.executeUpdate("UPDATE parking SET vname='"+vname+"' WHERE cname='"+companyName+"' AND slot_start='"+slot_start+"' AND slot_end='"+slot_end+"'");
                    valid=1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				output.println(valid);
			}
			else if(request.equals("Check Booked Slots")) {
				String name=input.readLine();
				Connection MyConn = null;
				try {
					MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/total_parking_solutions", "root", "root");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Statement myStmt = null;
				try {
					myStmt = MyConn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ResultSet myRs=myStmt.executeQuery("SELECT * FROM parking WHERE vname='"+name+"'");
					int count = 0;
                    int q = 0;
                    while(myRs.next()){
                      
                        count++;
                    }
                    String random=Integer.toString(count);
                    output.println(random);
                    myRs=myStmt.executeQuery("SELECT * FROM parking WHERE vname='"+name+"'");
                    int i=0;
                    while(myRs.next()) {
                    	String tpp=myRs.getString("slot_start")+"#"+myRs.getString("slot_end")+"#"+myRs.getDate("dateGiven");
                    	System.out.println("[SERVER] sent free slot to client: "+ tpp);
                        output.println(tpp);
                        i++;
                    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.close();
		
	}

	

}
