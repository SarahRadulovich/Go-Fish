/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.*;

class GoFish{

    public static void main(String[] args) {

        /* 
        playing boolean is while the player still wants to play the game, becomes false when they decide not to play again
        */
        boolean playing = true;
        while (playing == true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to Go Fish! Would you like some instructions? Type Y for yes and N for no.");
            String help = scan.next();
            if (help.equalsIgnoreCase("Y")) {
                
                //refer to the help class for instructions on how to play
                helpClass instructions = new helpClass();
                
                instructions.help();
                
            } else {
                System.out.println("Let's begin!");
                System.out.println();
            }
            /*
            int[] hand is the array for the players hand, and the for loop is printing the first 5
            */
            System.out.println("Here are your first 5 cards: ");
            
            //pushes from the deck class, and creates a deck object
            
            deck createADeck = new deck();
            int[] deck = createADeck.makeDeck();
            
            playerHandClass playerHandOb = new playerHandClass();
            computerHandClass compHandOb = new computerHandClass();
            
            int[] hand = playerHandOb.playerHand(deck);
            int[] compHand = compHandOb.computerHand(deck);
            
            cardValueClass cardOb = new cardValueClass();
            
            for (int c = 0; c < 5; c++) {
                cardOb.cardValue(hand[c]);
            }
            /*
            variables match and compMatch are int's to hold the number of pairs the player/computer gets
            */
            int match = 0;
            int compMatch = 0;
            /*
            matching() is the method to check if the player has any matches in their first 5 cards
            */
            match = matching(hand, match);
            System.out.println("You have: " + match + " total match(es)");
            /*
            Next is to check for the computers matches
            */
            compMatch = matching(compHand, compMatch);
//..
            System.out.println("The computer has: " + compMatch + " match(es)");

            /*
            asking() is the core of the program, and the player will 'ask' the computer for a card (if they have it)
            */
            hand = asking(compHand, hand, deck, match, compMatch);
            match = hand[499];
            compMatch = hand[498];
            winOrLose(match, compMatch);
            System.out.println("Would you like to play again? Type Y for yes and N for no.");
            String playAgain = scan.next();
            if (playAgain.equalsIgnoreCase("N")) {
                playing = false;
                System.out.println("Thanks for playing!!");
            }
        }
    }

    public static void winOrLose(int match, int compMatch) {

        if (match > compMatch) {
            System.out.println("You win!");
        }
        if (compMatch > match) {
            System.out.println("You lose!");
        }
        if (match == compMatch) {
            System.out.println("Its a tie!");
        }
    }

    public static int temp(int[] hand, int[] compHand, int match, int compMatch, int i) {
        //if there is a real (non-0) card in the hand, i will add
        for (int a = 0; a < 498; a++) {
            if (hand[a] != 0) {
                i++;
            }
        }
        //same goes for the computers hand
        for (int a = 0; a < 498; a++) {
            if (compHand[a] != 0) {
                i++;
            }
        }
        //by now every card should be counted in the hands of the player and computer
        //now its just 2 times their matches thats left
        i += (match * 2) + (compMatch * 2);

        return i;
    }

    public static int hitCard(int[] x) {
        //picks a random card from the deck
        int ran = new Random().nextInt(x.length);
        //if the card is 0 it loops and picks again until its not 0
        while (x[ran] == 0) {
            ran = new Random().nextInt(x.length);
        }
        int temp = x[ran];
        //returns the new card
        return x[temp];
    }

    public static int matching(int[] x, int match) {
        boolean match1 = false;
        for (int c = 0; c < 498; c++) {
            for (int a = c + 1; a < 498; a++) {
                if (x[c] != 0) {
                    if (x[c] == x[a]) {
                        match1 = true;
                        x[c] = 0;
                        x[a] = 0;
                    }
                }
            }
        }
        if (match1 == true) {
            match++;
        }
        return match;
    }

    public static int[] compAsking(int[] compHand, int[] hand, int[] deck, int compMatch) {
        System.out.println("It's now the computer's turn to ask.");
        boolean temp = true;
        //the computer will scan through it's hand to find a card to ask the player about
        //picks a random card from the computers deck
        int ran = new Random().nextInt(compHand.length);
        //if its 0, it picks again until its not 0
        while (compHand[ran] == 0) {
            ran = new Random().nextInt(compHand.length);
        }
        //asks the player about the card
        System.out.println("Do you have a " + compHand[ran] + "?");
        boolean goFish = false;
        //scans through the players hand to see if they have that card
        for (int c = 0; c < 498; c++) {
            if (compHand[ran] == hand[c]) {
                System.out.println("You have that card! Now you must give it to them.");
                goFish = false;
                temp = false;
                //makes that card from the players hand 0, so they don't have the value of that card anymore
                hand[c] = 0;
                //takes the card 'asked' about, and adds it to the computers hand
                compHand = compTakeCard(compHand, deck, compHand[ran]);
                //shows the *player* their new deck (now without the 'asked' card)
                System.out.println("Your new deck is: ");
                
                cardValueClass cardOb = new cardValueClass();
                
                for (int a = 0; a < 498; a++) {
                    if (hand[a] != 0) {
                        cardOb.cardValue(hand[a]);
                    }
                }
            }
            //if the player didn't have the card goFish() will be true
            if (temp == true) {
                if (compHand[ran] != hand[c]) {
                    goFish = true;
                }
            }
        }
        if (goFish == true) {
            System.out.println("You don't, so the computer must go fish!");
            //the computer goes fish in the method
            compHand = compGoFish(compHand, deck);
        }
        //checks for matches in the computers hand
        compMatch = matching(compHand, compMatch);
        System.out.println("The computer has " + compMatch + " match(es).");
        compHand[499] = compMatch;

        return compHand;
    }

