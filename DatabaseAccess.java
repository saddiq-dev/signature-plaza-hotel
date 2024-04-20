
/**
 * This class creates a database object to load drivers and perform queries. 
 * Upon creation of an instance of this class a connection to the database is 
 * established using a data source. From an instance of this class objects of 
 * the Connection, Statement and ResultSet Interface classes can be obtained.
 * 
 * @author       CTS Invincible Software Developers Ltd
 * @version     v1.0 27 July 2021
 */

/**
 * Provides the API for accessing and processing data stored in a 
 * data source .
 */
import java.sql.*;

//connection to the MySQL Database
public class DatabaseAccess
{
    /**
     * Connection object
     */
 
    protected Connection con = null;

    /**
     * An object used for executing a static SQL statement and returning the 
     * results it produces.
     */    
    protected Statement stat = null;

    /**
     * An object that maintains a cursor pointing to its current row of data
     */
    protected ResultSet rst = null;
    
    protected PreparedStatement  ps = null;
    
    //protected SearchBooking sbinfo = null;

    /**
     * This constructor connects to a MySQL database. It creates instances
     * of the Statments and ResultSet classes to be used by other classes.
     */
    
    DatabaseAccess()
    {
         createConnection();
    }
    
    //Apache Server on MAMP, HotelBMS is the name of our database
    private final String URL = "jdbc:mysql://localhost/signatureplazahoteldms";

    /**
     *Connection information for the MYSQL database on server
     *@exception Exception:if no connection was found.
     */
    public void createConnection()
    {
        try
        {
            /** Load Driver */
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            /** Returns a database connection from the currently active connection provider */
            
            con = DriverManager.getConnection(URL, "root", "");

            /** 
             * Creates a Statement object that will generate ResultSet objects with the given 
             * type and concurrency.
             */
            
            stat = con.createStatement();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
}