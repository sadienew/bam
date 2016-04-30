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
    private final int bet;
    public int total;
    
    public Player(Card [] cards, int bet, int total)
    {
        this.cards = cards;
        this.bet = bet;
        this.total = total;
    }
    
    public String toString()
    {
        String string = "";
        for(int i=0;i<cards.length;i++)
        {
            string+="Card "+ (i+1)+"= " +cards[i]+ ", ";
        }
        return string + ", bet= $" + bet + " Total= " + total;
    }
    
    public int calcTotal()
    {
        int sum=0;
        for(int i=0; i<cards.length;i++)
        {
            sum+=cards[i].value;
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