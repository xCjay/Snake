import acm.graphics.GCanvas;
import acm.graphics.GRect;

import java.awt.*;

public class SnakeHead extends GRect {


    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;



    private GCanvas screen;



    public SnakeHead(double v, double v1, GCanvas screen) {
        super(v, v1);
        setFilled(true);
        setFillColor(Color.red);
        this.screen = screen;
    }

    private void move(int y, int x) {
        move(x, y);
    }


}
