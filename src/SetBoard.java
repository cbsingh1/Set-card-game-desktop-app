import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetBoard extends JFrame {

    private CardUI[][] cardMatrix = new CardUI[3][4];
    Card first, second, third;
    Card cardObj = new Card();
    ArrayList<Card> deck, table = new ArrayList<>();
    int setCount = 0;
    JPanel cardsPanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JLabel cardLeftLabel;
    JLabel setLabel;
    public SetBoard() {
        super("Set Card Game");
        deck =  cardObj.createDeck();
        moveCards(Deck.shuffle(deck), table, 12);
        while(! cardObj.setExists(table)) {
            moveCards(table, deck, 12);
            moveCards(Deck.shuffle(deck), table, 12);
        }

        updateBoard();
        cardsPanel.setBackground(Color.GRAY);
        cardsPanel.setLayout(new GridLayout(3, 4));
        cardsPanel.setSize(700, 600);

        scorePanel.setBackground(Color.PINK);
        scorePanel.setLayout(new GridLayout(1, 2));
        scorePanel.setSize(700, 100);
        cardLeftLabel = new JLabel("Card Left : " + deck.size());
        cardLeftLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        setLabel = new JLabel("Set : " + setCount);
        setLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        scorePanel.add(cardLeftLabel);
        scorePanel.add(setLabel);

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        add(cardsPanel, BorderLayout.NORTH);
        add(scorePanel);
        setVisible(true);

/*      while(! deck.isEmpty()) {
          System.out.println("Game Over");
      }*/
    }

    private void updateBoard() {
        String fileName;
        Card card;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                card = table.get(index++);
                if(cardMatrix[i][j] != null){
                    cardsPanel.remove(cardMatrix[i][j]);
                }

                cardMatrix[i][j] = new CardUI(i, j, card.getColor(), card.getShading(), card.getShape(), card.getNumber());
                fileName = "/images/" + card.toString() + ".jpeg";

                try {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(fileName))
                            .getImage()
                            .getScaledInstance(170, 180, Image.SCALE_DEFAULT));
                    cardMatrix[i][j].setIcon(imageIcon);
                } catch (Exception e) {
                    System.out.println("File not found for" + fileName);
                }

                cardMatrix[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardUI clickedCard = (CardUI) e.getSource();
                        handleClickEvent(clickedCard.getCoordinates());
                    }
                });
                cardsPanel.add(cardMatrix[i][j]);
            }
        }
    }

    private void handleClickEvent(int[] clickedCardXY) {

        int i = clickedCardXY[0];
        int j = clickedCardXY[1];
        cardMatrix[i][j].setHighlight(true);
        CardUI card = cardMatrix[i][j];
        Card tempCard = new Card(card.getColor(), card.getShading(), card.getShape(), card.getNum());

        if (first == null) {
            first = tempCard;
        } else if (first != null && second == null) {
            second = tempCard;
        } else if (first != null && second != null && third == null) {
            third = tempCard;

            if (cardObj.isSet(first, second, third)) {
                setCount++;

                for (int k = 0; k < table.size(); k++) {
                    String tableCardString = table.get(k).toString();
                    if(first.toString().equals(tableCardString) ||
                            second.toString().equals(tableCardString) ||
                            third.toString().equals(tableCardString)
                    ) {
                        table.remove(k);
                    }
                }

                moveCards(deck, table, 3);
                updateBoard();
                updateTitleBoard();
            }
            first = second = third = null;
            setColorToDefault();
        }
    }

    private void updateTitleBoard() {
        cardLeftLabel.setText("Card Left : " + deck.size());
        setLabel.setText("Set : " + setCount);
    }


    private void setColorToDefault() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                cardMatrix[i][j].setHighlight(false);
            }
        }
    }

    void moveCards(ArrayList<Card> from, ArrayList<Card> to, int numberOfCards) {
        for (int i=0; i < numberOfCards; i++) {
            if (from.isEmpty()) break;
            to.add(from.remove(from.size() - 1));
        }
    }

    void removeCards(ArrayList<Card> toBeRemoved, ArrayList<Card> from) {
        for (Card card : toBeRemoved) {
            from.remove(card);
        }
    }
}
