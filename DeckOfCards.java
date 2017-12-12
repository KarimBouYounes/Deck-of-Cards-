//
//  Pre-Interview Code ETS
//  DeckOfCards.java
//
//  Created by Karim Bou Younes on 12/11/17.
//This program creates a deck of cards, shuffles it randomly , and deals cards 
//


import java.util.Vector;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;


//Class Card is a Card and has a face value and a suit
class Card {
	private String faceValue;
	private String suit;
		
	//constructor for Card
	Card(String f, String x) {
		setFaceValue(f);
		setSuit(x);
	}
	
	//sets the face value
	void setFaceValue(String fv) {
		faceValue = fv;
	}
	
	//gets the face value
	String getFaceValue() {
		return faceValue;
	}
		
	//sets the suit
	void setSuit(String s) {
		suit = s;
	}
	
	//gets the suit
	String getSuit() {
		return suit;
	}
		
}
	
//Class Deck is a deck of cards
class Deck {
	private Vector<Card> deck = new Vector<Card>(); //the vector deck is a deck of cards
	
	//the array faceValues contains all the face values in a deck of cards
	String[] faceValues = new String[] {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};  
	
	//the array suits contains all the suits in a deck of cards
	String[] suits = new String[] {"hearts", "spades", "clubs", "diamonds"};
		
	//constructor for deck
	//constructs the deck of cards
	Deck() {
		for(int i = 0; i < 13; i++) { //iterates through the faceValues array
			for(int j = 0; j < 4; j++) { //iterates through the suits array
				
				Card card = new Card (faceValues[i], suits[j]); //generates a Card by choosing
															   //a face value and a suit
				deck.add(card); //adds the card to the deck
			} 
		}
	}
	
	//shuffles the deck of card randomly
	void shuffle() {
		Random rand = new Random();
		
		//iterates through the whole deck of cards
		for(int i = 0; i < 52; i++) {
			int r = i + rand.nextInt(52 - i); //generates a random index "r"
			Collections.swap(deck, i, r); //swaps card at index "i" with 
										 //the card at the random index "r"
		}
	}
	
	//deals a card from the deck of cards
	Card dealOneCard() {
		if(deck.isEmpty()) { //if the deck is empty, inform the user, and return null
			System.out.println("The deck is empty, all cards have been dealt!");
			return null;
		}
		
		//else, pick a card from the deck, return it, and remove it from the deck
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}
}
	
public class DeckOfCards {
	
	public static void main(String[] args) {
		
		System.out.println("\t *** Deck of Cards ***");
		Deck deck = new Deck(); //constructs a deck of cards
		System.out.println("Shuffling the deck of cards...");
		deck.shuffle(); //call for shuffling the deck
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Each time you want to be dealt a card, enter the letter d");
		System.out.println("If at any time you want to exit, enter the letter e");
		System.out.println("If you want to be dealt a precise amount of cards, enter the number here, or press d to be dealt the first card");
		
		
		String command = scan.nextLine();
		command = command.toLowerCase();
		
		int number = -1; //number of cards to be dealt if the user 
						 //chooses to be dealt a specific number of cards
		boolean dealCard; 
		
		//if user enters d, we start dealing cards
		if(command.equals("d")) {
			dealCard = true;
			
		//if user enters e, we exit the program	
		} else if(command.equals("e")) {
			dealCard = false;
			System.out.println("Exiting the program");
			
		//else, we verify if the user has entered a specific number of cards that he wants
		} else {
			
			number = Integer.parseInt(command);
			
			//if number is valid we deal the cards
			if (number > 0) {
				dealCard = true;
				
			//else, we inform the user that he has entered an unknown command 
			//and we exit the program
			} else {
				dealCard = false;
				System.out.println("Unknown command");
				System.out.println("Exiting the program");
			}
		}
		
		
		// while the user still wants to be dealt cards or 
		//while the specified number of cards has not yet been dealt, 
		//deal cards
		while (dealCard || number > 0) {
			
			Card c = deck.dealOneCard(); //pick a card from the deck of cards
			
			//if the deck of cards is empty, we exit the program
			if(c == null) {
				break;
			}
			
			
			System.out.print(c.getFaceValue()); //prints the face value of the card
			System.out.print(" of ");
			System.out.println(c.getSuit()); //prints the suit of the card
			
			//if no specific number of cards has been requested by the user,
			//we wait for his next command
			if(number < 0) {
			
				command = scan.nextLine();
				command = command.toLowerCase();
				
				//if user enters d, we deal the next card
				if(command.equals("d")) {
					dealCard = true;
				
				//if user enters e, we exit the program
				} else if(command.equals("e")) {
					dealCard = false;
					System.out.println("Exiting the program");
					
				//else, we inform the user that he has entered an unknown command 
				//and we exit the program
				} else {
					dealCard = false;
					System.out.println("Unknown command");
					System.out.println("Exiting the program");
				}
			
			//if a specific number of cards has been requested by the user,
			//we decrement the variable "number"
			} else {
				number = number -1;
				if(number == 0) {
					dealCard = false;
				}
			}
			
		}
		
		scan.close(); //closes the scanner
		

	}


}
