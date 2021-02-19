// Class to maintain all the actions to be performed on cells.
public class Cell {
    int currentState = 0;
    int nextState;

    public int getCellState() {
        return currentState;
    }

    public void updateCellState(int state) {
        nextState = state;
    }

    public void saveCellState() {
        currentState = nextState;
    }
}