import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.*;

public class MainUI extends JFrame implements ComponentListener, EventListener{

    // objects
    private JFrame window;
    private SelectionSide selectionSide;
    private MainContentSide mainContentSide;

    // constructor
    public MainUI(){
        // create a window
        window = this;
        // content pane of window
        Container windowPane = window.getContentPane();

        // minimum size of window
        Dimension minimumWindowSize = new Dimension(960, 540);

        // set the minimum size of window, and visibility
        window.setSize(minimumWindowSize); // WHY ARE YOU NOT MINIMUM SIZE??
        window.setMinimumSize(minimumWindowSize);
        window.setVisible(true);
        window.setLocationRelativeTo(null);

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
    
        // repaint in case of any sub JPanel changes
        this.componentResized(null);
    }

    // event listener
    @Override
    public void componentResized(ComponentEvent E){
        this.selectionSide.callResize(this.window.getSize());
        this.mainContentSide.callResize(this.window.getSize());
        this.revalidate();
        this.repaint();
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
    private class SelectionSide extends JPanel implements WindowPanel, ActionListener{
        private UserPanel userPanel;
        private ButtonsPanel buttonsPanel;
        private NewsPanel newsPanel;

        public SelectionSide(Dimension currentWindowDimension){
            // initialize size and color
            this.setSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
            this.setBackground(new Color(255, 255, 255));

            // set the container layout to box layout
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // creates the three panels inside
            userPanel = new UserPanel();
            buttonsPanel = new ButtonsPanel();
            newsPanel = new NewsPanel();

            this.add(userPanel);
            this.add(buttonsPanel);
            buttonsPanel.uwuButton.addActionListener(this);
            buttonsPanel.owoButton.addActionListener(this);
            this.add(newsPanel);
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("it is uwu time")){
                mainContentSide.remove(mainContentSide.mainContent);
                mainContentSide.mainContent = new UwU();
                mainContentSide.add(mainContentSide.mainContent);
                window.revalidate();
                window.repaint();
                System.out.print("uwu?");
            }else if(e.getActionCommand().equals("it is owo time")){
                System.out.print("owo?");
            }else{
                System.out.print("uwaaaaa???");
            }
        }

        // userPanel, the thingy that display usernmae and user profile picture
        private class UserPanel extends JPanel{
            public UserPanel(){
                this.setBackground(new Color(191, 13, 62));
            }
        }

        // buttonsPanel, the thingy that display all the selctable secton
        private class ButtonsPanel extends JPanel{
            public JButton uwuButton;  // Made public so outer class can access
            public JButton owoButton;
            public ButtonsPanel(){
                this.setBackground(new Color(224, 249, 249));
                // buttons
                uwuButton = new JButton("uwu time");
                owoButton = new JButton("owo time");

                uwuButton.setActionCommand("it is uwu time");
                owoButton.setActionCommand("it is owo time");

                this.add(uwuButton);
                this.add(owoButton);
            }
        }

        // newsPanel, the thingy that display news upon selection
        private class NewsPanel extends JPanel{
            public NewsPanel (){

            }
        }
    }


    // MainContentSide, the part that display the main content
    public class MainContentSide extends JPanel implements WindowPanel{
        JPanel mainContent;

        public MainContentSide(Dimension currentWindowDimension){
            this.setBackground(new Color(0, 0, 0));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            mainContent = new JPanel();
            this.add(mainContent);
            mainContent.setSize(currentWindowDimension);
            mainContent.setBackground(new Color(0, 45, 114));
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.7), currentWindowDimension.height));
            mainContent.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.7), currentWindowDimension.height));
        }
    }

    // interface for left and right panel
    private interface WindowPanel{
        public void callResize(Dimension currentDimension); // call when the window is resized
    }
}