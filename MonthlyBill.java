/**
 * MonthlyBill.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage MonthlyBill
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.sql.*;

public class MonthlyBill extends JFrame implements ActionListener 
{

    JTextField ttotalAmount,tbillId;
    JCheckBox tpaid;
    JComboBox tguestId ;
    JFormattedTextField tstartDate, tendDate;
    JButton submit, clear;
    
    Vector tguestIdModel = new Vector();
    Vector tbillIdModel = new Vector();
    
    
    public MonthlyBill() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    //---------------------------------------------------------------------------------------------     
    public void MonthlyBill (String user) 
    {  
        
        //Setting the Form properties
        setTitle("Hotel Online Booking Management System: MonthlyBill");
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
        JLabel title = new JLabel("   Monthly Bill "); 
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
        //Create Booking Sub Panel 2: The Bill ID label on the form
        JLabel lbillId = new JLabel("Bill ID");
        lbillId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbillId.setSize(120, 20);
        lbillId.setLocation(20, 20);
        mainBodySubPanel2.add(lbillId);

        //Create Booking Sub Panel 2: The Booking No textbox on the form
        tbillId = new JTextField(); 
        tbillId.setFont(new Font("Arial", Font.PLAIN, 9)); 
        tbillId.setBackground(Color.LIGHT_GRAY);
        tbillId.setText("System Assigned");
        tbillId.setForeground(Color.BLUE);
        tbillId.setEditable(false);
        tbillId.setSize(210, 20); 
        tbillId.setLocation(150, 20);
        mainBodySubPanel2.add(tbillId);

        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Guest ID label on the form
        JLabel lguestId = new JLabel("Guest ID");
        lguestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lguestId.setSize(120, 20);
        lguestId.setLocation(20, 50);
        mainBodySubPanel2.add(lguestId);

        //Create Booking Sub Panel 2: The Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        try 
        {
            String guestIdQuery = "SELECT guestId,name FROM GUEST ORDER BY name"; 
            PreparedStatement statement = da.con.prepareStatement(guestIdQuery); 
            tguestIdModel.addElement(new Item("-1", "--Select a Guest Number--" ));
            da.rst = da.stat.executeQuery(guestIdQuery);
            
            while (da.rst.next()) 
            { 
                String id = da.rst.getString(1);
                String desc = da.rst.getString(2);
                tguestIdModel.addElement(new Item(id, id +   "   " + desc));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        tguestId = new JComboBox(tguestIdModel);
        tguestId.setSize(210, 20); 
        tguestId.setLocation(150, 50); 
        tguestId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox guestIdComboBox = (JComboBox)e.getSource();
                    Item item = (Item)guestIdComboBox.getSelectedItem();
                }
            });
        
        tbillId.setNextFocusableComponent(tguestId);
        mainBodySubPanel2.add(tguestId);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Start Date label on the form
        JLabel lstartDate = new JLabel("Start Date*"); 
        lstartDate .setFont(new Font("Arial", Font.PLAIN, 14)); 
        lstartDate .setSize(120, 20); 
        lstartDate .setLocation(20, 80); 
        mainBodySubPanel2.add(lstartDate );
        
        //Create Booking Sub Panel 2: The Start Date textbox on the form
        MaskFormatter startDateMask = null;
        try 
        {
          startDateMask = new MaskFormatter("####-##-##"); //the # is for numeric values
          startDateMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tstartDate  = new JFormattedTextField(startDateMask);
        tstartDate .setFont(new Font("Arial", Font.PLAIN, 15)); 
        tstartDate .setSize(100, 20);
        tstartDate .setLocation(150, 80);
        tguestId.setNextFocusableComponent(tstartDate );
        mainBodySubPanel2.add(tstartDate );

        lstartDate  = new JLabel("yyyy-mm-dd");
        lstartDate .setFont(new Font("Arial", Font.PLAIN, 14));
        lstartDate .setSize(120, 20);
        lstartDate .setLocation(260, 80);
        mainBodySubPanel2.add(lstartDate );


      //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The End Out Date label on the form
        JLabel lendDate = new JLabel("End Date*"); 
        lendDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lendDate.setSize(120, 20); 
        lendDate.setLocation(20, 110); 
        mainBodySubPanel2.add(lendDate);
        
        //Create Booking Sub Panel 2: The End Out Date textbox on the form
        MaskFormatter endDateMask = null;
        try 
        {
          endDateMask = new MaskFormatter("####-##-##"); //the # is for numeric values
          endDateMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tendDate = new JFormattedTextField(endDateMask);
        tendDate.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tendDate.setSize(100, 20);
        tendDate.setLocation(150, 110);
        tstartDate.setNextFocusableComponent(tendDate);
        mainBodySubPanel2.add(tendDate);

        lendDate = new JLabel("yyyy-mm-dd");
        lendDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lendDate.setSize(120, 20);
        lendDate.setLocation(260, 110);
        mainBodySubPanel2.add(lendDate); 
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Total Amount textbox on the form
        JLabel ltotalAmount = new JLabel("Total Amount");
        ltotalAmount.setFont(new Font("Arial", Font.PLAIN, 14));
        ltotalAmount.setSize(120, 20);
        ltotalAmount.setLocation(20,140);
        mainBodySubPanel2.add(ltotalAmount);

        //Create Booking Sub Panel 2: The Total Amount textbox on the form
        ttotalAmount = new JTextField(); 
        ttotalAmount.setFont(new Font("Arial", Font.PLAIN, 9)); 
        ttotalAmount.setBackground(Color.LIGHT_GRAY);
        ttotalAmount.setText("");
        ttotalAmount.setForeground(Color.BLUE);
        ttotalAmount.setEditable(true);
        ttotalAmount.setSize(210, 20); 
        ttotalAmount.setLocation(150, 140);
        tendDate.setNextFocusableComponent(ttotalAmount);
        mainBodySubPanel2.add(ttotalAmount);   
        
     //-------------------------------------------------------------------------------
     
     //Create Booking Sub Panel 2: The Paid label on the form
        JLabel lpaid = new JLabel("Paid (Yes/No)");
        lpaid.setFont(new Font("Arial", Font.PLAIN, 14));
        lpaid.setSize(120, 20);
        lpaid.setLocation(20, 170);
        mainBodySubPanel2.add(lpaid);

        //Create Booking Sub Panel 2: The Paid textbox on the form
        tpaid = new JCheckBox(); 
        tpaid.setFont(new Font("Arial", Font.PLAIN, 14)); 
        tpaid.setSize(50, 20); 
        tpaid.setLocation(150,170);
        ttotalAmount.setNextFocusableComponent(tpaid);
        mainBodySubPanel2.add(tpaid);
        
        //---------------------------------------------------------------------------------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Save");
        submit.addActionListener(this);
        tpaid.setNextFocusableComponent(submit);
        
        clear = new JButton("Reset All Fields");
        clear.addActionListener(this);
        submit.setNextFocusableComponent(clear);

        JButton close = new JButton("Close Form");
        close.addActionListener(e -> {
            this.dispose();
        });

        clear.setNextFocusableComponent(close);
        close.setNextFocusableComponent(tbillId);

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
                String s1 = "";   //selected Guest ID
                String s2 = tguestId.getSelectedItem().toString().substring(0,tguestId.getSelectedItem().toString().indexOf(' ')); //entered Start date
                String s3 = tstartDate.getText();      //entered End date
                String s4 = tendDate.getText();        //entered Total Amount
                String s5 = ttotalAmount.getText();    //selected Paid or Not
                String s6 = "";
                if(tpaid.isSelected())
                    s6 = "1";                          //checked if paid
                else
                    s6 = "0";     
                    
                    
            if(!s2.equals("") && !s3.equals("") && !s4.equals("") && !s5.equals("")& !s6.equals(""))
            {
                    String query = "INSERT INTO MonthlyBill VALUES (null, ?, ?, ?, ?,? )";
                    da.ps = da.con.prepareStatement(query);
                    da.ps.setString(1, s2);     //selected Guest ID
                    da.ps.setString(2, s3);     //entered Start date
                    da.ps.setString(3, s4);     //entered End Date 
                    da.ps.setString(4, s5);     //entered Total Amount
                    da.ps.setString(5, s6);     //selected Paid or Not

                    int success = da.ps.executeUpdate();
                    if (success > 0) 
                    {  
                        query = "SELECT MAX(billId) FROM MonthlyBill WHERE  guestId = ? AND startDate= ? AND endDate= ? AND totalAmount = ?  AND paid = ?";
                        da.ps = da.con.prepareStatement(query);
                        da.ps.setString(1, s2);     //selected ed Guest ID
                        da.ps.setString(2, s3);     //entered Start date
                        da.ps.setString(3, s4);     //entered End Date 
                        da.ps.setString(4, s5);     //entered Total Amount
                        da.ps.setString(5, s6);    //selected Paid or Not
                        
                        da.rst = da.ps.executeQuery();
                        while(da.rst.next())
                        {
                           s1 = da.rst.getString(1); 
                        }
                    
                        JOptionPane.showMessageDialog ( null,"Data Saved Successfully\n Your Bill Reference Number is: " + s1);
                        //reset Guest Form Fieldsnull, "Data Saved Successfully\n Your Booking Reference Number is: " + s1
                        tbillId.setText("");
                        tguestId.setSelectedIndex(1);
                        tstartDate.setText("");
                        tendDate.setText("");
                        ttotalAmount.setText("");
                        tpaid.setSelected(false);
                       
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
