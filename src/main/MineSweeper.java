package main;

import java.util.Scanner;
import utils.GameUtils;

public class MineSweeper {

    int fieldRow;
    int fieldColumn;
    int row;
    int column;
    int chances;
    boolean gameStatus = false;
    String[][] field;
    String[][] fieldWithMines;
    String[][] temp;

    public void startGame() {
        System.out.print("Welcome to minesweeper game"
                + "\nPlease input row size: ");
        Scanner sc = new Scanner(System.in);
        this.fieldRow = sc.nextInt();

        System.out.print("Please input column size: ");
        this.fieldColumn = sc.nextInt();

        System.out.println("\nNumber of mines: " + (this.fieldRow * this.fieldColumn) / 4);
        chances = (fieldRow * fieldColumn) - ((fieldRow * fieldColumn) / 4);

        field = GameUtils.assignField(fieldRow, fieldColumn);
        fieldWithMines = GameUtils.assignMinesAndNumbers(field);
        temp = GameUtils.assignTempArray(fieldWithMines);

        GameUtils.printField(fieldWithMines);
        GameUtils.printField(field);

        while (gameStatus == false && chances > 0) {
            System.out.print("Input row: ");
            this.row = sc.nextInt();
            System.out.print("Input column: ");
            this.column = sc.nextInt();
            
            if ((this.row < fieldWithMines.length + 1 && this.column < fieldWithMines[0].length + 1) || this.row == 0 || this.column == 0) {
                if (!"-".equals(field[row - 1][column - 1])) {
                    System.out.println("\n---\n** You already opened row " + row + ", column " + column + "!. Pick another..\n---\n");
                } else if (gameStatus == false) {
                    gameStatus = GameUtils.checkMines(field, fieldWithMines, temp, row - 1, column - 1);
                    GameUtils.printField(field);
                    chances--;
                    if (chances == 0) {
                        System.out.println("\nWINNER WINNER CHICKEN DINNER!!!");
//                        break;
                    }
                }
                
                if (gameStatus == true) {
                    System.out.println("\nYou exploded the BOMB !!. Next time be careful");
                }
            } else {
                System.out.println("\n---\n** Wrong input!\n** Try again..\n---\n");
            }
        }
        System.out.println("Game over!");

    }

}
