package com.Casino;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * BlackJack Class
 * @author Abdul
 *
 */
final class BlackJack extends Casino{
	public static Scanner scanner = new Scanner(System.in);


	//private final int gamePt = 21;
	protected boolean hit;
	protected int  dealerScore, bet, freshHand; 
	protected int playerScore;
	private ArrayList<Player> players; // arrayList of players


	/**
	 * Constructor
	 */
	public BlackJack() {
		this.dealerScore = (int)(Math.random()*11 +1) + (int)(Math.random()*11 +1); //generate dealer score
		this.playerScore = (int)(Math.random()*11 +1) + (int)(Math.random()*11 +1);
		this.players = new ArrayList<>(); // array initialization 
	}
	//*************************************FIXXXXXXX*******************************************
	/**
	 * Method adds player using string name
	 * @param name
	 * @return true if player added, false if not
	 */
	public boolean addPlayer(String name) {
		Player newPlayer = new Player(name);
		if(players.contains(newPlayer)) {
			System.out.println(name + " is Already playing.");
			return false;
		}else {
			players.add(newPlayer);
			System.out.println(name + " Added ----> Starting balance: $"+newPlayer.balance );
		}
		return true;
	}

	/**
	 * Adds new player using player object
	 * @param newPlayer
	 * @return returns false if player already added, returns true if player added
	 */
	public boolean addPlayer(Player newPlayer) {
		if(findPlayer(newPlayer)> 0) {
			System.out.println(newPlayer.getName() + " is already Playing");
			return false;
		}
		else 
			players.add(newPlayer);
		System.out.println(newPlayer.getName() + " Added ----> Starting balance: $"+newPlayer.balance );	
		return true;
	}
	/**
	 * Method removes player using object
	 * @param player
	 * @return true if player removed, false if player not playing
	 */
	public boolean removePlayer(Player player) {
		if(findPlayer(player)> 0) {
			players.remove(player);
			System.out.println("Player " + player.getName() + " has been removed");
			return true;
		}
		else
			System.out.println("Player " + player.getName() + " is not playing");
		return false;
	}

	/**
	 * method removes player using String name
	 * @param newName
	 * @return true if player removed, false if not
	 */
	public boolean removePlayer(String newPlayer) {
		if(findPlayer(newPlayer)> 0) {
			players.remove(findPlayer(newPlayer));
			System.out.println(newPlayer + " has been removed");
			return true;
		}
		else 
			System.out.println("Player " + newPlayer + " is not playing");
		return false;
	}
	/**
	 * Method allows player to bet 
	 */
	public void bet() {

		for(int i =0; i < players.size(); i++) {
			System.out.println("\nHow much are you betting " + players.get(i).getName());
			int amount = scanner.nextInt();

			while(amount > players.get(i).balance) {
				System.out.println("You don't have enough chips, chip balance: $"+ players.get(i).balance + "\nbet a different amount.");
				amount = scanner.nextInt();
			}
			if(amount <= players.get(i).balance) {
				int newBalance = players.get(i).balance - amount;
				players.get(i).balance = newBalance;
			}

			players.get(i).bet = amount;

		}
	}

	/**
	 * Method finds player index using player object
	 * @param name
	 * @return index of player or -1 if player not found
	 */
	public int findPlayer(Player name) {
		return players.indexOf(name);
	}

	/**
	 * Finds player index with String name
	 * @param name
	 * @return index of player
	 */
	public int findPlayer(String name) {
		for(int i=0; i<this.players.size(); i++) {
			Player contact = this.players.get(i);
			if(contact.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Generates card numbers
	 * @param name
	 * @return card value
	 */
	public int cards(String name) {
		int newCard =0;

		for(int i =0; i< players.size(); i++) {
			players.get(i).score = playerScore;

			if(players.get(i).score <= 21) {
				if(players.get(i).getName().equalsIgnoreCase(name)) {
					int answer = 1;
					while(answer == 1) {
						System.out.println(players.get(i).getName() + " Hand: "+ 
								players.get(i).score +
								"\nDo you want to hit?  \n1.HIT \n2.STAY");
						answer = scanner.nextInt();
						if(answer == 2) {
							return players.get(i).score;
						}
						newCard = (int)(Math.random()*11 +1);
						players.get(i).score +=newCard;
						System.out.println("\nNew Hit Card: " + newCard);

						if(players.get(i).score > 21) {
							System.out.println("\nBust!! Drew "+ newCard + 
									" Score: "+ players.get(i).score);
							players.get(i).score = -1;
							return -1;
						}
					}
					return players.get(i).score ;
				}
			}
			else
				System.out.println("Bust!!");
			players.get(i).score = -1;
		}
		return -1;
	}

	/**
	 * Player Balance
	 * @param name
	 */
	public void balance(String name) {
		for(int i =0; i< players.size(); i++) {
			if(players.get(i).getName().equalsIgnoreCase(name)) {
				System.out.println(players.get(i).getName() + 
						"'s Balance: $" + players.get(i).balance );
			}
		}
	}

	/**
	 * Method starts a round of blackJack
	 */
	public void playGame() {
		if(players.size() > 0) {
			bet();

			dealerHand();
			System.out.println("\n");
			for(Player i : players) {
				if(i.score > dealerScore ) {
					i.balance += (i.bet * 2);
					System.out.println("\n" +i.getName() + " YOU WIN!");
				} 
				else if(i.score == dealerScore) {
					System.out.println("\n" +i.getName() +" TIE!");
					i.balance += i.bet;
				}
				else if(i.score < dealerScore)
					System.out.println("\n" + i.getName() +" YOU LOSE!");
			}
		}
		else
			System.out.println("\nNO PLAYERS!!\nADD PLAYERS!!");
	}

	/**
	 * Deals the dealers hand
	 * @return dealer hand
	 */
	public int dealerHand() {
		if(dealerScore>21) {
			System.out.println("\nBUST!!");
			dealerScore = -1;
		}
		System.out.println("\nDealer hand: " + dealerScore);
		for(Player i : players) {
			System.out.println("\n");
			cards(i.getName());
		}
		while(dealerScore < 17) {

			int exNum = (int)(Math.random()*11 +1);
			dealerScore += exNum;
			if(dealerScore > 21) {
				System.out.println("\nNew Card: " + exNum);
				System.out.println("\nDealer Bust: " + dealerScore);
				dealerScore = -1;
				return -1;
			}
			System.out.println("\nDealer Hits, Card: " + exNum +
					" \nDealer new Hand: " +dealerScore);
		}
		return dealerScore ;
	}

	/**
	 * Print List of Players
	 */
	public void print() {
		if(players.size() <=0) {
			System.out.println("\nNO PLAYERS!!\nADD PLAYERS!!");
			return;
		}
		System.out.println("\nPLAYERS: ");
		for(Player i : players) {

			System.out.println("\n" + i.getName());
		}
	}

	/**
	 * Writes on a file user input
	 */
	public void writeOnFile() {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("BlackJack"));
			write.write("Players:");
			for(int i = 0; i< players.size(); i++) {
				write.write("\n"+players.get(i).getName());
			}
			write.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Reads file
	 */
	public void readOnFile() {
		try {
			BufferedReader read = new BufferedReader(new FileReader("BlackJack"));
			String line;
			while((line = read.readLine()) != null) {
				System.out.println("\n"+line);
			}
			read.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
