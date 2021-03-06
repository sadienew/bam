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
    public String face;
    //public final String face;
    public int points;

    public Card(String suit, String face, int points)
    {
        this.suit = suit;
	this.face = face;
        this.points = points;
    }
    
    public int getValue()
    {
        if(face.equals("Ace"))
        {
            points= 11;
        }
        else if(face.equals("Two"))
        {
            points = 2;
        }
        else if(face.equals("Three"))
        {
            points = 3;
        }
        else if(face.equals("Four"))
        {
            points = 4;
        }
        else if(face.equals("Five"))
        {
            points = 5;
        }
        else if(face.equals("Six"))
        {
            points = 6;
        }
        else if(face.equals("Seven"))
        {
            points = 7;
        }else if(face.equals("Eight"))
        {
            points = 8;
        }else if(face.equals("Nine"))
        {
            points = 9;
        }
        else
        {
            points = 10;
        }
        return points;
    }

    //override toString()
    public String toString()
    {
	return face + " of " + suit + ". Points= " + points;
    }
    
}
