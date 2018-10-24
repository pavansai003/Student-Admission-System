import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Window;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Choice;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LPortal {

	private JFrame frmLogin;
	private JTextField userField;
	private JPasswordField passwordField;
	public String uname;
	public String pass;
	JButton btnLogin = new JButton("Login");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public LPortal() {
		initialize();
		event();
	    frmLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void event()
	{
		    btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uname=userField.getText();
				char[] passchar=passwordField.getPassword();
				pass=new String(passchar);
				ConnectMysql conct=new ConnectMysql("Java1_DB");
				try
				{
				Statement st=conct.con.createStatement();
				String query="SELECT * FROM `Login` WHERE `Username`='"+uname+"' AND `Password`='"+pass+"'";
				//System.out.println(query);
				ResultSet res=st.executeQuery(query);
				if(res.next())
				{
					JOptionPane.showMessageDialog(null,"Login Successfull");
					frmLogin.dispose();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainPage window = new MainPage();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Login Failed");
				}
				}
				catch(Exception es)
				{
					System.out.println("statement failes"+es);
				}
			}
		});
	}
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		userField = new JTextField();
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addComponent(lblPassword))
							.addGap(54)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField)
								.addComponent(userField)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(172)
							.addComponent(btnLogin)))
					.addContainerGap(133, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(userField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addComponent(btnLogin)
					.addGap(41))
		);
		frmLogin.getContentPane().setLayout(groupLayout);
	}
}