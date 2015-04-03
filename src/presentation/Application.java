package presentation;

import java.awt.CardLayout;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.GameController;

/**
 * 
 * @author Shabbir
 * @author Jeffrey Kirman
 * This class is the container class for the project
 */
public class Application extends JFrame implements Serializable {

	// constants
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static final String APP_NAME = "Tower Defense";
	
	public static final int TIMEOUT = 50;
	
	
	protected GameController controller = new GameController();
	private static JPanel container;
	private JPanel main_game;
	private JPanel main_menu;
	private CardLayout card_layout;
	
	public  Application(){
		init();
	}
	
	/**
	 * Initialize the window
	 */
	private void init(){
		
		container = new JPanel();
		card_layout = controller.getCardLayout();
		container.setLayout(card_layout);
		add(container);
		
		// create a container for all the frames in the main game JPanel
		main_game = new JPanel();
		main_game.setLayout(new BoxLayout(main_game, BoxLayout.X_AXIS));
		container.add(main_game, GameController.CARD_MAIN_GAME);
		main_game.add(controller.getGameField());
		main_game.add(controller.getGameSideMenu());
		
		// create a container for all the frames in the main game JPanel
		main_game = new JPanel();
		main_game.setLayout(new BoxLayout(main_game, BoxLayout.X_AXIS));
		container.add(main_game, GameController.CARD_CUSTOM_MAP_MAKER);
		main_game.add(controller.getCustomMapField());
		main_game.add(controller.getCustomMapSideMenu());
		
		// create a container for all the frames in the main menu JPanel
		main_menu = new JPanel();
		main_menu.setLayout(new BoxLayout(main_menu, BoxLayout.X_AXIS));
		container.add(main_menu, GameController.CARD_MAIN_MENU);
		main_menu.add(controller.getMainMenu());
		
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);	
		setTitle(APP_NAME);
		pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		card_layout.show(container, GameController.CARD_MAIN_MENU);
		
	}
	
	public static JPanel getCardContainer() {
		return container;
	}
}
