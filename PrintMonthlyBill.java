
/**
 * PrintMonthlyBill.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Printing a Monthly Bill.
 */

import java.io.File;  
import java.io.PrintWriter;
import java.io.FileWriter; 
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class PrintMonthlyBill
{
    public void PrintMonthlyBill() throws Exception {  

        //Protected Java Database Connection
        DatabaseAccess da = new DatabaseAccess();
        
        try
        {
            //String data = "Testing file output stream";
            //String data1 = "Test Data written to file...";
            String billId = "";
            String guestId = "";
            String startDate = "";
            String endDate = "";
            String totalAmount = "";
            String paid = "";
            
            
            //Decalare the name and file path
            String fileName = "C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\MonthlyBill.txt";
            
            //Set file printing parameters
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            //Get data from the database
            String staffQuery = "SELECT * FROM MonthlyBill ORDER BY billId";
            da.ps = da.con.prepareStatement(staffQuery); 
            da.rst = da.ps.executeQuery();
            
            //PrintMonthlyBill Header
            pw.println(String.format("%1$60s", " PrintMonthlyBill"));
            
            //Output Heading
            pw.println(String.format("%1$5s", "|") + "---------------------------------------------------------------------------------------------------------------------------------------------|");
            pw.println(String.format("%1$5s", "|") + String.format("%1$-5s", " ") + String.format("%1$-10s", "billId") + String.format("%1$-5s", "|") + String.format("%1$-20s", "guestId")  + String.format("%1$-5s", "|") + String.format("%1$-20s", "Start Date") + String.format("%1$-5s", "|") + String.format("%1$-25s", "End Date") + String.format("%1$-5s", "|") + String.format("%1$-15s", "Total Amount") + String.format("%1$-5s", "|") + String.format("%1$-10s", "Paid"));// + String.format("%1$12s", "|"));
            pw.println(String.format("%1$5s", "|") + "---------------------------------------------------------------------------------------------------------------------------------------------|");

           //Output data for the file
            while (da.rst.next()) 
            { 
                billId = String.format("%1$-10s", da.rst.getString(1));
                guestId = String.format("%1$-20s", da.rst.getString(2));
                startDate = String.format("%1$-25s", da.rst.getString(3));
                endDate = String.format("%1$-15s", da.rst.getString(4));
                totalAmount = String.format("%1$-20s", da.rst.getString(5));
                paid = String.format("%1$-20s", da.rst.getString(5));
                //phoneno = String.format("%1$-20s", da.rst.getString(6));
                //usertype = String.format("%1$-17s", da.rst.getString(7));
                
                pw.print(String.format("%1$5s", "|"));
                pw.print(String.format("%1$-5s", " "));
                pw.print(billId);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(guestId);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(startDate);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(endDate);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(totalAmount);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(paid);
                pw.print(String.format("%1$-5s", "|"));
                //pw.print(address);
                //pw.print(String.format("%1$5s", "|"));
                pw.println();            
            
            }
            
            pw.println(String.format("%1$5s", "|") + "---------------------------------------------------------------------------------------------------------------------------------------------|");

            pw.flush();
            pw.close();            
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
    }
}
