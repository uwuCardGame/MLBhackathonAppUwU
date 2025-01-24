import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Dimension;

import java.io.Serial;
import java.io.Serializable;    // used to create serialVersion UID when press Alt + Enter

public class Window extends Canvas implements Serializable{

    @Serial
    private static final long serialVersionUID = -8145970048653281630L;

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(80 * Game.WIDTH_RATIO, 80 * Game.HEIGHT_RATIO));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null); // allows window to start in middle instead of left side
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}