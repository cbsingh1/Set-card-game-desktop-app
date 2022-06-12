import java.util.ArrayList;
import java.util.Scanner;

public class SetConsole {
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name.");
        String name = sc.nextLine();
        int point = 0;
        Card cardClass = new Card();
        ArrayList<Card> deck = cardClass.createDeck();
        ArrayList<Card> cardsOnBoard = cardClass.createCardsOnBoard(deck);
        for(int i = 0; i < cardsOnBoard.size(); i++ ) {
            System.out.println(cardsOnBoard.get(i));
        }
        //remove 12 elements from board
        while(!deck.isEmpty() && cardClass.isSetPresent(cardsOnBoard) == true) {
            int firstCard = sc.nextInt();
            int secondCard = sc.nextInt();
            int thirdCard = sc.nextInt();
            if(cardClass.isSet(cardsOnBoard.get(firstCard), cardsOnBoard.get(secondCard), cardsOnBoard.get(thirdCard)) == false) {
                point--;
                System.out.println("Not a set, try again.");
            }else{
                point++;
                //method to remove three cards from the cardsOnBoard
                //method to add three new cards from the deck to cardsOnBoard
            }
        }
        System.out.println("Game over. " + name +" has" + point + " points.");
    }
}
