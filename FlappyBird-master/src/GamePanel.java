import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GamePanel extends JPanel implements Runnable {

    private Game game;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
               g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);


        g2D.setColor(Color.CYAN);

        if (!game.started) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2D.drawString("Press SPACE to Start", 120, 240);
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.setColor(Color.BLUE);
            g2D.drawString("Score:", 5, 465);
            g2D.setColor(Color.MAGENTA);
            g2D.drawString(Integer.toString(game.score), 70, 465);
           
        
           
        }

        if (game.gameover) {
        	
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2D.setColor(Color.WHITE);
            g2D.drawString("Your Score:", 150, 210);
            g2D.setColor(Color.WHITE);
            g2D.drawString(Integer.toString(game.score), 300, 210);
            g2D.setColor(Color.orange);
            g2D.drawString("Press R to Restart", 150, 240);
        }
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
