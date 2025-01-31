import java.awt.*;
import javax.swing.*;

public class Collection extends JPanel{
    private Display display;
    private Header header;

    public Collection(){
        header = new Header();
        this.add(header);
        display = new Display();
        this.add(display);
    }

    private class Header extends JPanel{
        public Header(){
            this.setBackground(new Color(165, 165, 165));
            this.setPreferredSize(new Dimension(100, 200));
        }
    }

    private class Display extends JPanel{
        public Display(){
            this.setBackground(new Color(0, 45, 114));
        }
    }
}