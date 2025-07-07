package Main;

import java.util.Collection;
import java.util.HashSet;

public class Board {
    private HashSet<Cell> currentAlive;
    private HashSet<Cell> aliveAndNeighbors;
    private int iterations;
    private int alive;

    // starts with the cells which are alive
    public Board() {
        currentAlive = new HashSet<>();
        iterations = 0;
    }
    public Board(Collection<Cell> cells) {
        currentAlive = new HashSet<>(cells);
        iterations = 0;
    }
    public HashSet<Cell> getCurrentCells() {
        return currentAlive;
    }
    public boolean contains(Cell cell) {
        return currentAlive.contains(cell);
    }
    public void nextStep() {
        setAliveAndNeighbors();
        setNewAlive();
        iterations++;
    }
    public int getIterations() {
        return iterations;
    }
    public int getAliveNumber() {
        return currentAlive.size();
    }
    public void reset() {
        currentAlive = new HashSet<>();
        iterations = 0;
    }
    private void setNewAlive() {
        HashSet<Cell> nextAlive = new HashSet<>();
        for (Cell cell : aliveAndNeighbors) {
            boolean alive = currentAlive.contains(cell);
            int livingNeighbors = alive ? -1 : 0; // account for double counting with alive check
            for (Cell neighbor : cell.neighbors()) {
                if (currentAlive.contains(neighbor)) {
                    livingNeighbors++;
                }
            }
            if (cell.aliveNext(alive, livingNeighbors)) {
                nextAlive.add(cell);
            }
        }
        currentAlive = nextAlive;
    }
    private void setAliveAndNeighbors() {
        aliveAndNeighbors = new HashSet<>();
        for (Cell cell : currentAlive) {
            aliveAndNeighbors.addAll(cell.neighbors());
        }
    }
    public void changeCell(Cell cell) {
        if (contains(cell)) {
            currentAlive.remove(cell);
        } else {
            currentAlive.add(cell);
        }
    }


}
