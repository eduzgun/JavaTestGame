import javax.swing.*;

public class App {
    // window size
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;

    public static void main(String[] args) {
        // frame conf
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // keybrd input
        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);
        // window conf
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
    }
}
