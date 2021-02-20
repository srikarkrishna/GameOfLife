// Class to maintain all the actions to be performed on cells.
public class Cell {
    /*************************************************************************************
     *  - public Class
     *  - Author : Sneha
     *  - Creation Date : 14/02/2021
     *  - Desc: Defining the cell class and providing getters/setters to modify cell state.
     ***************************************************************************************/
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