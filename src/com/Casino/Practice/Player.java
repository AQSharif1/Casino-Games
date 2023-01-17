package com.Casino.Practice;

/**
 * Player Class
 * @author Abdul
 *
 */
public class Player {
	private String name;
	protected int balance = 1000;
	protected int card1,card2,bet;
	protected int score;
	protected int colorChoice;
	
	//Constructor
	public Player(String name) {
		this.name = name;
		this.card1 = (int)(Math.random()*11 +1);
		this.card2 = (int)(Math.random()*11 +1) ;
		this.score = card1 + card2; 
		
	
	}
	 /**
	  * Card 1 Getter
	  * @return card 1 value
	  */
	public int getCard1() {
		return card1;
	}

	/**
	 * Card 2 Getter
	 * @return card 2 value
	 */
	public int getCard2() {
		return card2;
	}

	/**
	 * Score Getter
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Name Getter
	 * @return player name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Blance Getter
	 * @return Player balance
	 */
	public int getBalance() {
		return this.balance;
	}
	/**
	 * Creates new player
	 * @param newName
	 * @return
	 */
	public static Player createPlayer(String newName) {
		return new Player(newName);
	}
}
