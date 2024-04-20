/**
 * StaffSearchBooking.java   01/08/2021
 * 
 * @author       CTS Invincible Software Developers Ltd
 * @version     v1.0 01 August 2021
 */

/**
 * Class to manage Search Booking Information
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*; 
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.sql.*;

public class StaffSearchBooking extends JFrame implements ActionListener 
{
    
    JTextField tbookingId;
    JComboBox troomType,tbookingStatus,tguestId,tbillToGuestId; 
    JFormattedTextField tdateBooked; 
    JButton submit, clear;
    String passedInfo[] = new String[2];
    Vector troomTypeModel = new Vector();
    Vector tguestIdModel = new Vector();
    Vector tbillToGuestIdModel = new Vector();
    
    
    public StaffSearchBooking() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();
    
    public void StaffSearchBooking(String user) 
    { 
    
        //Setting the Form properties
        setTitle("Hotel Online Booking Management System: Search Booking");
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
        //Search Booking Sub Panel 1: The top label on the form
        JPanel mainBodySubPanel1 = new JPanel();
        mainBodySubPanel1.setLayout(null);
        mainBodySubPanel1.setBackground(Color.BLUE);
        mainBodySubPanel1.setPreferredSize(new Dimension(450, 40));

        //The title on the form to be placed in mainBodySubPanel1
        JLabel title = new JLabel("     Search Booking"); 
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25)); 
        title.setForeground(Color.YELLOW);
        title.setSize(450, 35); 
        mainBodySubPanel1.add(title);
        
        passedInfo[1] = user;
        JLabel guestNameLabel = new JLabel("User: " + user + "     ");
        guestNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        guestNameLabel.setVerticalAlignment(JLabel.CENTER);
        guestNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        guestNameLabel.setForeground(Color.BLUE);
        guestNameLabel.setSize(450, 35);
        mainBodySubPanel1.add(guestNameLabel);

        //--------------------------------------------------------------------------------------------- 
        //Search Booking Sub Panel 2: Search Data Sub Panel 2
        JPanel mainBodySubPanel2 = new JPanel();
        mainBodySubPanel2.setLayout(null);
        mainBodySubPanel2.setPreferredSize(new Dimension(425,380));
        mainBodySubPanel2.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        //Search Booking Sub Panel 2: The Booking No label on the form
        JLabel lsearchNotice = new JLabel("Please enter or select one or more criteria to search by");
        lsearchNotice.setFont(new Font("Arial", Font.PLAIN, 14));
        lsearchNotice.setSize(425, 40);
        lsearchNotice.setHorizontalAlignment(JLabel.CENTER);
        guestNameLabel.setVerticalAlignment(JLabel.CENTER);
        //lsearchNotice.setLocation(20, 20);
        lsearchNotice.setForeground(Color.blue);
        mainBodySubPanel2.add(lsearchNotice);
        
        //---------------------------------------------------------------------------------------------- 
        //Search Booking Sub Panel 2: The Booking No label on the form
        JLabel lbookingId = new JLabel("Booking No");
        lbookingId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbookingId.setSize(120, 20);
        lbookingId.setLocation(20, 40);
        mainBodySubPanel2.add(lbookingId);

        //Search Booking Sub Panel 2: The Booking No textbox on the form
        tbookingId = new JTextField(); 
        tbookingId.setFont(new Font("Arial", Font.PLAIN, 9)); 
        tbookingId.setSize(210, 20); 
        tbookingId.setLocation(150, 40);
        this.addWindowFocusListener(new WindowAdapter() 
        {
            public void windowGainedFocus(WindowEvent e) {
                tbookingId.requestFocusInWindow();
            }
        });
        mainBodySubPanel2.add(tbookingId);
        //----------------------------------------------------------------------------------------------
        //Search Booking Sub Panel 2: The Booking Date label on the form
        JLabel ldateBooked = new JLabel("Booking Date"); 
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14)); 
        ldateBooked.setSize(120, 20); 
        ldateBooked.setLocation(20, 70); 
        mainBodySubPanel2.add(ldateBooked);
        
        //Search Booking Sub Panel 2: The Booking Date textbox on the form
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
        tdateBooked.setLocation(150, 70);
        tbookingId.setNextFocusableComponent(tdateBooked);
        mainBodySubPanel2.add(tdateBooked);        

        ldateBooked = new JLabel("yyyy-mm-dd");
        ldateBooked.setFont(new Font("Arial", Font.PLAIN, 14));
        ldateBooked.setSize(120, 20);
        ldateBooked.setLocation(260, 70);
        mainBodySubPanel2.add(ldateBooked);

        //----------------------------------------------------------------------------------------------
        //Search Booking Sub Panel 2: The Room Type label on the form
        JLabel lroomType = new JLabel("Room Type");
        lroomType.setFont(new Font("Arial", Font.PLAIN, 14));
        lroomType.setSize(120, 20);
        lroomType.setLocation(20, 100);
        mainBodySubPanel2.add(lroomType);

        //Search Booking Sub Panel 2: The Room Type textbox on the form
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
        troomType.setLocation(150, 100); 
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
        //Search Booking Sub Panel 2: The Guest No label on the form
        JLabel lguestId = new JLabel("Guest No");
        lguestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lguestId.setSize(120, 20);
        lguestId.setLocation(20, 130);
        mainBodySubPanel2.add(lguestId);

        //Search Booking Sub Panel 2: The Guest No textbox on the form
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
        tguestId.setLocation(150, 130); 
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
        //Search Booking Sub Panel 2: The Bill To Guest No label on the form
        JLabel lbillToGuestId = new JLabel("Bill To Guest No");
        lbillToGuestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lbillToGuestId.setSize(120, 20);
        lbillToGuestId.setLocation(20, 160);
        mainBodySubPanel2.add(lbillToGuestId);

        //Search Booking Sub Panel 2: The Bill To Guest No textbox on the form
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
        tbillToGuestId.setLocation(150, 160); 
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
        //Search Booking Sub Panel 3: Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Search For Booking");
        submit.addActionListener(this);
        tbillToGuestId.setNextFocusableComponent(submit);
        
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
    
    //---------------------------------------------------------------------------------- 
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == submit)
        {
            String s1 = tbookingId.getText();
            String s2 = tguestId.getSelectedItem().toString();
            String s3 = tbillToGuestId.getSelectedItem().toString();
            String s4 = troomType.getSelectedItem().toString();
            String s5 = tdateBooked.getText();

            try 
            {
                try
                {
                    if(!s1.equals("") )
                    {
                        StaffViewBooking svb = new StaffViewBooking();
                        String[] svbinfo = new String[6];
                        svbinfo[0] = s1;
                        svbinfo[1] = passedInfo[1];
                        svbinfo[2] = s2;
                        svbinfo[3] = s3;
                        svbinfo[4] = s4;
                        svbinfo[5] = s5;
                        svb.StaffViewBooking(svbinfo);
                        setVisible(true);
                        this.dispose();
                    }
                }
                catch (Exception ex) 
                {  
                    JOptionPane.showMessageDialog(null, "No Data input!! \nPlease enter or select data and try again!"); 
                    //System.out.println(ex);  
                }                  
            }
            catch (Exception ex) 
            {  
                System.out.println(ex);  
            }
        }
        else 
            if (e.getSource() == clear)
            {
                tbookingId.setText("");
                tguestId.setSelectedIndex(0);
                tbillToGuestId.setSelectedIndex(0);
                troomType.setSelectedIndex(0);
                tdateBooked.setText("");
                
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