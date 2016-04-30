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
public class Dealer {
    public Card [] cards;
    public int total;
    
    public Dealer(Card [] cards, int total)
    {
        this.cards = cards;
        this.total = total;
    }
    
    public String toString()
    {
        String string = "";
        for(int i=0;i<cards.length;i++)
        {
            string+="Card "+ (i+1)+"= " +cards[i]+ ", ";
        }
        return string + " Total= " + total;
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
