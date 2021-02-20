import javax.swing.*;
import java.awt.*;


public class Button extends JButton {
    /*************************************************************************************
     *  - public Class
     *  - Author : Srikar
     *  - Creation Date : 16/02/2021
     *  - Desc: Configure the properties of the buttons.
     ***************************************************************************************/
    public Button(String buttonLabel) {
        super(buttonLabel);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(150, 50);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100, 50);
    }

    //Adjust the size of Buttons in the GUI
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 50);
    }

    @Override
    public Font getFont() {
        return new Font("Arial", Font.BOLD, 25);
    }
}
