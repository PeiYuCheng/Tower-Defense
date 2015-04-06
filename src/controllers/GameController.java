package controllers;

import img.Images;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import map.*;
import presentation.*;
import towerModels.AreaOfEffectTower;
import towerModels.RadialTower;
import towerModels.RegularTower;
import towerModels.Tower;
import audio.MusicPlayer;
import buttons.*;
import critterModels.*;
import domain.CritterWaveFactory;
import domain.Player;

public class GameController implements ActionListener, Serializable{

	private File savedGame;
	private JComboBox<File> savedMapsDropdown;
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
	private CritterWaveFactory critter_factory;
	private CardLayout card_layout;
	private boolean gameStarted;
	private Dimension map_size;
	private boolean customMapMode;
	private boolean validCustomMap;
	private boolean dropdownFull;
	private Images images;
	
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
		critter_factory = CritterWaveFactory.getInstance();
		card_layout = new CardLayout();
		savedGame = new File("src/savedGames/game.txt");
		dropdownFull = false;
		MusicPlayer.playMainMenuBGM();;
		images = Images.getUniqueInstance();
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
		
		// Set up the main menu buttons
		main_menu.add(new StartEasyGameButton(30, 100, 150, 50));
		main_menu.add(new StartHardGameButton(200, 100, 150, 50));
		main_menu.add(new StartCustomGameButton(30, 170, 150, 50));
		main_menu.add(new StartLoadedMapButton(115, 300, 150, 50));
		
		savedMapsDropdown = new JComboBox<File>() {
			@Override
			public void paintComponent(Graphics g) {
				savedMapsDropdown.setLocation(new Point(100, 260));
				super.paintComponent(g);
			}
		};
		
		main_menu.add(savedMapsDropdown);
		
		main_menu.revalidate();
		
		// Set up the side menu buttons
		game_side_menu.add(new BuyRegularTowerButton(10, 130, 50, 50));
		game_side_menu.add(new BuySplashTowerButton(70, 130, 50, 50));
		game_side_menu.add(new BuyRadialTowerButton(130, 130, 50, 50));
		game_side_menu.add(new UpgradeButton(10, 250, 50, 50));
		game_side_menu.add(new SellTowerButton(70, 250, 50, 50));
		game_side_menu.add(new AttackModeButton(130, 250, 50, 50));
		game_side_menu.add(new StartWaveButton(10, 490, 50, 50));
//		game_side_menu.add(new SaveGameButton(0, 555, 100, 45));
		game_side_menu.add(new MenuButton(0, 555, 200, 45));
		
		custom_map_side_menu.add(new ValidateAndSaveButton(70, 420, 80, 80));
		custom_map_side_menu.add(new MenuButton(0, 555, 200, 45));
		
