/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

/**
 *
 * @author Sarah
 */
public class cardValueClass {
    
        public static int cardValue(int card){
        if (card==11){
            System.out.print("Jack  ");
        }
        if (card==12){
            System.out.print("Queen ");
        }
        if (card==13){
            System.out.print("King  ");
        }
        else 
            System.out.print(card+" ");
        return card;
    }
    
}
