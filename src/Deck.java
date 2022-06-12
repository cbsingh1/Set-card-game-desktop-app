import java.util.ArrayList;
import java.util.List;

public class Deck {
    static Card[] fullDeck = new Card[81];

    //method that creates a deck of 81 unique cards
    public Card[] createDeck() {
        List<Card> fullDeck = new ArrayList<>();

        for (Card.PossibleColors col : Card.PossibleColors.values()) {
            for (Card.PossibleShadings shd : Card.PossibleShadings.values()) {
                for (Card.PossibleShapes shp : Card.PossibleShapes.values()) {
                    for (Card.PossibleNumbers num : Card.PossibleNumbers.values()) {
                        fullDeck.add(new Card(col, shd, shp, num));
                    }
                }
            }

        }
        return fullDeck.toArray(new Card[fullDeck.size()]);
    }

    //method that shuffles the deck
    public static ArrayList<Card> shuffle(ArrayList<Card> deck) {
        int currentIndex = deck.size();
        int randomIndex;
        while (currentIndex != 0) {
            randomIndex = (int) Math.floor(Math.random() * currentIndex);
            currentIndex--;
            Card temp = deck.get(currentIndex);
            deck.set(currentIndex, deck.get(randomIndex));
            deck.set(randomIndex, temp);

        }
        return deck;
    }
}
