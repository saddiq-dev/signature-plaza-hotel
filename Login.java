
/**
 * Login.java 27/07/2021
 * The Login class
 *
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 27 July 2021
 */

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*; 
import java.io.File; 
import java.io.IOException;

public class Login extends JFrame implements ActionListener {  
 
    JTextField tguestID;  
    JButton submit, clear;  
    JPasswordField tPassword;  
    
    DatabaseAccess da = new DatabaseAccess();

    Login() {  
    }
    
    public void userValidate() {
        //Setting the Form properties
        setTitle("Hotel Online Management System: System Login");
        setBounds(500, 200, 400, 250);//size of the frame
        //setBackground(Color.YELLOW);
        setResizable(false);        

        //---------------------------------------------------------------------------------------------     
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Help");
        menuBar.add(menu1);
        menuBar.add(menu2);     

        //Adding Components to the File option on the Main MenuBar
        JMenuItem menu11 = new JMenuItem("Close");
        menu1.add(menu11);

        //---------------------------------------------------------------------------------------------             
        menu11.addActionListener(e -> {
            this.dispose();
        });
        
        JMenuItem menu21 = new JMenuItem("User Guide");
        JMenuItem menu22 = new JMenuItem("Frequently Asked Questions");
        JMenuItem menu23 = new JMenuItem("About Extra Curricular Event Management");
        
        menu2.add(menu21);
        menu2.add(menu22);
        menu2.add(menu23);  
                    
        //---------------------------------------------------------------------------------------------         
        //Main Body Panel: The middle panel
        JPanel mainBodyPanel = new JPanel();
        mainBodyPanel.setSize(400,180);

        //--------------------------------------------------------------------------------------------- 
        //Main Body Sub Panel 0: The top label on the form
        JPanel mainBodySubPanel0 = new JPanel();
        mainBodySubPanel0.setLayout(null);
        mainBodySubPanel0.setBackground(Color.YELLOW);
        mainBodySubPanel0.setPreferredSize(new Dimension(400, 40));

        //The title on the form to be placed in mainBodySubPanel0
        JLabel title = new JLabel("Guest Login"); 
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25)); 
        title.setForeground(Color.BLUE);
        title.setSize(400, 35); 
        mainBodySubPanel0.add(title); 
        mainBodyPanel.add(mainBodySubPanel0);   
        
        //--------------------------------------------------------------------------------------------- 
        //Login Panel: The middle panel
        JPanel mainBodySubPanel1 = new JPanel();
        mainBodySubPanel1.setLayout(null);
        mainBodySubPanel1.setPreferredSize(new Dimension(390,110));

        //---------------------------------------------------------------------------------------------- 
        //Login Panel: The student ID label on the form
        JLabel lguestID = new JLabel("Guest No"); 
        lguestID.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lguestID.setSize(120, 20); 
        lguestID.setLocation(20, 20); 
        mainBodySubPanel1.add(lguestID);
        
        //Login Panel: The student ID textbox on the form
        tguestID = new JTextField(); 
        tguestID.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tguestID.setSize(170, 20); 
        tguestID.setLocation(150, 20); 

        tguestID.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){

                char ch = e.getKeyChar();
                if(Character.isDigit(ch)){
                }
                else{
                    JOptionPane.showMessageDialog(null, "Only numbers are allowed!");
                    tguestID.setText("");
                }
            }
        }); 
    
        mainBodySubPanel1.add(tguestID);

        //---------------------------------------------------------------------------------- 
        //Login Panel: The create password label on the form
        JLabel lcreatePassword = new JLabel("Password"); 
        lcreatePassword.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lcreatePassword.setSize(120, 20); 
        lcreatePassword.setLocation(20, 50); 
        mainBodySubPanel1.add(lcreatePassword);
        
        //Login Panel: The create password textbox on the form
        tPassword = new JPasswordField(); 
        tPassword.setSize(170, 20); 
        tPassword.setLocation(150, 50); 
        mainBodySubPanel1.add(tPassword);
        mainBodyPanel.add(mainBodySubPanel1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        submit = new JButton("Login");  
        clear = new JButton("Clear");
        buttonPanel.add(submit);
        buttonPanel.add(clear);
        submit.addActionListener(this);  
        clear.addActionListener(this); 

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
            int x = 0;  
            String s1 = tguestID.getText();         //entered guest id
            char[] s2 = tPassword.getPassword();    //entered password
            String s3 = new String(s2);             //entered password as String
            String s4 = null;                       //database guest id
            String s5 = null;                       //database password
            String s6 = null;                       //database guest role
            String s7 = null;                       //database guest name

            //String info = new String();
            
            if(!tguestID.getText().isEmpty() && !tPassword.getText().isEmpty()) 
            {  
                try  
                {  
                    tguestID.setText("");
                    tPassword.setText(""); 
                    String query = "SELECT guestID, name, password, userType FROM GUEST WHERE guestID = ? AND password = ?";
                    PreparedStatement ps = da.con.prepareStatement(query); 
                    ps.setString(1, s1);  
                    ps.setString(2, s3);
                    
                    String user = null;

                    //Validation Code to load the application
                    da.rst = ps.executeQuery();
                    if(da.rst.next()) {
                        s4 = da.rst.getString(1);   //Guest No
                        s7 = da.rst.getString(2);   //Name
                        s5 = da.rst.getString(3);   //Password
                        s6 = da.rst.getString(4);   //Guest Type

                        if(s1.equals(s4) && s3.equals(s5) && s6.equals("Individual")) {
                            //JOptionPane.showMessageDialog(null, "Data Match Successfull");
                            GuestServices gs = new GuestServices();
                            user = s7;
                            gs.guestServices(user);
                            setVisible(true);
                            this.dispose();
                        }
                        else if((s1.equals(s4)) && (s3.equals(s6)) && (s5.equals("2"))) {
                            //JOptionPane.showMessageDialog(submit, "Data Match Successfull");
                            //AppHomeStudent.main(info);
                            //AppHomeStudent.studentPortal(info);
                            //setVisible(true);
                            //this.dispose();                         
                        }
                        else if((s1.equals(s4)) && (s3.equals(s6)) && (s5.equals("2"))) {
                            //JOptionPane.showMessageDialog(submit, "Data Match Successfull");
                            //AppHomeEventOrg.main(info);
                            //setVisible(true);
                            //this.dispose();                         
                        }                       
                    } 
                    da.rst.close();
                    da.con.close();
                }  
                catch (Exception ex) {  
                    System.out.println(ex);  
                }  
            }  
            else {  
                JOptionPane.showMessageDialog(null, "Invalid Credentials!! Please Try Again!");  
            }   
          }   
          else {
            JOptionPane.showMessageDialog(null, "Invalid Credentials!! Please Try Again!"); 
            tguestID.setText("");  
            tPassword.setText(""); 
          }  
    }  

    //----------------------------------------------------------------------------------
    public static void userLogin() {  
        Login l = new Login();
        l.userValidate();
    }  
}  