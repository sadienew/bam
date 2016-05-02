/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author sadie
 */
public class Player {
    public Card [] cards;
    public int bet;
    public int value;
    public int insuranceBet;
    public int winnings;
    
    public Player(Card [] cards, int bet, int value)
    {
        this.cards = cards;
        this.bet = bet;
        this.value = value;
    }
    
    public Player(Card [] cards, int bet, int value, int insuranceBet)
    {
        this.cards= cards;
        this.bet = bet;
        this.value = value;
        this.insuranceBet = insuranceBet;
    }
    
    public Player(Card [] cards, int bet, int value, int insuranceBet, int winnings)
    {
        this.cards= cards;
        this.bet = bet;
        this.value = value;
        this.insuranceBet = insuranceBet;
        this.winnings = winnings;
    }
    
    public String toString()
    {
        String string = "";
        for(int i=0;i<cards.length;i++)
        {
            string+="Card "+ (i+1)+"= " +cards[i]+ ", ";
        }
        value=calcTotal();
        return string + ", bet= $" + bet + " Value of Hand= " + value;
    }
    
    public int calcTotal()
    {
        int sum=0;
        for(int i=0; i<cards.length;i++)
        {
            sum+=cards[i].points;
        }
        return sum;
    }
}


/*************************************************
 * Player class 1
 *************************************************
 public class Player {
    public final Card card1;
    public final Card card2;
    private final int bet;
    
    public Player(Card card1, Card card2, int bet)
    {
        this.card1 = card1;
        this.card2 = card2;
        this.bet = bet;
    }
    
    public String playerString()
    {
        return "Card 1= " + card1 + ", Card 2= " + card2 + ", bet= $" + bet;
    }
 *****************************************************/