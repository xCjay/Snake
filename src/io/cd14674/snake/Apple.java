import acm.graphics.GOval;

import java.awt.*;

public class Apple extends GOval {


    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public Apple(){
        super(WIDTH, HEIGHT);
        setFilled(true);
        setFillColor(Color.red);
    }
}
