import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Dues {

	private ConnectMysql cont=new ConnectMysql("Java1_DB");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dues window = new Dues();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void func(String qry){
		String Query=new String(qry);
		//String Query="SELECT * FROM `Records` WHERE 1 ORDER BY `DOR` ASC";
		try
		{
		Statement st=cont.con.createStatement();
		try
		{
			ResultSet res=st.executeQuery(Query);
			ResultSetMetaData md=res.getMetaData();
			int columnCount=md.getColumnCount();
			int rowCount=res.last()?res.getRow():0;
			if(rowCount!=0)
			{
			System.out.println(columnCount+"\n"+rowCount);
			res.first();
			String[] col=new String[columnCount];
			Vector data=new Vector();
			Object[][] obj=new Object[rowCount][columnCount];
			for(int i=1;i<=columnCount;i++)
			{
				col[i-1]=md.getColumnName(i);
			}
			int j=0;
			System.out.println("not entered");
			do
			{ 
				System.out.println("entered");
				for(int i=1;i<=columnCount;i++)
				{
					System.out.println(res.getString(i));
					obj[j][i-1]=res.getString(i);
				}
				j++;
			}while(res.next());
			JTable table = new JTable(obj, col);
			table.setPreferredScrollableViewportSize(new Dimension(1000,table.getRowHeight()*rowCount));
			for(int i=0;i<columnCount;i++)
			{
				System.out.println(col[i]);
				if(!col[i].equals("DOT")&&!col[i].equals("DOR"))
				{
					System.out.println("someshit");
					table.getColumn(col[i]).setPreferredWidth(50);
				}
				else
				{
					System.out.println("nyc");
					table.getColumn(col[i]).setPreferredWidth(250);
				}
			}
			JScrollPane scrollPane = new JScrollPane(table);
			JPanel panel = new JPanel();
			panel.add(scrollPane);
			JFrame frame = new JFrame();
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.setSize(1100,50*rowCount);
			frame.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"No Warnings Available");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			 JOptionPane.showMessageDialog(null,"Query Failed1233:"+ee);
		}
		}
		catch(Exception sqle)
		{
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null,"Query Failed:"+sqle);
		}
	}
	/**
	 * Create the application.
	 */
	public Dues() {
		func("SELECT * FROM `due` WHERE 1");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */

}
