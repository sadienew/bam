/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.security.SecureRandom;

/**
 *
 * @author sadie
 */
public class DeckOfCards {
    private Card [] deck;       //array of card objects
    private int currentCard;
    private static final int NOCARDS = 52; //constant # of cards
    
    //random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
     //constructor
    public DeckOfCards()
    {
        //assign value to cards
        String [] faces = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
        String [] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        
        
        //create an array of card objects
        deck = new Card[NOCARDS];
        currentCard=0;
        
        //populate deck with Card objects
        
        for(int i=0; i<deck.length; i++)
        {
            deck[i]= new Card(suits[i/13],faces[i%13], 0);
            int value = deck[i].getValue();
            deck[i].value = value;
            
        }
        
    }
    
    //shuffle deck of cards
    public void shuffle()
    {
        //reset currentCard index to 0
        currentCard=0;
        
        //for each Card, pick another random Card and swap
        for(int i=0;i<deck.length;i++)
        {
            //select a random number between 0-51
            int j= randomNumbers.nextInt(NOCARDS);
            
            //swap current card with random card
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;            
        }
    }
    
    public Card dealCard()
    {
        //check to see if not at end of deck, return card
        if(currentCard <deck.length)
            return deck[currentCard++];
        else
            return null;    //all cards have been dealt
    }
}
