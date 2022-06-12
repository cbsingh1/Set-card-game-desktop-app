import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable {

    private PossibleColors color;
    private PossibleShadings shading;
    private PossibleShapes shape;
    private PossibleNumbers number;

    public Card() {
        this.color = PossibleColors.Red;
        this.shape = PossibleShapes.Diamond;
        this.shading = PossibleShadings.Empty;
        this.number = PossibleNumbers.One;
    }

    public Card(PossibleColors color, PossibleShadings shading, PossibleShapes shape, PossibleNumbers number) {
        this.color = color;
        this.shading = shading;
        this.shape = shape;
        this.number = number;
    }

    public Card(Card copy) {
        Card newdeck = new Card();
        newdeck.color = copy.color;
        newdeck.shape = copy.shape;
        newdeck.shading = copy.shading;
        newdeck.number = copy.number;
    }

    @Override
    public int compareTo(Object o) {
        Card c = (Card) o;

        if (this.getNumber().equals(c.getNumber()) && this.getColor().equals(c.getColor()) && this.getShape().equals(c.getShape()) && this.getShading().equals(c.getShading())) {
            return 0;
        } else {
            return 1;
        }
    }

    public PossibleColors getColor() {
        return color;
    }

    public PossibleShadings getShading() {
        return shading;
    }

    public PossibleShapes getShape() {
        return shape;
    }

    public PossibleNumbers getNumber() {
        return number;
    }

    //method that creates a deck of 81 unique cards
    public ArrayList<Card> createDeck() {
        ArrayList<Card> deck = new ArrayList<Card>(81);
        for (int i = 0; i < PossibleColors.values().length; i++) {
            for (int j = 0; j < PossibleShadings.values().length; j++) {
                for (int k = 0; k < PossibleShapes.values().length; k++) {
                    for (int l = 0; l < PossibleNumbers.values().length; l++) {
                        deck.add(new Card(PossibleColors.values()[i], PossibleShadings.values()[j], PossibleShapes.values()[k], PossibleNumbers.values()[l]));
                    }
                }
            }
        }
        return deck;
    }

    //method that compares 3 cards and checks whether they form a set
    public boolean isSet(Card a, Card b, Card c) {
        if (!((a.number == b.number) && (b.number == c.number) || (a.number != b.number) && (a.number != c.number) && (b.number != c.number))) {
            return false;
        }
        if (!((a.shape == b.shape) && (b.shape == c.shape) || (a.shape != b.shape) && (a.shape != c.shape) && (b.shape != c.shape))) {
            return false;
        }
        if (!((a.shading == b.shading) && (b.shading == c.shading) || (a.shading != b.shading) && (a.shading != c.shading) && (b.shading != c.shading))) {
            return false;
        }
        return (a.color == b.color) && (b.color == c.color) || (a.color != b.color) && (a.color != c.color) && (b.color != c.color);
    }

    public Card[] removeArrayElement(Card[] arr, int index) {
        Card[] newArray = new Card[arr.length - 1];
        int newIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                newArray[newIndex] = arr[i];
                newIndex++;
            }
        }
        return newArray;
    }

    public ArrayList<Card> createCardsOnBoard(ArrayList<Card> deck) {
        ArrayList<Card> cardsOnBoard = new ArrayList<>(12);
        ArrayList<Card> shuffledDeck = Deck.shuffle(deck);
        for (int i = 0; i < 12; i++) {
            cardsOnBoard.set(i, shuffledDeck.get(i));
        }
        return cardsOnBoard;
    }

    //method to check if there is a set present in the cards on the board
    public boolean isSetPresent(ArrayList<Card> cardsOnBoard) {
        for (int firstCard = 0; firstCard <= cardsOnBoard.size() - 3; firstCard++) {
            for (int secondCard = firstCard + 1; secondCard <= cardsOnBoard.size() - 2; secondCard++) {
                for (int thirdCard = secondCard + 1; thirdCard <= cardsOnBoard.size() - 1; thirdCard++) {

                    if (isSet(Deck.fullDeck[firstCard], Deck.fullDeck[secondCard], Deck.fullDeck[thirdCard])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return color.toString() + shading.toString() + shape.toString() + number.toString();
    }

    ArrayList<ArrayList<Card>> getAllSets(List<Card> cards, boolean findOnlyFirstSet) {
        ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>();
        if (cards == null) return result;
        int size = cards.size();
        for (int ai = 0; ai < size; ai++) {
            Card a = cards.get(ai);
            for (int bi = ai + 1; bi < size; bi++) {
                Card b = cards.get(bi);
                for (int ci = bi + 1; ci < size; ci++) {
                    Card c = cards.get(ci);
                    if (isSet(a, b, c)) {
                        ArrayList<Card> set = new ArrayList<Card>();
                        set.add(a);
                        set.add(b);
                        set.add(c);
                        System.out.println(set);
                        result.add(set);
                        if (findOnlyFirstSet) return result;
                    }
                }
            }
        }
        return result;
    }

    boolean setExists(List<Card> cards) {
        return getAllSets(cards, true).size() > 0;
    }

    public enum PossibleColors {Red, Green, Purple}

    public enum PossibleShadings {Empty, Filled, Striped}

    public enum PossibleShapes {Diamond, Wave, Oval}

    public enum PossibleNumbers {One, Two, Three}
}
