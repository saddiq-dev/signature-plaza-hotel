
/**
 * Guest.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Registering a Guest.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.text.DecimalFormat;
import java.sql.*; 
import java.io.File; 
//import java.io.IOException;

public class Guest extends JFrame implements ActionListener 
{
    JTextField tGuestId, tname, temail;
    JPasswordField tpassword, tconfirmPassword;
    JComboBox tUserType;
    JFormattedTextField tphoneNo, tcreditCardNo, tcreditcardexpdate, tCreditCardCVC, tdiscount, tregistrationDate;
    JTextArea taddress, tadditionalGuestDetails, tgeneralNotes;
    JButton submit, clear;
    
    //---------------------------------------------------------------------------------------------
    public Guest() 
    {
    }

    //Protected Java Database Connection
    DatabaseAccess da = new DatabaseAccess();
    
    //---------------------------------------------------------------------------------------------     
    public void manageGuest() {  

        //Setting the Guest Form properties
        setTitle("Hotel Online Booking Management System: Guest Services");
        setBounds(262, 50, 900, 425); //size of the frame
        setResizable(false);
        
        //---------------------------------------------------------------------------------------------     
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Help");
        
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        
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
        //Guest Main Body Panel: The middle component of the JFrame
        JPanel mainBodyPanel = new JPanel();
        mainBodyPanel.setSize(900,450);

        //--------------------------------------------------------------------------------------------- 
        //Guest Sub Panel 1: The top label on the form
        JPanel mainBodySubPanel1 = new JPanel();
        mainBodySubPanel1.setLayout(null);
        mainBodySubPanel1.setBackground(Color.YELLOW);
        mainBodySubPanel1.setPreferredSize(new Dimension(825, 40));

        //The title on the form to be placed in mainBodySubPanel1
        JLabel title = new JLabel("Hotel Online Booking Management System: Guest Management"); 
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.BLUE);
        title.setSize(825, 35); 
        mainBodySubPanel1.add(title);        
        
        //--------------------------------------------------------------------------------------------- 
        //Guest Sub Panel 2: Guest Data Sub Panel 2
        JPanel mainBodySubPanel2 = new JPanel();
        mainBodySubPanel2.setLayout(null);
        mainBodySubPanel2.setPreferredSize(new Dimension(830,240));
        //mainBodySubPanel2.setBackground(Color.BLUE);
        mainBodySubPanel2.setBorder(BorderFactory.createLineBorder(Color.blue));
        
        //---------------------------------------------------------------------------------------------- 
        //Guest Sub Panel 2: The Guest No label on the form
        JLabel lGuestId = new JLabel("Guest ID");
        lGuestId.setFont(new Font("Arial", Font.PLAIN, 14));
        lGuestId.setSize(120, 20);
        lGuestId.setLocation(20, 20);
        mainBodySubPanel2.add(lGuestId);

        //Guest Sub Panel 2: The Guest No textbox on the form
        JTextField tGuestId = new JTextField(); 
        tGuestId.setFont(new Font("Arial", Font.PLAIN, 9)); 
        tGuestId.setBackground(Color.LIGHT_GRAY);
        tGuestId.setText("System Assigned");
        tGuestId.setForeground(Color.BLUE);
        tGuestId.setEditable(false);
        tGuestId.setSize(210, 20); 
        tGuestId.setLocation(150, 20);
        mainBodySubPanel2.add(tGuestId);

        //----------------------------------------------------------------------------------------------
        //Guest Sub Panel 2: The Guest Name label on the form
        JLabel lname = new JLabel("Name*"); 
        lname.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lname.setSize(120, 20); 
        lname.setLocation(20, 50); 
        mainBodySubPanel2.add(lname);
        
        //Guest Sub Panel 2: The Guest Name textbox on the form
        tname = new JTextField(); 
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(210, 20); 
        tname.setLocation(150, 50); 
        this.addWindowFocusListener(new WindowAdapter() 
        {
            public void windowGainedFocus(WindowEvent e) {
                tname.requestFocusInWindow();
            }
        });
        mainBodySubPanel2.add(tname);
        //-------------------------------------------------------------------------------------------------------
        
        //Guest Sub Panel 2: The Guest Email label on the form
        JLabel lemail = new JLabel("Email*"); 
        lemail.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lemail.setSize(120, 20); 
        lemail.setLocation(20, 80); 
        mainBodySubPanel2.add(lemail);
        
        //Guest Sub Panel 2: The Guest Email textbox on the form
        temail = new JTextField(); 
        temail.setFont(new Font("Arial", Font.PLAIN, 15)); 
        temail.setSize(210, 20); 
        temail.setLocation(150, 80);
        tname.setNextFocusableComponent(temail);
        mainBodySubPanel2.add(temail);  
        
        //----------------------------------------------------------------------------------------------
        
        //Guest Sub Panel 2: The Guest Password label on the form
        JLabel lpassword = new JLabel("Password*"); 
        lpassword.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lpassword.setSize(120, 20); 
        lpassword.setLocation(20, 110); 
        mainBodySubPanel2.add(lpassword);
        
        //Guest Sub Panel 2: The Guest Password textbox on the form
        tpassword = new JPasswordField(); 
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tpassword.setSize(210, 20); 
        tpassword.setLocation(150 ,110);
        temail.setNextFocusableComponent(tpassword);
        mainBodySubPanel2.add(tpassword);   
        
        //------------------------------------------------------------------------------------------------------
        
        //Guest Sub Panel 2: The Guest Address label on the form
        JLabel laddress = new JLabel("Address*"); 
        laddress.setFont(new Font("Arial", Font.PLAIN, 14)); 
        laddress.setSize(120, 20); 
        laddress.setLocation(20, 140); 
        mainBodySubPanel2.add(laddress);
        
        //Guest Sub Panel 2: The Guest Address textbox on the form
        //JPanel p = new JPanel();
        taddress = new JTextArea(); 
        taddress.setFont(new Font("Arial", Font.PLAIN, 15)); 
        taddress.setSize(210, 50); 
        taddress.setLocation(150, 140);
        taddress.setBackground(Color.WHITE);
        taddress.setLineWrap(true); 
        taddress.setEditable(true); 
        taddress.addKeyListener(new KeyAdapter() //Prevent Tabbing within the TestArea
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_TAB)
                {
                    e.consume();
                    KeyboardFocusManager.
                        getCurrentKeyboardFocusManager().focusNextComponent();
                }

                if (e.getKeyCode() == KeyEvent.VK_TAB
                &&  e.isShiftDown())
                {
                    e.consume();
                    KeyboardFocusManager.
                        getCurrentKeyboardFocusManager().focusPreviousComponent();
                }
            }
        });
        
        JScrollPane taddressScroll = new JScrollPane(taddress);
        taddressScroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        taddressScroll.setSize(210, 50);
        taddressScroll.setLocation(150, 140);
        tname.setNextFocusableComponent(taddress);       
        mainBodySubPanel2.add(taddressScroll);        
        
        //----------------------------------------------------------------------------------------------
        
        //Guest Sub Panel 2: The Guest Type label on the form
        JLabel lUserType = new JLabel("UserType*"); 
        lUserType.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lUserType.setSize(120, 20); 
        lUserType.setLocation(20, 200); 
        mainBodySubPanel2.add(lUserType);
        
        //Guest Sub Panel 2: The Guest Type Combobox on the form
        tUserType = new JComboBox();
        tUserType.setSize(210, 20);
        tUserType.setLocation(150, 200);
        tUserType.addItem("");
        tUserType.addItem("Corporate");
        tUserType.addItem("Individual");
        tUserType.addItem("Guest");
        temail.setNextFocusableComponent(tUserType);
        mainBodySubPanel2.add(tUserType);
        
        //-----------------------------------------------------------------------------------------------------------------
        //Guest Sub Panel 2: The Guest phoneNo label on the form
        JLabel lphoneNo = new JLabel("Phone Number*"); 
        lphoneNo.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lphoneNo.setSize(120, 20); 
        lphoneNo.setLocation(450, 20); 
        mainBodySubPanel2.add(lphoneNo);

        //Guest Sub Panel 2: The Guest phoneNo textbox on the form
        MaskFormatter tphoneNoMask = null;
        try 
        {
          tphoneNoMask = new MaskFormatter("(###) ###-####"); //the # is for numeric values
          tphoneNoMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tphoneNo = new JFormattedTextField(tphoneNoMask);
        tphoneNo.setFont(new Font("Arial", Font.PLAIN, 15));
        tphoneNo.setSize(210, 20);
        tphoneNo.setLocation(600, 20);
        tUserType.setNextFocusableComponent(tphoneNo);
        mainBodySubPanel2.add(tphoneNo);
             
        //----------------------------------------------------------------------------------------------
        //Guest Sub Panel 2: The Guest Credit Card Number label on the form
        JLabel lcreditCardNo = new JLabel("Credit Card No*"); 
        lcreditCardNo.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcreditCardNo.setSize(120, 20); 
        lcreditCardNo.setLocation(450, 50); 
        mainBodySubPanel2.add(lcreditCardNo);

        //Guest Sub Panel 2: The Guest Credit Card Number textbox on the form
        MaskFormatter creditCardNoMask = null;
        try 
        {
          creditCardNoMask = new MaskFormatter("####-####-####-####"); //the # is for numeric values
          creditCardNoMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tcreditCardNo = new JFormattedTextField(creditCardNoMask);
        tcreditCardNo.setFont(new Font("Arial", Font.PLAIN, 15));
        tcreditCardNo.setSize(210, 20);
        tcreditCardNo.setLocation(600, 50);
        tUserType.setNextFocusableComponent(tcreditCardNo);
        mainBodySubPanel2.add(tcreditCardNo);
        
        //----------------------------------------------------------------------------------------------
        //Guest Sub Panel 2: The Guest Credit Card Expiry Date label on the form
        JLabel lcreditcardexpdate = new JLabel("CC Expiry Date*");
        lcreditcardexpdate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcreditcardexpdate.setSize(120, 20);
        lcreditcardexpdate.setLocation(450, 80);
        mainBodySubPanel2.add(lcreditcardexpdate);

        //Guest Sub Panel 2: The Guest Credit Card Expiry Date textbox on the form
        MaskFormatter creditcardexpdateMask = null;
        try 
        {
          creditcardexpdateMask = new MaskFormatter("####-##-##"); //the # is for numeric values
          creditcardexpdateMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tcreditcardexpdate = new JFormattedTextField(creditcardexpdateMask);
        tcreditcardexpdate.setFont(new Font("Arial", Font.PLAIN, 15));
        tcreditcardexpdate.setSize(100, 20);
        tcreditcardexpdate.setLocation(600, 80);
        tcreditCardNo.setNextFocusableComponent(tcreditcardexpdate);
        mainBodySubPanel2.add(tcreditcardexpdate);

        lcreditcardexpdate = new JLabel("yyyy-mm-dd");
        lcreditcardexpdate.setFont(new Font("Arial", Font.PLAIN, 14));
        lcreditcardexpdate.setSize(120, 20);
        lcreditcardexpdate.setLocation(710, 80);
        mainBodySubPanel2.add(lcreditcardexpdate);

        //----------------------------------------------------------------------------------------------
        //Guest Sub Panel 2: The Guest Credit Card Code label on the form
        JLabel lCreditCardCVC = new JLabel("CC CVC*");
        lCreditCardCVC.setFont(new Font("Arial", Font.PLAIN, 14));
        lCreditCardCVC.setSize(120, 20);
        lCreditCardCVC.setLocation(450, 110);
        mainBodySubPanel2.add(lCreditCardCVC);
        
        //Guest Sub Panel 2: The Guest Credit Card Code textbox on the form
        MaskFormatter CreditCardCVCMask = null;
        try 
        {
          CreditCardCVCMask = new MaskFormatter("###"); //the # is for numeric values
          CreditCardCVCMask.setPlaceholderCharacter(' ');
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        } 
        
        tCreditCardCVC = new JFormattedTextField(CreditCardCVCMask);
        tCreditCardCVC.setFont(new Font("Arial", Font.PLAIN, 15));
        tCreditCardCVC.setSize(45, 20);
        tCreditCardCVC.setLocation(600, 110);
        tcreditcardexpdate.setNextFocusableComponent(tCreditCardCVC);
        mainBodySubPanel2.add(tCreditCardCVC);

        //---------------------------------------------------------------------------------- 
        //Guest Sub Panel 2: The Guest Discount label on the form
        JLabel ldiscount = new JLabel("Discount (%)*"); 
        ldiscount.setFont(new Font("Arial", Font.PLAIN, 14)); 
        ldiscount.setSize(120, 20); 
        ldiscount.setLocation(450, 140); 
        mainBodySubPanel2.add(ldiscount);

        //Guest Sub Panel 2: The Guest Discount textbox on the form
        double lastDouble = 0.0;
        tdiscount = new JFormattedTextField(new DecimalFormat("###.00"));
        tdiscount.setFont(new Font("Arial", Font.PLAIN, 15));
        tdiscount.setSize(45, 20);
        tdiscount.setLocation(600, 140);
        tdiscount.setText(lastDouble + "");
        tdiscount.selectAll();
        tCreditCardCVC.setNextFocusableComponent(tdiscount);
        mainBodySubPanel2.add(tdiscount);
        
        //-------------------------------------------------------------------------------------------
        
        //Guest Sub Panel 4: Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Save");
        submit.addActionListener(this);
       
        
        clear = new JButton("Reset");
        clear.addActionListener(this);
        submit.setNextFocusableComponent(clear);

        JButton close = new JButton("Close");
        close.addActionListener(e -> {
            this.dispose();
        });

        clear.setNextFocusableComponent(close);
        close.setNextFocusableComponent(tname);

        buttonPanel.add(submit);
        
        buttonPanel.add(clear);
        buttonPanel.add(close);        
        clear.addActionListener(this);
        
        
        mainBodyPanel.add(mainBodySubPanel1);
        mainBodyPanel.add(mainBodySubPanel2);
       
        
        getContentPane().add(BorderLayout.NORTH, menuBar);
        getContentPane().add(BorderLayout.CENTER, mainBodyPanel);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        setVisible(true);

    }

    //---------------------------------------------------------------------------------------------
    public static String formatCard(String cardNumber) {
        if (cardNumber == null) return null;
        char delimiter = ' ';
        return cardNumber.replaceAll(".{4}(?!$)", "$0" + delimiter);
    }
    
    //---------------------------------------------------------------------------------------------    
    //Method to action Guest events 
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == submit)
        {
            String s1 = null;
            String s2 = tname.getText();
            String s3 = temail.getText();
            String s4 = tpassword.getText();
            String s5 = taddress.getText();
            String s6 = tUserType.getSelectedItem().toString();
            String s7 = tphoneNo.getText();
            String s8 = tcreditCardNo.getText();
            String s9 = tcreditcardexpdate.getText();
            String s10 = tCreditCardCVC.getText();
            String s11 = tdiscount.getText();
            
            if (!s2.equals("") && !s3.equals("") && !s4.equals("") && !s5.equals("") && !s6.equals("") && !s7.equals("") && !s8.equals("") && !s9.equals("") && !s10.equals("") && !s11.equals(""))
           
                    try 
                    {
                        String query = "INSERT INTO GUEST VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                        PreparedStatement ps = da.con.prepareStatement(query);
                        ps.setString(1, s1);    //Guest ID
                        ps.setString(1, s2);    //Name
                        ps.setString(2, s3);    //Email
                        ps.setString(3, s4);    //Password
                        ps.setString(4, s5);    //Address
                        ps.setString(5, s6);    //Guest Type
                        ps.setString(6, s7);    //phoneNo
                        ps.setString(7, s8);    //Credit Card No
                        ps.setString(8, s9);    //Credit Card Expiry Date
                        ps.setString(9, s10);   //Credit Card Code
                        ps.setString(10, s11);  //Discount
                        
                        int success = ps.executeUpdate();  
                        if (success > 0) 
                        {  
                            JOptionPane.showMessageDialog(null,"Data Saved Successfully");
                            //reset Guest Form Fields
                            tname.setText("");
                            temail.setText("");
                            tpassword.setText("");
                            taddress.setText("");
                            tUserType.setSelectedIndex(0);
                            tphoneNo.setText("");
                            tcreditCardNo.setText("");
                            tcreditcardexpdate.setText("");
                            tCreditCardCVC.setText("");
                            tdiscount.setText("");
                          
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error: Data Not Saved\nPlease check data input and try again");
                        }
                    }
                    catch (Exception ex) 
                    {  
                        System.out.println(ex);  
                    }  
            
   
            else
            {
                JOptionPane.showMessageDialog(null, "Error: Missing data\nPlease ensure all required data fields are populated and try again");
            }
        }
        else
        {  
            tname.setText("");
            temail.setText("");
            tpassword.setText("");
            taddress.setText("");
            tUserType.setSelectedIndex(0);
            tphoneNo.setText("");
            tcreditCardNo.setText("");
            tcreditcardexpdate.setText("");
            tCreditCardCVC.setText("");
            tdiscount.setText("");
            

        }
    }
}
