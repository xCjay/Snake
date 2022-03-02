import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
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

    double previousX;
    double previousY;

    int previousMoveX;
    int previousMoveY;

    private int segment = 4;
    private int time = 0;
    private int score = 0;
    private int highscore = 0;
    private int speed = 150;

    private GRect bounding;
    private GLabel scoreLabel;
    private GLabel highLabel;

    private JButton changeMode;


    //Begins gameLoop, start of the program
    @Override
    public void run() {
        spawnApple();
        Dialog.showMessage("Click ok, then click the window to start");
        waitForClick();
        addKeyListeners();
        gameLoop();

    }

    //Adds all the objects unto screen & fills variables
    @Override
    public void init() {
        setSize(700, 700);

        head = new SnakeHead(20, 20, getGCanvas());
        scoreLabel = new GLabel("Score: " + score);
        highLabel = new GLabel("Highscore: " + highscore);
        bounding = new GRect(500, 500);

        scoreLabel.setFont("Calibri-50");
        highLabel.setFont("Calibri-50");

        add(head, bounding.getX()+ head.getWidth()*2, bounding.getY() + head.getHeight()*3);
        add(bounding, 20, 20);
        add(scoreLabel, bounding.getX(), bounding.getY()+bounding.getHeight()+ scoreLabel.getHeight());
        add(highLabel, scoreLabel.getX(), scoreLabel.getY()+ 50);
    }

    /**
     * While True loop that runs the game
     */
    private void gameLoop(){
        while (true) {
            handleCollisions();
            double previousX = head.getX();
            double previousY = head.getY();
            if (previousMoveX == moveX){
                moveX *= -1;
            }
            if (previousMoveY == moveY){
                moveY *= -1;
            }
            head.move(moveX, moveY);
            previousMoveX = moveX * -1;
            previousMoveY = moveY * -1;
            bodypcs.add(new SnakeBody(20, 20));
                add(bodypcs.get(time), previousX, previousY);
                time +=1;
                if (time >= segment) {
                    remove(bodypcs.get(time - segment));
                }
            handleCollisions();
                pause(speed);
        }
    }




    /**
     * Gets a random multiple of 20 from the limit you pass into it
     * @param limit must be a multiple of 20
     * @return
     */
    private double getRandom20(int limit){
        int number = new Random().nextInt(limit/20)*20+20;
        return number;
    }

    /**
     * Spawns an apple somewhere on screen, bound by getRandom20.
     */
    private void spawnApple(){


        int random20X = (int) getRandom20((int) bounding.getWidth());
        int random20Y = (int) getRandom20((int) bounding.getHeight());

        if (!(this.getElementAt(random20X, random20Y) instanceof SnakeBody) && !(this.getElementAt(random20X, random20Y) instanceof SnakeHead)) {
            add(new Apple(), random20X, random20Y);
        }
    }


    /**
     * Handles all the collisions with the walls, apple, and SnakeBody parts.
     */
    private void handleCollisions(){
        GObject obj = null;
        //check right wall
        if (head.getX() + head.getWidth() > bounding.getX()+ bounding.getWidth()){
            lose();
            //check top
        }else if (head.getY() < bounding.getY()){
            lose();
            //check left
        }else if (head.getX() < bounding.getX()){
            lose();
            //check bottom
        }else if (head.getY() + head.getHeight() > bounding.getY() + bounding.getHeight()){
            lose();
        }

        if(obj == null){

            obj = this.getElementAt(head.getX()+head.getWidth()/2, head.getY()+ head.getHeight()/2);
        }

        if (obj != null){
            if (obj instanceof SnakeBody) {
                lose();
            } else if (obj instanceof  Apple){
                remove(obj);
                segment += 1;
                score += 1;
                scoreLabel.setLabel("Score: " + score);
                spawnApple();
            }
        }
    }

    /**
     * Handles what happens when you lose
     */
    private void lose(){
        if (highscore < score){
            highscore = score;
            highLabel.setLabel("Highscore: " + highscore);
        }
        Dialog.showMessage("You lose. Highscore:" + highscore);
        moveX = 20;
        moveY = 0;
        segment = 4;
        score = 0;
        previousMoveX = 0;
        previousMoveY = 0;
        removeAll();
        init();
        run();
    }

    /**
     * Handles the arrow keys getting pressed
     * @param ke
     */
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
