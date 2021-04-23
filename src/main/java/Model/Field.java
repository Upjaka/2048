package Model;

import java.util.*;

public class Field {
    private final int size = 4;
    private final int[][] field;
    private final int[][] previous;
    private final Random random;

    public Field() {
        random = new Random();
        field = new int[size][size];
        previous = new int[size][size];
        for (int[] ints : field) {
            Arrays.fill(ints, 0);
        }
        addRandom();
        addRandom();
    }

    public Integer get(int i, int j) {
        return pow(field[j][i]);
    }

    public Integer getPrevious(int i, int j) {
        return pow(previous[j][i]);
    }

    private int pow(int x) {
        if (x == 0) return 0;
        int result = 1;
        for (int i = 0; i < x; i++) {
            result *= 2;
        }
        return result;
    }

    public int[][] getField() {
        return field;
    }

    public void add(int x, int y) {
        add(x, y, 1);
    }

    public void add(int x, int y, int n) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            field[x][y] = n;
        }
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
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (field[x][y] == 0) return false;
                for (int i : getNeighbors(x, y)) {
                    if (i == field[x][y]) return false;
                }
            }
        }
        return true;
    }

    public List<Integer> getNeighbors(int x, int y) {
        List<Integer> result = new ArrayList<>();
        if (y - 1 != -1) result.add(field[x][y - 1]);
        if (x - 1 != -1) result.add(field[x - 1][y]);
        if (x + 1 != size) result.add(field[x + 1][y]);
        if (y + 1 != size) result.add(field[x][y + 1]);
        return result;
    }

    public void clear() {
        for (int[] ints : field) Arrays.fill(ints, 0);
    }

    public void makeMove(Direction dir) {
        for (int i = 0; i < size; i++) {
            previous[i] = Arrays.copyOf(field[i], size);
        }
        if (moveAll(dir)) addRandom();
    }

    public boolean moveAll(Direction dir) {
        boolean modified = false;
        switch (dir) {
            case RIGHT -> {
                for (int i = 0; i < size; i++) {
                    for (int j = size - 2; j >= 0; j--) {
                        if (field[i][j] != 0 && move(i, j, Direction.RIGHT)) modified = true;
                    }
                }
            }
            case LEFT -> {
                for (int i = 0; i < size; i++) {
                    for (int j = 1; j < size; j++) {
                        if (field[i][j] != 0 && move(i, j, Direction.LEFT)) modified = true;
                    }
                }
            }
            case UP -> {
                for (int i = 1; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (field[i][j] != 0 && move(i, j, Direction.UP)) modified = true;
                    }
                }
            }
            case DOWN -> {
                for (int i = size - 2; i >= 0; i--) {
                    for (int j = 0; j < size; j++) {
                        if (field[i][j] != 0 && move(i, j, Direction.DOWN)) modified = true;
                    }
                }
            }
        }
        return modified;
    }

    public boolean move(int x, int y, Direction dir) {
        int[] newXY = new int[]{x, y};
        switch (dir) {
            case DOWN -> {
                for (int i = x + 1; i <= size; i++) {
                    if (i == size) {
                        newXY[0] = size - 1;
                    } else if (field[i][y] != 0) {
                        newXY[0] = field[i][y] == field[x][y] ? i : i - 1;
                        break;
                    }
                }
            }
            case UP -> {
                for (int i = x - 1; i >= -1; i--) {
                    if (i == -1) {
                        newXY[0] = 0;
                    } else if (field[i][y] != 0) {
                        newXY[0] = field[i][y] == field[x][y] ? i : i + 1;
                        break;
                    }
                }
            }
            case RIGHT -> {
                for (int j = y + 1; j <= size; j++) {
                    if (j == size) {
                        newXY[1] = size - 1;
                    } else if (field[x][j] != 0) {
                        newXY[1] = field[x][j] == field[x][y] ? j : j - 1;
                        break;
                    }
                }
            }
            case LEFT -> {
                for (int j = y - 1; j >= -1; j--) {
                    if (j == -1) {
                        newXY[1] = 0;
                    } else if (field[x][j] != 0) {
                        newXY[1] = field[x][j] == field[x][y] ? j : j + 1;
                        break;
                    }
                }
            }
        }
        if (x != newXY[0] || y != newXY[1]) {
            field[newXY[0]][newXY[1]] = field[newXY[0]][newXY[1]] == 0 ? field[x][y] : field[x][y] + 1;
            field[x][y] = 0;
            return true;
        }
        return false;
    }

    public void back() {
        for (int i = 0; i < size; i++) {
            field[i] = Arrays.copyOf(previous[i], size);
        }
    }

    public String getScores() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] != 0 && field[i][j] != 1) sum += Math.pow(2.0, field[i][j]);
            }
        }
        return String.valueOf(sum);
    }

    public int getSize() {
        return size;
    }

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
}