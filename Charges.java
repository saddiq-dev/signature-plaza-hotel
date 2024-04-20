/**
 * Charges.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Charges.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.sql.*;

public class Charges extends JFrame implements ActionListener 
{

    JTextField tamountCharged,tchargeId;
    JComboBox thotelStaffId, tbookingId,tchargeType;
    JButton submit, clear;
    
    Vector thotelStaffIdModel = new Vector();
    Vector tbookingIdModel = new Vector();
    
    
    public Charges() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    //---------------------------------------------------------------------------------------------     
    public void Charges (String user) 
    {  
        
        //Setting the Form properties
        setTitle("Hotel Online Booking Management System: CHARGES");
        setBounds(487, 75, 450, 520); //size of the frame
        setBackground(Color.YELLOW);
        setResizable(false);    

        //---------------------------------------------------------------------------------------------     
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Help");
        
        menuBar.add(menu1);
        menuBar.add(menu2);

        //--------------------------------------------------------------------------------------------- 
        //Adding Components to the File option on the Main MenuBar
        JMenuItem menu11 = new JMenuItem("Close");
        menu1.add(menu11);

        menu11.addActionListener(e -> {
            this.dispose();
        });
        
        //---------------------------------------------------------------------------------------------         
        //Main Body Panel: The middle panel
        JPanel mainBodyPanel = new JPanel();
        mainBodyPanel.setSize(450,450);
        mainBodyPanel.setBackground(Color.PINK);  

        //--------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 1: The top label on the form
        JPanel mainBodySubPanel1 = new JPanel();
        mainBodySubPanel1.setLayout(null);
        mainBodySubPanel1.setBackground(Color.BLUE);
        mainBodySubPanel1.setPreferredSize(new Dimension(450, 40));

        //The title on the form to be placed in mainBodySubPanel1
        JLabel title = new JLabel("   Charges "); 
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25)); 
        title.setForeground(Color.YELLOW);
        title.setSize(450, 35); 
        mainBodySubPanel1.add(title);
        
        JLabel guestNameLabel = new JLabel("User: " + user + "     ");
        guestNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        guestNameLabel.setVerticalAlignment(JLabel.CENTER);
        guestNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        guestNameLabel.setForeground(Color.YELLOW);
        guestNameLabel.setSize(450, 35);
        mainBodySubPanel1.add(guestNameLabel);

        //--------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: Create Data Sub Panel 2
        JPanel mainBodySubPanel2 = new JPanel();
        mainBodySubPanel2.setLayout(null);
        mainBodySubPanel2.setPreferredSize(new Dimension(425,360));
        mainBodySubPanel2.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Charge ID label on the form
        JLabel lchargeId = new JLabel("Charge ID");
        lchargeId.setFont(new Font("Arial", Font.PLAIN, 14));
        lchargeId.setSize(120, 20);
        lchargeId.setLocation(20, 20);
        mainBodySubPanel2.add(lchargeId);

        //Create Booking Sub Panel 2: The Booking No textbox on the form
        tchargeId = new JTextField(); 
        tchargeId.setFont(new Font("Arial", Font.PLAIN, 9)); 
        tchargeId.setBackground(Color.LIGHT_GRAY);
        tchargeId.setText("System Assigned");
        tchargeId.setForeground(Color.BLUE);
        tchargeId.setEditable(false);
        tchargeId.setSize(210, 20); 
        tchargeId.setLocation(150, 20);
        mainBodySubPanel2.add(tchargeId);

        
         //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Booking No label on the form
        JLabel lbookingId = new JLabel("Booking ID");
        lbookingId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbookingId.setSize(120, 20);
        lbookingId.setLocation(20, 50);
        mainBodySubPanel2.add(lbookingId);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        try 
        {
            String guestidQuery = "SELECT bookingId, guestid FROM BOOKING ORDER BY guestid"; 
            PreparedStatement statement = da.con.prepareStatement(guestidQuery); 
            tbookingIdModel.addElement(new Item("-1", "--Select a Guest Number--" ));
            da.rst = da.stat.executeQuery(guestidQuery);
            
            while (da.rst.next()) 
            { 
                String id = da.rst.getString(1);
                String desc = da.rst.getString(2);
                tbookingIdModel.addElement(new Item(id, id +   "   " + desc));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        tbookingId = new JComboBox(tbookingIdModel);
        tbookingId.setSize(210, 20); 
        tbookingId.setLocation(150, 50); 
        tbookingId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox bookingIdComboBox = (JComboBox)e.getSource();
                    Item item = (Item)bookingIdComboBox.getSelectedItem();
                }
            });
        
        tchargeId.setNextFocusableComponent(tbookingId);
        mainBodySubPanel2.add(tbookingId);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Bill To Guest No label on the form
        JLabel lhotelsatffid = new JLabel("Staff No");
        lhotelsatffid.setFont(new Font("Arial", Font.PLAIN, 14));
        lhotelsatffid.setSize(120, 20);
        lhotelsatffid.setLocation(20, 80);
        mainBodySubPanel2.add(lhotelsatffid);

        
        //Create Booking Sub Panel 2: The Bill To Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        try 
        {
            String hotelsatffidQuery = "SELECT hotelStaffId, Name FROM HOTELSTAFF ORDER BY Name"; 
            PreparedStatement statement = da.con.prepareStatement(hotelsatffidQuery); 
            thotelStaffIdModel.addElement(new Item("-1", "--Select a Staff Number--" ));
            da.rst = da.stat.executeQuery(hotelsatffidQuery);
            
            while (da.rst.next()) 
            { 
                String id = da.rst.getString(1);
                String desc = da.rst.getString(2);
                thotelStaffIdModel.addElement(new Item(id, id + "   " + desc));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        thotelStaffId = new JComboBox(thotelStaffIdModel);
        thotelStaffId.setSize(210, 20); 
        thotelStaffId.setLocation(150, 80); 
        thotelStaffId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox hotelsatffidComboBox = (JComboBox)e.getSource();
                    Item item = (Item)hotelsatffidComboBox.getSelectedItem();
                }
            });
        tbookingId.setNextFocusableComponent(thotelStaffId);
        mainBodySubPanel2.add(thotelStaffId);

      //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Bill To Guest No label on the form
        JLabel lchargeType = new JLabel("Charge type*"); 
        lchargeType.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lchargeType.setSize(120, 20); 
        lchargeType.setLocation(20, 110); 
        mainBodySubPanel2.add(lchargeType);
        
        //Guest Sub Panel 2: The Guest Type Combobox on the form
        tchargeType = new JComboBox();
        tchargeType.setSize(210, 20);
        tchargeType.setLocation(150, 110);
        tchargeType.addItem("--Select a Charge Type--");
        tchargeType.addItem("Bar");
        tchargeType.addItem("Restaurant");
        thotelStaffId.setNextFocusableComponent(tchargeType);
        mainBodySubPanel2.add(tchargeType);
        
          //---------------------------------------------------------------------------------- 
        JLabel lamountCharged = new JLabel("Amount Charged");
        lamountCharged.setFont(new Font("Arial", Font.PLAIN, 14));
        lamountCharged.setSize(120, 20);
        lamountCharged.setLocation(20,140);
        mainBodySubPanel2.add(lamountCharged);

        //Create Booking Sub Panel 2: The Booking No textbox on the form
        tamountCharged = new JTextField(); 
        tamountCharged.setFont(new Font("Arial", Font.PLAIN, 9)); 
        tamountCharged.setBackground(Color.LIGHT_GRAY);
        tamountCharged.setText("");
        tamountCharged.setForeground(Color.BLUE);
        tamountCharged.setEditable(true);
        tamountCharged.setSize(210, 20); 
        tamountCharged.setLocation(150, 140);
        tchargeType.setNextFocusableComponent(tamountCharged);
        mainBodySubPanel2.add(tamountCharged);   
        //tchargeType.setNextFocusableComponent(tamountCharged); 
        
        //---------------------------------------------------------------------------------------------- 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Save");
        submit.addActionListener(this);
        tamountCharged.setNextFocusableComponent(submit);
        
        clear = new JButton("Reset All Fields");
        clear.addActionListener(this);
        submit.setNextFocusableComponent(clear);

        JButton close = new JButton("Close Form");
        close.addActionListener(e -> {
            this.dispose();
        });

        clear.setNextFocusableComponent(close);
        close.setNextFocusableComponent(tchargeId);

        buttonPanel.add(submit);
        buttonPanel.add(clear);
        buttonPanel.add(close);        
        clear.addActionListener(this);

        //---------------------------------------------------------------------------------- 
        mainBodyPanel.add(mainBodySubPanel1);
        mainBodyPanel.add(mainBodySubPanel2);
        getContentPane().add(BorderLayout.NORTH, menuBar);  
        getContentPane().add(BorderLayout.CENTER, mainBodyPanel);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);      
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) 
    {
          if (e.getSource() == submit)  
        { 
            try
            {
                String s1 = "";   
                String s2 = tbookingId.getSelectedItem().toString().substring(0,tbookingId.getSelectedItem().toString().indexOf(' ')); 
                String s3 = thotelStaffId.getSelectedItem().toString().substring(0,thotelStaffId.getSelectedItem().toString().indexOf(' '));//selected Booking Id
                String s4 = tchargeType.getSelectedItem().toString();     //selected Charge Type                              
                String s5 = tamountCharged.getText();                    //entered Amount Charged
                    
            if(!s2.equals("") && !s3.equals("") && !s4.equals("") && !s5.equals(""))
            {
                    String query = "INSERT INTO CHARGES VALUES (null, ?, ?, ?, ? )";
                    da.ps = da.con.prepareStatement(query);
                    da.ps.setString(1, s2);     //selected Booking ID
                    da.ps.setString(2, s3);     //selected Staff ID
                    da.ps.setString(3, s4);     //selected Charge Type
                    da.ps.setString(4, s5);     //entered Amount Charged

                    int success = da.ps.executeUpdate();
                    if (success > 0) 
                    {  
                        query = "SELECT MAX(chargeId) FROM CHARGES WHERE  bookingId = ? AND hotelStaffId = ? AND chargeType = ? AND amountCharged = ?  ";
                        da.ps = da.con.prepareStatement(query);
                        da.ps.setString(1, s2);     //selected Booking ID
                        da.ps.setString(2, s3);     //selected Staff ID
                        da.ps.setString(3, s4);     //selected Charge Type
                        da.ps.setString(4, s5);     //entered Amount Charged
                        
                        da.rst = da.ps.executeQuery();
                        while(da.rst.next())
                        {
                           s1 = da.rst.getString(1); 
                        }
                    
                        JOptionPane.showMessageDialog ( null,"Data Saved Successfully\n Your Charge Reference Number is: " + s1);
                        //reset Guest Form Fieldsnull, "Data Saved Successfully\n Your Booking Reference Number is: " + s1
                        tchargeId.setText("");               
                        tbookingId.setSelectedIndex(1);     
                        thotelStaffId.setSelectedIndex(1);  
                        tchargeType.setSelectedIndex(0);    
                        tamountCharged.setText("");         
                        
            }
            else
            {
                        JOptionPane.showMessageDialog(null, "Error: Data Not Saved\nPlease check data input and try again");
            }
                }
            }
            catch (Exception ex) 
            {  
                System.out.println(ex);  //JOptionPane.showMessageDialog(null, "Error: Data Not Saved\nPlease check data input and try again");
            }                
        }   
    }
    
      //---------------------------------------------------------------------------------- 
    //Item class to be used to populate data from the database into JcomboBoxes
     public class Item {
        private String id;
        private String description;

        public Item(String id, String description) {
            this.id = id;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
    }    
}
    //---------------------------------------------------------------------------------
