import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.awt.*;
import java.util.Random;

public class SnakeBody extends GRect {

    double previousX;
    double previousY;

    private int segValue = 0;

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    RandomGenerator rn = new RandomGenerator();


    public SnakeBody(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
        Color color = new Color(rn.nextInt(255), rn.nextInt(255), rn.nextInt(255));
        setFillColor(Color.green);
        setFilled(true);
    }

    //Adds the number of the position the body is in the chain
    private void addValue(){
        segValue += 1;
    }

    private int getSeg(){
        return segValue;
    }
}
