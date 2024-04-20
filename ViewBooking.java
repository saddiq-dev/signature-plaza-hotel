

/**
 * ViewBooking.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage View Booking Information
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.sql.*;

public class ViewBooking extends JFrame implements ActionListener 
{

    JTextField tbookingId;
    JCheckBox tpaid;
    JComboBox troomType, troomNo, tbookingStatus, tguestId, tbillToGuestId, thotelStaffId;
    JFormattedTextField tdateBooked, tcheckInDate, tcheckOutDate;
    JButton submit, clear;
    String bookingId, dateBooked, checkInDate, checkOutDate, roomType, roomNo, bookingStatus, guestId, billToGuestId, hotelStaffId;
    String passedInfo[] = new String[6];
    Boolean paid;
    
    Vector troomTypeModel = new Vector();
    Vector troomNoModel = new Vector();
    Vector tguestIdModel = new Vector();
    Vector tbillToGuestIdModel = new Vector();
    Vector thotelStaffIdModel = new Vector();
    
    public ViewBooking() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    //---------------------------------------------------------------------------------------------     
    public void viewBooking(String[] info) 
    {  
        //Setting the Form properties
        setTitle("Hotel Online Booking Management System: View Booking");
        setBounds(487, 75, 450, 520); //size of the frame
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
        JLabel title = new JLabel("     View Booking"); 
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25)); 
        title.setForeground(Color.YELLOW);
        title.setSize(450, 35); 
        mainBodySubPanel1.add(title);
        
        passedInfo = info;
        JLabel guestNameLabel = new JLabel("User: " + info[1] + "     ");
        guestNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        guestNameLabel.setVerticalAlignment(JLabel.CENTER);
        guestNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        guestNameLabel.setForeground(Color.BLUE);
        guestNameLabel.setSize(450, 35);
        mainBodySubPanel1.add(guestNameLabel);

        //--------------------------------------------------------------------------------------------- 
        //View Booking Sub Panel 2: View Data Sub Panel 2
        JPanel mainBodySubPanel2 = new JPanel();
        mainBodySubPanel2.setLayout(null);
        mainBodySubPanel2.setPreferredSize(new Dimension(425,360));
        mainBodySubPanel2.setBorder(BorderFactory.createLineBorder(Color.blue));

        
        try 
        {
            String query = "";
            
            if(!info[0].equals(""))
                query = "SELECT * FROM BOOKING WHERE bookingId = ?";
            else
                if(!info[2].equals(""))
                    query = "SELECT * FROM BOOKING WHERE dateBooked = ?";
            else
                if(!info[3].equals("-1"))
                    query = "SELECT * FROM BOOKING WHERE roomType = ?";
            else
                if(!info[4].equals("-1"))
                    query = "SELECT * FROM BOOKING WHERE guestId = ?";
            else
                if(!info[5].equals("-1"))
                    query = "SELECT * FROM BOOKING WHERE billToGuestId = ?";                    
                    
            da.ps = da.con.prepareStatement(query);
            
            if(!info[0].equals(""))
                da.ps.setString(1, info[0]);
            else
                if(!info[2].equals(""))
                    da.ps.setString(1, info[2]);
            else
                if(!info[3].equals(""))
                    da.ps.setString(1, info[3]);
            else
                if(!info[4].equals(""))
                    da.ps.setString(1, info[4]);
            else
                if(!info[5].equals(""))
                    da.ps.setString(1, info[5]);   
            
            da.rst = da.ps.executeQuery(); 
                
            while (da.rst.next())
            {
                bookingId = da.rst.getString(1);
                guestId = da.rst.getString(2);
                roomNo = da.rst.getString(3);
                billToGuestId = da.rst.getString(4);
                hotelStaffId = da.rst.getString(5);
                checkInDate = da.rst.getString(6);
                checkOutDate = da.rst.getString(7);
                roomType = da.rst.getString(8); 
                paid = da.rst.getBoolean(9);
                bookingStatus = da.rst.getString(10);
                dateBooked = da.rst.getString(11);
            }
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        }
                
                
        //---------------------------------------------------------------------------------------------- 
        //View Booking Sub Panel 2: The Booking No label on the form
        JLabel lbookingId = new JLabel("Booking No");
        lbookingId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbookingId.setSize(120, 20);
        lbookingId.setLocation(20, 20);
        mainBodySubPanel2.add(lbookingId);

        //View Booking Sub Panel 2: The Booking No textbox on the form
        tbookingId = new JTextField(); 
        tbookingId.setFont(new Font("Arial", Font.PLAIN, 9)); 
        //tbookingId.setForeground(Color.BLUE);
        tbookingId.setSize(210, 20); 
        tbookingId.setLocation(150, 20);
        tbookingId.setText(bookingId);
        tbookingId.setEditable(false);
        this.addWindowFocusListener(new WindowAdapter() 
        {
            public void windowGainedFocus(WindowEvent e) {
                tbookingId.requestFocusInWindow();
            }
        });
        mainBodySubPanel2.add(tbookingId);

        //----------------------------------------------------------------------------------------------
        //View Booking Sub Panel 2: The Guest No label on the form
        JLabel lguestId = new JLabel("Guest No");
        lguestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lguestId.setSize(120, 20);
        lguestId.setLocation(20, 230);
        mainBodySubPanel2.add(lguestId);
            
        //View Booking Sub Panel 2: The Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        try 
        {
            String id = "";
            String desc = "";
            
            String guestIdQuery = "SELECT guestId, Name FROM GUEST WHERE guestId = ?";
            da.ps = da.con.prepareStatement(guestIdQuery);
            da.ps.setString(1, guestId);
            da.rst = da.ps.executeQuery();
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
                tguestIdModel.addElement(new Item("-1", id + "   " + desc));
            } 

            guestIdQuery = "SELECT guestId, Name FROM GUEST ORDER BY Name"; 
            da.ps = da.con.prepareStatement(guestIdQuery); 
            tguestIdModel.addElement(new Item("-1", "--Select a Guest Number--" ));
            da.rst = da.ps.executeQuery(guestIdQuery);
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
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
        tguestId.setEnabled(false);
        tbookingId.setNextFocusableComponent(tguestId);
        mainBodySubPanel2.add(tguestId);
        
        //----------------------------------------------------------------------------------------------------
        //View Booking Sub Panel 2: The Room No label on the form
        JLabel lroomNo = new JLabel("Room No");
        lroomNo.setFont(new Font("Arial", Font.PLAIN, 14));
        lroomNo.setSize(120, 20);
        lroomNo.setLocation(20, 170);
        mainBodySubPanel2.add(lroomNo);

        //View Booking Sub Panel 2: The Room No textbox on the form
        //Query to retrieve the Room data from the database and populate the Vector object
        try 
        {
            String id = "";
            String desc = "";
            
            String roomNoQuery = "SELECT RoomNo, roomType FROM ROOM WHERE RoomNo = ?";
            da.ps = da.con.prepareStatement(roomNoQuery);
            da.ps.setString(1, roomNo);
            da.rst = da.ps.executeQuery();
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
                troomNoModel.addElement(new Item("-1", id + "   " + desc));
            } 

            roomNoQuery = "SELECT RoomNo, roomType FROM ROOM" ; 

            troomNoModel.addElement(new Item("-1", "--Select a Room Number--" ));
            
            da.ps = da.con.prepareStatement(roomNoQuery); 
            da.rst = da.ps.executeQuery(roomNoQuery);
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
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
        troomNo.setEnabled(false);
        tguestId.setNextFocusableComponent(troomNo);
        mainBodySubPanel2.add(troomNo);
         //--------------------------------------------------------------------
        
        //View Booking Sub Panel 2: The Bill To Guest No label on the form
        JLabel lbillToGuestId = new JLabel("Bill To Guest No");
        lbillToGuestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbillToGuestId.setSize(120, 20);
        lbillToGuestId.setLocation(20, 260);
        mainBodySubPanel2.add(lbillToGuestId);

        //View Booking Sub Panel 2: The Bill To Guest No textbox on the form
        //Query to retrieve the Guest data from the database and populate the Vector object
        try 
        {
            String id = "";
            String desc = "";
                
            String billToGuestIdQuery = "SELECT guestId, Name FROM GUEST WHERE guestId = ?";
            da.ps = da.con.prepareStatement(billToGuestIdQuery);
            da.ps.setString(1, billToGuestId);
            da.rst = da.ps.executeQuery();
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
                tbillToGuestIdModel.addElement(new Item("-1", id + "   " + desc));
            } 
            
            billToGuestIdQuery = "SELECT guestId, Name FROM GUEST ORDER BY Name"; 
            da.ps = da.con.prepareStatement(billToGuestIdQuery); 
            tbillToGuestIdModel.addElement(new Item("-1", "--Select a Guest Number--" ));
            da.rst = da.ps.executeQuery(billToGuestIdQuery);
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
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
        tbillToGuestId.setEnabled(false);
        troomNo.setNextFocusableComponent(tbillToGuestId);
        mainBodySubPanel2.add(tbillToGuestId);
        
       //----------------------------------------------------------------------------------------------------------------------
        //View Booking Sub Panel 2: The HotelStaffNo label on the form
        JLabel lhotelStaffId = new JLabel("Staff No");
        lhotelStaffId.setFont(new Font("Arial", Font.PLAIN, 14));
        lhotelStaffId.setSize(120, 20);
        lhotelStaffId.setLocation(20, 290);
        mainBodySubPanel2.add(lhotelStaffId);

        //View Booking Sub Panel 2: The StaffNo textbox on the form
        //Query to retrieve the HotelStaff data from the database and populate the Vector object
        try 
        {
            String id = "";
            String desc = "";
            
            String hotelStaffIdQuery = "SELECT hotelStaffId, Name FROM HOTELSTAFF WHERE hotelStaffId = ?";
            da.ps = da.con.prepareStatement(hotelStaffIdQuery);
            da.ps.setString(1, hotelStaffId);
            da.rst = da.ps.executeQuery();
            
            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
                thotelStaffIdModel.addElement(new Item("-1", id + "   " + desc));
            }          

            thotelStaffIdModel.addElement(new Item("-1", "--Select a Staff Number--" ));
            hotelStaffIdQuery = "SELECT hotelStaffId, Name FROM HOTELSTAFF ORDER BY Name"; 
            da.ps = da.con.prepareStatement(hotelStaffIdQuery); 
            da.rst = da.ps.executeQuery(hotelStaffIdQuery);

            while (da.rst.next()) 
            { 
                id = da.rst.getString(1);
                desc = da.rst.getString(2);
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
                    JComboBox hotelStaffIdComboBox = (JComboBox)e.getSource();
                    Item item = (Item)hotelStaffIdComboBox.getSelectedItem();
                }
            });
        thotelStaffId.setEnabled(false);
        tbillToGuestId.setNextFocusableComponent(thotelStaffId);
        mainBodySubPanel2.add(thotelStaffId);
        
        //---------------------------------------------------------------------------------------------------------------------------
        //View Booking Sub Panel 2: The Checkin Date label on the form
        JLabel lcheckInDate = new JLabel("Checkin Date*"); 
        lcheckInDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcheckInDate.setSize(120, 20); 
        lcheckInDate.setLocation(20, 80); 
        mainBodySubPanel2.add(lcheckInDate);
        
        //View Booking Sub Panel 2: The Checkin Date textbox on the form
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
        tcheckInDate.setText(checkInDate);
        tcheckInDate.setEditable(false);
        tbillToGuestId.setNextFocusableComponent(tcheckInDate);
        mainBodySubPanel2.add(tcheckInDate);

        lcheckInDate = new JLabel("yyyy-mm-dd");
        lcheckInDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcheckInDate.setSize(120, 20);
        lcheckInDate.setLocation(260, 80);
        mainBodySubPanel2.add(lcheckInDate);
                    
        //----------------------------------------------------------------------------------------------
        //View Booking Sub Panel 2: The Check Out Date label on the form
        JLabel lcheckOutDate = new JLabel("Check Out Date*"); 
        lcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcheckOutDate.setSize(120, 20); 
        lcheckOutDate.setLocation(20, 110); 
        mainBodySubPanel2.add(lcheckOutDate);
        
        //View Booking Sub Panel 2: The Check Out Date textbox on the form
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
        tcheckOutDate.setText(checkInDate);
        tcheckOutDate.setEditable(false);
        tcheckInDate.setNextFocusableComponent(tcheckOutDate);
        mainBodySubPanel2.add(tcheckOutDate);

        lcheckOutDate = new JLabel("yyyy-mm-dd");
        lcheckOutDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcheckOutDate.setSize(120, 20);
        lcheckOutDate.setLocation(260, 110);
        mainBodySubPanel2.add(lcheckOutDate); 
                    
        //---------------------------------------------------------------------------------------------- 
        //View Booking Sub Panel 2: The Room Type label on the form
        JLabel lroomType = new JLabel("Room Type");
        lroomType.setFont(new Font("Arial", Font.PLAIN, 14));
        lroomType.setSize(120, 20);
        lroomType.setLocation(20, 140);
        mainBodySubPanel2.add(lroomType);

        
        //View Booking Sub Panel 2: The Room Type textbox on the form
        //Query to retrieve the Room data from the database and populate the Vector object
        try 
        {
            String roomTypeQuery = "SELECT DISTINCT roomType FROM ROOM ORDER BY roomType"; 
            da.ps = da.con.prepareStatement(roomTypeQuery); 
            troomTypeModel.addElement(new Item("-2", roomType ));
            troomTypeModel.addElement(new Item("-1", "--Select a Room Type--" ));
            da.rst = da.ps.executeQuery(roomTypeQuery);
            
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
        troomType.setEnabled(false);
        tcheckOutDate.setNextFocusableComponent(troomType);
        mainBodySubPanel2.add(troomType);
        
        //------------------------------------------------------------------------------------------------------
        //View Booking Sub Panel 2: The Paid label on the form
        JLabel lpaid = new JLabel("Paid (Yes/No)");
        lpaid.setFont(new Font("Arial", Font.PLAIN, 14));
        lpaid.setSize(120, 20);
        lpaid.setLocation(20, 320);
        mainBodySubPanel2.add(lpaid);

        //View Booking Sub Panel 2: The Paid textbox on the form
        tpaid = new JCheckBox(); 
        tpaid.setFont(new Font("Arial", Font.PLAIN, 14)); 
        tpaid.setSize(50, 20); 
        tpaid.setLocation(150, 320);
        tpaid.setSelected(paid);
        tpaid.setEnabled(false);
        troomType.setNextFocusableComponent(tpaid);
        mainBodySubPanel2.add(tpaid);

        //---------------------------------------------------------------------------------------------- 
        //View Booking Sub Panel 2: The Booking Status label on the form
        JLabel lbookingStatus = new JLabel("Booking Status");
        lbookingStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        lbookingStatus.setSize(120, 20);
        lbookingStatus.setLocation(20, 200);
        mainBodySubPanel2.add(lbookingStatus);

        //View Booking Sub Panel 2: The Booking Status textbox on the form
        tbookingStatus = new JComboBox(); 
        tbookingStatus.setFont(new Font("Arial", Font.PLAIN, 14)); 
        tbookingStatus.setSize(210, 20); 
        tbookingStatus.setLocation(150, 200);
        tbookingStatus.addItem(bookingStatus);
        tbookingStatus.addItem("--Select Booking Status--");
        tbookingStatus.addItem("Guaranteed");
        tbookingStatus.addItem("Unguaranteed");
        tbookingStatus.addItem("active");
        tbookingStatus.addItem("Completed");
        tbookingStatus.addItem("Paid");
        tbookingStatus.addItem("Pending");
        tbookingStatus.addItem("Cancel");
        tbookingStatus.setEnabled(false);
        tpaid.setNextFocusableComponent(tbookingStatus);
        mainBodySubPanel2.add(tbookingStatus);
        
       //---------------------------------------------------------------------------------------------- 
                //View Booking Sub Panel 2: The Booking Date label on the form
        JLabel ldateBooked = new JLabel("Booking Date*"); 
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14)); 
        ldateBooked.setSize(120, 20); 
        ldateBooked.setLocation(20, 50);
        mainBodySubPanel2.add(ldateBooked);

        //View Booking Sub Panel 2: The Booking Date textbox on the form
        MaskFormatter dateBookedMask = null;
        try 
        {
            dateBookedMask = new MaskFormatter("####-##-##"); //the # is for numeric values
            dateBookedMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
            JOptionPane.showMessageDialog(null, "Invalid Search Criteria!! \nPlease Try Again!"); 
            e.printStackTrace();
        } 
                    
        tdateBooked = new JFormattedTextField(dateBookedMask);
        tdateBooked.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tdateBooked.setSize(100, 20); 
        tdateBooked.setLocation(150, 50);
        tdateBooked.setText(dateBooked);
        tdateBooked.setEditable(false);
        tbookingStatus.setNextFocusableComponent(ldateBooked);
        mainBodySubPanel2.add(tdateBooked);        
        
        ldateBooked = new JLabel("yyyy-mm-dd");
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14));
        ldateBooked.setSize(120, 20);
        ldateBooked.setLocation(260, 50);
        mainBodySubPanel2.add(ldateBooked);
            
        //---------------------------------------------------------------------------------- 
        //View Booking Sub Panel 3: Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Update Booking");
        submit.addActionListener(this);
        tdateBooked.setNextFocusableComponent(submit);
        
        clear = new JButton("Reset All Fields");
        clear.addActionListener(this);
        submit.setNextFocusableComponent(clear);

        JButton close = new JButton("Close Form");
        close.addActionListener(e -> {
            this.dispose();
        });

        submit.setNextFocusableComponent(close);
        close.setNextFocusableComponent(tbookingId);

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
                UpdateBooking ub = new UpdateBooking();
                ub.updateBooking(passedInfo);
                setVisible(true);
                this.dispose();
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
    
    //---------------------------------------------------------------------------------- 
    
}