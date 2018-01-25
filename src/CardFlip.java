import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFlip {

    public static void main(String[] args) {
        boolean gameWon = false;
        int gameCount = 0;
        while(!gameWon){
            gameWon = playGame();
            gameCount++;
        }
        System.out.println("It took " + gameCount + " games to win!");
    }

    private static boolean playGame(){
        List<String> myShuffledDeck = createDeck();
        List<String> myPlayDeck = new ArrayList<String>();
        int playDeckCounter = 0;

        //start game
        gameloop:
        while(true){
            //check for victory/loss/beginning game states
            if(myShuffledDeck.size() == 0){
                if(myPlayDeck.size() == 0){
                    System.out.println("You win!");
                    return true;
                } else {
                    System.out.println("You lose!  There are " + myPlayDeck.size() + " cards left.");
                    return false;
                }
            }

            //increment the play deck cards and flip a card
            playDeckCounter++;
            myPlayDeck.add(myShuffledDeck.get(myShuffledDeck.size()-1));
            myShuffledDeck.remove(myShuffledDeck.size()-1);
            System.out.println("Flipped over the " + myPlayDeck.get(myPlayDeck.size() - 1));

            //if not enough cards to play, keep flipping cards
            if (playDeckCounter < 4) {
                continue;
            }

            //check flipped card and comparable card
            boolean wasCardCompared = false;
            do {
                wasCardCompared = false;
                String lastCard = myPlayDeck.get(myPlayDeck.size() - 1);
                String compareCard = myPlayDeck.get(myPlayDeck.size() - 4);

                //if values match, remove four cards
                if (lastCard.charAt(0) == compareCard.charAt(0)) {
                    System.out.println(lastCard + " has the same value as " + compareCard);
                    System.out.println("Removing " + lastCard);
                    System.out.println("Removing " + myPlayDeck.get(myPlayDeck.size() - 2));
                    System.out.println("Removing " + myPlayDeck.get(myPlayDeck.size() - 3));
                    System.out.println("Removing " + compareCard);
                    playDeckCounter -= 4;

                    for (int i = 0; i < 4; i++) {
                        myPlayDeck.remove(myPlayDeck.size() - 1);
                    }
                    wasCardCompared = true;

                    if (playDeckCounter < 4) {
                        continue gameloop;
                    }

                    continue;
                }

                //if suits match, remove two middle cards
                if (lastCard.charAt(1) == compareCard.charAt(1)) {
                    System.out.println(lastCard + " has the same suit as " + compareCard);
                    System.out.println("Removing " + myPlayDeck.get(myPlayDeck.size() - 2));
                    System.out.println("Removing " + myPlayDeck.get(myPlayDeck.size() - 3));
                    myPlayDeck.remove(myPlayDeck.size() - 2);
                    myPlayDeck.remove(myPlayDeck.size() - 3);
                    playDeckCounter -= 2;
                    wasCardCompared = true;

                    if (playDeckCounter < 4) {
                        continue gameloop;
                    }
                }
            } while (wasCardCompared);
        }
    }

    private static List<String> createDeck() {
        //create deck
        List<String> myShuffledDeck = new ArrayList<String>();

        //fill deck with cards
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                StringBuilder newCard = new StringBuilder();

                switch(j){
                    case 0:
                        newCard.append('A');  break;
                    case 1:
                        newCard.append('2');  break;
                    case 2:
                        newCard.append('3');  break;
                    case 3:
                        newCard.append('4');  break;
                    case 4:
                        newCard.append('5');  break;
                    case 5:
                        newCard.append('6');  break;
                    case 6:
                        newCard.append('7');  break;
                    case 7:
                        newCard.append('8');  break;
                    case 8:
                        newCard.append('9');  break;
                    case 9:
                        newCard.append('T');  break;
                    case 10:
                        newCard.append('J');  break;
                    case 11:
                        newCard.append('Q');  break;
                    case 12:
                        newCard.append('K');  break;
                }

                switch (i){
                    case 0:
                        newCard.append('H');  break;
                    case 1:
                        newCard.append('S');  break;
                    case 2:
                        newCard.append('D');  break;
                    case 3:
                        newCard.append('C');  break;
                }
                myShuffledDeck.add(newCard.toString());
            }
        }

        //deck is full
        //shuffle deck
        System.out.println("Shuffling deck...");
        Collections.shuffle(myShuffledDeck);

        //deck is shuffled
        return myShuffledDeck;
    }
}
