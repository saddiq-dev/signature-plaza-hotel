/**
 * TestDatabaseConnection.java 27/07/2021
 * 
 * @author      CTS Invincible Software Developers Ltd
 * @version     v1.0 27 July 2021
 */

/**
 * Provides the API for accessing and processing data stored in a data source.
 */

import java.util.*; 
import java.sql.*;

public class TestDatabaseConnection
{
    DatabaseAccess da = new DatabaseAccess();
    
    //Constructor for objects of class TestDatabaseConnection
    public TestDatabaseConnection()
    { 
        System.out.println("Congratulations:\nYour connection test to the SignaturePlazaHotel database was successful!!!");

    }

    //Method to test MySQL database connection and table data access 
    public void testMyConnection()
    {
        //Test for Database Connectivity via Apache Server
        try 
        {
            String testConnection = "select * from room;";
            PreparedStatement ps = da.con.prepareStatement(testConnection); 
            da.rst = ps.executeQuery(testConnection);
        }
        catch (Exception ex) 
        {  
            System.out.println(ex);  
        } 
    }
    
    public void printResult()
    {
        try
        {
            while (da.rst.next()) 
            { 
                System.out.println("The contents of the table you requested follows...\n");
                System.out.println(da.rst.getString(1) + "   " + da.rst.getString(2) + "   " + da.rst.getString(3));
            } 
            
            da.rst.close();
            da.con.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex); 
        }
    } 
    
//-------------------------------------------------------------------------  
    //Main application method
    public static void main(String[]args) {
        TestDatabaseConnection tdc = new TestDatabaseConnection();
        tdc.testMyConnection(); 
    }
    
}