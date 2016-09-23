/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import static gofish.GoFish.hitCard;

/**
 *
 * @author Sarah
 */
public class computerHandClass {

    public static int[] computerHand(int[] x) {
        //makes the computers hand
        int[] hand = new int[500];
        for (int c = 0; c < 5; c++) {
            //picks cards from the hitCard() method
            hand[c] = hitCard(x);
        }
        //returns the computers hand
        return hand;
    }    
}
