import java.awt.Image;
import java.util.Random;

public class Ufo {

    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 15;
    private Spaceship spaceship;
    public Random rand;


    private Image image;

    public Ufo() {
        reset();
    }

    public void reset() {
        rand = new Random();
        width = 80;
        height = 50;
        y = 0;
        this.x = rand.nextInt(1150 - 0 + 1);
        //x = 500;

    }

    public void update() {
        y += speed;
    }

    public boolean collides(Spaceship r) {
        int uw = width;
        int uh = height;
        int sw = r.width;
        int sh = r.height;
        if (uw <= 0 || uh <= 0 || sw <= 0 || sh <= 0){
            return false;
        }
        int ux = x;
        int uy = y;
        int sx = r.x;
        int sy = r.y;

        uw += ux;
        uh += uy;
        sw += sx;
        sh += sy;

        return ((uw < ux || uw > sx) &&
                (uh < uy || uh > sy) &&
                (sw < sx || sw > ux) &&
                (sh < sy || sh > sy));
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("graphics/ufosprite.png");
        }
        r.image = image;

        return r;
    }


}