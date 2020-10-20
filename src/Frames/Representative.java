package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.event.ChangeListener;
import javax.swing.text.DateFormatter;
import javax.swing.SpringLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Representative extends JFrame {

	public JPanel contentPane,p1,p2,p3,p4;
	public JTabbedPane tabbedPane;
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JPanel panel_4;
	public SpringLayout springLayout;
	public JDatePickerImpl datePicker;
	public JSpinner spinner, spinner2;
	public Calendar calendar, calendar1;
	public JButton btnAddSlot;
	public JLabel lblChooseDate, lblStartTime, lblEndTime,lblYouCanAdd;
	public JTable table;

	public JScrollPane sp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Representative frame = new Representative();
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
	public Representative() {
		setLayout(new FlowLayout());
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 427);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 36, 667, 369);
		p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setBounds(0, 36, 667, 369);
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBounds(0, 36, 667, 369);
		p3 = new JPanel();
		p3.setBackground(Color.WHITE);
		p3.setBounds(0, 36, 667, 369);
		
		contentPane.add(panel);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);

		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(0, 6, 667, 29);
		contentPane.add(tabbedPane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Add Free Slots", panel_1);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("View Free Slots", panel_2);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("View Booked Slots", panel_3);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Logout", panel_4);
		
		
		
		
                       
                        
        
      
		
		
		
	}
	public static class DateLabelFormatter extends AbstractFormatter {

	    public String datePattern = "yyyy-MM-dd";
	    public SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}

}
