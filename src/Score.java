import java.awt.*;

public class Score extends Rectangle {

    int gameWidth;
    int gameHeight;
    int player1;
    int player2;

    public Score(int width, int height){
        gameWidth = width;
        gameHeight = height;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));

        Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{15}, 30);
        g2d.setStroke(dashed);
        g2d.drawLine(gameWidth/2, 0, gameWidth/2, gameHeight);

        g.drawString(String.valueOf(player1/10)+(player1%10), (gameWidth/2) - 85, 50);
        g.drawString(String.valueOf(player2/10)+(player2%10), (gameWidth/2) + 20, 50);
    }
}
