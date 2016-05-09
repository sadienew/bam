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
        
        Card [] dealCards = {myDeck.dealCard(),myDeck.dealCard()};
        Player player = new Player(dealCards, placeBets(50,500),0,0);
        
        
         //deal cards out to dealer
        Card [] dealToDealer={myDeck.dealCard(), myDeck.dealCard()};
        Dealer dealer = new Dealer(dealToDealer,0);
        
        //calculate value of dealer's hand
         dealer.value=dealer.calcTotal();

        //print player and dealer info
         printPlayerInfo(player, dealer);
         System.out.println("");
        
        
        //ask user if they want their ace to have a value of 1 or 11
        int aceVal = 0; 
        int change = 0;
        for(int j=0; j<player.cards.length;j++)
        {
            //if players have an ace...
            if(player.cards[j].face.equals("Ace" ))
            {
                change += 1;
                //prompt player for value of ace
                Scanner scan = new Scanner(System.in);
                System.out.println("Player, do you want the value of your Ace to be 1 or 11?");
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
                player.cards[j].points = aceVal;
            }
        }   
         
         

         
        System.out.println("");
        //reprint players info with new values for aces
        if(change > 0)
        {
            printPlayerInfo(player, dealer);
            System.out.println("");
        }
        
        //if dealer has a blackjack
        isDealerBlackJack(player, dealer);        
        
        //start stand or hit for each player
            //prompt user to stand or hit
            Scanner in = new Scanner(System.in);
            System.out.print("Player, Would you like to stand or hit?\nS=stand, H=hit\n");
            //read in input from user
            String ans = in.nextLine();
            
            //until player enters S...
            while(ans.toUpperCase().equals("H"))
            {
                //draw another card for player
                player.cards = addElement(player.cards, myDeck.dealCard());
                
                //print out player's hand with new card
                for(int j=0;j<player.cards.length;j++)
                {
                    System.out.printf("Card %d: %s\n", j+1, player.cards[j]);
                }
                
                
                //players[i].calcTotal();
                System.out.printf("Player's hand value = %s\n", player.value=player.calcTotal());
                System.out.println("");
                
                if(player.value > 21)
                {
                        System.out.print("Player, you have busted!\n");
                        player.winnings -= player.bet;
                        break;
                }
                else if(player.value == 21)
                {
                    System.out.println("Player %d has a blackjack!!\n");
                    break;
                }
                                
                System.out.println("");
                System.out.print("Stand or hit again?\nS=stand, H=hit\n");
                ans = in.nextLine();          
                        
            //System.out.printf("Player %d's info= %s\n",i+1,players[i].toString());
            }
        
        /*******************************************************
        * dealer stand or hit????
        ******************************************************/
        while(dealer.value < 16)
        {
            dealer.cards = addElement(dealer.cards, myDeck.dealCard());
            dealer.value = dealer.calcTotal();
            System.out.print("Dealer's hand: \n");
            for(int k=0;k<dealer.cards.length;k++)
            {
                System.out.printf("Card %d= %s\n",k+1,dealer.cards[k]);
            }
            System.out.printf("Dealer's hand value = %d\n", dealer.value);  
        }
        
            if(player.value == 21)
            {
                player.winnings += 1.5 * player.bet;
            }
            else if((dealer.value > 21) && (player.value < 21))
            {
                player.winnings += player.bet;
            }
            else if((player.value > dealer.value) && (player.value < 21))
            {
                player.winnings += player.bet;
            }
            else if(dealer.value == player.value)
            {
                player.winnings += 0;
            }
            System.out.printf("Player's winnings = $%d\n", player.winnings);
            
        
        
    }

    //get bets from players
    public static int placeBets(int min, int max)
    {
        //declare and initialize bet variable
        int bet=0;
        
        //prompt user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player place your bet: \n");
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
    

    public static void printPlayerInfo(Player player, Dealer dealer)
    { 
        System.out.printf("Player: %s\n", player.toString());
        System.out.printf("Dealer: %s\n", dealer.toString());
    }
    
    //add element to Card array
    
    public static Card [] addElement(Card [] original, Card... newElements)
    {
        Card[] newArray = new Card[original.length+1];
        System.arraycopy(original, 0, newArray, 0, original.length);
        System.arraycopy(newElements, 0, newArray, original.length, newElements.length);
        return newArray;
    }

    public static void isDealerBlackJack(Player player, Dealer dealer)
    {
        if(dealer.value == 21)
        {
            //check to see if any of the players also have blackjack
                //if they do, this results in a push
                if(player.value == 21)
                {
                    //push
                    //their original bet is returned to them
                    player.winnings += player.bet;
                    System.out.printf("Player's winnings: $%s\n", player.winnings);
                }
                //if theirs does not equal 21, then they lose
                else
                {
                    //and no money is returned to them
                    player.winnings += 0;
                    System.out.printf("Player's winnings: $%s\n", player.winnings);
                }
            System.exit(0);    
        }       
    }
}
