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
        frame.setPreferredSize(new Dimension(1700, 956));
        frame.setMinimumSize(new Dimension(960, 540));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null); // allows window to start in middle instead of left side
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}