package Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    public Collection<? extends Cell> neighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                neighbors.add(new Cell(x +i, y+j));
            }
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cell that = (Cell) o;
        return x == that.x && y == that.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString(){
        String string = "";
        string += x +" " + y;
        return string;
    }
    public boolean aliveNext(boolean currentlyAlive, int neighborsAlive) {
        switch (neighborsAlive) {
            case 2: {
                return currentlyAlive;
            }
            case 3: return true;
            default: return false;
        }
    }
}
