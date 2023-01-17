package com.Casino.Practice;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Roulette Class
 * @author Abdul
 *
 */
public class Roulette {
	protected int color; // Roulette Color
	private int number; // Player score
	private int bet; // Player bet
	private ArrayList<Player> rPlayers; // Roulette Player List
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Constructor
	 */
	public Roulette() {
		this.color = (int) (Math.random()*2 +1);
		this.number = ((int) Math.random()*36 +1);
		this.rPlayers = new ArrayList<Player>();
	}
	
	/**
	 * Add Player
	 * @param player
	 * @return
	 */
	public boolean addPlayer(String name) {
		Player newPlayer = new Player(name);
		if(rPlayers.contains(newPlayer)) {
			System.out.println(name + " is Already playing.");
			return false;
		}else {
			rPlayers.add(newPlayer);
			System.out.println(name + " Added ----> Starting balance: $"+newPlayer.balance );
		}
		return true;
	}

	/**
	 * Add Player
	 * @param newPlayer
	 * @return
	 */
	public boolean addPlayer(Player newPlayer) {
		if(rPlayers.contains(newPlayer)==false) {
			rPlayers.add(newPlayer);
			System.out.println(newPlayer.getName() + " Added ----> Starting balance: $"+newPlayer.balance );
		}else if (rPlayers.contains(newPlayer) == true){
				System.out.println(newPlayer.getName() + " already Playing");
			return true;
		}
		return false;
	}

	/**
	 * Remove Player
	 * @param player
	 * @return
	 */
	public boolean removePlayer(Player player) {
		if(findPlayer(player)> 0) {
			System.out.println(player.getName() + " is already Playing");
			return false;
		}
		else 
			rPlayers.add(player);
			System.out.println(player.getName() + " Added ----> Starting balance: $"+player.balance );	
		return true;
	}

	/**
	 * Remove Player 
	 * @param newName
	 * @return True if player removed, false if not
	 */
	public boolean removePlayer(String newName) {
		for(int i =0; i< rPlayers.size(); i++) {
			if(rPlayers.get(i).getName().equalsIgnoreCase(newName)) {
				rPlayers.remove(i);
				System.out.println(newName + " has been removed");
				return true;
			}
		}
		System.out.println("Player " + newName + " is not playing");
		return false;
	}

	/**
	 * Place Player Bets
	 */
	public void bet() {
		for(int i =0; i < rPlayers.size(); i++) {
			System.out.println("\nHow much are you betting " + rPlayers.get(i).getName());
			int amount = scanner.nextInt();
			rPlayers.get(i);
			
			while(amount > rPlayers.get(i).balance) {
				System.out.println("Hold up G!! You aint ballin like that, Yo balance: $"+ rPlayers.get(i).balance + "\nPick a different amount.");
				amount = scanner.nextInt();
			}
			if(amount <= rPlayers.get(i).balance) {
				//rPlayers.get(i);
				int newBalance = rPlayers.get(i).balance - amount;

				rPlayers.get(i).balance = newBalance;

				//	rPlayers.get(i);
				//	System.out.println(rPlayers.get(i).getName() +" Bet: " + amount );//+ " \nRemaining cash: " + rPlayers.get(i).balance + " \n");
			}
			rPlayers.get(i).bet = amount;
			this.bet = amount;
		}
	}
	
	/**
	 * Find player 
	 * @param name 
	 * @return
	 */
	public int findPlayer(String name) {
		for(Player i : rPlayers) {
			if(i.getName().equalsIgnoreCase(name)) {
				return rPlayers.indexOf(i);
			}
		}
		return -1;
	}
	/**Find player index
	 * 
	 * @param name
	 * @return
	 */
	public int findPlayer(Player name) {
		return rPlayers.indexOf(name);
	}

	/**
	 * Play Roulette
	 */
	public void playRoulette() {
		bet();
		for(Player i: rPlayers) {
			System.out.println("Which are you betting on " + i.getName() +"? \n1.BLACK \n2.RED");
			int newChoice = scanner.nextInt();
			switch(newChoice) {
			case 1:
				System.out.println(i.getName() +" chose Black");
				i.colorChoice =newChoice;
				break;
			case 2:
				System.out.println(i.getName() +" chose red");
				i.colorChoice = newChoice;
				break;
			}
			if(color == 1) {
				if(i.colorChoice == color) {
					System.out.println("Roulette:BLACK!! You win!!");
					i.balance += (i.bet*2);
				}else {
					System.out.println("Roulette: BLACK!! You Lose!!");
				}
			}
			if(color == 2) {
				if(i.colorChoice ==color) {
					System.out.println("Roulette: RED!! You win!!");
					i.balance += (i.bet*2);
				}else {
					System.out.println("Roulette: RED!! You Lose!!");
				}
			}

		}

	}
	
	/**
	 * Get Player Balances
	 * @param name
	 */
	public void balance(String name) {
		for(Player i: rPlayers) {
			if(i.getName().equalsIgnoreCase(name)) {
				System.out.println("Balance: " + i.getBalance());
				return;
			}
		}
		System.out.println("Player not playing");
	}
	
	/**
	 * Print Players
	 */
	public void print() {
		if(rPlayers.size() <=0) {
			System.out.println("NO PLAYERS!!\nADD PLAYERS!!");
			return;
		}
		System.out.println("ROULETTE PLAYERS: ");
		for(Player i : rPlayers) {
			System.out.println(i.getName());
		}
	}

	public void writeOnFile() {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("BlackJack"));
			write.write("Players:");
			for(int i = 0; i< rPlayers.size(); i++) {
				write.write("\n"+rPlayers.get(i).getName());
			}
			write.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
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
