/**
 * CreateBooking.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Creating a Booking
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.sql.*;

public class CreateBooking extends JFrame implements ActionListener 
{

    JTextField tbookingId;
    JComboBox troomType, tguestId, tbillToGuestId;
    JFormattedTextField tdateBooked ,tcheckInDate, tcheckOutDate;
    JButton submit, clear;
    
    Vector troomTypeModel = new Vector();
    Vector troomNoModel = new Vector();
    Vector tguestIdModel = new Vector();
    Vector tbillToGuestIdModel = new Vector();
    
    
    public CreateBooking() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    //---------------------------------------------------------------------------------------------     
    public void createBooking(String user) 
    {  
        
        //Setting the Form properties
        setTitle("Hotel Online Booking Management System: Make Booking");
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
        JLabel title = new JLabel("     Create Booking"); 
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
        //Create Booking Sub Panel 2: The Booking ID label on the form
        JLabel lbookingId = new JLabel("Booking ID");
        lbookingId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbookingId.setSize(120, 20);
        lbookingId.setLocation(20, 20);
        mainBodySubPanel2.add(lbookingId);

        //Create Booking Sub Panel 2: The Booking No textbox on the form
        tbookingId = new JTextField(); 
        tbookingId.setFont(new Font("Arial", Font.PLAIN, 9)); 
        tbookingId.setBackground(Color.LIGHT_GRAY);
        tbookingId.setText("System Assigned");
        tbookingId.setForeground(Color.BLUE);
        tbookingId.setEditable(false);
        tbookingId.setSize(210, 20); 
        tbookingId.setLocation(150, 20);
        mainBodySubPanel2.add(tbookingId);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The DATE BOOKED label on the form
        JLabel ldateBooked = new JLabel("Date Booked*"); 
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14)); 
        ldateBooked.setSize(120, 20); 
        ldateBooked.setLocation(20, 50); 
        mainBodySubPanel2.add(ldateBooked);
        
        //Create Booking Sub Panel 2: The Date Booked textbox on the form
        MaskFormatter dateBookedMask = null;
        try 
        {
          dateBookedMask = new MaskFormatter("####-##-##"); //the # is for numeric values
          dateBookedMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tdateBooked = new JFormattedTextField(dateBookedMask);
        tdateBooked.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tdateBooked.setSize(100, 20); 
        tdateBooked.setLocation(150, 50);
        this.addWindowFocusListener(new WindowAdapter() 
        {
            public void windowGainedFocus(WindowEvent e) {
                tdateBooked.requestFocusInWindow();
            }
        });
        mainBodySubPanel2.add(tdateBooked);        

        ldateBooked = new JLabel("yyyy-mm-dd");
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14));
        ldateBooked.setSize(120, 20);
        ldateBooked.setLocation(260, 50);
        mainBodySubPanel2.add(ldateBooked);
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Room Type label on the form
        JLabel lroomType = new JLabel("Room Type");
        lroomType.setFont(new Font("Arial", Font.PLAIN, 14));
        lroomType.setSize(120, 20);
        lroomType.setLocation(20, 80);
        mainBodySubPanel2.add(lroomType);
        
        //Create Booking Sub Panel 2: The Room Type textbox on the form
        //Query to retrieve the Room data from the database and populate the Vector object
        try 
        {
            String roomTypeQuery = "SELECT DISTINCT roomType FROM ROOM ORDER BY roomType"; 
            PreparedStatement statement = da.con.prepareStatement(roomTypeQuery); 
            troomTypeModel.addElement(new Item("-1", "--Select a Room Type--" ));
            da.rst = da.stat.executeQuery(roomTypeQuery);
            
            while (da.rst.next()) 
            { 
                troomTypeModel.addElement(new Item("", da.rst.getString(1)));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        troomType = new JComboBox(troomTypeModel);
        troomType.setSize(210, 20); 
        troomType.setLocation(150, 80); 
        troomType.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox roomTypeComboBox = (JComboBox)e.getSource();
                    Item item = (Item)roomTypeComboBox.getSelectedItem();
                }
            });
        tdateBooked.setNextFocusableComponent(troomType);
        mainBodySubPanel2.add(troomType);
        
        
        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Guest Id textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        JLabel lguestId = new JLabel("Guest No");
        lguestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lguestId.setSize(120, 20);
        lguestId.setLocation(20, 110);
        mainBodySubPanel2.add(lguestId);

        try 
        {
            String guestIdQuery = "SELECT guestId, Name FROM GUEST ORDER BY Name"; 
            PreparedStatement statement = da.con.prepareStatement(guestIdQuery); 
            tguestIdModel.addElement(new Item("-1", "--Select a Guest Number--" ));
            da.rst = da.stat.executeQuery(guestIdQuery);
            
            while (da.rst.next()) 
            { 
                String id = da.rst.getString(1);
                String desc = da.rst.getString(2);
                tguestIdModel.addElement(new Item(id, id + "   " + desc));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        tguestId = new JComboBox(tguestIdModel);
        tguestId.setSize(210, 20); 
        tguestId.setLocation(150, 110); 
        tguestId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox guestIdComboBox = (JComboBox)e.getSource();
                    Item item = (Item)guestIdComboBox.getSelectedItem();
                }
            });
        troomType.setNextFocusableComponent(tguestId);
        mainBodySubPanel2.add(tguestId);
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Bill To Guest ID label on the form
        JLabel lbillToGuestId = new JLabel("Bill To Guest ID");
        lbillToGuestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbillToGuestId.setSize(120, 20);
        lbillToGuestId.setLocation(20, 140);
        mainBodySubPanel2.add(lbillToGuestId);

        //Create Booking Sub Panel 2: The Bill To Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        try 
        {
            String billToGuestIdQuery = "SELECT guestId, Name FROM GUEST ORDER BY Name"; 
            PreparedStatement statement = da.con.prepareStatement(billToGuestIdQuery); 
            tbillToGuestIdModel.addElement(new Item("-1", "--Select a Guest Number--" ));
            da.rst = da.stat.executeQuery(billToGuestIdQuery);
            
            while (da.rst.next()) 
            { 
                String id = da.rst.getString(1);
                String desc = da.rst.getString(2);
                tbillToGuestIdModel.addElement(new Item(id, id + "   " + desc));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        tbillToGuestId = new JComboBox(tbillToGuestIdModel);
        tbillToGuestId.setSize(210, 20); 
        tbillToGuestId.setLocation(150, 140); 
        tbillToGuestId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox billToGuestIdComboBox = (JComboBox)e.getSource();
                    Item item = (Item)billToGuestIdComboBox.getSelectedItem();
                }
            });
        tguestId.setNextFocusableComponent(tbillToGuestId);
        mainBodySubPanel2.add(tbillToGuestId);
        
        //---------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Checkin Date label on the form
        JLabel lcheckInDate = new JLabel("Checkin Date*"); 
        lcheckInDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcheckInDate.setSize(120, 20); 
        lcheckInDate.setLocation(20, 170); 
        mainBodySubPanel2.add(lcheckInDate);
        
        //Create Booking Sub Panel 2: The Checkin Date textbox on the form
        MaskFormatter checkInDateMask = null;
        try 
        {
          checkInDateMask = new MaskFormatter("####-##-##"); //the # is for numeric values
          checkInDateMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tcheckInDate = new JFormattedTextField(checkInDateMask);
        tcheckInDate.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tcheckInDate.setSize(100, 20);
        tcheckInDate.setLocation(150, 170);
        tguestId.setNextFocusableComponent(tcheckInDate);
        mainBodySubPanel2.add(tcheckInDate);

        lcheckInDate = new JLabel("yyyy-mm-dd");
        lcheckInDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcheckInDate.setSize(120, 20);
        lcheckInDate.setLocation(260, 170);
        mainBodySubPanel2.add(lcheckInDate);
        
        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Check Out Date label on the form
        JLabel lcheckOutDate = new JLabel("Check Out Date*"); 
        lcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcheckOutDate.setSize(120, 20); 
        lcheckOutDate.setLocation(20, 200); 
        mainBodySubPanel2.add(lcheckOutDate);
        
        //Create Booking Sub Panel 2: The Check Out Date textbox on the form
        MaskFormatter checkOutDateMask = null;
        try 
        {
          checkOutDateMask = new MaskFormatter("####-##-##"); //the # is for numeric values
          checkOutDateMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tcheckOutDate = new JFormattedTextField(checkOutDateMask);
        tcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tcheckOutDate.setSize(100, 20);
        tcheckOutDate.setLocation(150, 200);
        tcheckInDate.setNextFocusableComponent(tcheckOutDate);
        mainBodySubPanel2.add(tcheckOutDate);

        lcheckOutDate = new JLabel("yyyy-mm-dd");
        lcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcheckOutDate.setSize(120, 20);
        lcheckOutDate.setLocation(260, 200);
        mainBodySubPanel2.add(lcheckOutDate); 
        
        //---------------------------------------------------------------------------------------------- 
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Save Booking");
        submit.addActionListener(this);
        tcheckOutDate.setNextFocusableComponent(submit);
        
        clear = new JButton("Reset All Fields");
        clear.addActionListener(this);
        submit.setNextFocusableComponent(clear);

        JButton close = new JButton("Close Form");
        close.addActionListener(e -> {
            this.dispose();
        });

        clear.setNextFocusableComponent(close);
        close.setNextFocusableComponent(tdateBooked);

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
                String s2 = tguestId.getSelectedItem().toString().substring(0,tguestId.getSelectedItem().toString().indexOf(' ')); //entered guest id
                String s3 = tbillToGuestId.getSelectedItem().toString().substring(0,tbillToGuestId.getSelectedItem().toString().indexOf(' '));//entered booking billToGuestId;                                    
                String s4 = tcheckInDate.getText();   //entered checkin date
                String s5 = tcheckOutDate.getText(); //entered checkout date
                String s6 = troomType.getSelectedItem().toString(); 
                String s7 = tdateBooked.getText(); //selected dateBooked
                
                    
                if(!s2.equals("") && !s3.equals("") && !s4.equals("") && !s5.equals("") && !s6.equals(""))
                {
                    String query = "INSERT INTO BOOKING VALUES (null, ?, 1, ?, 7, ?, ?, ?, 0,'Pending', ? )";
                    da.ps = da.con.prepareStatement(query);
                    da.ps.setString(1, s2);   //entered guestId
                    da.ps.setString(2, s3);   //selected billToGuestId
                    da.ps.setString(3, s4);   //entered checkin date
                    da.ps.setString(4, s5);   //entered checkout date
                    da.ps.setString(5, s6);   //selected roomType
                    da.ps.setString(6, s7);   //selected dateBooked


                    int success = da.ps.executeUpdate();
                    if (success > 0) 
                    {  
                        query = "SELECT MAX(bookingId) FROM BOOKING WHERE  guestId = ? AND billToGuestId = ? AND checkInDate = ? AND checkOutDate = ? AND roomType = ? AND dateBooked = ? ";
                        da.ps = da.con.prepareStatement(query);
                        da.ps.setString(1, s2);     //entered guestId
                        da.ps.setString(2, s3);     //selected billToGuestId
                        da.ps.setString(3, s4);     //entered checkin date
                        da.ps.setString(4, s5);     //entered checkout date
                        da.ps.setString(5, s6);     //selected roomType
                        da.ps.setString(6, s7);     ///selected dateBooked
                        
                        da.rst = da.ps.executeQuery();
                        while(da.rst.next())
                        {
                           s1 = da.rst.getString(1); 
                        }
                    
                        JOptionPane.showMessageDialog ( null,"Data Saved Successfully\n Your Booking Reference Number is: " + s1);
                        //reset Guest Form Fieldsnull, 
                        tbookingId.setText("");
                        tguestId.setSelectedIndex(1);
                        tbillToGuestId.setSelectedIndex(1);
                        tcheckInDate.setText("");
                        tcheckOutDate.setText("");
                        troomType.setSelectedIndex(1);
                        tdateBooked.setText("");
                          
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: Data Not Saved\nPlease check data input and try again");
                    }
                }
            }
            catch (Exception ex) 
            {  
                System.out.println(ex);  
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
