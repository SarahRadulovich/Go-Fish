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
public class helpClass {

    public static void help() {
        System.out.println("HOW TO PLAY GO FISH:");
        System.out.println("You and the computer each are dealt 5 cards from the deck. You set down all pairs of matches.");
        System.out.println("A match consists of two cards that are the same number, but doesn't have to be the same colour.");
        System.out.println("You ask the opponent if they have a specific card, the point of the game being to match all your cards.");
        System.out.println("If the opponent doesn't have the card you asked for, they say 'go fish' and you draw from the deck.");
        System.out.println("After, the opponent asks you for a card, playing under the same rules.");
        System.out.println("You play until the deck is done, and who ever has the most matches by the end, wins the game.");
        System.out.println("You type in the number you want to ask for.");
        System.out.println("Your amount of matches will be tracked by the game.");
    }

}