    public static int[] compTakeCard(int[] compHand, int[] deck, int ask) {
        //boolean draw = true;
        int end = compHand.length;
        for (int c = 0; c < compHand.length; c++) {
            if (compHand[c] == 0) {
                compHand[c] = ask;
                c = end;
            }

        }
        return compHand;
    }

    public static int[] compGoFish(int[] compHand, int[] deck) {
        //takes a new card in hitCard()
        int card = hitCard(deck);
        int end = compHand.length;
        for (int c = 0; c < compHand.length; c++) {
            //if there's a zero in the computers hand the card will be put there
            if (compHand[c] == 0) {
                compHand[c] = card;
                //ends the loop
                c = end;
            }
        }
//..
        return compHand;
    }

    public static int[] asking(int[] compHand, int[] hand, int[] deck, int match, int compMatch) {
        int i = 0;
        // i is the amount of cards that have been drawn, that way when 52 cards are used, the game will end.
        while (i <= 52) {
            //out is to check if the player has run out of cards.
            int out = 0;
            for (int zz = 0; zz < 498; zz++) {
                if (hand[zz] != 0) {
                    out++;
                }
            }
            //once the player has run out of cards, they go to the newDeck() method for 5 more cards.
            if (out == 0) {
                System.out.println("You're out of cards! You get 5 more!");
                newDeck(hand, deck);
                System.out.println("Your new deck is:");
                
                cardValueClass cardOb = new cardValueClass();
                
                for (int z=0; z<498; z++){
                    cardOb.cardValue(hand[z]);
                }
            }
            //out2 is for the computer's hand, and it will check to see if they've run out of cards
            int out2 = 0;
            for (int zzz = 0; zzz < 498; zzz++) {
                if (compHand[zzz] != 0) {
                    out2++;
                }
            }
            //then using the same newDeck() method, the computer will get 5 more cards (if they've run out)
            if (out2 == 0) {
                System.out.println("The computer is out of cards! They get 5 more!");
                newDeck(compHand, deck);
            }
            //the real start of the 'asking'
            System.out.println("Please type the card number you'd like to ask about: ");
            //scans the number they've typed in = int ask
            Scanner scan = new Scanner(System.in);
            int ask = scan.nextInt();
            //booleans made just to exit the for loop if the computer has their card
            boolean goFish = false;
            boolean temp = true;
            //for loop scans the computers deck
            for (int c = 0; c < 498; c++) {
                if (ask == compHand[c]) {
                    System.out.println("The computer has that card! Now they must give it to you!");
                    goFish = false;
                    temp = false;
                    //makes the computers card 0, so they don't have the vaule of that card anymore
                    compHand[c] = 0;
                    //takeCard() method so the asking card is put into the players hand
                    hand = takeCard(hand, deck, ask);
                    //for loop to print the cards in the players hand
                    System.out.println("Your new deck is: ");
                    
                    cardValueClass cardOb = new cardValueClass();
                    
                    for (int a = 0; a < 498; a++) {
                        if (hand[a] != 0) {
                            cardOb.cardValue(hand[a]);
                        }
                    }
                    //checks for matches
                    match = matching(hand, match);
                    System.out.println("You have " + match + " total match(es)");
                }
                //will not enter if statement if the computer had the players 'asking' card
                if (temp == true) {
                    //if the computer doesn't have the card it exits the loop 
                    if (ask != compHand[c]) {
                        goFish = true;
                    }
                }
            }
            //goFish will only be true if the computer doesn't have the asking card
            if (goFish == true) {
                System.out.println("Go fish!");
                //goFish() method picks a new card from the deck and puts in into the players hand
                
                goFishClass goFishObject = new goFishClass();
                
                hand = goFishObject.goFish(hand, deck);
                
                //checks for new matches
                match = matching(hand, match);
                System.out.println("You have " + match + " total match(es)");
            }
            //goes to the computers turn in compAsking
            compHand = compAsking(compHand, hand, deck, compMatch);
            compMatch = compHand[499];
            hand[499] = match;
            //checking if 52 cards have been used
            i = temp(hand, compHand, match, compMatch, i);
            hand[498] = compMatch;
            //if i is less than 52, then asking() will loop again
        }
        return hand;
    }

    public static int[] newDeck(int[] hand, int[] deck) {
        //if all the cards are 0, int thing should be 0
        int thing = 0;
        for (int z = 0; z < 498; z++) {
            if (hand[z] != 0) {
                thing++;
            }
        }
        //when int thing is 0, the player or computer will get 5 more cards from hitCard()
        if (thing == 0) {
            for (int c = 0; c < 5; c++) {
                hand[c] = hitCard(deck);
            }
        }
        return hand;
    }

    public static int[] takeCard(int[] hand, int[] deck, int ask) {
        int end = hand.length;
        //scans the hand, and if there is a 0, they will get the card they asked for
        for (int c = 0; c < end; c++) {
            if (hand[c] == 0) {
                hand[c] = ask;
                //if they got their card the loop ends
                c = end;
            }
        }
        return hand;
    }


}

