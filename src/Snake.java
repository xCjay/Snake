import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import svu.csc213.Dialog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends GraphicsProgram {


    //remove last in line if == or more then the allowed length

    // randomly generate apples +1 snake length

    // coliding with body, game end

    // colliding with apple.





    SnakeHead head;

    List<SnakeBody> bodypcs = new ArrayList<SnakeBody>();

    RandomGenerator ran = new RandomGenerator();

    Random rn = new Random();


    private int moveX = 20;
    private int moveY = 0;

    private double previousX;
    private double previousY;

    public int segment = 5;

    int time = 0;

    GRect bounding = new GRect(500, 500);
    private Object GObject;


    @Override
    public void run() {
        addKeyListeners();
        gameLoop();

    }

    @Override
    public void init() {
        setSize(700, 700);
        head = new SnakeHead(20, 20, getGCanvas());
        add(head, 100, 100);
        add(bounding, 20, 20);
        spawnApple();
    }

    private void gameLoop(){
        while (true) {
            previousX = head.getX();
            previousY = head.getY();
            head.move(moveX, moveY);
            handleCollisions();
            bodypcs.add(new SnakeBody(20, 20));
                add(bodypcs.get(time), previousX, previousY);
                time +=1;
                if (time >= segment) {
                    remove(bodypcs.get(time - segment));
                }
                pause(150);
        }
    }


    private double getRandom20(){
        int number = new Random().nextInt(25)*20+20;
        return number;
    }


    private void spawnApple(){
        add(new Apple(), getRandom20(), getRandom20());
    }

    private void handleCollisions(){
        GObject obj = null;

        if (head.getX() + head.getWidth() == bounding.getX()+ bounding.getWidth()){
            lose();
        }else if (head.getY() == bounding.getY() + 1){
            lose();
        }

        if(obj == null){

            obj = this.getElementAt(head.getX()+head.getWidth()/2 + moveX, head.getY()+head.getHeight()/2 + moveY);
        }

        if (obj != null){
            if (obj instanceof SnakeBody) {
                lose();
            } else if (obj instanceof  Apple){
                remove(obj);
                segment += 1;
                spawnApple();
            }
        }
    }

    private void lose(){
        Dialog.showMessage("You lose");
        for (int i = 0; i < segment; i++) {
            remove(bodypcs.get(i));
        }
        head.setLocation(20, 20);
        moveX = 20;
        moveY = 0;

    }

    public void keyPressed(KeyEvent ke){
        switch (ke.getKeyCode()){
            case 38:
                moveX = 0;
                moveY = -20;
                break;
            case 40:
                moveX = 0;
                moveY = 20;
                break;
            case 37:
                moveX = -20;
                moveY = 0;
                break;
            case 39:
                moveX = 20;
                moveY = 0;
                break;
            case 82:
                removeAll();
                init();
                break;
        }
    }


    public static void main(String[] args) {
        Snake snake = new Snake();
        snake.start();
    }
}
