import javax.swing.*;

public class LifeFrame extends JFrame {


    // Creating frame for adding grids in it
    // and performing different actions in those grids.
    public LifeFrame() {

        add(new LifePanel());

//        button1.setBounds(960,810,100,50);
//        button2.setBounds(1060,810,100,50);

//        this.getContentPane().add(panel);


        setSize(1366, 768);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new LifeFrame();


    }
}
