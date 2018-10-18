import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class MainWindow {
	Timer t;
	private JFrame frame;
	private JPanel panel;
	int k = 10;
	int iloscCentroidow = 15;
	int iloscPunktow = 1000;
	Punkt arr[];
	Centroid arrCent[];
	
	

	public MainWindow(String title) {

		t = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				getFrame().repaint();
			}
		});
		
		initialize(title);
	}

	private void initialize(String title) {
		
		arr = new Punkt[iloscPunktow];
		Punkt.create(iloscPunktow, arr);
		
		arrCent = new Centroid[iloscCentroidow];
		Centroid.create(iloscCentroidow, arrCent);
		
	
		frame = new JFrame(title) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				t.start();

				
				g.setColor(new Color(20,20,120));
				double arc = Math.toRadians(k);
				
				double x =  Math.cos(arc);
				double y =  Math.sin(arc);
				
				int startx = 500 + (int)(424*x);
				int starty = 800 + (int)(106*y);
				
				int dx1 = (int)(424*x) + (int)(424*y);
				int dy1 = (int)(106*y) - (int)(106*x);
				
				int dx2 = (int)(424*x) - (int)(424*y);
				int dy2 = (int)(106*y) + (int)(106*x);
				
				for(int i = 0; i < 2; i++){
					g.drawLine(500 - (int)(424*x), 800 - (int)(106*y) - i*600,500 - (int)(424*y), 800 + (int)(106*x) - i*600);
					g.drawLine(500 - (int)(424*x), 800 - (int)(106*y) - i*600,500 + (int)(424*y), 800 - (int)(106*x) - i*600);
					g.drawLine(500 + (int)(424*x), 800 + (int)(106*y) - i*600,500 - (int)(424*y), 800 + (int)(106*x) - i*600);
					g.drawLine(500 + (int)(424*x), 800 + (int)(106*y) - i*600,500 + (int)(424*y), 800 - (int)(106*x) - i*600);
				}
	
				g.drawLine(500 - (int)(424*x), 800 - (int)(106*y), 500 - (int)(424*x), 200 - (int)(106*y));
				g.drawLine(500 + (int)(424*x), 800 + (int)(106*y), 500 + (int)(424*x), 200 + (int)(106*y));
				g.drawLine(500 - (int)(424*y), 800 + (int)(106*x), 500 - (int)(424*y), 200 + (int)(106*x));
				g.drawLine(500 + (int)(424*y), 800 - (int)(106*x), 500 + (int)(424*y), 200 - (int)(106*x));
				
				g.setColor(Color.RED);
				if(!Punkt.singleItteration(arr, arrCent)) t.stop();
				
				for(int i = 0; i < iloscPunktow; i++){
					g.setColor(arrCent[arr[i].getClasster()].color);
					g.drawOval(startx  - dx1*arr[i].x/100 - dx2*arr[i].y/100, starty - dy1*arr[i].x/100 - dy2*arr[i].y/100 - 600*arr[i].z/100, 5, 5);
				}
				
				for(int i = 0; i < iloscCentroidow; i++){
					g.setColor(arrCent[i].color);
					g.fillOval(startx  - (int)(dx1*arrCent[i].x/100) - (int)(dx2*arrCent[i].y/100), starty - (int)(dy1*arrCent[i].x/100) - (int)(dy2*arrCent[i].y/100) - (int)(600*arrCent[i].z/100), 25, 25);
				}
			}
		};
		
		getFrame().getContentPane().setBackground(new Color(20,20,80));
	    getFrame().setBounds(100, 100, 1400, 1000);
	    getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getFrame().getContentPane().setLayout(null);
	    getFrame().setResizable(false);
	    
	    
	    panel = new JPanel();
	    panel.setBackground(new Color(20,20,100));
	    panel.setBounds(1000, 0, 400, 1000);
	    getFrame().getContentPane().add(panel);
	    
	    JLabel text1 = new JLabel("Ilosc punktow:");
	    text1.setFont(new Font("TimesRoman", Font.PLAIN, 32));
	    text1.setForeground(Color.CYAN);
	    TextField tf1 = new TextField();
	    tf1.setPreferredSize(new Dimension(200,20));
	    tf1.setText("" + iloscPunktow);
	    
	    JLabel text2 = new JLabel("Ilosc centroidow:");
	    text2.setFont(new Font("TimesRoman", Font.PLAIN, 32));
	    text2.setForeground(Color.CYAN);
	    TextField tf2 = new TextField();
	    tf2.setPreferredSize(new Dimension(200,20));
	    tf2.setText("" + iloscCentroidow);
	    
	    JButton startButton = new JButton("START! =)");
	    startButton.setPreferredSize(new Dimension(300, 30));
	    startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				iloscCentroidow = Integer.parseInt(tf2.getText());
				iloscPunktow = Integer.parseInt(tf1.getText());
				arr = new Punkt[iloscPunktow];
				Punkt.create(iloscPunktow, arr);
				
				arrCent = new Centroid[iloscCentroidow];
				Centroid.create(iloscCentroidow, arrCent);
				getFrame().repaint();
			}
	    });
	    
	    panel.add(text1);
	    panel.add(tf1);
	    panel.add(text2);
	    panel.add(tf2);
	    panel.add(startButton);
	    
	    getFrame().addMouseWheelListener(new MouseWheelListener(){
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				k += e.getWheelRotation();
				getFrame().repaint();	
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
