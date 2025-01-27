import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.*;

public class MainUI implements ComponentListener, EventListener{

    // objects
    private JFrame window;
    private SelectionSide selectionSide;
    private MainContentSide mainContentSide;

    // constructor
    public MainUI(){
        // create a window
        window = new JFrame("UwU Card Game");
        // content pane of window
        Container windowPane = window.getContentPane();

        // minimum size of window
        Dimension minimumWindowSize = new Dimension(960, 540);

        // set the minimum size of window, and visibility
        window.setSize(minimumWindowSize); // WHY ARE YOU NOT MINIMUM SIZE??
        window.setMinimumSize(minimumWindowSize);
        window.setVisible(true);

        // set the operation perform when user close the window
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // customize window
        windowPane.setBackground(new Color(4, 30, 66)); // I think it is because JFrame extends from Frame extends from Window, that's why you need getContentPane() to get its Container???
        windowPane.setLayout(new BoxLayout(windowPane, BoxLayout.X_AXIS));
    
        // add the selection side to the left
        selectionSide = new SelectionSide(this.window.getSize());
        windowPane.add(selectionSide);

        // add the main content side to the right
        mainContentSide = new MainContentSide(this.window.getSize());
        windowPane.add(mainContentSide);

        // add event listener
        window.addComponentListener(this); // "this" because MainUI implements the listener and override the method
    }

    // event listener
    @Override
    public void componentResized(ComponentEvent E){
        this.selectionSide.callResize(this.window.getSize());
        this.mainContentSide.callResize(this.window.getSize());
        //System.out.println("window size: " + (String)(this.window.getSize().width));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }


    // SelectionSide, the part which user can choose the main content options
    private class SelectionSide extends JPanel implements WindowPanel{
        public SelectionSide(Dimension currentWindowDimension){
            this.setMaximumSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
            this.setBackground(new Color(255, 255, 255));
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setMaximumSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
            this.setBackground(new Color(50, 50, 50));
        }
    }


    // MainContentSide, the part that display the main content
    private class MainContentSide extends JPanel implements WindowPanel{
        public MainContentSide(Dimension currentWindowDimension){
            this.setMaximumSize(new Dimension(currentWindowDimension.width, currentWindowDimension.height));
            this.setBackground(new Color(30, 66, 100));
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setMaximumSize(new Dimension(currentWindowDimension.width, currentWindowDimension.height));
            this.setBackground(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
        }
    }

    // interface for left and right panel
    private interface WindowPanel{
        public void callResize(Dimension currentDimension);
    }

    // main function
    public static void main(String[] args){
        MainUI mainUI = new MainUI();
    }
}