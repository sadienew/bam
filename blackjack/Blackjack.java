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
   // @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // TODO code application logic here
        DeckOfCards myDeck = new DeckOfCards();   
        myDeck.shuffle();
              
        //determine number of players
        int noPlayers = numPlayers();
        
        //create an array of players
        Player [] players = new Player[noPlayers];
        
        //deal out cards to players, and get bets for each player
         for(int i=0;i<noPlayers;i++)
         {
             Card [] dealCards = {myDeck.dealCard(),myDeck.dealCard()};             
             players[i] = new Player(dealCards,placeBets(i, 50, 500),0);             
             
             //calculate value of player's hand
             int sum = players[i].calcTotal();
             players[i].value = sum;
             players[i].winnings = 0;
            
         }
         
         //deal cards out to dealer
        Card [] dealToDealer={myDeck.dealCard()};
        Dealer dealer = new Dealer(dealToDealer,0);
        
        //calculate value of dealer's hand
         dealer.value=dealer.calcTotal();

        //print player and dealer info
         printPlayerInfo(players, dealer);
         System.out.println("");
       
         //determine if insurance bet is a possibility
        if(dealer.cards[0].face.equals("Ace"))
        {
            //call insurance function
            insurance(players, dealer, myDeck);
        }
        else
        {
            //deal second card to dealer.
            dealer.cards = addElement(dealer.cards, myDeck.dealCard());
            
            //print out dealer's hand
            System.out.printf("Dealer's hand: %s\n",dealer.toString());
        }
         
        
        players = split(players, myDeck);
        
        
         //ask user if they want their ace to have a value of 1 or 11
         for(int i=0;i<players.length;i++)
         {
            int aceVal = 0; 
             for(int j=0; j<players[i].cards.length;j++)
             {
                 //if players have an ace...
                if(players[i].cards[j].face.equals("Ace" ))
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
                    players[i].cards[j].points = aceVal;
                }
             }   
         }
         

         
        System.out.println("");
        //reprint players info with new values for aces
        printPlayerInfo(players, dealer);
        System.out.println("");
        
        //if dealer has a blackjack
        isDealerBlackJack(players, dealer);        
        
        //start stand or hit for each player
        for(int i=0;i<players.length;i++)
        {
            //prompt user to stand or hit
            Scanner in = new Scanner(System.in);
            System.out.printf("Player %d, Would you like to stand or hit?\nS=stand, H=hit\n", i+1);
            //read in input from user
            String ans = in.nextLine();
            
            //until player enters S...
            while(ans.toUpperCase().equals("H"))
            {
                //draw another card for player
                players[i].cards = addElement(players[i].cards, myDeck.dealCard());
                
                //print out player's hand with new card
                for(int j=0;j<players[i].cards.length;j++)
                {
                    System.out.printf("Card %d: %s\n", j+1, players[i].cards[j]);
                }
                
                //players[i].calcTotal();
                System.out.printf("Player %d's hand value = %s\n", i+1, players[i].calcTotal());
                System.out.println("");
                
                System.out.println("Stand or hit again?\nS=stand, H=hit\n");
                ans = in.nextLine();
            }
            
            System.out.printf("Player %d's info= %s\n",i+1,players[i].toString());
        }
        
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
    public static int placeBets(int i, int min, int max)
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
        while(bet<min || bet >max)
        {
            System.out.printf("Bets must be between %d and %d. Try again.\n", min, max);
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
    
    //determine if dealer's first card is an ace to start insurance bets
    public static void insurance(Player [] players, Dealer dealer, DeckOfCards myDeck)
    {
        //inform users the dealer's first card is an ace
        System.out.println("The Dealer has an Ace!! \nAnyone care to place an insurnace bet?");
        
        //prompt players to place insurance bets (if dealer's 2nd card has value of 10)
        for(int i=0;i<players.length;i++)
        {
            players[i].insuranceBet = placeInsBets(i,players[i].bet);                 
        }
             
        //deal second card to dealer.
        dealer.cards = addElement(dealer.cards, myDeck.dealCard());
        
        //print out dealer's hand
        System.out.printf("Dealer's hand: %s\n",dealer.toString());
            
        //determine if players get insurance bet winnings
            
        if(dealer.cards[1].points == 10)
        {
            for(int i=0;i<players.length;i++)
            {
                players[i].winnings = 2* players[i].insuranceBet;
            }
        }
    }
    
        //get insurance bets from players
    public static int placeInsBets(int i, int insurBet)
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
        while(bet > insurBet/2)
        {
            System.out.printf("Bets must not exceed half your original wager\n");
            String bet2 = scanner.nextLine();
            bet = Integer.parseInt(bet2);
        }
        
        //return bet
        return bet;
    }
    
    //add element to Card array
    public static Card [] addElement(Card [] original, Card... newElements)
    {
        Card[] newArray = new Card[original.length+1];
        System.arraycopy(original, 0, newArray, 0, original.length);
        System.arraycopy(newElements, 0, newArray, original.length, newElements.length);
        return newArray;
    }
    
    public static Player [] addPlayer(Player [] original, Player... newPlayers)
    {
        Player [] newArray = new Player[original.length+1];
        System.arraycopy(original, 0, newArray, 0, original.length);
        System.arraycopy(newPlayers, 0, newArray, original.length, newPlayers.length);
        return newArray;
    }
    
    public static void isDealerBlackJack(Player [] players, Dealer dealer)
    {
        if(dealer.value == 21)
        {
            //check to see if any of the players also have blackjack
            for(int i=0;i<players.length;i++)
            {
                //if they do, this results in a push
                if(players[i].value == 21)
                {
                    //push
                    //their original bet is returned to them
                    players[i].winnings += players[i].bet;
                    System.out.printf("Player %d's winnings: $%s",i+1, players[i].winnings);
                }
                //if theirs does not equal 21, then they lose
                else
                {
                    //and no money is returned to them
                    players[i].winnings += 0;
                    System.out.printf("Player %d's winnings: $%s",i+1, players[i].winnings);
                }
            }
            System.exit(0);
        }
    }
    
    
    //split the player's hand
    public static Player [] split(Player [] players, DeckOfCards myDeck)
    {
        for(int i=0;i<players.length;i++)
        {
            //if the player's face values 
            if(players[i].cards[0].face.equals(players[i].cards[1].face))
            {
                Scanner scanner = new Scanner(System.in);
                System.out.printf("Player %d, would you like to split your hand?\nY=yes N=no", i+1);
                String ans = scanner.nextLine();
                
                if(ans.toUpperCase().equals("Y"));
                {
                    Card [] hand = {players[i].cards[1],myDeck.dealCard()};
                    Player newPlayer = new Player(hand,players[i].bet,0);
                    newPlayer.value = newPlayer.calcTotal();
                    players = addPlayer(players,newPlayer);
                    players[i].cards[1] = myDeck.dealCard();
                }
            }
        }
        return players;
    }
}
