// Placeholder for the actual critter class
package domain;

import java.io.Serializable;


public class Player implements Serializable{
	
	private final int LIVES_AT_START = 10;
	private final int MONEY_AT_START = 200;
	
	private int lives;
	private int money;
	private String name;
	private static Player player;
	
	//TODO Set up player name here
	private Player() {
		setLives(LIVES_AT_START);
		setMoney(MONEY_AT_START);
	}

	/**
	 * Getter for the singleton Player.
	 * @return The Player.
	 */
	public static Player getPlayerInstance() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}
	
	/**
	 * Changes the amount of lives the player has.
	 * @param change_in_lives The amount of lives that should be added to the current amount of lives the player has.
	 */
	public void changeLives(int change_in_lives) {
		setLives(getLives() + change_in_lives);
	}
	
	/**
	 * Changes the amount of money the player has.
	 * @param change_in_lives The amount of money that should be added to the current amount of money the player has.
	 */
	public void changeMoney(int change_in_money) {
		setMoney(getMoney() + change_in_money);
	}
	
	public boolean isDead() {
		if (lives <= 0)
			return true;
		return false;
	}
	
	
	/*
	 * Getters and Setters
	 */
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void restartPlayer() {
		player.lives = LIVES_AT_START;
		player.money = MONEY_AT_START;
	}
	
}