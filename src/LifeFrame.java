import javax.swing.*;

public class LifeFrame extends JFrame {

    final static String gameTitle = "GAME OF LIFE";
    // Creating frame for adding grids in it
    // and performing different actions in those grids.
    public LifeFrame(String title) {
        super(title);
        add(new LifePanel());
        setSize(1366, 768);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        JFrame jframe = new LifeFrame(gameTitle);

    }
}
