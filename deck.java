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
public class deck {
    
    public static int[] makeDeck() {
        int[] d = new int[52];
        for (int c = 0; c < 13; c++) {
            d[c] = c + 1;
            d[c + 13] = c + 1;
            d[c + 26] = c + 1;
            d[c + 39] = c + 1;
        }
        return d;
    }
    
}
