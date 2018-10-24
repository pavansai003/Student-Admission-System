import java.sql.Connection;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.Statement;
public class ConnectMysql {
	 private String user = "root";
	 private String password = "";
	 public Connection con;
	ConnectMysql(String db)
	{
		 String url = "jdbc:mysql://127.0.0.1/"+db;
		 try
	        {
	            con = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected");
	          //Statement st=con.createStatement();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            System.out.println("Unable to connect"+e);
	        }
	}
	public void Disconnect()
	{
		try{
			con.close();
            System.out.println("Disconnected");
		}
		catch(Exception e)
		{
			System.out.println("Unable to disconnect\n"+e);
		}
	}

	
}