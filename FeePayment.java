import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FeePayment {

	private ConnectMysql conct=new ConnectMysql("Java1_DB");
	private JFrame frame;
	private JTextField name;
	private JTextField branch;
	private JTextField amount;
	private JButton btnSubmit,btnBack;
	private JLabel lblYear;
	private JTextField year;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeePayment window = new FeePayment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FeePayment() {
		initialize();
		backevent();
		submitbtn();
		frame.setVisible(true);
	}
	private void backevent(){
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
	
	public void submitbtn()
	{
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
				String nme=name.getText();
				String brnch=branch.getText();
				String yer=year.getText();
				String amn=amount.getText();
				int amnt=Integer.parseInt(amn);
				Statement ste=conct.con.createStatement();
				String query="UPDATE `due` SET `Fee paid`=`Fee paid` + "+amnt+" , `Due`= `Due`-"+amnt+" WHERE `Name`='"+nme+"' AND `Branch`='"+brnch+"' AND `Year`='"+yer+"'";
				ste.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Paid successfully");
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblFeePayment = new JLabel("Fee Payment");
		
		JLabel lblName = new JLabel("Name");
		
		name = new JTextField();
		name.setColumns(10);
		
		JLabel lblBranch = new JLabel("Branch");
		
		branch = new JTextField();
		branch.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		
		amount = new JTextField();
		amount.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		
		btnBack = new JButton("Back");
		
		lblYear = new JLabel("Year");
		
		year = new JTextField();
		year.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(183)
							.addComponent(lblFeePayment))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(104)
								.addComponent(btnSubmit)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBack))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(84)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblName)
											.addComponent(lblBranch)
											.addComponent(lblAmount))
										.addGap(87)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(branch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblYear, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(20))))))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblFeePayment)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBranch)
						.addComponent(branch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAmount)
						.addComponent(amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblYear))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(28))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
