package game;



import java.util.Random;


public class Game {

    private static final Random random = new Random();
    //    private int fieldSizeX;  // по вертикали
//    private int fieldSizeY;  // по горизонтали
//    private int winCount;
    private boolean isGameOver;  // Игра окончена
    private boolean isInitialized; // Поле инициализировано
    private char[][] charArr = new char[7][7];
    private int row = charArr.length;
    private int col = charArr[0].length;

    public int getCell(int y, int x) {
        return charArr[y][x];
    }

    public void initMap() {
        charArr = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                charArr[i][j] = ' ';
            }
        }
        isInitialized = true;
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public boolean isEmptyCell(int x, int y) {
        return charArr[x][y] == ' ';
    }

    public void move(int x, int y) {
        charArr[x][y] = 'X';
    }

    public boolean isMapFull() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (charArr[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public boolean checkWin() {
        //  проверяем победу 'X'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int[] countTop = new int[4]; // ящейки: 0='X', 1=' ', 2='O', 3='|'
                if (charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 4) return true;

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 4) return true;

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 4) return true;

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 4) return true;
                }
            }
        }
        //  проверяем победу 'O'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int[] countTop = new int[4]; // ящейки: 0='X', 1=' ', 2='O', 3='|'
                if (charArr[i][j] == 'O') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 4) return true;

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 4) return true;

                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 4) return true;

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 4) return true;
                }
            }
        }
        return false;
    }

    public int[] checkLine(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем строку на 4 хода вперёд -->
        for (int k = j; k < j + 4; k++) {
            if ((k < charArr[0].length) && (charArr[i][k] == 'X')) {
                count[0]++;
            } else if ((k < charArr[0].length) && (charArr[i][k] == ' ')) {
                count[1]++;
            } else if (k >= charArr[0].length) {
                count[3]++;
            } else if ((k < charArr[0].length) && (charArr[i][k] == 'O')) {
                count[2]++;
            }
        }
        return count;
    }

    public int[] checkColl(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = i; k < i + 4; k++) {
            if ((k < charArr.length) && (charArr[k][j] == 'X')) {
                count[0]++;
            } else if ((k < charArr.length) && (charArr[k][j] == ' ')) {
                count[1]++;
            } else if (k >= charArr.length) {
                count[3]++;
            } else if ((k < charArr.length) && (charArr[k][j] == 'O')) {
                count[2]++;
            }
        }

        return count;
    }

    public int[] checkDown(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = 0; k < 4; k++) {
            if (((i + k) < charArr.length & (j + k) < charArr[0].length) && (charArr[i + k][j + k] == 'X')) {
                count[0]++;
            } else if (((i + k) < charArr.length & (j + k) < charArr[0].length) && (charArr[i + k][j + k] == ' ')) {
                count[1]++;
            } else if ((i + k) == charArr.length && (j + k) == charArr[0].length) {
                count[3]++;
            } else if (((i + k) < charArr.length & (j + k) < charArr[0].length) && (charArr[i + k][j + k] == 'O')) {
                count[2]++;
            }
        }
        return count;
    }

    public int[] checkUp(char[][] charArr, int i, int j) {
        int[] count = new int[4];
        // проверяем столбцы на 4 хода вперёд -->
        for (int k = 0; k < 4; k++) {
            if (((i - k) >= 0 & (j + k) < charArr[0].length) && (charArr[i - k][j + k] == 'X')) {
                count[0]++;
            } else if (((i - k) >= 0 & (j + k) < charArr[0].length) && (charArr[i - k][j + k] == ' ')) {
                count[1]++;
            } else if ((i - k) < 0 && (j + k) == charArr[0].length) {
                count[3]++;
            } else if (((i - k) >= 0 & (j + k) < charArr[0].length) && (charArr[i - k][j + k] == 'O')) {
                count[2]++;
            }
        }
        return count;
    }


    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public char[][] getCharArr() {
        return charArr;
    }

    public void setCharArr(char[][] charArr) {
        this.charArr = charArr;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
