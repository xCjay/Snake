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
        setFillColor(color);
        setFilled(true);
    }

    private void addValue(){
        segValue += 1;
    }
}
