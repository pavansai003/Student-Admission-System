import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage {

	private JFrame frame;
	JButton btnRegisterStudent,btnFeeStructure,btnFeePayment;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
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
	public MainPage() {
		initialize();
		frame.setVisible(true);
		Rsevent();
		Fsevent();
		Fpevent();
		
	}
	
	private void Rsevent(){
		btnRegisterStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Register window = new Register();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		
	}
	private void Fsevent(){
		btnFeeStructure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FS Window = new FS();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		
	}
	private void Fpevent(){
		btnFeePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FeePayment window = new FeePayment();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

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
		frame.getContentPane().setLayout(null);
		
		btnRegisterStudent = new JButton("Register Student");
		btnRegisterStudent.setBounds(139, 30, 166, 25);
		frame.getContentPane().add(btnRegisterStudent);
		
		btnFeeStructure = new JButton("Fee Structure");
		btnFeeStructure.setBounds(139, 67, 166, 25);
		frame.getContentPane().add(btnFeeStructure);
		
		JButton btnDues = new JButton("Dues");
		btnDues.setBounds(139, 104, 166, 25);
		frame.getContentPane().add(btnDues);
		
		JButton btnStudentDetails = new JButton("Student Details");
		btnStudentDetails.setBounds(139, 141, 166, 25);
		frame.getContentPane().add(btnStudentDetails);
		
		btnFeePayment = new JButton("Fee Payment");

		btnFeePayment.setBounds(139, 178, 166, 25);
		frame.getContentPane().add(btnFeePayment);
	}

}
