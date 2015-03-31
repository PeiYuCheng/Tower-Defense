package controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Queue;

import javafx.scene.control.Cell;

import javax.swing.JLayeredPane;
import javax.swing.Timer;

import buttons.*;
import map.*;
import critterModels.Critter;
import domain.CritterWaveFactory;
import domain.Player;
import presentation.*;
import towerModels.*;

public class GameController implements ActionListener {

	private Player player;
	private Map map;
	private ButtonSelector button_selector;
	private CellSelector cell_selector;
	private Field field;
	private SideMenu side_menu;
//	private MainMenu main_menu;
	private Timer timer;
	private ArrayList<Tower> list_of_towers_on_map;
	private ArrayList<Critter> list_of_critters_on_map;
	private ArrayList<Button> list_of_buttons;
	private CritterWaveFactory critter_factory;
	private int waveNumber = 1;
	private static final int CUSTOM_MAP = 0;
	private static final int EASY_MAP = 1;
	private static final int HARD_MAP = 2;

	
	public GameController() {
		
		player = Player.getPlayerInstance();
		//map = FixedMap.createGeneric();	
		map = MapFactory.getUniqueInstance().createMap(HARD_MAP,0,0);
		button_selector = ButtonSelector.getInstance();
		cell_selector = CellSelector.getInstance();
		list_of_critters_on_map = new ArrayList<>();
		list_of_towers_on_map = new ArrayList<>();
		list_of_buttons = new ArrayList<>();
		critter_factory = CritterWaveFactory.getInstance();
		
		//create Field with paint function defined in controller
		setField(new Field() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doFieldDrawing(g);
		        Toolkit.getDefaultToolkit().sync();
			}
		});
		
//		setMain_menu(new MainMenu() {
//			@Override
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				doFieldDrawing(g);
//		        Toolkit.getDefaultToolkit().sync();
//			}
//		});
		
		// populate field with cells
		for (int i = 0; i < map.getMapHeight(); i++) {
			for (int j = 0; j < map.getMapWidth(); j++) {
				field.getLayeredPane().add(map.getComponent(i,j), new Integer(0));
			}
		}
		
		setSideMenu(new SideMenu() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doMenuDrawing(g);
		        Toolkit.getDefaultToolkit().sync();
			}
		});
		
		// Set up the buttons
		side_menu.add(new BuyRegularTowerButton(10, 80, 30, 30));
		side_menu.add(new BuySplashTowerButton(70, 80, 30, 30));
		side_menu.add(new BuyRadialTowerButton(130, 80, 30, 30));
		side_menu.add(new UpgradeButton(10, 200, 30, 30));
		side_menu.add(new SellTowerButton(70, 200, 30, 30));
		side_menu.add(new StartWaveButton(100, 20, 30, 30));
		
		timer = new Timer(Application.TIMEOUT,this);
		timer.start();
		
	}
	
	protected void doFieldDrawing(Graphics g) {
		drawGrid(g);
	}
	
	protected void doMenuDrawing(Graphics g) {
		drawSideMenu(g);
	}
	
	protected void doMainMenuDrawing(Graphics g) {
		drawMainMenu(g);
	}
	
	/**
	 * Draws all the cells in the grid
	 * @param g
	 */
	private void drawGrid(Graphics g) {
		field.getLayeredPane().paintAll(g);
		field.getLayeredPane().repaint();
	}
	
	private void drawSideMenu(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString("Lives: " + player.getLives(), 10, 20);
		g.drawString("Money: " + player.getMoney(), 10, 40);
		g.drawString("Towers: ", 10, 70);
		
		for (int i = 0; i < side_menu.getComponents().length; i++) {
			side_menu.getComponent(i).paint(g);
		}
		
		printTowerStats(g, new Point(10,400));
	}
	
	private void drawMainMenu(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Tower Defense", 100, 20);
		g.drawString("Play Game", 100, 40);
		g.drawString("Options", 100, 70);
		g.drawString("Quit Game", 100, 120);
		
	}
//		for (int i = 0; i < main_menu.getComponents().length; i++) {
//			main_menu.getComponent(i).paint(g);
//		}
//	}
//	
	private void damagePlayer() {
		for (Critter critter : list_of_critters_on_map) {
			if (critter.isDamagePlayer()) {
				player.changeLives(-critter.getDamagingPower());
				field.getLayeredPane().remove(critter.getComponent());
				list_of_critters_on_map.remove(critter);
				break;
			}
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
				field.getLayeredPane().add(newTower.getComponent(), new Integer(1));
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
				
				field.getLayeredPane().remove(soldTower.getComponent());
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
		
		Queue<Critter> critters;
		Critter current_critter;
		
		if (button_selector.isStartWave()) {
			
			if (list_of_critters_on_map.isEmpty()) {

				critters = critter_factory.createWave(waveNumber);
				waveNumber++;
				
				while (!critters.isEmpty()) {
					current_critter = critters.poll();
					list_of_critters_on_map.add(current_critter);
					field.getLayeredPane().add(current_critter.getComponent(), new Integer(1));
					current_critter.startWalking();
				}
			}
				
		}
		button_selector.setStartWave(false);
		
	}
	
	private void moveCritters() {
		if (!list_of_critters_on_map.isEmpty()) {
			for (Critter critter : list_of_critters_on_map) {
				critter.startWalking();
			}
		}
	}
	
	private void printTowerStats(Graphics g, Point position) {
		
		g.setColor(Color.white);
		g.drawString("Tower stats:", position.x, position.y + 15);
		
		if (cell_selector.getSelectedCell() == null) {
			return;
		}
		
		Tower tower = cell_selector.getSelectedCell().getTowerInCell();
		
		if (tower == null) {
			return;
		}
		
		int upgradeLevel = tower.getUpgradeLevel();
		
		g.drawString("Level: " + upgradeLevel, position.x, position.y + 30);
		g.drawString("Power: " + tower.getPower(), position.x, position.y + 45);
		g.drawString("Range: " + tower.getRange(), position.x, position.y + 60);
		g.drawString("Rate of fire: " + tower.getRateOfFire(), position.x, position.y + 75);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		buyTower();
		sellTower();
		upgradeTower();
		moveCritters();
		startWave();
		damagePlayer();
		fireTowers();
		field.repaint();
		side_menu.repaint();
		
	}

	
	/*
	 * Getters and Setters
	 */
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public SideMenu getSideMenu() {
		return side_menu;
	}

	public void setSideMenu(SideMenu side_menu) {
		this.side_menu = side_menu;
	}
	
	

//	public MainMenu getMain_menu() {
//		return main_menu;
//	}
//
//	public void setMain_menu(MainMenu main_menu) {
//		this.main_menu = main_menu;
//	}



	
}
