
/**
 * StaffServices.java   27/07/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 27 July 2021
 */

/**
 * Class to manage Hotel Staff Services Information
 */

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.*; 
import java.io.File; 
import java.io.IOException;

public class StaffServices extends JFrame implements ActionListener 
{

    JButton submit, retrieveConfirmedBooking, retrieveTentativeBooking,retrieveCancelledBooking, retrieveDeferredBooking, retrieveClosedBooking; 
    
    /**
     * Constructor for objects of class StaffServices
     */
    public StaffServices()
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    //defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);
    
    //---------------------------------------------------------------------------------------------     
    public void staffServices(String user) 
    {  

        //Setting the Staff Form properties
        setTitle("Hotel Online Booking Management System: Staff Services");
        setBounds(262, 50, 900, 420); //size of the frame
        setResizable(false);
        
        //---------------------------------------------------------------------------------------------     
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Modules");
        JMenu menu4 = new JMenu("Reports");
        JMenu menu5 = new JMenu("Tools");
        JMenu menu6 = new JMenu("Help");
        
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        menuBar.add(menu5);
        menuBar.add(menu6);
        
        //--------------------------------------------------------------------------------------------- 
        //Adding Components to the File option on the Main MenuBar
        JMenuItem menu11 = new JMenuItem("Close");
        menu1.add(menu11);

        menu11.addActionListener(e -> {
            this.dispose();
        });

        //--------------------------------------------------------------------------------------------- 
        //Add Components to the Edit option on the Main MenuBar
        JMenuItem menu21 = new JMenuItem("Copy");
        JMenuItem menu22 = new JMenuItem("Paste");
        JMenuItem menu23 = new JMenuItem("Search");
        menu2.add(menu21);
        menu2.add(menu22);
        menu2.add(new JSeparator());
        menu2.add(menu23);

        //--------------------------------------------------------------------------------------------- 
        //Add Components to the Modules option on the Main MenuBar
        JMenu menu31 = new JMenu("Booking");
        JMenuItem menu311 = new JMenuItem("Search for a Booking");
            menu311.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) {
                    StaffSearchBooking ssb = new StaffSearchBooking();
                    ssb.StaffSearchBooking(user);
                    setVisible(true);
                }
            });
        
        JMenuItem menu312 = new JMenuItem("Make a Booking");
            menu312.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StaffCreateBooking scb = new StaffCreateBooking();
                    scb.StaffCreateBooking(user);
                    setVisible(true);
                }
            });
        
        JMenuItem menu313 = new JMenuItem("Update a Booking");
            menu313.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    StaffSearchBooking ssb = new StaffSearchBooking();
                    ssb.StaffSearchBooking(user);
                    setVisible(true);
                }
            });
        
        JMenuItem menu314 = new JMenuItem("Charge a Booking");
            menu314.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Charges c = new Charges();
                    c.Charges(user);
                    setVisible(true);
                }
            });
        
        
        menu31.add(menu311);
        menu31.add(menu312);
        menu31.add(menu313);
        menu31.add(menu314);
        menu3.add(menu31);

        JMenu menu32 = new JMenu("Staff Services");
        JMenuItem menu321 = new JMenuItem("Check In Staff");        
        JMenuItem menu322 = new JMenuItem("Check Out Staff");
        JMenuItem menu323 = new JMenuItem("Print Staff Bill");
        JMenuItem menu324 = new JMenuItem("Order External Services");        
        
        menu32.add(menu321);
        menu32.add(menu322);
        menu32.add(menu323);
        menu32.add(menu324);
        menu3.add(menu32);
        
        
        //------------------------------------------------------------------------------------------------
        


        //---------------------------------------------------------------------------------------------         
        //Main Body Panel: The middle panel
        JPanel mainBodyPanel = new JPanel();
        mainBodyPanel.setSize(900,450);
        //mainBodyPanel.setBackground(Color.BLUE);  

        //--------------------------------------------------------------------------------------------- 
        //Staff Services Sub Panel 1: The top label on the form
        JPanel mainBodySubPanel1 = new JPanel();
        mainBodySubPanel1.setLayout(null);
        mainBodySubPanel1.setBackground(Color.WHITE);
        mainBodySubPanel1.setPreferredSize(new Dimension(900, 40));

        //The title on the form to be placed in mainBodySubPanel1
        JLabel title = new JLabel("     Staff Services Portal: Staff Management"); 
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20)); 
        title.setForeground(Color.BLUE);
        title.setSize(900, 35); 
        mainBodySubPanel1.add(title);
        
        JLabel StaffNameLabel = new JLabel("User: " + user + "     ");
        StaffNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        StaffNameLabel.setVerticalAlignment(JLabel.CENTER);
        StaffNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        StaffNameLabel.setForeground(Color.BLUE);
        StaffNameLabel.setSize(900, 35);
        mainBodySubPanel1.add(StaffNameLabel);
        mainBodyPanel.add(mainBodySubPanel1);
        
        //---------------------------------------------------------------------------------------------
        //Staff Services Sub Panel 1: Form Spacer        
        JPanel mainBodySubPanelSpacer = new JPanel();
        mainBodySubPanelSpacer.setLayout(new GridLayout());
        mainBodySubPanelSpacer.setPreferredSize(new Dimension(900, 10));        

        //--------------------------------------------------------------------------------------------- 
        //Staff Services Sub Panel 2: Create Staff Booking Sub Panel 2
        JPanel mainBodySubPanel2 = new JPanel();
        mainBodySubPanel2.setLayout(new GridLayout(1,0));
        mainBodySubPanel2.setPreferredSize(new Dimension(890, 175));
        mainBodySubPanel2.setBorder(BorderFactory.createTitledBorder(""));       

        //--------------------------------------------------------------------------------------------- 
        //Staff Services Sub Panel 2: Create Staff Booking Sub Panel 21
        JPanel mainBodySubPanel21 = new JPanel();
        mainBodySubPanel21.setLayout(new GridLayout());
        mainBodySubPanel21.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel21.setBorder(BorderFactory.createTitledBorder("Make Booking"));
        
        //Staff Services Sub Panel 2: Create Staff Booking Image
        JButton createBooking = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\StaffBooking.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
        createBooking.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    StaffCreateBooking scb = new StaffCreateBooking();
                    scb.StaffCreateBooking(user);
                    setVisible(true);
                }
            });

        mainBodySubPanel21.add(createBooking);
        mainBodySubPanel2.add(mainBodySubPanel21);

        //--------------------------------------------------------------------------------------------- 
        //Staff Services Sub Panel 2: View Staff Booking Sub Panel 22
        JPanel mainBodySubPanel22 = new JPanel();
        mainBodySubPanel22.setLayout(new GridLayout());
        mainBodySubPanel22.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel22.setBorder(BorderFactory.createTitledBorder("Search View Update Booking"));

        //Staff Services Sub Panel 2: View Staff Booking Image
        JButton viewBooking = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\ViewBooking.jpg")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
        viewBooking.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    StaffSearchBooking ssb = new StaffSearchBooking();
                    ssb.StaffSearchBooking(user);
                    setVisible(true);
                }
            });
        
        mainBodySubPanel22.add(viewBooking);
        mainBodySubPanel2.add(mainBodySubPanel22);

        //--------------------------------------------------------------------------------------------- 
        //Staff Services Sub Panel 3: Change Staff Booking Sub Panel 23
        JPanel mainBodySubPanel23 = new JPanel();
        mainBodySubPanel23.setLayout(new GridLayout());
        mainBodySubPanel23.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel23.setBorder(BorderFactory.createTitledBorder("Monthly Bill"));

        //Staff Services Sub Panel 2: View Staff Booking Image
        JButton MonthlyBill = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\Bill.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
            MonthlyBill.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MonthlyBill mb = new MonthlyBill();
                    mb.MonthlyBill(user);
                    setVisible(true);
                }
            });
        
        mainBodySubPanel23.add(MonthlyBill);
        mainBodySubPanel2.add(mainBodySubPanel23);

        //--------------------------------------------------------------------------------------------- 
        //Staff Services Sub Panel 2: Cancel Staff Booking Sub Panel 24
        JPanel mainBodySubPanel24 = new JPanel();
        mainBodySubPanel24.setLayout(new GridLayout());
        mainBodySubPanel24.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel24.setBorder(BorderFactory.createTitledBorder("Charge to Booking"));

        //Staff Services Sub Panel 2: Cancel Staff Booking Image
        JButton Charges = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\Charge.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
           Charges.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Charges c = new Charges();
                    c.Charges(user);
                    setVisible(true);
                }
            });
        
        mainBodySubPanel24.add(Charges);
        mainBodySubPanel2.add(mainBodySubPanel24);
        mainBodyPanel.add(mainBodySubPanel2);
        mainBodyPanel.add(mainBodySubPanelSpacer);
       
        //---------------------------------------------------------------------------------------------        
        
        
        //---------------------------------------------------------------------------------------------- 
        
        
        //---------------------------------------------------------------------------------------------- 
        //Staff Services Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        
        submit = new JButton("Close");  
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                //AppHomeEvent.this.dispose();
            }
        });

        
        submit.addActionListener(this);  
        //buttonPanel.add(clear);
        //clear.addActionListener(this);         
        
        
        buttonPanel.add(submit);        
        

        getContentPane().add(BorderLayout.NORTH, menuBar);
        getContentPane().add(BorderLayout.CENTER, mainBodyPanel);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        setVisible(true);

    }
    //---------------------------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) 
    {

    }

}