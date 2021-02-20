import javax.swing.*;

public class LifeFrame extends JFrame {


    // Creating frame for adding grids in it
    // and performing different actions in those grids.
    public LifeFrame() {

        add(new LifePanel());
        setTitle("Game of Life");
        setSize(1366, 768);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new LifeFrame();


    }
}
