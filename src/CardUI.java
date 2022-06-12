import javax.swing.*;
import java.awt.*;

public class CardUI extends JButton {
    private int x;
    private int y;
    private Card.PossibleColors color;
    private Card.PossibleNumbers num;
    private Card.PossibleShapes shape;
    private Card.PossibleShadings shading;
    static final String HIGLIGHT = "#FFFF99";

    public CardUI() {
    }

    int[] getCoordinates() {
        return new int[]{x, y};
    }

    public Card.PossibleColors getColor() {
        return color;
    }

    public Card.PossibleNumbers getNum() {
        return num;
    }

    public Card.PossibleShapes getShape() {
        return shape;
    }

    public Card.PossibleShadings getShading() {
        return shading;
    }

    public CardUI(int i, int j,
                  Card.PossibleColors color,
                  Card.PossibleShadings shading,
                  Card.PossibleShapes shape,
                  Card.PossibleNumbers num) {
        x =i;
        y =j;
        this.color = color;
        this.shading = shading;
        this.shape = shape;
        this.num = num;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    void setHighlight(boolean isHighlighted) {
        if (isHighlighted)
            this.setBackground(Color.decode(HIGLIGHT));
        else
            this.setBackground(new JButton().getBackground());
    }
}
