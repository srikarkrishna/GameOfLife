import javax.swing.*;
import java.awt.*;

public class LifeFrame extends JFrame {

    public LifeFrame() {

        add(new LifePanel());
//        JPanel panel = new LifePanel();
//
//        JButton button1 = new JButton("Start");
//        JButton button2 = new JButton("Reset");
//
//        JToolBar jt = new JToolBar("Tools");
//
//        jt.add(button1);
//        jt.add(button2);

//        panel.setLayout (new BorderLayout());
//        panel.add (jt, BorderLayout.SOUTH);


//        panel.add(button1);
//        panel.add(button2);

//        button1.setBounds(960,810,100,50);
//        button2.setBounds(1060,810,100,50);

//        this.getContentPane().add(panel);


        setSize(1366,768);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){

        new LifeFrame();


    }
}
