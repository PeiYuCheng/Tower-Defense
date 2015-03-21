package presentation;

import javax.swing.JFrame;

import controllers.GameController;

/**
 * 
 * @author Shabbir
 * @author Jeffrey Kirman
 * This class is the container class for the project
 */
public class Application extends JFrame {

	// constants
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static final String APP_NAME = "Tower Defense";
	
	public static final int TIMEOUT = 50;
	
	
	protected GameController controller = new GameController();
	
	public  Application(){
		init();
	}
	
	/**
	 * Initialize the window
	 */
	private void init(){
		add(controller.getField());
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);	
		setTitle(APP_NAME);
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}