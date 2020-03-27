package magicsquare;

import java.util.Arrays;

/**
 *
 * @author PM051
 */
public class MagicSquare {

    private int[][] matrix;
    private final int size;
    private final int magicConst;

    public MagicSquare(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Неверно задан размер");
        }
        if (n == 2) {
            throw new IllegalArgumentException("Магический квадрат 2х2 невозможно построить");
        }
        matrix = new int[n][n];
        size = n;
        magicConst = n * (n * n + 1) / 2;
    }

    public int getMagicConst() {
        return magicConst;
    }

    public void printMatrix() {
        for (int[] a : matrix) {
            System.out.println(Arrays.toString(a));
        }
    }

    public int[] summaStrok() {
        int[] array = new int[size];
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += matrix[i][j];
            }
            array[i] = sum;
            sum = 0;
        }
        return array;
    }

    public int[] summaColumns() {
        int[] array = new int[size];
        int sum = 0;
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                sum += matrix[i][j];
            }
            array[j] = sum;
            sum = 0;
        }
        return array;
    }

    public int[] summaDiagonalei() {
        int[] array = new int[2];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < size; i++) {
            sum1 += matrix[i][i];
            sum2 += matrix[size - 1 - i][i];
        }
        array[0] = sum1;
        array[1] = sum2;
        return array;
    }

    public void build() {
        if (size % 2 == 1) {
            matrix = odd(size);
        } else if (size % 2 == 0 && size % 4 != 0) {
            even();
        } else {
            evenFour();
        }
    }

    private int[][] odd(int n) {
        int[][] tempMatrix = new int[n][n];
        int x = 0;
        int y = (n - 1) / 2;     //середина верхней строчки
        int maxNumber = n * n;
        for (int i = 1; i <= maxNumber; i++) {
            tempMatrix[x][y] = i;
            int[] newXY = nextStep(tempMatrix, n, x, y);
            x = newXY[0];
            y = newXY[1];
        }
        return tempMatrix;
    }

    private int[] nextStep(int[][] tempMatrix, int n, int x, int y) {
        int newX = x - 1;
        int newY = y + 1;   //вверх и вправо
        if (newX < 0) {
            newX += n;
        }
        if (newY > n - 1) {
            newY = 0;
        }
        if (tempMatrix[newX][newY] != 0) {  //если занято, то вниз от предыдущего числа
            x += 1;
        } else {
            x = newX;
            y = newY;
        }
        return new int[]{x, y};
    }

    private void even() {
        //квадрат делится на 4 четверти, каждая из которых является нечетным квадратом
        int quarterSize = size / 2;
        int[][] tempMatrix = odd(quarterSize);
        int multiplier = quarterSize * quarterSize;

        //заполнение четвертей квадрата
        for (int i = 0; i < quarterSize; i++) {
            for (int j = 0; j < quarterSize; j++) {
                matrix[i][j] = tempMatrix[i][j];
            }
        }
        for (int i = 0; i < quarterSize; i++) {
            for (int j = quarterSize; j < size; j++) {
                matrix[i][j] = tempMatrix[i][j - quarterSize] + 2 * multiplier;
            }
        }
        for (int i = quarterSize; i < size; i++) {
            for (int j = 0; j < quarterSize; j++) {
                matrix[i][j] = tempMatrix[i - quarterSize][j] + 3 * multiplier;
            }
        }
        for (int i = quarterSize; i < size; i++) {
            for (int j = quarterSize; j < size; j++) {
                matrix[i][j] = tempMatrix[i - quarterSize][j - quarterSize] + multiplier;
            }
        }

        //меняем местами некоторые ячейки
        swapElements(matrix, 0, 0, quarterSize, 0);
        swapElements(matrix, quarterSize - 1, 0, size - 1, 0);

        for (int i = 1; i < quarterSize - 1; i++) {
            swapElements(matrix, i, 1, i + quarterSize, 1);
        }

        int numberOfMiddleColumnsToSwap = quarterSize - 3;
        int firstToSwap = quarterSize - numberOfMiddleColumnsToSwap / 2;
        for (int j = 0; j < numberOfMiddleColumnsToSwap; j++) {
            for (int i = 0; i < quarterSize; i++) {
                swapElements(matrix, i, firstToSwap + j, i + quarterSize, firstToSwap + j);
            }
        }
    }

    private void swapElements(int[][] someMatrix, int firstI, int firstJ, int secondI, int secondJ) {
        int temp = someMatrix[firstI][firstJ];
        someMatrix[firstI][firstJ] = someMatrix[secondI][secondJ];
        someMatrix[secondI][secondJ] = temp;
    }

    private void evenFour() {
        //выделяем по углам квадрата и в центре между ними промежуточные квадраты 
        int cornerSquareSize = size / 4;
        int middleSquareSize = 2 * cornerSquareSize;
        int koefSdviga = size - cornerSquareSize;
        int number = 1;

        //заполняем угловые промежуточные квадраты
        for (int i = 0; i < cornerSquareSize; i++) {
            for (int j = 0; j < cornerSquareSize; j++) {
                matrix[i][j] = number;
                number++;
            }
            number += koefSdviga;
        }
        for (int i = 0; i < cornerSquareSize; i++) {
            for (int j = koefSdviga; j < size; j++) {
                matrix[i][j] = matrix[i][j - koefSdviga] + koefSdviga;
            }
        }
        for (int i = koefSdviga; i < size; i++) {
            for (int j = 0; j < cornerSquareSize; j++) {
                matrix[i][j] = matrix[i - koefSdviga][j] + size * koefSdviga;
            }
        }
        for (int i = koefSdviga; i < size; i++) {
            for (int j = koefSdviga; j < size; j++) {
                matrix[i][j] = matrix[i - koefSdviga][j] + size * koefSdviga;
            }
        }

        //заполняем центральный промежуточный квадрат
        number = matrix[cornerSquareSize - 1][size - 1] + middleSquareSize - (cornerSquareSize - 1);
        for (int i = cornerSquareSize; i < cornerSquareSize + middleSquareSize; i++) {
            for (int j = cornerSquareSize; j < cornerSquareSize + middleSquareSize; j++) {
                matrix[i][j] = number;
                number++;
            }
            number += middleSquareSize;
        }

        //заполняем оставшиеся ячейки
        int maxNumber = size * size;
        number = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = maxNumber - number;
                }
                number++;
            }
        }
    }
}
