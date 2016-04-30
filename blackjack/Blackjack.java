/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author sadie
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // TODO code application logic here
        DeckOfCards myDeck = new DeckOfCards();   
        myDeck.shuffle();
              
        //determine number of players
        int noPlayers = numPlayers();
        
        //create an array of players
        Player [] players = new Player[noPlayers];
        
        //get bets for each player, and deal out cards to players
         for(int i=0;i<noPlayers;i++)
         {
             Card [] dealCards = {myDeck.dealCard(),myDeck.dealCard()};
             players[i] = new Player(dealCards,placeBets(i),0);
             int sum = players[i].calcTotal();
             players[i].total = sum;
            
         }  
         
         //deal cards out to dealer
         Card [] dealToDealer={myDeck.dealCard(), myDeck.dealCard()};
         Dealer dealer = new Dealer(dealToDealer,0);
         //calculate
         dealer.total=dealer.calcTotal();
         
        //print player and dealer info
         printPlayerInfo(players, dealer);
         
         
         
         
         //ask user if they want their ace to have a value of 1 or 11
         for(int i=0;i<players.length;i++)
         {
             int aceVal = 0;
             for(int j=0; j<players[i].cards.length;j++)
             {
                 //if players have an ace...
                if(players[i].cards[j].face == "Ace" )
                {
                    //prompt player for value of ace
                    Scanner scan = new Scanner(System.in);
                    System.out.printf("Player %d, do you want the value of your Ace to be 1 or 11?", i+1);
                    String input = scan.nextLine();
                    //convert input to an int
                    aceVal = Integer.parseInt(input);
                    //check to see if value entered was a 1 or 11
                    while(aceVal != 1 && aceVal != 11)
                    {
                        System.out.print("Invalid value. Please enter a 1 or 11.");
                        input = scan.nextLine();
                        aceVal= Integer.parseInt(input);
                    }
                    //change value of ace card to input
                    players[i].cards[j].value = aceVal;
                }
             }   
         }
         
         
         //reprint players info with new values for aces
         printPlayerInfo(players, dealer);
        
    }
    
    //ask for number of players
    public static int numPlayers()
    {
        Scanner scanner = new Scanner(System.in);
        
        //prompt user
        System.out.print("How many players?");
        String in = scanner.nextLine();
     
        //convert user input to int
        int players = Integer.parseInt(in);

        //check to see if num of players is between 2-8
        while(players > 8 || players < 2)
        {
            System.out.print("Number of players must be between 2 and 8. Try again.\n");
            String input = scanner.nextLine();
            players = Integer.parseInt(input);
        }
        //return number of players
        return players;
    }
    
    //get bets from players
    public static int placeBets(int i)
    {
        //declare and initialize bet variable
        int bet=0;
        
        //prompt user
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Player %d place your bet: \n", i+1);
        //read in input from user
        String betsIn = scanner.nextLine();
        //convert to int value
        bet= Integer.parseInt(betsIn);
        
        //check to see if bet placed is between 50-500
        while(bet<50 || bet >500)
        {
            System.out.print("Bets must be between 50 and 500. Try again.\n");
            String bet2 = scanner.nextLine();
            bet = Integer.parseInt(bet2);
        }
        
        //return bet
        return bet;
    }
    
    public static void printPlayerInfo(Player [] players, Dealer dealer)
    {
        for(int i=0;i<players.length;i++)
         {
             
             System.out.printf("Player %d: %s\n", i+1, players[i].toString());
         }
         System.out.printf("Dealer: %s\n", dealer.toString());
    }
    
}
