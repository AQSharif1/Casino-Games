
package com.Casino;

import java.util.Scanner;


/**
 * Main Class
 * @author Abdul
 *
 */
public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static BlackJack players = new BlackJack();
	private static Roulette rPlayers = new Roulette();
	public static void main(String[] args) {
	
	
		
		int choice = 0;
		int gameType =0;
		boolean quit = false;

		
		System.out.println("WELCOME TO Abduls CASINO");
		System.out.println("What Do You Want To Play? \n1.BlackJack \n2.Roulette");
		gameType = scanner.nextInt();
		
		switch(gameType) {
		case 1:
			while(!quit) {
				
				System.out.println("\nEnter your choice: ");
				menu();
				choice = scanner.nextInt();
				scanner.nextLine();
				
				switch (choice) {
				case 1:
					addPlayer();
					break;
					
				case 2:
					removePlayer();
					break;
					
				case 3:
					playGame();
					break;
					
				case 4:
					findBalance();
					break;
					
				case 5:
					print();
					break;
				
				case 6:
					quit = true;
					System.out.println("GOODBYE!!!");
					break;	
				case 7:
					writeOnFile();
					break;
				case 8:
					readOnFile();
				}
			}
			
			break;
			case 2:
				while(!quit) {
				
				System.out.println("\nEnter your choice: ");
				rMenu();
				choice = scanner.nextInt();
				scanner.nextLine();
				
				switch (choice) {
				case 1:
					addRPlayer();
					break;
					
				case 2:
					removeRPlayer();
					break;
					
				case 3:
					playRGame();
					break;
					
				case 4:
					findRBalance();
					break;
					
				case 5:
					rPrint();
					break;
				
				case 6:
					quit = true;
					System.out.println("GOOODBYE!");
					break;
				case 7:
					gameType = 1;
					break;
					
				}
				}
			
				break;
		}
		
		
	}
		
	public static void menu() {
	
		System.out.println("\n1.Add Player \n2.Remove Player\n3.Play Blackjack"+
				" \n4.Find Balance\n5.print \n6.Quit \n7.Write On File \n8.Read on File");
	}
	
	public static void addPlayer() {
		System.out.println("\nPlayer name");
		String newName = scanner.nextLine();
		//Player newPlayer = Player.createPlayer(newName);
		players.addPlayer(newName);
	}
	
	public static void removePlayer() {
		System.out.println("\nWhich player is leaving?");
		String player = scanner.nextLine();
		players.removePlayer(player);		
	}
	
	public static void findBalance() {
	
		System.out.println("\nWhich players balance are you checking?");
		String name = scanner.nextLine();
		players.balance(name);
		if(players.findPlayer(name) < 0) {
			System.out.println(name + " is not Playing!");
			}
	}
	
	public static void playGame() {
		players.playGame();
		players.dealerScore = (int)(Math.random()*11 +1) + (int)(Math.random()*11 +1);
		players.playerScore = (int)(Math.random()*11 +1) + (int)(Math.random()*11 +1);
	}
	
	public static void print() {
		System.out.println("\n");
		players.print();
	}
	
	public static void writeOnFile() {
		players.writeOnFile();
		System.out.println("\nData written on File.");
	}
	
	public static void readOnFile() {
		players.readOnFile();
	}
	
	//******************************ROULETTE**************************************************************8
	
	
	public static void rMenu() {
		System.out.println("\n1.Add Player \n2.Remove Player\n3.Play Roulette"+
	" \n4.Find Balance\n5.print \n6.Quit ");
	}
	
	public static void addRPlayer() {
		System.out.println("\nEnter Player Name");
		String newName = scanner.nextLine(); 
		Player newPlayer = new Player(newName);
				rPlayers.addPlayer(newPlayer);
				if(rPlayers.addPlayer(newPlayer)) {
			
				}
	}
	
	public static void removeRPlayer() {
		System.out.println("\nWhich player is leaving?");
		String player = scanner.nextLine();
		rPlayers.removePlayer(player);
	}
	
	public static void findRBalance() {

		System.out.println("\nWhich players balance are you checking?");
		String name = scanner.nextLine();
		rPlayers.balance(name);
		if(rPlayers.findPlayer(name) < 0) {
			System.out.println(name + " is not Playing!");
		}	
	}
	
	public static void playRGame() {
		rPlayers.playRoulette();
		rPlayers.color= (int) (Math.random()*2 +1);
	}
	
	public static void rPrint() {
		System.out.println("\n");
		rPlayers.print();
	}
}
