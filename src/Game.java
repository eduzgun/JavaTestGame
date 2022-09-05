import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {

    public static final int UFO_DELAY = 50;

    private Boolean paused;

    private int pauseDelay;
    private int restartDelay;
    private int ufoDelay;

    private Spaceship ship;
    private ArrayList<Ufo> ufos;

    private Keyboard keyboard;

    public int score;
    public Boolean gameover;
    public Boolean started;

    public Game() {
        keyboard = Keyboard.getInstance();
        restart();
    }

    public void restart() {
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        ufoDelay = 0;

        ship = new Spaceship();
        ufos = new ArrayList<Ufo>();
    }

    public void update()  {
        watchForStart();

        if (!started)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        ship.update();

        if (gameover)
            return;

        moveUfos();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "graphics/background.png"));
        for (Ufo ufo : ufos)
            renders.add(ufo.getRender());
        //renders.add(new Render(0, 0, "graphics/foreground.png"));
        renders.add(ship.getRender());
        return renders;
    }

    private void watchForStart() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;

        }
    }

    private void moveUfos()  {
        ufoDelay -= 100;

        /*
        if (ufoDelay < 0){
            ufoDelay = UFO_DELAY;
            for (Ufo ufo : ufos) {
                if (ufo.y - ufo.height > 720){
                    ufo.reset();
                }
            }

            Ufo ufo = new Ufo();
            ufos.add(ufo);

            for (Ufo ufo1 : ufos){
                ufo1.update();
            }
        }*/


        if (ufoDelay < 0) {
            ufoDelay = UFO_DELAY;
            Ufo tempUfo = null;
            // offscreen ufos
            for (Ufo ufo : ufos) {
                if (ufo.y - ufo.height > 720) {
                    if (tempUfo == null) {
                        tempUfo = ufo;
                        break;
                    }

                    ufo.reset();
                }
            }
            if (tempUfo == null) {
                if (ufos.size() < 5) {
                    Ufo ufo = new Ufo();
                    ufos.add(ufo);
                    tempUfo = ufo;
                }

            } else {
                tempUfo.reset();
            }
        for (Ufo ufo : ufos) {
            ufo.update();
        }
        }
    }


    private void checkForCollisions() {

        for (Ufo ufo : ufos) {
            if (ufo.collides(ship)) {
                gameover = true;
                ship.dead = true;
                System.out.println("GAME OVER");
            } else if (ufo.y >= ship.y) {
                score++;
            }
        }

    }
}