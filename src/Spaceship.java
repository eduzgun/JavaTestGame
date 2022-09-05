import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Spaceship {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;


    private Image image;
    private Keyboard keyboard;

    public Spaceship() {
        x = 600; // x spawn cord
        y = 500; // y spawn cord
        width = 100;
        height = 100;

        dead = false;

        keyboard = Keyboard.getInstance();
    }

    public void update() {


        if (!dead && keyboard.isDown(KeyEvent.VK_LEFT)) {
            x -= 10;
        }
         if (!dead && keyboard.isDown(KeyEvent.VK_RIGHT)) {
            x += 10;
        }

    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("graphics/spaceship.png");
        }
        r.image = image;



        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.translate(-width / 2, -height / 2);

        return r;
    }
}