import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                MainWindow window = new MainWindow("k-means in 3D");
	                window.getFrame().setVisible(true);
	                
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
		

	}

}
