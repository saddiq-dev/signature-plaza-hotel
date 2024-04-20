/**
 * PrintChargesToGuestBill.java   01/08/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 10 August 2021
 */

/**
 * Class to manage Printing Charges To Guest Bill.
 */

import java.io.File;  
import java.io.PrintWriter;
import java.io.FileWriter; 
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class PrintChargesToGuestBill
{
    public void PrintChargesToGuestBill() throws Exception {  

        //Protected Java Database Connection
        DatabaseAccess da = new DatabaseAccess();
        
        try
        {
            //String data = "Testing file output stream";
            //String data1 = "Test Data written to file...";
            String chargeId = "";
            String bookingId = "";
            String hotelStaffId = "";
            String chargeType = "";
            String amountCharged = "";
            
            
            //Decalare the name and file path
            String fileName = "C:\\Users\\Saddiq Abdul Qadir\\Downloads\\Networking labs\\Coding\\SignaturePlazaHotelDMS\\PrintChargesToGuestBill.txt";
            
            //Set file printing parameters
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            //Get data from the database
            String staffQuery = "SELECT * FROM CHARGES ORDER BY chargeId";
            da.ps = da.con.prepareStatement(staffQuery); 
            da.rst = da.ps.executeQuery();
            
            //PrintChargesToGuestBill Header
            pw.println(String.format("%1$60s", " PrintChargesToGuestBill"));
            
            //Output Heading
            pw.println(String.format("%1$5s", "|") + "---------------------------------------------------------------------------------------------------------------------------------------------|");
            pw.println(String.format("%1$5s", "|") + String.format("%1$-5s", " ") + String.format("%1$-10s", "Charge ID") + String.format("%1$-5s", "|") + String.format("%1$-20s", "Booking Id")  + String.format("%1$-5s", "|") + String.format("%1$-20s", "Staff ID") + String.format("%1$-5s", "|") + String.format("%1$-25s", "Charge Type") + String.format("%1$-5s", "|") + String.format("%1$-15s", "Amount Charged")); //+ String.format("%1$-5s", "|") + String.format("%1$-10s", "Role") + String.format("%1$12s", "|"));
            pw.println(String.format("%1$5s", "|") + "---------------------------------------------------------------------------------------------------------------------------------------------|");

           //Output data for the file
            while (da.rst.next()) 
            { 
                chargeId = String.format("%1$-10s", da.rst.getString(1));
                bookingId = String.format("%1$-20s", da.rst.getString(2));
                hotelStaffId = String.format("%1$-25s", da.rst.getString(3));
                chargeType = String.format("%1$-15s", da.rst.getString(4));
                amountCharged = String.format("%1$-20s", da.rst.getString(5));
                //phoneno = String.format("%1$-20s", da.rst.getString(6));
                //usertype = String.format("%1$-17s", da.rst.getString(7));
                
                pw.print(String.format("%1$5s", "|"));
                pw.print(String.format("%1$-5s", " "));
                pw.print(chargeId);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(bookingId);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(hotelStaffId);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(chargeType);
                pw.print(String.format("%1$-5s", "|"));
                pw.print(amountCharged);
                pw.print(String.format("%1$-5s", "|"));
                ///pw.print(usertype);
                //pw.print(String.format("%1$-5s", "|"));
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
