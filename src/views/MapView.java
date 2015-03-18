package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MapView extends JPanel{

	public static JFrame frame;
	public static JPanel panel;
	
	public static Image dirt;
	
	public MapView() {
		createWindow();
	}

	public static void createWindow() {
		frame = new JFrame("Map Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		createPanel();
		frame.add(panel);
		frame.pack();
	}
	
	public static void createPanel() {
		panel = new JPanel(true);
		panel.setPreferredSize(new Dimension(600, 900));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("HELLO", 500, 500);
//		try {
//			dirt = ImageIO.read(new URL("img/dirt.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		g.drawImage(dirt, 0, 0, null);
	}
}