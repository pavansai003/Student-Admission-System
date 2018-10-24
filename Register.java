import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private ConnectMysql conct=new ConnectMysql("Java1_DB");
	private JFrame frmRegistration;
	private JTextField name;
	private JTextField addr;
	private JTextField pno;
	private JTextField mname;
	private JTextField fname;
	private JComboBox branch,cat;
	private JComboBox year;
	JButton btnSubmit,btnBack;
	private JLabel lblPay;
	private JTextField pay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
		frmRegistration.setVisible(true);
		Rsevent();
		back();
	}
	private void back(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
	}	
	public void Rsevent()
	{
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String nme=name.getText();
					String phone=pno.getText();
					String adrs=addr.getText();
					String mthrname=mname.getText();
					String fthrname=fname.getText();
					String brnch=(String) branch.getSelectedItem();
					String yer=(String) year.getSelectedItem();
					String catg=(String) cat.getSelectedItem();
					String pm=(String) pay.getText();
					int pmd=Integer.parseInt(pm);
					String query="INSERT INTO `2015-2019`(`Name`, `Branch`, `Category`, `Father name`, `Mother name`, `Phone number`, `Year`, `Address`) VALUES "
							+ "('"
							+ nme+"','"
							+ brnch+"','"
							+ catg+"','"
							+ fthrname+"','"
							+ mthrname+"','"
							+ phone+"','"
							+ yer+"','"
							+ adrs+"')";
					Statement ste=conct.con.createStatement();
					ste.executeUpdate(query);
					String categ=new String(catg);
					if(categ.equals("BC")||categ.equals("OC"))
						categ="oc";
					else
						categ="sc";
					query="SELECT `Total` FROM `"+categ+"` WHERE `Years`='"+yer+"'";
					JOptionPane.showMessageDialog(null, query);
					Statement ste2=conct.con.createStatement();
					ResultSet res=ste2.executeQuery(query);	
					res.first();
					String ttl=res.getString(1);
					JOptionPane.showMessageDialog(null, ttl);
					int total=Integer.parseInt(ttl);
					query="INSERT INTO `due`(`Name`, `Branch`, `Year`, `Total fee`, `Fee paid`, `Due`) VALUES "
							+ "('"
							+ nme+"','"
							+ brnch+"','"
							+ yer+"','"
							+ total+"','"
							+ pm+"','"
							+ (total-pmd)+"')";
					Statement ste3=conct.con.createStatement();
					ste3.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Resgistered successfully");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistration = new JFrame();
		frmRegistration.setTitle("Registration");
		frmRegistration.setBounds(100, 100, 563, 495);
		frmRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(119, 409, 83, 25);
		JLabel lblStudentRegistration = new JLabel("STUDENT REGISTRATION");
		lblStudentRegistration.setBounds(141, 12, 166, 15);
		btnBack = new JButton("Back");
		btnBack.setBounds(334, 409, 98, 25);
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setBounds(68, 47, 39, 15);
		
		name = new JTextField();
		name.setBounds(236, 45, 114, 19);
		name.setColumns(10);
		
		JLabel lblAddres = new JLabel("ADDRESS");
		lblAddres.setBounds(105, 314, 64, 15);
		
		addr = new JTextField();
		addr.setBounds(236, 310, 114, 19);
		addr.setColumns(10);
		
		JLabel lblCourse = new JLabel("BRANCH");
		lblCourse.setBounds(68, 81, 56, 15);
		
		branch = new JComboBox();
		branch.setBounds(248, 76, 67, 24);
		branch.setModel(new DefaultComboBoxModel(new String[] {"CSE", "ECE", "EEE", "IT", "MECH"}));
		
		JLabel lblPhno = new JLabel("PH.NO");
		lblPhno.setBounds(119, 281, 45, 15);
		
		pno = new JTextField();
		pno.setBounds(236, 279, 114, 19);
		pno.setColumns(10);
		
		JLabel lblMotherName = new JLabel("MOTHER NAME");
		lblMotherName.setBounds(68, 205, 101, 15);
		
		mname = new JTextField();
		mname.setBounds(226, 203, 114, 19);
		mname.setColumns(10);
		
		JLabel lblFatherNam = new JLabel("FATHER NAME");
		lblFatherNam.setBounds(74, 166, 95, 15);
		
		fname = new JTextField();
		fname.setBounds(226, 166, 114, 19);
		fname.setColumns(10);
		
		JLabel lblCategorry = new JLabel("CATEGORY");
		lblCategorry.setBounds(68, 133, 72, 15);
		
		cat = new JComboBox();
		cat.setBounds(248, 118, 48, 24);
		cat.setModel(new DefaultComboBoxModel(new String[] {"OC", "BC", "SC", "ST"}));
		frmRegistration.getContentPane().setLayout(null);
		frmRegistration.getContentPane().add(lblStudentRegistration);
		frmRegistration.getContentPane().add(lblCourse);
		frmRegistration.getContentPane().add(lblNewLabel);
		frmRegistration.getContentPane().add(lblCategorry);
		frmRegistration.getContentPane().add(lblMotherName);
		frmRegistration.getContentPane().add(lblFatherNam);
		frmRegistration.getContentPane().add(lblAddres);
		frmRegistration.getContentPane().add(lblPhno);
		frmRegistration.getContentPane().add(name);
		frmRegistration.getContentPane().add(cat);
		frmRegistration.getContentPane().add(branch);
		frmRegistration.getContentPane().add(addr);
		frmRegistration.getContentPane().add(mname);
		frmRegistration.getContentPane().add(fname);
		frmRegistration.getContentPane().add(pno);
		frmRegistration.getContentPane().add(btnSubmit);
		frmRegistration.getContentPane().add(btnBack);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(89, 245, 70, 15);
		frmRegistration.getContentPane().add(lblYear);
		
		year = new JComboBox();
		year.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		
		year.setBounds(263, 234, 52, 24);
		frmRegistration.getContentPane().add(year);
		
		lblPay = new JLabel("Pay");
		lblPay.setBounds(94, 357, 70, 15);
		frmRegistration.getContentPane().add(lblPay);
		
		pay = new JTextField();
		pay.setBounds(236, 355, 114, 19);
		frmRegistration.getContentPane().add(pay);
		pay.setColumns(10);
	}
}
