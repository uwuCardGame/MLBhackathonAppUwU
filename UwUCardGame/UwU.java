import java.awt.Color;
import java.awt.Label;
import javax.swing.JPanel;

public class UwU extends JPanel{
    private Display display;
    private Header header;
    public UwU(){
        header = new Header();
        this.add(header);
        display = new Display();
        this.add(display);
    }
    public class Header extends JPanel{
        public Header(){
            this.setBackground(new Color(165, 165, 165));
        }
    }
    public class Display extends JPanel{
        public Display(){
            this.setBackground(new Color(0, 45, 114));
        }
    }
}