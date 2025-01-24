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

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // allows window to start in middle instead of left side
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}