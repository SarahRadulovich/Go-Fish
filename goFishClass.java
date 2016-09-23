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
public class goFishClass {
    
        public static int[] goFish(int[] hand, int[] deck) {
        //takes a card from the hitCard() method
        int card = hitCard(deck);
        int end = hand.length;
        for (int c = 0; c < end; c++) {
            if (hand[c] == 0) {
                hand[c] = card;
                c = end;
            }
        }
        System.out.println("You draw: " + card);
        System.out.println("This is your new hand: ");
        //outprints the hand
        for (int a = 0; a < 498; a++) {
            if (hand[a] != 0) {
                System.out.print(hand[a] + ",    ");
            }
        }
        return hand;
    }
    
}
