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
    public int value;
    
    public Dealer(Card [] cards, int value)
    {
        this.cards = cards;
        this.value = value;
    }
    
    public String toString()
    {
        String string = "";
        for(int i=0;i<cards.length;i++)
        {
            string+="Card "+ (i+1)+"= " +cards[i]+ ", ";
        }
        value = calcTotal();
        return string + " Value of Hand= " + value;
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
