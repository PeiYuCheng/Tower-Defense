package controllers;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import map.CellSelector;
import map.Map;
import map.MapFactory;
import presentation.Application;
import presentation.Field;
import presentation.MainMenu;
import presentation.SideMenu;
import towerModels.Tower;
import buttons.Button;
import buttons.ButtonSelector;
import buttons.BuyRadialTowerButton;
import buttons.BuyRegularTowerButton;
import buttons.BuySplashTowerButton;
import buttons.SellTowerButton;
import buttons.StartCustomGameButton;
import buttons.StartEasyGameButton;
import buttons.StartHardGameButton;
import buttons.StartWaveButton;
import buttons.UpgradeButton;
import critterModels.Critter;
import domain.CritterWaveFactory;
import domain.Player;

public class GameController implements ActionListener, Serializable{

	private File savedGame;
	private Player player;
	private Map map;
	private ButtonSelector button_selector;
	private CellSelector cell_selector;
	private Field game_field;
	private SideMenu game_side_menu;
	private Field custom_map_field;
	private SideMenu custom_map_side_menu;
	private MainMenu main_menu;
	private Timer timer;
	private ArrayList<Tower> list_of_towers_on_map;
	private ArrayList<Critter> list_of_critters_on_map;
	private Queue<Critter> critter_buffer;
	private long time_of_last_deploy;
	private ArrayList<Button> list_of_buttons;
	private CritterWaveFactory critter_factory;
	private int waveNumber = 1;
	private CardLayout card_layout;
	private boolean gameStarted;
	private Dimension map_size;
	private boolean waveStarted;
	
	public static final int CUSTOM_MAP = 0;
	public static final int EASY_MAP = 1;
	public static final int HARD_MAP = 2;
	public static final int LOAD_MAP = 3;
	
	public static final String CARD_MAIN_MENU = "Main Menu";
	public static final String CARD_MAIN_GAME = "Main Game";
	public static final String CARD_CUSTOM_MAP_MAKER = "Custom Map Maker";


