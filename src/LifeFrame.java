import javax.swing.*;

public class LifeFrame extends JFrame {

    final static String gameTitle = "GAME OF LIFE";

    /*************************************************************************************
     *  - public Constructor
     *  - Author : Keshav
     *  - Creation Date : 13/02/2021
     *  - Desc: Initiating the frame and configuring the properties for the frame
     ***************************************************************************************/
    public LifeFrame(String title) {
        super(title);
        add(new LifePanel());
        setSize(1366, 768);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Initializing a frame object
    public static void main(String[] args) {

        JFrame jframe = new LifeFrame(gameTitle);

    }
}
