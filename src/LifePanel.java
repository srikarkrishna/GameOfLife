import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class LifePanel extends JPanel implements ActionListener,
        MouseListener,
        MouseMotionListener {

    int xPanel = 1920;
    int yPanel = 1080;
    int cellSize = 20;
    LifeGrid lifeGrid;
    Timer time;
    JButton startButton;
    JButton resetButton;
    JLabel jLabel;

    public static int iterator = 0;

    public LifePanel() {

        setSize(xPanel, yPanel);
        setLayout(null);
        lifeGrid = new LifeGrid(xPanel, yPanel, cellSize);
        addMouseMotionListener(this);
        addMouseListener(this);
        startButton = new Button("Start");
        resetButton = new Button("Reset");

        startButton.setPreferredSize(new Dimension(80,60));
        resetButton.setPreferredSize(new Dimension(80,60));


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.getText().equals("Start")) {
                    time.start();
                    startButton.setText("Stop");
                } else {
                    time.stop();
                    startButton.setText("Start");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lifeGrid.resetGridState();
                startButton.setText("Start");
                time.stop();
                lifeGrid.spawnStartShape();
                LifePanel.iterator = 0;
                updateJLabel();
                repaint();
            }
        });

        jLabel = new JLabel();
        updateJLabel();
        JToolBar gameToolBar = new JToolBar("Tools"); // For adding buttons to start and reset the game.
        gameToolBar.add(startButton);
        gameToolBar.add(resetButton);
        gameToolBar.add(jLabel);
        gameToolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(gameToolBar);
        this.setLayout(new BorderLayout());
        this.add(gameToolBar, BorderLayout.SOUTH);
        setFocusable(true);
        time = new Timer(500, this);
        repaint();

    }

    // Used to update the Iterations jLabel displayed in the toolbar.
    public void updateJLabel() {
        jLabel.setText("   "+"Iterations: "+LifePanel.iterator+" ");
    }

    public void paintComponent(Graphics view) {
        super.paintComponent(view);
        setBackground(Color.BLACK);
        grid(view);
        display(view);
    }

    //Displaying grid and creating simple rows and columns of same size.
    public void grid(Graphics view) {
        view.setColor(Color.DARK_GRAY);
        for (int i = 0; i < (xPanel / cellSize); i++) {
            view.drawLine(0, i * cellSize, xPanel, i * cellSize);
            view.drawLine(i * cellSize, 0, i * cellSize, yPanel);
        }
    }

    // Displaying grid using Graphics
    // and filling colors in squares when a click is performed.
    private void display(Graphics view) {
        view.setColor(Color.ORANGE);
        lifeGrid.saveGridState();
        for (int x = 0; x < xPanel / cellSize; x++) {
            for (int y = 0; y < (yPanel / cellSize); y++) {
                if (lifeGrid.currentCellState[x][y].getCellState() == 1) {
                    view.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    // Action performed is used to paint the cells
    // and show the score based on event performed.
    public void actionPerformed(ActionEvent e) {
        LifePanel.iterator += 1;
        lifeGrid.updateGridState();
        repaint();
        updateJLabel();
    }

    // On click event
    public void mousePressed(MouseEvent e) {
        int xClicked = e.getX() / cellSize;
        int yClicked = e.getY() / cellSize;
        lifeGrid.updateClickedCellState(xClicked, yClicked);
        repaint();

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

}
