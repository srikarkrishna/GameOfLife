public class LifeGrid {

    Cell[][] currentCellState;
    int gridRows;
    int gridColumns;

    // Initial grid structure and setting all cell values to 0(dead).
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

    // Default cell patterns to be displayed on the game screen before starting the game.
    public void spawnStartShape() {
        int selectRandomX = getRandomX();
        int selectRandomY = getRandomY();
        setAlive(selectRandomX, selectRandomY);
        setAlive(selectRandomX + 1, selectRandomY - 1);
        setAlive(selectRandomX - 1, selectRandomY - 2);
        setAlive(selectRandomX, selectRandomY - 2);
        setAlive(selectRandomX + 1, selectRandomY - 2);
    }

    private int getRandomX() {
        return ((int) (Math.random() * (gridRows / 3))) + gridRows / 4;
    }

    private int getRandomY() {
        return ((int) (Math.random() * (gridColumns / 3))) + (gridColumns / 4);
    }

    public void setAlive(int xCell, int yCell) {
        currentCellState[xCell][yCell].updateCellState(1);
    }

    // Change the state of the cell from dead to alive or vice versa on click event.
    public void updateClickedCellState(int xCell, int yCell) {
        if (currentCellState[xCell][yCell].getCellState() == 0) {
            currentCellState[xCell][yCell].updateCellState(1);
        } else if (currentCellState[xCell][yCell].getCellState() == 1) {
            currentCellState[xCell][yCell].updateCellState(0);
        }
    }

    // Based on the values of neighbours the next state is computed for each cell.
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


    // Function to check the state of cell, whether the cell is alive or dead.
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


    // Function to return to initial state in the game.
    public void resetGridState() {
        for (Cell[] cells : currentCellState) {
            for (int yCell = 0; yCell < gridColumns; yCell++) {
                cells[yCell].updateCellState(0);
            }
        }
    }


    // Save the current state of the grid.
    public void saveGridState() {
        for (Cell[] cells : currentCellState) {
            for (int yCell = 0; yCell < gridColumns; yCell++) {
                cells[yCell].saveCellState();
            }
        }
    }

    // Updating and computing the grid state.
    public void updateGridState() {
        computeNextState();
        saveGridState();
    }

}
