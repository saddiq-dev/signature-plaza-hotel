/**
 *GuestServices.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Creating GuestServices.
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

public class GuestServices extends JFrame implements ActionListener 
{

    JButton submit; 
    
    /**
     * Constructor for objects of class GuestServices
     */
    public GuestServices()
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();

    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    //defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);
    
    //---------------------------------------------------------------------------------------------     
    public void guestServices(String user) 
    {  

        //Setting the Guest Form properties
        setTitle("Hotel Online Booking Management System: Guest Services");
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
                    SearchBooking sb = new SearchBooking();
                    sb.searchBooking(user);
                    setVisible(true);
                }
            });
        
        JMenuItem menu312 = new JMenuItem("Make a Booking");
            menu312.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CreateBooking cb = new CreateBooking();
                    cb.createBooking(user);
                    setVisible(true);
                }
            });
        
        JMenuItem menu313 = new JMenuItem("Update a Booking");
            menu313.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SearchBooking sb = new SearchBooking();
                    sb.searchBooking(user);
                    setVisible(true);
                }
            });
        
        JMenuItem menu314 = new JMenuItem("Delete a Booking");
        
        menu31.add(menu311);
        menu31.add(menu312);
        menu31.add(menu313);
        menu31.add(menu314);
        menu3.add(menu31);

        JMenu menu32 = new JMenu("Guest Services");
        JMenuItem menu321 = new JMenuItem("Check In Guest");        
        JMenuItem menu322 = new JMenuItem("Check Out Guest");
        JMenuItem menu323 = new JMenuItem("Print Guest Bill");
        JMenuItem menu324 = new JMenuItem("Order External Services");        
        
        menu32.add(menu321);
        menu32.add(menu322);
        menu32.add(menu323);
        menu32.add(menu324);
        menu3.add(menu32);
    
        //---------------------------------------------------------------------------------------------         
        //Main Body Panel: The middle panel
        JPanel mainBodyPanel = new JPanel();
        mainBodyPanel.setSize(900,450);
        //mainBodyPanel.setBackground(Color.BLUE);  

        //--------------------------------------------------------------------------------------------- 
        //Guest Services Sub Panel 1: The top label on the form
        JPanel mainBodySubPanel1 = new JPanel();
        mainBodySubPanel1.setLayout(null);
        mainBodySubPanel1.setBackground(Color.WHITE);
        mainBodySubPanel1.setPreferredSize(new Dimension(900, 40));

        //The title on the form to be placed in mainBodySubPanel1
        JLabel title = new JLabel("     Guest Services Portal: Guest Management"); 
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20)); 
        title.setForeground(Color.BLUE);
        title.setSize(900, 35); 
        mainBodySubPanel1.add(title);
        
        JLabel guestNameLabel = new JLabel("User: " + user + "     ");
        guestNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        guestNameLabel.setVerticalAlignment(JLabel.CENTER);
        guestNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        guestNameLabel.setForeground(Color.BLUE);
        guestNameLabel.setSize(900, 35);
        mainBodySubPanel1.add(guestNameLabel);
        mainBodyPanel.add(mainBodySubPanel1);
        
        //---------------------------------------------------------------------------------------------
        //Guest Services Sub Panel 1: Form Spacer        
        JPanel mainBodySubPanelSpacer = new JPanel();
        mainBodySubPanelSpacer.setLayout(new GridLayout());
        mainBodySubPanelSpacer.setPreferredSize(new Dimension(900, 10));        

        //--------------------------------------------------------------------------------------------- 
        //Guest Services Sub Panel 2: Create Guest Booking Sub Panel 2
        JPanel mainBodySubPanel2 = new JPanel();
        mainBodySubPanel2.setLayout(new GridLayout(1,0));
        mainBodySubPanel2.setPreferredSize(new Dimension(890, 175));
        mainBodySubPanel2.setBorder(BorderFactory.createTitledBorder(""));       

        //--------------------------------------------------------------------------------------------- 
        //Guest Services Sub Panel 2: Create Guest Booking Sub Panel 21
        JPanel mainBodySubPanel21 = new JPanel();
        mainBodySubPanel21.setLayout(new GridLayout());
        mainBodySubPanel21.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel21.setBorder(BorderFactory.createTitledBorder("Make Booking"));
        
        //Guest Services Sub Panel 2: Create Guest Booking Image
        JButton createBooking = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\Create.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
        createBooking.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    CreateBooking cb = new CreateBooking();
                    cb.createBooking(user);
                    setVisible(true);
                }
            });

        mainBodySubPanel21.add(createBooking);
        mainBodySubPanel2.add(mainBodySubPanel21);

        //--------------------------------------------------------------------------------------------- 
        //Guest Services Sub Panel 2: View Guest Booking Sub Panel 22
        JPanel mainBodySubPanel22 = new JPanel();
        mainBodySubPanel22.setLayout(new GridLayout());
        mainBodySubPanel22.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel22.setBorder(BorderFactory.createTitledBorder("Search View Update Booking"));

        //Guest Services Sub Panel 2: View Guest Booking Image
        JButton viewBooking = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\Booking.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
        viewBooking.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    SearchBooking sb = new SearchBooking();
                    sb.searchBooking(user);
                    setVisible(true);
                }
            });
        
        mainBodySubPanel22.add(viewBooking);
        mainBodySubPanel2.add(mainBodySubPanel22);

        //--------------------------------------------------------------------------------------------- 
        //Guest Services Sub Panel 3: Change Guest Booking Sub Panel 23
        JPanel mainBodySubPanel23 = new JPanel();
        mainBodySubPanel23.setLayout(new GridLayout());
        mainBodySubPanel23.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel23.setBorder(BorderFactory.createTitledBorder("Update Booking"));

        //Guest Services Sub Panel 2: View Guest Booking Image
        JButton changeBooking = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\UpdateBooking.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
            changeBooking.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SearchBooking sb = new SearchBooking();
                    sb.searchBooking(user);
                    setVisible(true);
                }
            });
        
        mainBodySubPanel23.add(changeBooking);
        mainBodySubPanel2.add(mainBodySubPanel23);

        //--------------------------------------------------------------------------------------------- 
        //Guest Services Sub Panel 2: Cancel Guest Booking Sub Panel 24
        JPanel mainBodySubPanel24 = new JPanel();
        mainBodySubPanel24.setLayout(new GridLayout());
        mainBodySubPanel24.setPreferredSize(new Dimension(40, 40));
        mainBodySubPanel24.setBorder(BorderFactory.createTitledBorder(""));

        //Guest Services Sub Panel 2: Cancel Guest Booking Image
        JButton cancelBooking = new JButton(new ImageIcon(((new ImageIcon("C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\Img\\Arrow.png")).getImage()).getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH)));
        cancelBooking.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                }
            });
        
        mainBodySubPanel24.add(cancelBooking);
        mainBodySubPanel2.add(mainBodySubPanel24);
        mainBodyPanel.add(mainBodySubPanel2);
        mainBodyPanel.add(mainBodySubPanelSpacer);
       
        
        
        //Guest Services Button Panel
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