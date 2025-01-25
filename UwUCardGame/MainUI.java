import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainUI{

    // constructor
    public MainUI(){
        // create a window
        JFrame window = new JFrame("UwU Card Game");

        // minimum size of window
        Dimension minimumWindowSize = new Dimension(960, 540);

        // set the minimum size of window, and visibility
        window.setSize(minimumWindowSize); // WHY ARE YOU NOT MINIMUM SIZE??
        window.setMinimumSize(minimumWindowSize);
        window.setVisible(true);

        // set the operation perform when user close the window
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // main function
    public static void main(String[] args){
        MainUI mainUI = new MainUI();
    }
}