		timer = new Timer(Application.TIMEOUT,this);
		timer.start();

	}
	
	private void initiateGame(int mapChoice) {
				
		if (mapChoice == LOAD_MAP) {
			loadMap((File) savedMapsDropdown.getSelectedItem());
		}
		else {
			map = MapFactory.getUniqueInstance().createMap(mapChoice, map_size.width, map_size.height);
		}
		
		// populate field with cells
		if (mapChoice == CUSTOM_MAP) {
			
			customMapMode = true;
			for (int i = 0; i < map.getMapHeight(); i++) {
				for (int j = 0; j < map.getMapWidth(); j++) {
					custom_map_field.getLayeredPane().add(map.getComponent(i,j), new Integer(0));
				}
			}
		}
		else {
			
			gameStarted = true;
			
			list_of_critters_on_map = new ArrayList<>();
			list_of_towers_on_map = new ArrayList<>();
			critter_factory = CritterWaveFactory.getInstance();
			critter_buffer = new LinkedList<>();
			
			for (int i = 0; i < map.getMapHeight(); i++) {
				for (int j = 0; j < map.getMapWidth(); j++) {
					game_field.getLayeredPane().add(map.getComponent(i,j), new Integer(0));
				}
			}
		}
	}

	protected void doGameFieldDrawing(Graphics g) {
		game_field.getLayeredPane().repaint();
	}
	
	protected void doCustomMapFieldDrawing(Graphics g) {
		custom_map_field.getLayeredPane().repaint();
	}

	protected void doGameSideMenuDrawing(Graphics g) {
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		
		g.setColor(Color.GREEN);
		g.drawString("much health: ", 10, 30);
		g.drawImage(images.healthIcon.getScaledInstance(30, 30, 0), 115, 10, null);
		g.drawString("x " + player.getLives(), 150, 30);
		
		g.setColor(Color.YELLOW);
		g.drawString("very money: ", 10, 70);
		g.drawImage(images.moneyIcon.getScaledInstance(30, 30, 0), 115, 50, null);
		g.drawString("x " + player.getMoney(), 150, 70);
		
		// menu seperator, change color
		g.setColor(new Color(97,80,194,50));
		g.fillRect(0, 90, 200, 5);
		
		// tower buying icons
		g.setColor(Color.MAGENTA);
		g.drawString("very buy:", 10, 115);
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		g.drawImage(images.moneyIcon.getScaledInstance(20, 20, 0), 10, 185, null);
		g.drawString(" x " + RegularTower.COST, 30, 200);
		g.drawImage(images.moneyIcon.getScaledInstance(20, 20, 0), 70, 185, null);
		g.drawString(" x " + AreaOfEffectTower.COST, 90, 200);
		g.drawImage(images.moneyIcon.getScaledInstance(20, 20, 0), 130, 185, null);
		g.drawString(" x " + RadialTower.COST, 150, 200);
		
		// menu seperator, change color
		g.setColor(new Color(97,80,194,50));
		g.fillRect(0, 210, 200, 5);
		
		// effect icons
		g.setColor(Color.CYAN);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		g.drawString("much change:", 10, 235);
		
		// menu seperator, change color
		g.setColor(new Color(97,80,194,50));
		g.fillRect(0, 310, 200, 5);
		
		// tower stats
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		g.drawString("so tower:", 10, 335);
		
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		printTowerStats(g, new Point(10, 355));
		
		// menu seperator, change color
		g.setColor(new Color(97,80,194,50));
		g.fillRect(0, 450, 200, 5);
		
		// enemy info
		g.setColor(Color.PINK);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		g.drawString("much enemy:", 10, 475);
		
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		g.drawString("very wave: " + (critter_factory.getWaveNumber()) + "/50", 70, 490);
		if (button_selector.isShowNextWaveInfo()) {
			printNextWaveInfo(g, new Point(70, 510));
		}
				
		// menu seperator, change color
		g.setColor(new Color(97,80,194,50));
		g.fillRect(0, 550, 200, 5);

	}
	
	protected void doCustomMapSideMenuDrawing(Graphics g) {
		if (validCustomMap) {
			g.drawImage(images.validBackground.getScaledInstance(200, 600, 0), 0, 0, null);
			
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
			g.setColor(Color.GREEN);
			g.drawString("very accept", 75, 355);
			
			g.setColor(Color.CYAN);
			g.drawString("such valid", 94, 105);
			
			g.setColor(Color.RED);
			g.drawString("wow.", 28, 182);
			
			g.setColor(Color.MAGENTA);
			g.drawString("so map", 13, 409);
		}
		g.setColor(Color.white);
	}

	protected void doMainMenuDrawing(Graphics g) {
		g.drawImage(images.menuBackground, 0, 0, null);
	}

	private void fireTowers() {

		for (Tower tower : list_of_towers_on_map) {
			tower.setAllCrittersOnMap(list_of_critters_on_map);
			tower.fire();
		}

	}

	private void buyTower() {

		CustomButton button = button_selector.getSelectedButton();
		Tower newTower;

		// check selected button
		if (button != null) {
			newTower = button.getNewTower();
		}
		else {
			return;
		}

		map.Cell towersCell = cell_selector.getSelectedCell();

		if (towersCell != null) {

			// check player money
			if (player.getMoney() < newTower.getCost()) {}
			else if (towersCell.cellAvailable()) {
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
		int upgradeCost;

		if ((towersCell == null)) {
			button_selector.setUpgradeTowerSelected(false);
			return;
		}

		upgradeTower = towersCell.getTowerInCell();

		if (upgradeTower == null) {
			button_selector.setUpgradeTowerSelected(false);
			return;
		}
		
		upgradeCost = upgradeTower.getUpgradeCost();

		// check player money
		if (player.getMoney() < upgradeTower.getUpgradeCost()) {
			button_selector.setUpgradeTowerSelected(false);
		}
		else {
			if (button_selector.isUpgradeTowerSelected()) {
	
				if (upgradeTower.upgradeTower()) {
					player.changeMoney(-upgradeCost);
				}
				button_selector.setUpgradeTowerSelected(false);
	
			}
		}

	}
	
	private void changeTowerAttackMode() {

		map.Cell towersCell = cell_selector.getSelectedCell();
		Tower selectedTower;

		if ((towersCell == null)) {
			button_selector.setToggleAttackMode(false);
			return;
		}

		selectedTower = towersCell.getTowerInCell();

		if (selectedTower == null) {
			button_selector.setToggleAttackMode(false);
			return;
		}


		if (button_selector.isToggleAttackMode()) {

			selectedTower.toggleTowerAI();
			button_selector.setToggleAttackMode(false);

		}

	}

	private void startWave() {

		if (list_of_critters_on_map.isEmpty() && critter_buffer.isEmpty()) {
			MusicPlayer.playInBetweenWavesBGM();
			if (button_selector.isStartWave()) {
				MusicPlayer.playWaveBGM();
				critter_factory.setupNextWave();
				critter_buffer = critter_factory.createWave(map);
			}
		}
		button_selector.setStartWave(false);
	}

	private void deployCritters() {

		Critter current_critter = null;
		Random r = new Random();

		if (!critter_buffer.isEmpty()) {
			// deploy each critter at a random time
			if (System.currentTimeMillis() - time_of_last_deploy > Critter.DEPLOY_TIME - r.nextInt(800)) {
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

		g.drawString("such lvl: " + upgradeLevel, position.x, position.y);
		g.drawString("much pwr: " + tower.actualPower(), position.x, position.y + 15);
		g.drawString("very range: " + tower.actualRange(), position.x, position.y + 30);
		g.drawString("so fire rate: " + tower.actualRateOfFire(), position.x, position.y + 45);
		if (upgradeLevel == 3) {
			g.drawString("very upgrade cost: ---", position.x, position.y + 60);
		}
		else {
			g.drawString("very upgrade cost: " + tower.getUpgradeCost(), position.x, position.y + 60);
		}
		g.drawString("so sell value: " + tower.getRefundValue(), position.x, position.y + 75);
		g.drawString("much mode: " + tower.getAttackMode().toString(), position.x, position.y + 90);
		
		if (upgradeLevel < 3) {
			
		}

	}
	
	private void printNextWaveInfo(Graphics g, Point position) {
		g.drawImage(images.regularCritterRight.getScaledInstance(30, 21, 0), position.x, position.y - 15, null);
		g.drawString("x " + critter_factory.countAmountOfSmallCritters(critter_factory.getWaveNumber()+1), position.x + 30, position.y);
		g.drawImage(images.mediumCritterRight.getScaledInstance(30, 21, 0), position.x, position.y + 15, null);
		g.drawString("x " + critter_factory.countAmountOfMediumCritters(critter_factory.getWaveNumber()+1), position.x + 30, position.y + 30);
		g.drawImage(images.largeCritterRight.getScaledInstance(30, 21, 0), position.x + 60, position.y - 15, null);
		g.drawString("x " + critter_factory.countAmountOfLargeCritters(critter_factory.getWaveNumber()+1), position.x + 90, position.y);
	}
	
	private void startGame() {
		if (!gameStarted) {
			
			if (!dropdownFull) {
				fillSavedMapDropdown();
			}
			
			if (button_selector.isStartGame()) {
				
				map_size = new Dimension(0,0);
				if (button_selector.getMapType() == CUSTOM_MAP) {
					
					boolean userInputSuccessful = userInputMapSize();
					
					if (!userInputSuccessful) {
						button_selector.setStartGame(false);
						return;
					}

					card_layout.show(Application.getCardContainer(), CARD_CUSTOM_MAP_MAKER);
					MusicPlayer.playMapMakingBGM();
				}
				else if (button_selector.getMapType() == LOAD_MAP) {
					if (savedMapsDropdown.getSelectedItem() == null) {
						button_selector.setStartGame(false);
						return;
					}
				}
				else {
					card_layout.show(Application.getCardContainer(), CARD_MAIN_GAME);
					MusicPlayer.playInBetweenWavesBGM();
				}
				
				initiateGame(button_selector.getMapType());
				button_selector.setStartGame(false);
			}
		}
	}
	
	private boolean userInputMapSize() {
		
		JTextField swidth = new JTextField();
		JTextField sheight = new JTextField();
		
		int iwidth;
		int iheight;
		
		Object[] message = {
		    "Map width:", swidth,
		    "Map height:", sheight
		};

		int option;
		
		while(true) {
			option = JOptionPane.showConfirmDialog(null, message, "Enter Map Size", JOptionPane.OK_CANCEL_OPTION);
			
			if (option == JOptionPane.OK_OPTION) {
				try {
					iwidth = Integer.parseInt(swidth.getText());
					iheight = Integer.parseInt(sheight.getText());
					map_size.width = Map.boundNumber(iwidth, 3, 15);
					map_size.height = Map.boundNumber(iheight, 3, 15);
					return true;
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(main_menu, "Invalid value entered.");
				}
			}
			else {
			    return false;
			}
		}
		
	}
	
	private void validateAndSaveCustomMap() {
		map.findAndSetEntryAndExitCells();
		validCustomMap = map.validate();
		if (button_selector.isValidateAndSave()) {
			if (validCustomMap) {
				String mapName = JOptionPane.showInputDialog("Enter map name");
				if (mapName != null) {
					for (Cell cell : map.getPath()) {
						cell.customMapModeOff();
					}
					customMapMode = false;
					saveMap(mapName);
					dropdownFull = false;
					resetGame();
				}
			}
		}
		button_selector.setValidateAndSave(false);
	}
	
	private void toggleCellOnCustomMap() {
		
		Cell current_cell;
		Cell new_cell;
		
		if (cell_selector.getSelectedCell() != null) {
			current_cell = cell_selector.getSelectedCell();
			new_cell = ((CustomMap) map).toggleCell(cell_selector.getSelectedCell());
			cell_selector.deselectSelectedCell();
			custom_map_field.getLayeredPane().remove(current_cell.getComponent());
			custom_map_field.getLayeredPane().add(new_cell.getComponent(), new Integer(0));
			
		}
	}
	
	protected void saveMap(String mapName) {
		try {
			FileOutputStream fileStream = new FileOutputStream("src/savedMaps/" + mapName + ".txt");
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
	        
			objectStream.writeObject(map);
			
	        objectStream.close();
	        fileStream.close();
	        
	        JOptionPane.showConfirmDialog(game_field, "Saved map Successfully", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(game_field, e.toString() + "\nFailed to save map", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	protected void loadMap(File mapName) {
		try {
			FileInputStream fileStream = new FileInputStream(mapName);
			ObjectInputStream objectStream = new ObjectInputStream(fileStream);
			
			map = (Map) objectStream.readObject();
			map.refreshCellSelectors();
			
			fileStream.close();
			objectStream.close();
			
		} catch(Exception e) {
			JOptionPane.showConfirmDialog(game_field, e.toString() + "\nFailed to load map", "Tower Defense", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	private void fillSavedMapDropdown() {
		savedMapsDropdown.removeAllItems();
		File mapDirectory = new File("src/savedMaps/");
		for (File file : mapDirectory.listFiles()) {
			savedMapsDropdown.addItem(file);
			dropdownFull = true;
		}
	}
	
	private void endGame() {
		if (getPlayer().isDead()) {
			JOptionPane.showMessageDialog(game_field, "very game over. much dissapoint.");
				resetGame();
		}
	}
	
	private void clearFields() {
		game_field.getLayeredPane().removeAll();
		custom_map_field.getLayeredPane().removeAll();
	}
	
	private void resetGame() {
		card_layout.show(Application.getCardContainer(), CARD_MAIN_MENU);
		MusicPlayer.playMainMenuBGM();
		player.restartPlayer();
		button_selector.deselectSelected();
		critter_factory.restartWaves();
		main_menu.revalidate();
		clearFields();
		customMapMode = false;
		gameStarted = false;
	}
	
	private void returnToMenu(JComponent field) {
		if (button_selector.isRestartGame()) {
			int reply = JOptionPane.showConfirmDialog(field, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				resetGame();
			}
			button_selector.setRestartGame(false);
		}
	}
	
	private void winGame() {
		if (list_of_critters_on_map.isEmpty() && critter_buffer.isEmpty() && (critter_factory.getWaveNumber() == CritterWaveFactory.FINAL_WAVE)) {
			JOptionPane.showMessageDialog(game_field, "much defended. so win. wow.");
			resetGame();
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		startGame();
		
		if (customMapMode) {
			toggleCellOnCustomMap();
			validateAndSaveCustomMap();
			returnToMenu(custom_map_field);
		}
		else if (gameStarted) {
			
			// Tower logic
			buyTower();
			sellTower();
			upgradeTower();
			changeTowerAttackMode();
			fireTowers();
	
			// Critter logic
			startWave();
			deployCritters();
			moveCritters();
			killCritter();
			
			// Miscellaneous logic
//			saveGameRequest();
			returnToMenu(game_field);
			winGame();
			endGame();
		}
		
			// Repainting
			game_field.repaint();
			game_side_menu.repaint();
			custom_map_field.repaint();
			custom_map_side_menu.repaint();
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
