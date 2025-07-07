package Main;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TestCell {

    @Test
    public void testNeighbors() {
        Cell cell = new Cell(3, 4);
        assert(cell.neighbors().contains(cell));
        assert(cell.neighbors().contains(new Cell(3,4)));

        assert(cell.neighbors().contains(new Cell(2,3)));
        assert(cell.neighbors().contains(new Cell(2,4)));
        assert(cell.neighbors().contains(new Cell(2,5)));
        assert(cell.neighbors().contains(new Cell(3,3)));
        assert(cell.neighbors().contains(new Cell(3,5)));
        assert(cell.neighbors().contains(new Cell(4,3)));
        assert(cell.neighbors().contains(new Cell(4,4)));
        assert(cell.neighbors().contains(new Cell(4,5)));
    }
    @Test
    public void testNonNeighbors() {
        Cell cell = new Cell(3, 4);
        assert(cell.neighbors().contains(cell));
        assertFalse(cell.neighbors().contains(new Cell(2,2)));

        assertFalse(cell.neighbors().contains(new Cell(2,6)));
        assertFalse(cell.neighbors().contains(new Cell(2,-4)));
        assertFalse(cell.neighbors().contains(new Cell(0,0)));

    }
}
