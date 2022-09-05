import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    private Game game;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
    }

    public void update()  {
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


        g2D.setColor(Color.RED);

        if (!game.started) {
            g2D.setFont(new Font("Arial", Font.PLAIN, 60));
            g2D.drawString("Press SPACE to start", App.WIDTH/2, App.HEIGHT/2);
        } else {
            g2D.setFont(new Font("Arial", Font.PLAIN, 30));
            g2D.drawString(Integer.toString(game.score), 10, 465);
        }

        if (game.gameover) {
            g2D.setFont(new Font("Arial", Font.PLAIN, 60));
            g2D.drawString("Press R to restart", 150, 240);
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
