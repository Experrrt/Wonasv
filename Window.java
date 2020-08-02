
import javax.swing.*;

public class Window extends JFrame {
    public Window(int width,int height,int scale,gameCore game){
        setTitle("SanoW");
        setSize(width*scale,height*scale);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(game);
        setVisible(true);
    }
}
