import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String buttonLabel)
    {
        super(buttonLabel);
    }
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(100,50);
    }
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100,50);
    }
}
