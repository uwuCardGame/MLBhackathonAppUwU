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
    private class SelectionSide extends JPanel implements WindowPanel{
        private UserPanel userPanel;
        private ButtonsPanel buttonsPanel;
        private NewsPanel newsPanel;

        public SelectionSide(Dimension currentWindowDimension){
            // initialize size and color
            this.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
            this.setBackground(new Color(255, 255, 255));

            // set the container layout to box layout
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // creates the three panels inside
            userPanel = new UserPanel();
            buttonsPanel = new ButtonsPanel();
            newsPanel = new NewsPanel();

            this.add(userPanel);
            this.add(buttonsPanel);
            this.add(newsPanel);
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.3), currentWindowDimension.height));
            this.revalidate();
            this.repaint();
        }

        // userPanel, the thingy that display usernmae and user profile picture
        private class UserPanel extends JPanel{
            public UserPanel(){
                this.setBackground(new Color(191, 13, 62));
            }
        }

        // buttonsPanel, the thingy that display all the selctable secton
        private class ButtonsPanel extends JPanel implements ActionListener{
            private JButton socialButton;  // Made public so outer class can access
            private JButton collectionButton;  // this one can keep private since it is a nested class, and I moved the action listener to here uwu
            private JButton matchButton;
            private JButton shopButton;

            // constant for string
            final String SOCIALACTION = "SOCIAL";
            final String COLLECTIONACTION = "COLLECTION";
            final String MATCHACTION = "MATCH";
            final String SHOPACTION = "SHOP";

            String CURRENTACTION = SOCIALACTION;

            public ButtonsPanel(){
                // customize
                this.setBackground(new Color(224, 249, 249));

                // set BoxLayout and arrange from top to bottom
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                // buttons
                socialButton = new JButton("Social");
                collectionButton = new JButton("Collection");
                matchButton = new JButton("Matches");
                shopButton = new JButton("Shop");

                socialButton.setActionCommand(SOCIALACTION);
                collectionButton.setActionCommand(COLLECTIONACTION);
                matchButton.setActionCommand(MATCHACTION);
                shopButton.setActionCommand(SHOPACTION);
                
                socialButton.addActionListener(this);
                collectionButton.addActionListener(this);
                matchButton.addActionListener(this);
                shopButton.addActionListener(this);

                this.add(socialButton);
                this.add(collectionButton);
                this.add(matchButton);
                this.add(shopButton);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                // check if the redraw is needed (avoid reloading the same page)
                if (!(e.getActionCommand().equals(CURRENTACTION))){
                    // remove the mainContent JPanel
                    mainContentSide.remove(mainContentSide.mainContent);
                    // set the CURRENTACTION to actionCommand
                    CURRENTACTION = e.getActionCommand();

                    // create and assign the new JPanel
                    if (e.getActionCommand().equals(SOCIALACTION)){
                        mainContentSide.mainContent = new Social();
                    }else if(e.getActionCommand().equals(COLLECTIONACTION)){
                        mainContentSide.mainContent = new Collection();
                    }else if(e.getActionCommand().equals(MATCHACTION)){
                        mainContentSide.mainContent = new Match();
                    }else if(e.getActionCommand().equals(SHOPACTION)){
                        mainContentSide.mainContent = new Shop();
                    }

                    // add the JPanel into the mainContentSide
                    mainContentSide.add(mainContentSide.mainContent);
                }

                mainContentSide.revalidate();
                mainContentSide.repaint();
            }
        }

        // newsPanel, the thingy that display news upon selection
        private class NewsPanel extends JPanel{
            public NewsPanel (){

            }
        }
    }


    // MainContentSide, the part that display the main content
    private class MainContentSide extends JPanel implements WindowPanel{
        JPanel mainContent;

        public MainContentSide(Dimension currentWindowDimension){
            this.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.7), currentWindowDimension.height));
            this.setBackground(new Color(0, 0, 0));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // setting a layout for JPanel remove the 5px border

            mainContent = new JPanel();
            this.add(mainContent);
            mainContent.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.7), currentWindowDimension.height));
            mainContent.setBackground(new Color(0, 45, 114));
        }

        @Override
        public void callResize(Dimension currentWindowDimension){
            this.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.7), currentWindowDimension.height));
            mainContent.setPreferredSize(new Dimension((int)(currentWindowDimension.width * 0.7), currentWindowDimension.height));
            this.revalidate();
            this.repaint();
        }
    }

    // interface for left and right panel
    private interface WindowPanel{
        public void callResize(Dimension currentDimension); // call when the window is resized
    }
}