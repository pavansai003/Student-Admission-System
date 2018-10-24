import java.awt.EventQueue;

public class Main {
	public static void main(String[] args)
	{	
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LPortal window = new LPortal();
						//window.frmLogin.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		
	}
}