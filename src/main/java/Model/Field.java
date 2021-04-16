package Model;

import View.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Field {
    private final int size = 4;
    private final int[][] field;
    private final Random random = new Random(size);

    public Field() {
        field = new int[size][size];
        for (int[] ints : field) {
            Arrays.fill(ints, 0);
        }
        addRandom();
        addRandom();
    }

    public int[][] getField() {
        return field;
    }

    public void add(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            field[x][y] = 1;
        }
    }

    public void add(int x, int y, int n) {
        field[x][y] = n;
    }

    public void addRandom() {
        if (isFull()) return;
        int x;
        int y;
        do {
            x = random.nextInt(size - 1);
            y = random.nextInt(size - 1);
        } while (field[x][y] != 0);
        field[x][y] = 1;
    }

    public boolean isFull() {
        for (int[] line : field) {
            for (int number : line) {
                if (number == 0) return false;
            }
        }
        return true;
    }

    public boolean isLose() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                for (int i : getNeighbors(x, y).values()) {
                    if (i == field[x][y]) return false;
                }
            }
        }
        return true;
    }

    private Map<Sides, Integer> getNeighbors(int x, int y) {
        Map<Sides, Integer> result = new HashMap<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i - 1 != -1) result.put(Sides.UP, field[i - 1][j]);
                if (j - 1 != -1) result.put(Sides.LEFT, field[i][j - 1]);
                if (i + 1 != size) result.put(Sides.DOWN, field[i + 1][j]);
                if (j + 1 != size) result.put(Sides.RIGHT, field[i][j + 1]);
            }
        }
        return result;
    }

    public void clear() {
        for (int[] ints : field) Arrays.fill(ints, 0);
    }

    public void moveAll(Sides side) {
        switch (side) {
            case RIGHT -> {
                for (int i = 0; i < size; i++) {
                    for (int j = size - 2; j >= 0; j--) {
                        if (field[i][j] != 0) {
                            final Pair next = nextElem(i, j, Sides.RIGHT);
                            final Pair pair = new Pair(i, j);
                            if (!pair.equals(next)) {
                                field[next.x][next.y] = field[next.x][next.y] == 0 ? field[i][j] : field[i][j] + 1;
                                field[i][j] = 0;
                            }
                        }
                    }
                }
            }
            case LEFT -> {
                for (int i = 0; i < size; i++) {
                    for (int j = 1; j < size; j++) {
                        if (field[i][j] != 0) {
                            final Pair next = nextElem(i, j, Sides.LEFT);
                            final Pair pair = new Pair(i, j);
                            if (!pair.equals(next)) {
                                field[next.x][next.y] = field[next.x][next.y] == 0 ? field[i][j] : field[i][j] + 1;
                                field[i][j] = 0;
                            }
                        }
                    }
                }
            }
            case UP -> {
                for (int i = 1; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (field[i][j] != 0) {
                            final Pair next = nextElem(i, j, Sides.UP);
                            final Pair pair = new Pair(i, j);
                            if (!pair.equals(next)) {
                                field[next.x][next.y] = field[next.x][next.y] == 0 ? field[i][j] : field[i][j] + 1;
                                field[i][j] = 0;
                            }
                        }
                    }
                }
            }
            case DOWN -> {
                for (int i = size - 2; i >= 0; i--) {
                    for (int j = 0; j < size; j++) {
                        if (field[i][j] != 0) {
                            final Pair next = nextElem(i, j, Sides.DOWN);
                            final Pair pair = new Pair(i, j);
                            if (!pair.equals(next)) {
                                field[next.x][next.y] = field[next.x][next.y] == 0 ? field[i][j] : field[i][j] + 1;
                                field[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
        addRandom();
        addRandom();
    }

    public Pair nextElem(int x, int y, Sides side) {
        switch (side) {
            case DOWN -> {
                for (int i = x + 1; i < size; i++) {
                    if (field[i][y] != 0) {
                        return field[i][y] == field[x][y] ? new Pair(i, y) : new Pair(i - 1, y);
                    }
                }
                return new Pair(size - 1, y);
            }
            case UP -> {
                for (int i = x - 1; i >= 0; i--) {
                    if (field[i][y] != 0) {
                        return field[i][y] == field[x][y] ? new Pair(i, y) : new Pair(i + 1, y);
                    }
                }
                return new Pair(0, y);
            }
            case RIGHT -> {
                for (int j = y + 1; j < size; j++) {
                    if (field[x][j] != 0) {
                        return field[x][j] == field[x][y] ? new Pair(x, j) : new Pair(x, j - 1);
                    }
                }
                return new Pair(x, size - 1);
            }
            case LEFT -> {
                for (int j = y - 1; j >= 0; j--) {
                    if (field[x][j] != 0) {
                        return field[x][j] == field[x][y] ? new Pair(x, j) : new Pair(x, j + 1);
                    }
                }
                return new Pair(x, 0);
            }
            default -> {
                return null;
            }
        }
    }

    public String getScores() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] != 0) sum += Math.pow(2.0, field[i][j]);
            }
        }
        return String.valueOf(sum);
    }

    public int getSize() {return size;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : field) {
            for (int j = 0; j < size - 1; j++) {
                sb.append(ints[j]).append(", ");
            }
            sb.append(ints[size - 1]);
            sb.append('\n');
        }
        return sb.toString();
    }

    public String get(int i, int j) {
        return field[i][j] == 0 ? "" : String.valueOf((Math.round(Math.pow(2.0, field[i][j]))));
    }
}