	public GameController() {

		player = Player.getPlayerInstance();
		button_selector = ButtonSelector.getInstance();
		cell_selector = CellSelector.getInstance();
		card_layout = new CardLayout();
		savedGame = new File("src/savedGames/game.txt");
		//create Field with paint function defined in controller
		setMainMenu(new MainMenu() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doMainMenuDrawing(g);
		        Toolkit.getDefaultToolkit().sync();
			}
		});
		
		setGameField(new Field() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doGameFieldDrawing(g);
				Toolkit.getDefaultToolkit().sync();
			}
		});

		setGameSideMenu(new SideMenu() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doGameSideMenuDrawing(g);
				Toolkit.getDefaultToolkit().sync();
			}
		});
		
		setCustomMapField(new Field() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doCustomMapFieldDrawing(g);
				Toolkit.getDefaultToolkit().sync();
			}
		});

		setCustomMapSideMenu(new SideMenu() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doCustomMapSideMenuDrawing(g);
				Toolkit.getDefaultToolkit().sync();
			}
		});
		
		// Set up the side menu buttons
		game_side_menu.add(new BuyRegularTowerButton(10, 80, 30, 30));
		game_side_menu.add(new BuySplashTowerButton(70, 80, 30, 30));
		game_side_menu.add(new BuyRadialTowerButton(130, 80, 30, 30));
		game_side_menu.add(new UpgradeButton(10, 200, 30, 30));
		game_side_menu.add(new SellTowerButton(70, 200, 30, 30));
		game_side_menu.add(new StartWaveButton(100, 20, 30, 30));
		
		// Set up the main menu buttons
		main_menu.add(new StartEasyGameButton(200, 200, 150, 50));
		main_menu.add(new StartHardGameButton(200, 300, 150, 50));
		main_menu.add(new StartCustomGameButton(200, 400, 150, 50));
		
		timer = new Timer(Application.TIMEOUT,this);
		timer.start();

	}
	
	private void initiateGame(int mapChoice) {
		gameStarted = true;
		
		if (mapChoice == LOAD_MAP) {
			//TODO map = ***load map here***
		}
		else {
			map = MapFactory.getUniqueInstance().createMap(mapChoice, map_size.width, map_size.height);
		}
		
		list_of_critters_on_map = new ArrayList<>();
		list_of_towers_on_map = new ArrayList<>();
		list_of_buttons = new ArrayList<>();
		critter_factory = CritterWaveFactory.getInstance();
		critter_buffer = new LinkedList<>();
		
		// populate field with cells
		
		if (mapChoice == CUSTOM_MAP) {
			for (int i = 0; i < map.getMapHeight(); i++) {
				for (int j = 0; j < map.getMapWidth(); j++) {
					custom_map_field.getLayeredPane().add(map.getComponent(i,j), new Integer(0));
				}
			}
		}
		else {
			for (int i = 0; i < map.getMapHeight(); i++) {
				for (int j = 0; j < map.getMapWidth(); j++) {
					game_field.getLayeredPane().add(map.getComponent(i,j), new Integer(0));
				}
			}
		}
	}

	protected void doGameFieldDrawing(Graphics g) {
		for (int i = 0; i < game_field.getLayeredPane().getComponents().length; i++) {
			game_field.getLayeredPane().getComponent(i).paint(g);
		}
		
		game_field.getLayeredPane().repaint();
	}
	
	protected void doCustomMapFieldDrawing(Graphics g) {
		for (int i = 0; i < custom_map_field.getLayeredPane().getComponents().length; i++) {
			custom_map_field.getLayeredPane().getComponent(i).paint(g);
		}
		
		custom_map_field.getLayeredPane().repaint();
	}

	protected void doGameSideMenuDrawing(Graphics g) {
		drawGameSideMenu(g);
	}
	
	protected void doCustomMapSideMenuDrawing(Graphics g) {
	}

	protected void doMainMenuDrawing(Graphics g) {
		drawMainMenu(g);
	}

	private void drawGameSideMenu(Graphics g) {

		g.setColor(Color.white);
		g.drawString("Lives: " + player.getLives(), 10, 20);
		g.drawString("Money: " + player.getMoney(), 10, 40);
		g.drawString("Towers: ", 10, 70);
		
		for (int i = 0; i < game_side_menu.getComponents().length; i++) {
			game_side_menu.getComponent(i).paint(g);
			
		}

		printTowerStats(g, new Point(10,400));
	}

	private void drawMainMenu(Graphics g) {

		for (int i = 0; i < main_menu.getComponents().length; i++) {
			main_menu.getComponent(i).paint(g);
		}
		
	}


	private void fireTowers() {

		for (Tower tower : list_of_towers_on_map) {
			tower.setAllCrittersOnMap(list_of_critters_on_map);
			tower.fire();
		}

	}


	private void buyTower() {

		Button button = button_selector.getSelectedButton();
		Tower newTower;

		// check selected button
		if (button != null) {
			newTower = button.getNewTower();
		}
		else {
			return;
		}

		// check player money
		if (player.getMoney() < newTower.getCost()) {
			return;
		}

		map.Cell towersCell = cell_selector.getSelectedCell();

		if (towersCell != null) {

			if (towersCell.cellAvailable()) {
				list_of_towers_on_map.add(newTower);
				newTower.placeTower(towersCell, true);
				game_field.getLayeredPane().add(newTower.getComponent(), new Integer(1));
				button_selector.deselectSelected();
				player.changeMoney(-newTower.getCost());
			}

			cell_selector.deselectSelectedCell();

		}

	}

	private void sellTower() {

		map.Cell towersCell = cell_selector.getSelectedCell();
		Tower soldTower;

		if (towersCell == null) {
			button_selector.setSellTowerSelected(false);
			return;
		}

		soldTower = towersCell.getTowerInCell();

		if (soldTower == null) {
			button_selector.setSellTowerSelected(false);
			return;
		}

		if (button_selector.isSellTowerSelected()) {
				
				game_field.getLayeredPane().remove(soldTower.getComponent());
				list_of_towers_on_map.remove(soldTower);
				cell_selector.getSelectedCell().setTowerInCell(null);
				player.changeMoney(soldTower.getRefundValue());
				button_selector.deselectSelected();
				cell_selector.deselectSelectedCell();
				button_selector.setSellTowerSelected(false);
				
		}

	}

	private void upgradeTower() {

		map.Cell towersCell = cell_selector.getSelectedCell();
		Tower upgradeTower;

		if ((towersCell == null)) {
			button_selector.setUpgradeTowerSelected(false);
			return;
		}

		upgradeTower = towersCell.getTowerInCell();

		if (upgradeTower == null) {
			button_selector.setUpgradeTowerSelected(false);
			return;
		}

		// check player money
		if (player.getMoney() < upgradeTower.getUpgradeCost()) {
			button_selector.setUpgradeTowerSelected(false);
			return;
		}

		if (button_selector.isUpgradeTowerSelected()) {

			if (upgradeTower.upgradeTower()) {
				player.changeMoney(-upgradeTower.getUpgradeCost());
			}
			button_selector.setUpgradeTowerSelected(false);

		}

	}

	private void startWave() {

		if (button_selector.isStartWave()) {

			if (list_of_critters_on_map.isEmpty() && critter_buffer.isEmpty()) {
				critter_buffer = critter_factory.createWave(waveNumber, map);
				waveStarted = true;
				waveNumber++;
			}

			button_selector.setStartWave(false);
		}
	}

	private void deployCritters() {

		Critter current_critter = null;

		if (!critter_buffer.isEmpty()) {
			if (System.currentTimeMillis() - time_of_last_deploy > Critter.DEPLOY_TIME) {
				current_critter = critter_buffer.poll();
				list_of_critters_on_map.add(current_critter);
				game_field.getLayeredPane().add(current_critter.getCritterComponent(), new Integer(1));
				game_field.getLayeredPane().add(current_critter.getHealthbarComponent(), new Integer(2));
				time_of_last_deploy = System.currentTimeMillis();
			}
		}
	}

	private void moveCritters() {
		if (!list_of_critters_on_map.isEmpty()) {
			for (Critter critter : list_of_critters_on_map) {
				critter.startWalking();
			}
		}
	}

	private void killCritter() {
		if (!list_of_critters_on_map.isEmpty()) {
			for (Critter critter : list_of_critters_on_map) {
				if (critter.hasReachedExit()) {
					player.changeLives(-critter.getDamagingPower());
					game_field.getLayeredPane().remove(critter.getCritterComponent());
					game_field.getLayeredPane().remove(critter.getHealthbarComponent());
					list_of_critters_on_map.remove(critter);
					break;
				}
				else if (critter.isDead()) {
					player.changeMoney(critter.getReward());
					game_field.getLayeredPane().remove(critter.getCritterComponent());
					game_field.getLayeredPane().remove(critter.getHealthbarComponent());
					list_of_critters_on_map.remove(critter);
					break;
				}
			}
		}
	}
	
	private void printTowerStats(Graphics g, Point position) {
		
		Tower tower;
		
		g.setColor(Color.white);
		g.drawString("Tower stats:", position.x, position.y + 15);
		
		if (button_selector.getSelectedButton() != null) {
			tower = button_selector.getSelectedButton().getNewTower();
		}
		else if (cell_selector.getSelectedCell() == null) {
			return;
		}
		else {
			tower = cell_selector.getSelectedCell().getTowerInCell();
		}
		
		if (tower == null) {
			return;
		}

		int upgradeLevel = tower.getUpgradeLevel();

		g.drawString("Level: " + upgradeLevel, position.x, position.y + 30);
		g.drawString("Power: " + tower.actualPower(), position.x, position.y + 45);
		g.drawString("Range: " + tower.actualRange(), position.x, position.y + 60);
		g.drawString("Rate of fire: " + tower.actualRateOfFire(), position.x, position.y + 75);
		if (upgradeLevel == 3) {
			g.drawString("Upgrade cost: ---", position.x, position.y + 90);
		}
		else {
			g.drawString("Upgrade cost: " + tower.getUpgradeCost(), position.x, position.y + 90);
		}
		g.drawString("Sell value: " + tower.getRefundValue(), position.x, position.y + 105);
		g.drawString("Attack mode: " + tower.getAttackMode().toString(), position.x, position.y + 120);
		//		g.drawString("Has pyro damage: " + tower.hasPyroDamage());
		//		g.drawString("Has slow damage: " + tower.hasSlowDamage());
		//		g.drawString("Is active: " + tower.isActive());
	}
	
	protected void saveGame() {
		try {
			FileOutputStream fileStream = new FileOutputStream(savedGame);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			
	        objectStream.writeObject(player);
	        objectStream.writeObject(map);
	        objectStream.writeObject(button_selector);
	        objectStream.writeObject(cell_selector);
	        objectStream.writeObject(game_field);
	        objectStream.writeObject(game_side_menu);
	        objectStream.writeObject(custom_map_field);
	        objectStream.writeObject(custom_map_side_menu);
	        objectStream.writeObject(main_menu);
	        objectStream.writeObject(timer);
	        objectStream.writeObject(list_of_towers_on_map);
	        objectStream.writeObject(list_of_critters_on_map);
	        objectStream.writeObject(critter_buffer);
	        objectStream.writeObject(new Long(time_of_last_deploy));
	        objectStream.writeObject(list_of_buttons);
	        objectStream.writeObject(critter_factory);
	        objectStream.writeObject(new Integer(waveNumber));
	        objectStream.writeObject(card_layout);
	        objectStream.writeObject(new Boolean(gameStarted));
	        objectStream.writeObject(new Boolean(waveStarted));
	        objectStream.writeObject(new Dimension(map_size));
	        
	        objectStream.close();
	        fileStream.close();
	        
	        JOptionPane.showConfirmDialog(game_field, "Saved game successfully", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(game_field, e.toString() + "\nFailed to save game", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	protected void loadGame() {
		try {
			FileInputStream fileStream = new FileInputStream(savedGame);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			
			player = (Player) objectStream.readObject();
			map = (Map) objectStream.readObject();
			button_selector = (ButtonSelector) objectStream.readObject();
			cell_selector = (CellSelector) objectStream.readObject();
			game_field = (Field) objectStream.readObject();
			game_side_menu = (SideMenu) objectStream.readObject();
			custom_map_field = (Field) objectStream.readObject();
			custom_map_side_menu = (SideMenu) objectStream.readObject();
			main_menu = (MainMenu) objectStream.readObject();
			timer = (Timer) objectStream.readObject();
			list_of_towers_on_map = (ArrayList<Tower>) objectStream.readObject();
			list_of_critters_on_map = (ArrayList<Critter>) objectStream.readObject();
			critter_buffer = (Queue<Critter>) objectStream.readObject();
			time_of_last_deploy = (Long) objectStream.readObject();
			list_of_buttons = (ArrayList<Button>) objectStream.readObject();
			critter_factory = (CritterWaveFactory) objectStream.readObject();
			waveNumber = (Integer) objectStream.readObject();
			card_layout = (CardLayout) objectStream.readObject();
			gameStarted = (Boolean) objectStream.readObject();
			waveStarted = (Boolean) objectStream.readObject();
			map_size = (Dimension) objectStream.readObject();
			
			fileStream.close();
			objectStream.close();
			
			JOptionPane.showConfirmDialog(game_field, "Loaded game successfully", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(game_field, e.toString() + "\nFailed to load game", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	private void startGame() {
		if (!gameStarted) {
			if (button_selector.isStartGame()) {
				
				map_size = new Dimension(0,0);
				if (button_selector.getMapType() == CUSTOM_MAP) {
					userInputMapSize();
					card_layout.show(Application.getCardContainer(), CARD_CUSTOM_MAP_MAKER);
				}
				else {
					card_layout.show(Application.getCardContainer(), CARD_MAIN_GAME);
				}
				
				initiateGame(button_selector.getMapType());
				button_selector.setStartGame(false);
			}
		}
	}
	
	private void userInputMapSize() {
		
		String swidth = null;
		String sheight = null;
		
		int iwidth;
		int iheight;
		
		while (swidth == null) {
			swidth = JOptionPane.showInputDialog("What is the map width (minimum 3, maximum 15)?");
			try {
				iwidth = Integer.parseInt(swidth);
				map_size.width = Map.boundNumber(iwidth, 3, 15);
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(main_menu, "Invalid width entered");
				swidth = null;
			}
		}
		
		while (sheight == null) {
			sheight = JOptionPane.showInputDialog("What is the map height (minimum 3, maximum 15)?");
			try {
				iheight = Integer.parseInt(sheight);
				map_size.height = Map.boundNumber(iheight, 3, 15);
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(main_menu, "Invalid height entered");
				sheight = null;
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		startGame();
		
		if (gameStarted) {
			// Tower logic
			buyTower();
			sellTower();
			upgradeTower();
			fireTowers();
	
			// Critter logic
			startWave();
			deployCritters();
			moveCritters();
			startWave();
			killCritter();
		}
		
			// Repainting
			game_field.repaint();
			game_side_menu.repaint();
			main_menu.repaint();

	}


	/*
	 * Getters and Setters
	 */

	public Field getGameField() {
		return game_field;
	}

	public void setGameField(Field field) {
		this.game_field = field;
	}

	public SideMenu getGameSideMenu() {
		return game_side_menu;
	}

	public void setGameSideMenu(SideMenu side_menu) {
		this.game_side_menu = side_menu;
	}

	public MainMenu getMainMenu() {
		return main_menu;
	}

	public void setMainMenu(MainMenu main_menu) {
		this.main_menu = main_menu;
	}

	public CardLayout getCardLayout() {
		return card_layout;
	}

	public void setCardLayout(CardLayout card_layout) {
		this.card_layout = card_layout;
	}

	public Field getCustomMapField() {
		return custom_map_field;
	}

	public void setCustomMapField(Field custom_map_field) {
		this.custom_map_field = custom_map_field;
	}

	public SideMenu getCustomMapSideMenu() {
		return custom_map_side_menu;
	}

	public void setCustomMapSideMenu(SideMenu custom_map_side_menu) {
		this.custom_map_side_menu = custom_map_side_menu;
	}

	public Dimension getMapSize() {
		return map_size;
	}

	public void setMapSize(Dimension map_size) {
		this.map_size = map_size;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
