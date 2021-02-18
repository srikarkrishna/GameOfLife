import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class LifePanel extends JPanel implements    ActionListener,
                                                    MouseListener,
                                                    MouseMotionListener,
                                                    KeyListener {

    int xPanel = 1920;
    int yPanel = 1080;

    int cellSize =20;
    int xWidth = xPanel / cellSize;
    int yHeight = yPanel / cellSize;

    int[][] lifeMatrix = new int[xWidth][yHeight];
    int[][] beforeLife = new int[xWidth][yHeight];

    boolean starts = true;
    Timer time;
    public static int iterator = 0;

    public LifePanel(){

        setSize(xPanel, yPanel);
        setLayout(null);

        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);

        JButton startButton = new JButton("Start");
        JButton resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startButton.getText().equals("Start")){
                    time.start();
                    startButton.setText("Stop");
                }
                else{
                    time.stop();
                    startButton.setText("Start");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                startButton.setText("Start");
                time.stop();
                spawn();
            }
        });

        JToolBar jt = new JToolBar("Tools");

        jt.add(startButton);
        jt.add(resetButton);
        this.add(jt);

        this.setLayout (new BorderLayout());
        this.add (jt, BorderLayout.SOUTH);

        setFocusable(true);

        time = new Timer(500, this);
//        time.start();
        spawn();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        setBackground(Color.BLACK);
//        g.setColor(Color.GRAY);
        grid(g);
//        spawn(g);
        display(g);
    }

    public void setAlive(int x, int y){
        beforeLife[x][y] = 1;
    }

    public void grid(Graphics g){

        g.setColor(Color.DARK_GRAY);
        for(int i = 0; i < (lifeMatrix.length) ; i++){

            g.drawLine(0, i*cellSize, xPanel, i*cellSize);
            g.drawLine(i*cellSize,0,i*cellSize, yPanel);

        }

    }

    private void spawn(){
        int selectRandomX = getRandomX();
        int selectRandomY = getRandomY();
        setAlive(selectRandomX,selectRandomY);
        setAlive(selectRandomX+1,selectRandomY-1);
        setAlive(selectRandomX-1,selectRandomY-2);
        setAlive(selectRandomX,selectRandomY-2);
        setAlive(selectRandomX+1,selectRandomY-2);
        repaint();
    }

    private int getRandomX() {
        return ((int) (Math.random() * (xWidth / 3))) + xWidth / 4;
    }

    private int getRandomY() {
        return  ((int) (Math.random() * (yHeight / 3))) + (yHeight / 4);
    }

    private void copyArray(){
        for (int x = 0; x < lifeMatrix.length; x++){

            for (int y = 0; y < (yPanel/cellSize); y++){

                lifeMatrix[x][y] = beforeLife[x][y];
            }
        }
    }

    private void display(Graphics g){
        g.setColor(Color.GRAY);
        copyArray();

        for (int x = 0; x < lifeMatrix.length; x++){

            for (int y = 0; y < (yPanel/cellSize); y++){

                if(lifeMatrix[x][y] == 1){
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }


    private int check(int x, int y){

        int countAlive = 0;

//        countAlive += lifeMatrix[x-1][y-1];
//        countAlive += lifeMatrix[x][y-1];
//        countAlive += lifeMatrix[x+1][y-1];
//        countAlive += lifeMatrix[x-1][y];
//        countAlive += lifeMatrix[x+1][y];
//        countAlive += lifeMatrix[x-1][y+1];
//        countAlive += lifeMatrix[x][y+1];
//        countAlive += lifeMatrix[x+1][y+1];
/*
        countAlive += lifeMatrix[(x-1 + xWidth) % xWidth][(y + yHeight-1) % yHeight];
        countAlive += lifeMatrix[(x+ xWidth)% xWidth][(y-1+yHeight) % yHeight];

        countAlive += lifeMatrix[(x+1+ xWidth)% xWidth][(y-1+yHeight) % yHeight];
        countAlive += lifeMatrix[(x-1+ xWidth)% xWidth][(y +yHeight) % yHeight];

        countAlive += lifeMatrix[(x+1+ xWidth)% xWidth][(y+yHeight) % yHeight];
        countAlive += lifeMatrix[(x-1+ xWidth)% xWidth][(y+1+yHeight) % yHeight];

        countAlive += lifeMatrix[(x+ xWidth)% xWidth][(y+1+yHeight) % yHeight];
        countAlive += lifeMatrix[(x+1+ xWidth)% xWidth][(y+1+yHeight) % yHeight];*/
        for(int k = (Math.max(x - 1, 0)); k<=Math.min(x + 1,xWidth-1); k++)
        {
            for(int l = (Math.max(y - 1, 0)); l<=Math.min(y + 1,yHeight-1); l++)
            {
                if(lifeMatrix[k][l]>0)
                {
                    countAlive++;
                }
            }
        }
        if(lifeMatrix[x][y]>0)
        {
            countAlive--;
        }
        return countAlive;
    }

    public void clear(){

        System.out.println("Pressed reset");

        for (int x = 0; x < lifeMatrix.length; x++){

            for (int y = 0; y < yHeight; y++){
                    beforeLife[x][y] = 0;
            }
        }

        repaint();

    }

//    public void startButton(ActionEvent e){
//        time.start();
//    }

    public void actionPerformed(ActionEvent e){

        int countAlive;
        LifePanel.iterator += 1;

        for (int x = 0; x < lifeMatrix.length; x++){

            for (int y = 0; y < (yPanel/cellSize); y++){

                countAlive = check(x,y);

                if (countAlive == 3){
                    beforeLife[x][y] = 1;
                }
                else if (countAlive == 2 && lifeMatrix[x][y] == 1){
                    beforeLife[x][y] = 1;
                }
                else {
                    beforeLife[x][y] = 0;
                }
            }
        }
        repaint();
        System.out.println(LifePanel.iterator);
    }


    public void mousePressed(MouseEvent e){

        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;
        //  time.stop();


        if (lifeMatrix[x][y] == 0){
            beforeLife[x][y] = 1;
        }

        else if (lifeMatrix[x][y] == 1){
            beforeLife[x][y] = 0;
        }
        repaint();

    }

    public void mouseReleased(MouseEvent e){ }

    public void mouseDragged(MouseEvent e){ }
    public void keyPressed(KeyEvent e){ }

    public void keyTyped(KeyEvent e){ }

    public void keyReleased(KeyEvent e){ }

    public void mouseEntered(MouseEvent e){ }

    public void mouseExited(MouseEvent e){ }

    public void mouseMoved(MouseEvent e){ }

    public void mouseClicked(MouseEvent e){ }

}
