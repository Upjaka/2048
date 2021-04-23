package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    Field field;

    @BeforeEach
    void init() {
        field = new Field();
        field.clear();
    }

    @Test
    void add() {
        field.add(0, 0);
        assertTrue(Arrays.equals(field.getField()[0], new int[]{1, 0, 0, 0}));

        field.add(-1, -1);
        assertTrue(Arrays.equals(field.getField()[0], new int[]{1, 0, 0, 0}));
    }

    @Test
    void addRandom() {
        field.addRandom();
        field.addRandom();
        int count = 0;
        for (int[] ints : field.getField()) {
            for (int i : ints) {
                if (i != 0) count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    void addRandom1() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(field.getField()[i], 1);
        }
        field.getField()[3] = new int[] {0, 1, 1, 1};
        field.addRandom();
        assertTrue(Arrays.equals(field.getField()[3], new int[] {1, 1, 1, 1}));
    }

    @Test
    void isFull() {
        assertFalse(field.isFull());
        for (int[] ints : field.getField()) {
            Arrays.fill(ints, 1);
        }
        assertTrue(field.isFull());
    }

    @Test
    void isLose() {
        assertFalse(field.isLose());
    }

    @Test
    void isLose1() {
        for (int[] ints : field.getField()) {
            Arrays.fill(ints, 1);
        }
        assertFalse(field.isLose());
    }

    @Test
    void isLose2() {
        for (int i = 0; i < field.getSize(); i++) {
            field.getField()[i] = i % 2 == 0 ? new int[]{1, 2, 1, 2} : new int[]{2, 1, 2, 1};
        }
        assertTrue(field.isLose());
    }

    @Test
    void getNeighbours() {
        field.add(1, 1);
        field.add(2, 1);
        field.add(1, 2);
        field.add(2, 2);
        assertEquals(Arrays.asList(0, 0), field.getNeighbors(0, 0));
        assertEquals(Arrays.asList(0, 0, 1, 1), field.getNeighbors(1, 1));
    }

    @Test
    void move() {
        field.add(0, 3);
        assertTrue(field.move(0, 3, Direction.LEFT));
        assertTrue(Arrays.equals(new int[]{1, 0, 0, 0}, field.getField()[0]));
    }

    @Test
    void move1() {
        field.add(0, 3);
        field.add(0, 0);
        assertTrue(field.move(0, 3, Direction.LEFT));
        assertTrue(Arrays.equals(new int[]{2, 0, 0, 0}, field.getField()[0]));
    }

    @Test
    void move2() {
        field.add(0, 2);
        field.add(0, 0, 2);
        assertTrue(field.move(0, 2, Direction.LEFT));
        assertTrue(Arrays.equals(new int[]{2, 1, 0, 0}, field.getField()[0]));
    }

    @Test
    void move3() {
        field.add(0, 1);
        field.add(0, 0, 2);
        assertFalse(field.move(0, 1, Direction.LEFT));
    }

    @Test
    void moveAll() {
        field.add(0, 3);
        field.moveAll(Direction.LEFT);
        assertEquals((int) field.get(0, 0), 2);
    }

    @Test
    void moveAll1() {
        field.add(0, 0);
        field.add(0, 3);
        assertTrue(field.moveAll(Direction.LEFT));
        assertTrue(Arrays.equals(new int[]{2, 0, 0, 0}, field.getField()[0]));
    }

    @Test
    void moveAll2() {
        field.add(0, 0, 2);
        field.add(0, 3);
        assertTrue(field.moveAll(Direction.LEFT));
        assertTrue(Arrays.equals(new int[]{2, 1, 0, 0}, field.getField()[0]));
    }

    @Test
    void moveAll3() {
        field.add(0, 0, 2);
        field.add(0, 1);
        field.add(0, 2);
        field.add(0, 3);
        field.moveAll(Direction.LEFT);
        assertTrue(Arrays.equals(new int[]{2, 2, 1, 0}, field.getField()[0]));

        assertFalse(field.moveAll(Direction.UP));
    }

    @Test
    void testToString() {
        assertEquals("0, 0, 0, 0\n0, 0, 0, 0\n0, 0, 0, 0\n0, 0, 0, 0\n", field.toString());
        field.add(0, 0);
        field.add(1, 1);
        field.add(2, 2);
        field.add(3, 3);
        assertEquals("1, 0, 0, 0\n0, 1, 0, 0\n0, 0, 1, 0\n0, 0, 0, 1\n", field.toString());
    }
}