package com.maf.ia;

import com.maf.DTO.MessageDTO;
import java.util.Arrays;
import java.util.List;

public class Reader {

    public String[][] makeMatrix(MessageDTO message) {
        String myself = message.getData().getSide().toString();

        String enemy = "S";
        if (myself.equals("S")) {
            enemy = "N";
        }
        String boardString = message.getData().getBoard();
        List<String> boardList = Arrays.asList(boardString);
        String[][] boardMatrix = new String[17][17];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                int position = (i * 17) + j;
                String cell = boardString.substring(position, (position + 1));
                boardMatrix[i][j] = convertCell(cell, i, j, myself, enemy);
            }
        }

        if (myself.equals("N")) {
            boardMatrix = turnMatrix(boardMatrix);
        }
        boardMatrix = renamePawns(boardMatrix);
        return boardMatrix;
    }

    public String convertCell(String cell, int i, int j, String myself, String enemy) {
        if (cell.equals("*") || cell.equals("-") || cell.equals("|")) {
            cell = "X";
        }
        if (cell.equals(" ")) {
            if ((i % 2) == 1 || (j % 2) == 1) {
                cell = "|";
            }
        }
        if (cell.equals(" ")) {
            if ((i % 2) == 0 && (j % 2) == 0) {
                cell = " ";
            }
        }
        if (cell.equals(myself)) {
            cell = "M";
        }
        if (cell.equals(enemy)) {
            cell = "E";
        }
        return cell;
    }

    public String[][] turnMatrix(String[][] board) {
        String[][] turnedBoard = new String[17][17];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                turnedBoard[i][j] = board[16 - i][16 - j];
            }
        }
        return turnedBoard;
    }

    public void showMatrix(String[][] matrix) {
        System.out.println("   00112233445566778 ");
        System.out.println("  XXXXXXXXXXXXXXXXXXX");
        for (int i = 0; i < 17; i++) {
            int r = i;
            if (i % 2 == 1) {
                r = i - 1;
            }
            r = r / 2;
            System.out.print((r % 10) + " X");
            for (int j = 0; j < 17; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("X");
            System.out.println("");
        }
        System.out.println("  XXXXXXXXXXXXXXXXXXX");
    }

    public void showIntMatrix(Integer[][] matrix) {
        System.out.println("   00112233445566778 ");
        System.out.println("  XXXXXXXXXXXXXXXXXXX");
        for (int i = 0; i < 17; i++) {
            int r = i;
            if (i % 2 == 1) {
                r = i - 1;
            }
            r = r / 2;
            System.out.print((r % 10) + " X");
            for (int j = 0; j < 17; j++) {
                if (matrix[i][j] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.print("X");
            System.out.println("");
        }
        System.out.println("  XXXXXXXXXXXXXXXXXXX");
    }

    public String[][] renamePawns(String[][] matrix) {
        int countMyself = 0;
        int countEnemy = 0;
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (matrix[i][j].equals("M")) {
                    switch (countMyself) {
                        case 0:
                            matrix[i][j] = "A";
                            break;
                        case 1:
                            matrix[i][j] = "B";
                            break;
                        case 2:
                            matrix[i][j] = "C";
                            break;
                    }
                    countMyself++;
                }
                if (matrix[i][j].equals("E")) {
                    switch (countEnemy) {
                        case 0:
                            matrix[i][j] = "D";
                            break;
                        case 1:
                            matrix[i][j] = "E";
                            break;
                        case 2:
                            matrix[i][j] = "F";
                            break;
                    }
                    countEnemy++;
                }
            }
        }
        return matrix;
    }

}
