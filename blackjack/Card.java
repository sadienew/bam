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
public class Card {
    private final String suit;
    public final String face;
    public int value;

    public Card(String suit, String face, int value)
    {
        this.suit = suit;
	this.face = face;
        this.value = value;
    }
    
    public int getValue()
    {
        if(face == "Ace")
        {
            value= 11;
        }
        else if(face == "Two")
        {
            value = 2;
        }
        else if(face == "Three")
        {
            value = 3;
        }
        else if(face == "Four")
        {
            value = 4;
        }
        else if(face == "Five")
        {
            value = 5;
        }
        else if(face == "Six")
        {
            value = 6;
        }
        else if(face == "Seven")
        {
            value = 7;
        }else if(face == "Eight")
        {
            value = 8;
        }else if(face == "Nine")
        {
            value = 9;
        }
        else
        {
            value = 10;
        }
        return value;
    }

    //override toString()
    public String toString()
    {
	return face + " of " + suit + ". Value= " + value;
    }
    
}
