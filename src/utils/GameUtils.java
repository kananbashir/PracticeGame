package utils;

import java.util.Random;

public class GameUtils {

    public static String[][] assignField(int row, int column) {
        String[][] field = new String[row][column];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = "-";
            }
        }
        return field;
    }

    public static String[][] assignMinesAndNumbers(String field[][]) {
        int indexColumn = 0;
        int indexRow = 0;
        int mineCount = (field.length * field[0].length) / 4;
        String[][] fieldWithMines;
        fieldWithMines = assignField(field.length, field[0].length);
        Random rand = new Random();

        for (int a = 0; a < mineCount;) {
            indexColumn = rand.nextInt(field[0].length);
            if ("-".equals(fieldWithMines[indexRow][indexColumn])) {
                fieldWithMines[indexRow][indexColumn] = "*";
                indexRow++;
                a++;
                if (indexRow == fieldWithMines.length) {
                    indexRow = 0;
                }
            }
        }
        return fieldWithMines;
    }

    public static String[][] assignTempArray(String fieldWithMines[][]) {
        String[][] temp = new String[fieldWithMines.length + 2][fieldWithMines[0].length + 2];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (i == 0 || j == 0 || i == temp.length - 1 || j == temp[0].length - 1) {
                    temp[i][j] = "-";
                } else {
                    temp[i][j] = fieldWithMines[i - 1][j - 1];
                }
            }
        }
        return temp;
    }

    public static boolean checkMines(String field[][], String fieldWithMines[][], String tempArray[][], int row, int column) {
        boolean isBomb = false;
        int count = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if ("*".equals(tempArray[i][j])) {
                    count++;
                }
            }
        }
        if (!"*".equals(fieldWithMines[row][column])) {
            isBomb = false;
            fieldWithMines[row][column] = String.valueOf(count);
            field[row][column] = String.valueOf(count);
        } else {
            field[row][column] = "*";
            isBomb = true;
        }

        return isBomb;
    }

    public static void printField(String[][] field) {
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===================");
    }
}
