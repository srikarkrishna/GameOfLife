import java.lang.Math;
public class LifeGrid {

    Cell[][] currentCellState;
    int gridRows;
    int gridColumns;

    /*************************************************************************************
     *  - public Constructor
     *  - Author : Sulabh
     *  - Creation Date : 14/02/2021
     *  - Desc: Instantiating a grid with an initial pattern and assigning the values to the cells
     ***************************************************************************************/
    public LifeGrid(int xPanel, int yPanel, int cellSize) {
        gridRows = xPanel / cellSize;
        gridColumns = yPanel / cellSize;
        currentCellState = new Cell[gridRows][gridColumns];
        for (int xCell = 0; xCell < currentCellState.length; xCell++) {
            for (int yCell = 0; yCell < currentCellState[xCell].length; yCell++) {
                currentCellState[xCell][yCell] = new Cell();
                currentCellState[xCell][yCell].updateCellState(0);
            }
        }
        spawnStartShape();
    }
    /*****************************************************************
     *  - Method Name: spawnStartShape()
     *  - Input Parameters :
     *  - Return Type : void
     *  - Author : Sneha
     *  - Creation Date : 16/02/2021
     *  - Description : Generate a default cell pattern to be displayed at the starting of the game and every reset.
     *******************************************************************/
    public void spawnStartShape() {
        int selectRandomX = getRandomX();
        int selectRandomY = getRandomY();
        setAlive(selectRandomX, selectRandomY);
        setAlive(selectRandomX + 1, selectRandomY);
        setAlive(selectRandomX - 1, selectRandomY);
        setAlive(selectRandomX + 1, selectRandomY - 1);
        setAlive(selectRandomX, selectRandomY - 2);
    }
    //Generate a random X co-ordinate
    private int getRandomX() {
        return ((int) (Math.random() * (gridRows / 3))) + gridRows / 4;
    }
    //Generate a random Y co-ordinate
    private int getRandomY() {
        return ((int) (Math.random() * (gridColumns / 3))) + (gridColumns / 4);
    }
    //Set a cell state to Alive
    public void setAlive(int xCell, int yCell) {
        currentCellState[xCell][yCell].updateCellState(1);
    }

    /*****************************************************************
     *  - Method Name: updateClickedCellState()
     *  - Input Parameters :  X,Y Co-ordinates
     *  - Return Type : void
     *  - Author : Srikar
     *  - Creation Date : 16/02/2021
     *  - Description :  Update the state of the cell based on the current state.
     *******************************************************************/
    public void updateClickedCellState(int xCell, int yCell) {
        if (currentCellState[xCell][yCell].getCellState() == 0) {
            currentCellState[xCell][yCell].updateCellState(1);
        } else if (currentCellState[xCell][yCell].getCellState() == 1) {
            currentCellState[xCell][yCell].updateCellState(0);
        }
    }

    /*****************************************************************
     *  - Method Name: computeNextState()
     *  - Input Parameters :
     *  - Return Type : void
     *  - Author : Srikar
     *  - Creation Date : 16/02/2021
     *  - Description : Compute the next state of a cell based on the alive members in the neighboring cells.
     *******************************************************************/
    public void computeNextState() {
        int countLiveNeighbors;
        for (int x = 0; x < currentCellState.length; x++) {
            for (int y = 0; y < (gridColumns); y++) {
                countLiveNeighbors = checkCellState(x, y);
                if (countLiveNeighbors == 3) {
                    currentCellState[x][y].updateCellState(1);
                } else if (countLiveNeighbors == 2 && currentCellState[x][y].getCellState() == 1) {
                    currentCellState[x][y].updateCellState(1);
                } else {
                    currentCellState[x][y].updateCellState(0);
                }
            }
        }
    }

    /*****************************************************************
     *  - Method Name: checkCellState()
     *  - Input Parameters : X,Y coordinates
     *  - Return Type : int
     *  - Author : Keshav
     *  - Creation Date : 16/02/2021
     *  - Description : Method to count the number of live neighbors to a cell.
     *******************************************************************/
    private int checkCellState(int xCell, int yCell) {
        int countLiveNeighbors = 0;
        for (int k = (Math.max(xCell - 1, 0)); k <= Math.min(xCell + 1, gridRows - 1); k++) {
            for (int l = (Math.max(yCell - 1, 0)); l <= Math.min(yCell + 1, gridColumns - 1); l++) {
                if (currentCellState[k][l].getCellState() > 0) {
                    countLiveNeighbors++;
                }
            }
        }
        if (currentCellState[xCell][yCell].getCellState() > 0) {
            countLiveNeighbors--;
        }
        return countLiveNeighbors;
    }

    /*****************************************************************
     *  - Method Name: resetGridState()
     *  - Input Parameters :
     *  - Return Type : void
     *  - Author : Keshav
     *  - Creation Date : 16/02/2021
     *  - Description :  Method to reset all the cells to initial state in the game.
     *******************************************************************/
    public void resetGridState() {
        for (Cell[] cells : currentCellState) {
            for (int yCell = 0; yCell < gridColumns; yCell++) {
                cells[yCell].updateCellState(0);
            }
        }
    }

    /*****************************************************************
     *  - Method Name: saveGridState()
     *  - Input Parameters :
     *  - Return Type : void
     *  - Author : Sneha
     *  - Creation Date : 16/02/2021
     *  - Description :  Save the current state of a cell.
     *******************************************************************/
    public void saveGridState() {
        for (Cell[] cells : currentCellState) {
            for (int yCell = 0; yCell < gridColumns; yCell++) {
                cells[yCell].saveCellState();
            }
        }
    }
    /*****************************************************************
     *  - Method Name: updateGridState()
     *  - Input Parameters :
     *  - Return Type : void
     *  - Author : Sneha
     *  - Creation Date : 16/02/2021
     *  - Description :  Compute the next state and Update it with the next grid state.
     *******************************************************************/
    public void updateGridState() {
        computeNextState();
        saveGridState();
    }

}
