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
    }

    @Test
    void add() {
        field.clear();
        field.add(0, 0);
        assertTrue(Arrays.equals(field.getField()[0], new int[]{1, 0, 0, 0}));
        field.add(-1, -1);
        assertTrue(Arrays.equals(field.getField()[0], new int[]{1, 0, 0, 0}));
    }

    @Test
    void addRandom() {
        int count = 0;
        for (int[] ints : field.getField()) {
            for (int i : ints) {
                if (i != 0) count++;
            }
        }
        assertEquals(2, count);
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
    void testToString() {
        field.clear();
        assertEquals("0, 0, 0, 0\n0, 0, 0, 0\n0, 0, 0, 0\n0, 0, 0, 0\n", field.toString());
        field.add(0, 0);
        field.add(1, 1);
        field.add(2, 2);
        field.add(3, 3);
        assertEquals("1, 0, 0, 0\n0, 1, 0, 0\n0, 0, 1, 0\n0, 0, 0, 1\n", field.toString());
    }

    @Test
    void moveAll() {
        field.clear();
        field.add(0, 3);
        field.moveAll(Sides.LEFT);
        assertTrue(Arrays.equals(new int[]{1, 0, 0, 0}, field.getField()[0]));
        field.add(0, 3);
        field.moveAll(Sides.LEFT);
        assertTrue(Arrays.equals(new int[]{2, 0, 0, 0}, field.getField()[0]));
        field.add(0, 3);
        field.moveAll(Sides.LEFT);
        assertTrue(Arrays.equals(new int[]{2, 1, 0, 0}, field.getField()[0]));
        field.add(0,2);
        field.add(0,3);
        field.moveAll(Sides.LEFT);
        assertTrue(Arrays.equals(new int[]{2, 2, 1, 0}, field.getField()[0]));
    }

    @Test
    void move() {
        field.clear();
        field.add(0, 3);
        assertTrue(field.move(0, 3, Sides.LEFT));
        assertTrue(Arrays.equals(new int[]{1,0,0,0}, field.getField()[0]));
        field.add(0, 3);
        assertTrue(field.move(0, 3, Sides.LEFT));
        assertTrue(Arrays.equals(new int[]{2,0,0,0}, field.getField()[0]));
        field.add(0, 2);
        assertTrue(field.move(0, 2, Sides.LEFT));
        assertTrue(Arrays.equals(new int[]{2,1,0,0}, field.getField()[0]));
        assertFalse(field.move(0,1, Sides.LEFT));
    }
}