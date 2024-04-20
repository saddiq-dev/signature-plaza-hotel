/**
 * StaffCreateBooking.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Staff Create Booking.
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.sql.*;

public class StaffCreateBooking extends JFrame implements ActionListener 
{

    JTextField tbookingId;
    JCheckBox tpaid;
    JComboBox troomType, troomNo, tbookingStatus, tguestId, tbillToGuestId, thotelStaffId;
    JFormattedTextField tdateBooked, tcheckInDate, tcheckOutDate;
    JButton submit, clear;
    
    Vector troomTypeModel = new Vector();
    Vector troomNoModel = new Vector();
    Vector tguestIdModel = new Vector();
    Vector tbillToGuestIdModel = new Vector();
    Vector thotelStaffIdModel = new Vector();
    
    
    public StaffCreateBooking() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    //---------------------------------------------------------------------------------------------     
    public void StaffCreateBooking(String user) 
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
        //Create Booking Sub Panel 2: The Booking No label on the form
        JLabel lbookingId = new JLabel("Booking No");
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
        //Create Booking Sub Panel 2: The Booking Date label on the form
        JLabel ldateBooked = new JLabel("Booking Date*"); 
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14)); 
        ldateBooked.setSize(120, 20); 
        ldateBooked.setLocation(20, 50); 
        mainBodySubPanel2.add(ldateBooked);
        
        //Create Booking Sub Panel 2: The Booking Date textbox on the form
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
        //Create Booking Sub Panel 2: The Checkin Date label on the form
        JLabel lcheckInDate = new JLabel("Checkin Date*"); 
        lcheckInDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcheckInDate.setSize(120, 20); 
        lcheckInDate.setLocation(20, 80); 
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
        tcheckInDate.setLocation(150, 80);
        tdateBooked.setNextFocusableComponent(tcheckInDate);
        mainBodySubPanel2.add(tcheckInDate);

        lcheckInDate = new JLabel("yyyy-mm-dd");
        lcheckInDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcheckInDate.setSize(120, 20);
        lcheckInDate.setLocation(260, 80);
        mainBodySubPanel2.add(lcheckInDate);
        
        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Check Out Date label on the form
        JLabel lcheckOutDate = new JLabel("Check Out Date*"); 
        lcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcheckOutDate.setSize(120, 20); 
        lcheckOutDate.setLocation(20, 110); 
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
        tcheckOutDate.setLocation(150, 110);
        tcheckInDate.setNextFocusableComponent(tcheckOutDate);
        mainBodySubPanel2.add(tcheckOutDate);

        lcheckOutDate = new JLabel("yyyy-mm-dd");
        lcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcheckOutDate.setSize(120, 20);
        lcheckOutDate.setLocation(260, 110);
        mainBodySubPanel2.add(lcheckOutDate); 
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Room Type label on the form
        JLabel lroomType = new JLabel("Room Type");
        lroomType.setFont(new Font("Arial", Font.PLAIN, 14));
        lroomType.setSize(120, 20);
        lroomType.setLocation(20, 140);
        mainBodySubPanel2.add(lroomType);

        //----------------------------------------------------------------------------------------------
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
        troomType.setLocation(150, 140); 
        troomType.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox roomTypeComboBox = (JComboBox)e.getSource();
                    Item item = (Item)roomTypeComboBox.getSelectedItem();
                }
            });
        tcheckOutDate.setNextFocusableComponent(troomType);
        mainBodySubPanel2.add(troomType);

        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Room No label on the form
        JLabel lroomNo = new JLabel("Room No");
        lroomNo.setFont(new Font("Arial", Font.PLAIN, 14));
        lroomNo.setSize(120, 20);
        lroomNo.setLocation(20, 170);
        mainBodySubPanel2.add(lroomNo);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Room No textbox on the form
        //Query to retrieve the Room data from the database and populate the Vector object
        try 
        {
            String roomNoQuery = "SELECT r.roomNo, roomType FROM ROOM r WHERE r.roomNo NOT IN " + 
            "(SELECT b.roomNo FROM BOOKING b  WHERE b.checkInDate <= (SELECT CURRENT_DATE) " + 
            "AND b.checkOutDate >= (SELECT CURRENT_DATE)) ORDER BY roomType"; 
            
            PreparedStatement statement = da.con.prepareStatement(roomNoQuery); 
            troomNoModel.addElement(new Item("-1", "--Select a Room Number--" ));
            da.rst = da.stat.executeQuery(roomNoQuery);
            
            while (da.rst.next()) 
            { 
                String id = da.rst.getString(1);
                String desc = da.rst.getString(2);
                troomNoModel.addElement(new Item(id, id + "   " + desc));
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }       

        //override toString() method of the Item class to populate the Combobox with the data from the Vector
        troomNo = new JComboBox(troomNoModel);
        troomNo.setSize(210, 20); 
        troomNo.setLocation(150, 170); 
        troomNo.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox roomNoComboBox = (JComboBox)e.getSource();
                    Item item = (Item)roomNoComboBox.getSelectedItem();
                }
            });
        troomType.setNextFocusableComponent(troomNo);
        mainBodySubPanel2.add(troomNo);

        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Booking Status label on the form
        JLabel lbookingStatus = new JLabel("Booking Status");
        lbookingStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        lbookingStatus.setSize(120, 20);
        lbookingStatus.setLocation(20, 200);
        mainBodySubPanel2.add(lbookingStatus);

        //Create Booking Sub Panel 2: The Booking Status textbox on the form
        tbookingStatus = new JComboBox(); 
        tbookingStatus.setFont(new Font("Arial", Font.PLAIN, 14)); 
        tbookingStatus.setSize(210, 20); 
        tbookingStatus.setLocation(150, 200);
        tbookingStatus.addItem("--Select Booking Status--");
        tbookingStatus.addItem("Guaranteed");
        tbookingStatus.addItem("Unguaranteed");
        tbookingStatus.addItem("active");
        tbookingStatus.addItem("Completed");
        tbookingStatus.addItem("Paid");
        tbookingStatus.addItem("Pending");
        tbookingStatus.addItem("Cancel");
        troomNo.setNextFocusableComponent(tbookingStatus);
        mainBodySubPanel2.add(tbookingStatus);
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Guest No label on the form
        JLabel lguestId = new JLabel("Guest No");
        lguestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lguestId.setSize(120, 20);
        lguestId.setLocation(20, 230);
        mainBodySubPanel2.add(lguestId);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
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
        tguestId.setLocation(150, 230); 
        tguestId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox guestIdComboBox = (JComboBox)e.getSource();
                    Item item = (Item)guestIdComboBox.getSelectedItem();
                }
            });
        tbookingStatus.setNextFocusableComponent(tguestId);
        mainBodySubPanel2.add(tguestId);
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Bill To Guest No label on the form
        JLabel lbillToGuestId = new JLabel("Bill To Guest No");
        lbillToGuestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbillToGuestId.setSize(120, 20);
        lbillToGuestId.setLocation(20, 260);
        mainBodySubPanel2.add(lbillToGuestId);

        //----------------------------------------------------------------------------------------------
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
        tbillToGuestId.setLocation(150, 260); 
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
        
        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The HotelStaffNo label on the form
        JLabel lhotelsatffid = new JLabel("Staff No");
        lhotelsatffid.setFont(new Font("Arial", Font.PLAIN, 14));
        lhotelsatffid.setSize(120, 20);
        lhotelsatffid.setLocation(20, 290);
        mainBodySubPanel2.add(lhotelsatffid);

        //----------------------------------------------------------------------------------------------
        //Create Booking Sub Panel 2: The HotelStaffNo textbox on the form
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
        thotelStaffId.setLocation(150, 290); 
        thotelStaffId.addActionListener (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    JComboBox hotelsatffidComboBox = (JComboBox)e.getSource();
                    Item item = (Item)hotelsatffidComboBox.getSelectedItem();
                }
            });
        tbillToGuestId.setNextFocusableComponent(thotelStaffId);
        mainBodySubPanel2.add(thotelStaffId);

        //---------------------------------------------------------------------------------------------- 
        //Create Booking Sub Panel 2: The Paid label on the form
        JLabel lpaid = new JLabel("Paid (Yes/No)");
        lpaid.setFont(new Font("Arial", Font.PLAIN, 14));
        lpaid.setSize(120, 20);
        lpaid.setLocation(20, 320);
        mainBodySubPanel2.add(lpaid);

        //Create Booking Sub Panel 2: The Paid textbox on the form
        tpaid = new JCheckBox(); 
        tpaid.setFont(new Font("Arial", Font.PLAIN, 14)); 
        tpaid.setSize(50, 20); 
        tpaid.setLocation(150, 320);
        thotelStaffId.setNextFocusableComponent(tpaid);
        mainBodySubPanel2.add(tpaid);
      
        //----------------------------------------------------------------------------------------------        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Save Booking");
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
                   String s2 = tguestId.getSelectedItem().toString().substring(0,tguestId.getSelectedItem().toString().indexOf(' '));  //slected guestId
                   String s3 = troomNo.getSelectedItem().toString().substring(0,troomNo.getSelectedItem().toString().indexOf(' '));  //slected room no
                   String s4 = tbillToGuestId.getSelectedItem().toString().substring(0,tbillToGuestId.getSelectedItem().toString().indexOf(' '));  //slected billToGuestId
                   String s5 = thotelStaffId.getSelectedItem().toString().substring(0,thotelStaffId.getSelectedItem().toString().indexOf(' '));   //slected hotelStaffId                              
                   String s6 = tcheckInDate.getText();                 //entered checkin date
                   String s7 = tcheckOutDate.getText();               //entered checkout date
                   String s8 = troomType.getSelectedItem().toString();      //slected room type
                   String s9 = "";
                if(tpaid.isSelected())
                    s9 = "1";                                    //checked if paid
                else
                    s9 = "0";           
                   String s10 = tbookingStatus.getSelectedItem().toString();      //selected booking status                                                             /
                   String s11 = tdateBooked.getText();                            //entered date booked
                    
                if(!s2.equals("") && !s3.equals("") && !s4.equals("") && !s5.equals("") && !s6.equals(""))
                {
                    String query = "INSERT INTO BOOKING VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
                    da.ps = da.con.prepareStatement(query);
                    da.ps.setString(1, s2);     //selected guest no
                    da.ps.setString(2, s3);     //selected room no
                    da.ps.setString(3, s4);     //selected bill To guest no
                    da.ps.setString(4, s5);     //selected staff no
                    da.ps.setString(5, s6);     ///entered checkin date
                    da.ps.setString(6, s7);     //entered checkout date
                    da.ps.setString(7, s8);     //selected room type
                    da.ps.setString(8, s9);     //selected paid
                    da.ps.setString(9, s10);    //selected booking status
                    da.ps.setString(10, s11);   //entered booking date


                    int success = da.ps.executeUpdate();
                    if (success > 0) 
                    {  
                    query = "SELECT MAX(bookingId) FROM BOOKING WHERE  guestId = ? AND roomNo = ?  AND billToGuestId = ? AND hotelStaffId = ? AND checkInDate = ? AND checkOutDate = ? AND roomType = ? AND paid = ? AND bookingstatus = ? AND dateBooked = ?";
                    da.ps = da.con.prepareStatement(query);
                    da.ps.setString(1, s2);     //selected guest no
                    da.ps.setString(2, s3);     //selected room no
                    da.ps.setString(3, s4);     //selected bill To guest no
                    da.ps.setString(4, s5);     //selected staff no
                    da.ps.setString(5, s6);     ///entered checkin date
                    da.ps.setString(6, s7);     //entered checkout date
                    da.ps.setString(7, s8);     //selected room type
                    da.ps.setString(8, s9);     //selected paid
                    da.ps.setString(9, s10);    //selected booking status
                    da.ps.setString(10, s11);   //entered booking date
                        
                    da.rst = da.ps.executeQuery();
                    while(da.rst.next())
                        {
                           s1 = da.rst.getString(1); 
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog ( null,"Error: Data Not Saved\nPlease check data input and try again");
                        //reset Guest Form Fields
                        tbookingId.setText("");
                        tguestId.setSelectedIndex(1);
                        troomNo.setSelectedIndex(1);
                        tbillToGuestId.setSelectedIndex(1);
                        thotelStaffId.setSelectedIndex(1);
                        tcheckInDate.setText("");
                        tcheckOutDate.setText("");
                        troomType.setSelectedIndex(1);
                        tpaid.setSelected(false); 
                        tbookingStatus.setSelectedIndex(1);   
                        tdateBooked.setText("");
                           
                    }
                    //else
                    //{
                        JOptionPane.showMessageDialog(null, "Data Saved Successfully\n Your Booking Reference Number is: " + s1);
                    //}
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
