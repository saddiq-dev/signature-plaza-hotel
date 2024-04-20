
/**
 * signatureplazahotel.java 27/07/2021
 * The main ToolBox class
 *
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 27 July 2021
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 
import java.io.IOException;

public class signatureplazahotel extends JFrame {
   
    signatureplazahotel() {
    }
    
    public void mainHome() {
        //Setting the Form properties
        setTitle("Hotel Online Management System: Application Console");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 50, 900, 650); //size of the frame
        //setBackground(Color.BLUE);
        setResizable(false); 

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                confirmAndExit();
            }
        });

        //--------------------------------------------------------------------------    
        Action exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent event) {
                confirmAndExit();
            }
        };

        //--------------------------------------------------------------------------    
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Application");
        JMenu menu2 = new JMenu("Help");

        menuBar.add(menu1);
        menuBar.add(menu2);

        //---------------------------------------------------------------------------------- 
        //Adding Components to the File option on the Main MenuBar
        JMenuItem menu11 = new JMenuItem("GuestLogin");
            menu11.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Login.userLogin();
                    setVisible(true);
                }
            });        
            
            JMenuItem menu12 = new JMenuItem("StaffLogin");
            menu12.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    StaffLogin.userStaffLogin();
                    setVisible(true);
                }
            });        
        
        JMenuItem menu13 = new JMenuItem("Register");
            menu13.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Guest g = new Guest();
                    g.manageGuest();
                    setVisible(true);
                }
          
           }); 
           
    

        menu1.add(new JSeparator());
        JMenuItem menu16 = new JMenuItem("Test Connection");
            menu16.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    TestDatabaseConnection tdc = new TestDatabaseConnection();
                    tdc.testMyConnection();
                    //tdc.printResult();
                }
            }); 

        JMenuItem menu14 = new JMenuItem("Print Guest Bill");
     menu14.addActionListener(
       new ActionListener(){
          public void actionPerformed(ActionEvent e) {
        PrintChargesToGuestBill pctgb = new PrintChargesToGuestBill();
        try
        {
             pctgb.PrintChargesToGuestBill();
        }
        catch (Exception ex) 
                {  
               System.out.println(ex);  
        } 
            }
           });      
           
        JMenuItem menu15 = new JMenuItem ("Print Monthly Bill");
    menu15.addActionListener(
          new ActionListener(){
          public void actionPerformed(ActionEvent e) {
        PrintMonthlyBill pmb = new PrintMonthlyBill();
        try
        {
             pmb.PrintMonthlyBill();
        }
        catch (Exception ex) 
                {  
               System.out.println(ex);  
        } 
            }
           });      
           
        //----------------------------------------------------------------------------------        
        menu1.add(menu11);
        menu1.add(menu12);
        menu1.add(menu13);
        menu1.add(new JSeparator()); 
        menu1.add(menu14);
        menu1.add(menu15);
        menu1.add(new JSeparator());
        menu1.add(menu16);
        menu1.add(new JSeparator());    
        menu1.add(exitAction);  

        //----------------------------------------------------------------------------------    
        //Adding Components to the Help option on the Main MenuBar
        JMenuItem menu21 = new JMenuItem("User Guide");
        JMenuItem menu22 = new JMenuItem("Frequently Asked Questions");
        JMenuItem menu23 = new JMenuItem("About Hotel Online Management System");
        JMenuItem menu24 = new JMenuItem("About Development Team");
        JMenuItem menu25 = new JMenuItem("Legal Agreement and Copyright");
        JMenuItem menu26 = new JMenuItem("Hotel Brief");
        
        menu2.add(menu21);
        menu2.add(menu22);
        menu2.add(new JSeparator());
        menu2.add(menu23);
        menu2.add(menu24);
        menu2.add(new JSeparator());
        menu2.add(menu25);
        menu2.add(menu26);

//---------------------------------------------------------------------------------------------     
        menu21.addActionListener (
            new ActionListener() {
                
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        File file = new File("docs/userGuide.pdf");
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists()) desktop.open(file);   
                    }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });

//---------------------------------------------------------------------------------------------     
        menu22.addActionListener (
            new ActionListener() {
                
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        File file = new File("docs/faqs.pdf");
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists()) desktop.open(file);   
                    }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });

//---------------------------------------------------------------------------------------------     
        menu23.addActionListener (
            new ActionListener() {
                
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        File file = new File("docs/aboutTheSystem.pdf");
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists()) desktop.open(file);   
                    }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });

//---------------------------------------------------------------------------------------------     
        menu24.addActionListener (
            new ActionListener() {
                
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        File file = new File("docs/developmentTeam.pdf");
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists()) desktop.open(file);   
                    }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }); 

//---------------------------------------------------------------------------------------------     
        menu25.addActionListener (
            new ActionListener() {
                
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        File file = new File("docs/legalCopyright.pdf");
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists()) desktop.open(file);   
                    }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });         

//---------------------------------------------------------------------------------------------     
        menu26.addActionListener (
            new ActionListener() {
                
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        File file = new File("docs/hotelBrief.pdf");
                        Desktop desktop = Desktop.getDesktop();
                        if(file.exists()) desktop.open(file);   
                    }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });   
            
//----------------------------------------------------------------------------------        
        //Main Body Panel: The middle panel
        JPanel mainBodyPanel = new JPanel();
        mainBodyPanel.setSize(1200,650);
        mainBodyPanel.setBackground(Color.PINK);
        
//---------------------------------------------------------------------------------- 
        //Main Body Sub Panel 0: The top label on the form
        JPanel mainBodySubPanel0 = new JPanel();
        mainBodySubPanel0.setLayout(null);
        mainBodySubPanel0.setBackground(Color.PINK);
        mainBodySubPanel0.setPreferredSize(new Dimension(1200, 40));

        //The title on the form to be placed in mainBodySubPanel0
        JLabel title = new JLabel("Welcome To Signature Plaza Hotel !!"); 
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setForeground(Color.BLUE);
        title.setSize(1200, 35); 
        mainBodySubPanel0.add(title); 
        mainBodyPanel.add(mainBodySubPanel0);       

//----------------------------------------------------------------------------------        
        //Main Body Sub Panel 1: The Backgroung Image sub panel
        ImagePanel mainBodySubPanel1 = new ImagePanel(new ImageIcon("img/hotel7.jpg").getImage());
        mainBodySubPanel1.setBackground(Color.BLUE);
        mainBodySubPanel1.setPreferredSize(new Dimension(750, 550));
        mainBodyPanel.add(mainBodySubPanel1);
        
     
//---------------------------------------------------------------------------------- 
        getContentPane().add(BorderLayout.NORTH, menuBar);      
        getContentPane().add(BorderLayout.CENTER, mainBodyPanel);
    }       
    
//---------------------------------------------------------------------------------- 
    class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

//---------------------------------------------------------------------------------- 
        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            //img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

//---------------------------------------------------------------------------------- 
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

    }

//------------------------------------------------------------------------- 
    void confirmAndExit() {
        if (JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to quit?",
            "Please confirm",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION
            )   {
            System.exit(0);
        }
    }

//-------------------------------------------------------------------------  
    //Main application method
    public static void main(String[]args) {
        signatureplazahotel dt = new signatureplazahotel();
        dt.mainHome();
        dt.setVisible(true);

        
        //String passedInfo = null;
        //Guest g = new Guest();
        //g.manageGuest(passedInfo);
        
        //GuestServices gs = new GuestServices();
        //gs.manageGuestServices(passedInfo);
    }
}