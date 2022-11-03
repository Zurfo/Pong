import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 858;
    static final int GAME_HEIGHT = 525;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static final int BALL_SIZE = 20;
    int ticks = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player1;
    Player player2;
    Ball ball;
    Score score;

    public GamePanel(){
        newGame();
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, BALL_SIZE);
    }

    public void newGame(){
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        player1 = new Player(35,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1, keyH);
        player2 = new Player((GAME_WIDTH-PADDLE_WIDTH) - 35,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2, keyH);
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/ticks;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player1.input();
        player2.input();
        ball.move();
        checkCollision();

        if (keyH.rest){
            newGame();
        }
    }

    public void checkCollision(){

        if (ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_SIZE){
            ball.setYDirection(-ball.yVelocity);
        }

        if (ball.intersects(player1)) {

            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;

            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if (ball.intersects(player2)) {

            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;

            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //Player Col
        if (player1.y <= 0){
            player1.y = 0;
        }
        if (player1.y >= GAME_HEIGHT - PADDLE_HEIGHT){
            player1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        if (player2.y <= 0){
            player2.y = 0;
        }
        if (player2.y >= GAME_HEIGHT - PADDLE_HEIGHT){
            player2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        //Score
        if (ball.x <= 0){
            score.player2++;
            newBall();
        }
        if (ball.x >= GAME_WIDTH - BALL_SIZE){
            score.player1++;
            newBall();
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
        score.draw(g);

        g2.dispose();
    }
}
