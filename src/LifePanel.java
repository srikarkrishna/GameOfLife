import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class LifePanel extends JPanel implements ActionListener,
        MouseListener,
        MouseMotionListener,
        KeyListener {

    int xPanel = 1920;
    int yPanel = 1080;
    int cellSize = 20;
    LifeGrid lifeGrid;
    Timer time;
    public static int iterator = 0;

    public LifePanel() {

        setSize(xPanel, yPanel);
        setLayout(null);
        lifeGrid = new LifeGrid(xPanel, yPanel, cellSize);
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        JButton startButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");
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
                repaint();
            }
        });

        JToolBar gameToolBar = new JToolBar("Tools");
        gameToolBar.add(startButton);
        gameToolBar.add(resetButton);
        this.add(gameToolBar);
        this.setLayout(new BorderLayout());
        this.add(gameToolBar, BorderLayout.SOUTH);
        setFocusable(true);
        time = new Timer(500, this);
        repaint();

    }

    public void paintComponent(Graphics view) {
        super.paintComponent(view);
        setBackground(Color.BLACK);
        grid(view);
        display(view);
    }

    public void grid(Graphics view) {
        view.setColor(Color.DARK_GRAY);
        for (int i = 0; i < (xPanel / cellSize); i++) {
            view.drawLine(0, i * cellSize, xPanel, i * cellSize);
            view.drawLine(i * cellSize, 0, i * cellSize, yPanel);
        }
    }

    private void display(Graphics view) {
        view.setColor(Color.GRAY);
        lifeGrid.saveGridState();
        for (int x = 0; x < xPanel / cellSize; x++) {
            for (int y = 0; y < (yPanel / cellSize); y++) {
                if (lifeGrid.currentCellState[x][y].getCellState() == 1) {
                    view.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        LifePanel.iterator += 1;
        lifeGrid.updateGridState();
        repaint();
        System.out.println(LifePanel.iterator);
    }


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

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
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
