import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FS {

	private ConnectMysql cont=new ConnectMysql("Java1_DB");
	private JFrame frame;
	 private JButton btnOc,btnBc,btnSc,btnSt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FS Window = new FS();
					Window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FS() {
		initialize();
		ocevent();
		bcevent();
		scevent();
		stevent();
		frame.setVisible(true);
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
	private void ocevent(){
		
			btnOc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				func("SELECT * FROM `oc` WHERE 1");
			}
		});
	}
	private void bcevent(){
		btnBc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				func("SELECT * FROM `oc` WHERE 1");
			}
		});
		
	}
	private void scevent(){
		btnSc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				func("SELECT * FROM `sc` WHERE 1");
			}
		});
	}
	private void stevent(){
		btnSt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				func("SELECT * FROM `sc` WHERE 1");
			}
		});
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnOc = new JButton("O.C.");
		
		btnBc = new JButton("B.C.");
		
		btnSc = new JButton("S.C.");
		
		
		btnSt = new JButton("S.T.");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(166)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(btnBc, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(btnSc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(btnSt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
					.addGap(167))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(btnOc)
					.addGap(28)
					.addComponent(btnBc)
					.addGap(38)
					.addComponent(btnSc)
					.addGap(35)
					.addComponent(btnSt)
					.addGap(38))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
