package game;

public class AI {

    public void stepIi(char[][] charArr) {
        int ROW = charArr.length;
        int COLL = charArr[0].length;
        int[] countTop = new int[4]; // ящейки: 0='X', 1=' ', 2='O', 3='|'

        // ставим 4 'O' на победу  OOO(O)
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' || charArr[i][j] == 'O') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiRow(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiCol(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiDown(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 3 & countTop[1] == 1) {
                        if (stepIiUp(charArr, i, j)) return;
                    }
                }
            }
        }

        // блокируем x(_)xx
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiRowBlock(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiColBlock(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiDownBlock(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 3 & countTop[1] == 1) {
                        if (stepIiUpBlock(charArr, i, j)) return;
                    }
                }
            }
        }

        // ставим 3 'O' в линию O(O)_O
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'O') {

                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        countTop = checkDown(charArr, i + 1, j + 1);
                        if (countTop[2] == 2 & countTop[1] == 2) {
                            if (stepIiDown(charArr, i + 2, j + 2)) return;
                        } else if (stepIiDown(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        countTop = checkUp(charArr, i - 1, j + 1);
                        if (countTop[2] == 2 & countTop[1] == 2) {
                            if (stepIiUp(charArr, i - 2, j + 2)) return;
                        } else if (stepIiUp(charArr, i, j)) return;
                    }


                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        if (countTop[2] == 2 & countTop[1] == 2) {
                            countTop = checkLine(charArr, i, j + 1);
                            if (countTop[2] == 2 & countTop[1] == 2) {
                                if (stepIiRow(charArr, i, j + 2)) return;
                            } else if (stepIiRow(charArr, i, j)) return;
                        }
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 2 & countTop[1] == 2) {
                        if (countTop[2] == 2 & countTop[1] == 2) {
                            countTop = checkColl(charArr, i + 1, j);
                            if (countTop[2] == 2 & countTop[1] == 2) {
                                if (stepIiCol(charArr, i + 2, j)) return;
                            } else if (stepIiCol(charArr, i, j)) return;
                        }
                    }


                }
            }
        }

        // блокируем x(_)_x
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'X') {

                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {

                        // пробуем обратобать xx(o), поставить рядом с ноликом свой нолик
                        countTop = checkLine(charArr, i, j + 1);
                        if (countTop[0] == 2 & countTop[2] == 1) {
                            int[] ijO = oFindRow(charArr, i, j + 1);
                            if (stepIiRow(charArr, ijO[0], ijO[1])) return;
                        }

                        if (stepIiRowBlock(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {
                        if (stepIiColBlock(charArr, i, j)) return;
                    }

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {
                        if (stepIiDownBlock(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 2 & countTop[1] == 2) {
                        if (stepIiUpBlock(charArr, i, j)) return;
                    }
                }
            }
        }

        // ставим 2 'O' в линию O(O)__
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == ' ' | charArr[i][j] == 'O') {
                    countTop = checkDown(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiDown(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiUp(charArr, i, j)) return;
                    }

                    countTop = checkLine(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiRow(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[2] == 1 & countTop[1] == 3) {
                        if (stepIiCol(charArr, i, j)) return;
                    }

                }
            }
        }

        // блокируем x(_)__
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLL; j++) {
                if (charArr[i][j] == 'X') {

                    countTop = checkDown(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiDownBlock(charArr, i, j)) return;
                    }

                    countTop = checkUp(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiUpBlock(charArr, i, j)) return;
                    }
                    countTop = checkLine(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiRowBlock(charArr, i, j)) return;
                    }

                    countTop = checkColl(charArr, i, j);
                    if (countTop[0] == 1 & countTop[1] == 3) {
                        if (stepIiColBlock(charArr, i, j)) return;
                    }


//                    if (((i - 1 > 0) & (j - 1 > 0)) && charArr[i - 1][j - 1] == ' ') {
//                        charArr[i - 1][j - 1] = 'O';
//                        return;
//                    }
                }
            }
        }
    }

    public boolean stepIiRowBlock(char[][] charArr, int i, int j) {
        for (int k = j; k < j + 4; k++) {
            if (charArr[i][k] == 'X') {
                if ((k + 1 < charArr[0].length && charArr[i][k + 1] == ' ')) {
                    charArr[i][k + 1] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[i][k - 1] == ' ')) {
                    charArr[i][k - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiRow(char[][] charArr, int i, int j) {
        for (int k = j; k < j + 4; k++) {
            if (charArr[i][k] == 'O') {
                if ((k + 1 < charArr[0].length && charArr[i][k + 1] == ' ')) {
                    charArr[i][k + 1] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[i][k - 1] == ' ')) {
                    charArr[i][k - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiColBlock(char[][] charArr, int i, int j) {
        for (int k = i; k < i + 4; k++) {
            if (charArr[k][j] == 'X') {
                if (k + 1 < charArr.length && charArr[k + 1][j] == ' ') {
                    charArr[k + 1][j] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[k - 1][j] == ' ')) {
                    charArr[k - 1][j] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiCol(char[][] charArr, int i, int j) {
        for (int k = i; k < i + 4; k++) {
            if (charArr[k][j] == 'O') {
                if (k + 1 < charArr.length && charArr[k + 1][j] == ' ') {
                    charArr[k + 1][j] = 'O';
                    return true;
                } else if ((k - 1 >= 0 && charArr[k - 1][j] == ' ')) {
                    charArr[k - 1][j] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiDownBlock(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i + k][j + k] == 'X') {
                if ((((i + k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i + k) + 1][(j + k) + 1] == ' ') {
                    charArr[(i + k) + 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i + k) - 1) >= 0 & ((j + k) - 1) >= 0) && charArr[(i + k) - 1][(j + k) - 1] == ' ') {
                    charArr[(i + k) - 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiDown(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i + k][j + k] == 'O') {
                if ((((i + k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i + k) + 1][(j + k) + 1] == ' ') {
                    charArr[(i + k) + 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i + k) - 1) >= 0 & ((j + k) - 1) >= 0) && charArr[(i + k) - 1][(j + k) - 1] == ' ') {
                    charArr[(i + k) - 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiUpBlock(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i - k][j + k] == 'X') {
                if ((((i - k) - 1) >= 0 & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i - k) - 1][(j + k) + 1] == ' ') {
                    charArr[(i - k) - 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i - k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length) && charArr[(i - k) + 1][(j + k) - 1] == ' ') {
                    charArr[(i - k) + 1][(j + k) - 1] = 'O';
                    return true;
                }
            }
        }
        return false;
    }

    public boolean stepIiUp(char[][] charArr, int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (charArr[i - k][j + k] == 'O') {
                if ((((i - k) - 1) >= 0 & ((j + k) + 1) < charArr[0].length) &&
                        charArr[(i - k) - 1][(j + k) + 1] == ' ') {
                    charArr[(i - k) - 1][(j + k) + 1] = 'O';
                    return true;
                } else if ((((i - k) + 1) < charArr.length & ((j + k) + 1) < charArr[0].length)
                        && charArr[(i - k) + 1][(j + k) - 1] == ' ') {
                    charArr[(i - k) + 1][(j + k) - 1] = 'O';
                    return true;
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

    public int[] oFindRow(char[][] charArr, int i, int j) {
        int[] ijO = new int[2];
        // проверяем строку на 4 хода вперёд -->
        for (int k = 0; k < 04; k++) {
            if (((j + k) < charArr[0].length) && (charArr[i][j + k] == 'O')) {
                ijO[0] = i;
                ijO[1] = j + k;
            }
        }
        return ijO;
    }

}
