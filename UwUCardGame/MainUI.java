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
        private JPanel userPanel;
        private JPanel buttonsPanel;
        private JPanel newsPanel;

        public SelectionSide(Dimension currentWindowDimension){
            // initialize size and color
            this.setSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
            this.setBackground(new Color(255, 255, 255));

            // set the container layout to box layout
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // creates the three panels inside
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
        }
    }


    // MainContentSide, the part that display the main content
    private class MainContentSide extends JPanel implements WindowPanel{
        public MainContentSide(Dimension currentWindowDimension){
            this.setSize(new Dimension(currentWindowDimension.width, currentWindowDimension.height));
            this.setBackground(new Color(0, 45, 114));
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setSize(new Dimension(currentWindowDimension.width, currentWindowDimension.height));
        }
    }

    // interface for left and right panel
    private interface WindowPanel{
        public void callResize(Dimension currentDimension); // call when the window is resized
    }

    // main function
    public static void main(String[] args){
        MainUI mainUI = new MainUI();
    }
}