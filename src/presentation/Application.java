package presentation;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private JPanel container;
	
	public  Application(){
		init();
	}
	
	/**
	 * Initialize the window
	 */
	private void init(){
		
		// create a container for all the frames in the game
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		add(container);
		container.add(controller.getField());
		container.add(controller.getSideMenu());
//		container.add(controller.getMain_menu());
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);	
		setTitle(APP_NAME);
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}