package controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javafx.scene.control.Cell;

import javax.swing.Timer;

import map.Map;
import critterModels.Critter;
import domain.Player;
import presentation.*;
import towerModels.*;

public class GameController implements ActionListener {

	private Player player;
	private Map map;
	private Field field;
	private Timer timer;
	private MouseMaster mouse_master;
	private ArrayList<Tower> list_of_towers_on_map;
	private ArrayList<Critter> list_of_critters_on_map;
	
	private boolean mouseClicked;
	private int mousePos[];
	private int mouse_position_at_click[];
	
	public GameController() {
		
		mouse_master = new MouseMaster();
		player = Player.getPlayerInstance();
		map = Map.createGeneric();
		list_of_critters_on_map = new ArrayList<>();
		list_of_towers_on_map = new ArrayList<>();
		
		//create Field with paint function defined in controller
		setField(new Field(mouse_master) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
		        Toolkit.getDefaultToolkit().sync();
			}
		});
		
		timer = new Timer(Application.TIMEOUT,this);
		timer.start();
		
	}
	
	protected void doDrawing(Graphics g) {
		drawGrid(g);		
	}
	
	private void drawGrid(Graphics g) {
		
		for (int i = 0; i < map.Grid.length; i++) {
			for (int j = 0; j < map.Grid[0].length; j++) {
				map.Grid[i][j].drawCell(g);
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
		
		
		
	}
	
	private void placeTower() {
		
		field.getGraphics().setColor(Color.BLACK);
		field.getGraphics().drawRect(mouse_position_at_click[0], mouse_position_at_click[1], 15, 15);
		field.paint(field.getGraphics());
//		field.paintComponents(field.getGraphics());
	}
	
	private void sellTower(Tower tower) {
		player.changeMoney(tower.getRefundValue());
		list_of_towers_on_map.remove(tower);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (mouseClicked) {
			mouseClicked = false;
			System.out.println("x="+mouse_position_at_click[0]+" y="+mouse_position_at_click[1]);
			placeTower();
		}
		
		fireTowers();
		field.repaint();
		
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
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	public int[] getMousePos() {
		return mousePos;
	}

	public void setMousePos(int mousePos[]) {
		this.mousePos = mousePos;
	}

	public int[] getMousePositionAtClick() {
		return mouse_position_at_click;
	}

	public void setMousePositionAtClick(int mouse_position_at_click[]) {
		this.mouse_position_at_click = mouse_position_at_click;
	}

	private class MouseMaster extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			// Selects a cell
			for (int i = 0; i < map.Grid.length; i++) {
				for (int j = 0; j < map.Grid[0].length; j++) {
					map.Grid[i][j].selectCell(e.getX(), e.getY());;
				}
			}
			
			setMouseClicked(true);
			int temp[] = new int[2];
			temp[0] = e.getX();
			temp[1] = e.getY();
			setMousePositionAtClick(temp);

		}
		
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
			int temp[] = new int[2];
			temp[0] = e.getX();
			temp[1] = e.getY();
			setMousePos(temp);

		}

	}

	
}
