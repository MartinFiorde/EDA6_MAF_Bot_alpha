package com.maf.ia;

public class Finder {

    private Reader reader = new Reader();
    private int fromX = 0;
    private int fromY = 0;
    private int toX = 0;
    private int toY = 0;

    public String findBestMove(String[][] matrix) {
        String actualPawn = "A";
        Integer actualMoves = 0;
        Integer minMoves = 100;

        while (!actualPawn.equals("end")) {
            System.out.println("ActualPawn: " + actualPawn);
            actualMoves = countMovesToTheEnemySide(matrix, actualPawn, minMoves);
            if (actualMoves < minMoves) {
                minMoves = actualMoves;
            }
            actualPawn = nextPawn(actualPawn);
        }

        return String.valueOf(fromX) + String.valueOf(fromY) + String.valueOf(toX) + String.valueOf(toY);
    }

    public int countMovesToTheEnemySide(String[][] matrix, String pawn, int minMoves) {
        int breakBucle = 0;
        int totalMoves = 0;
        String actualPosition = pawn;
        boolean reachedEnd = false;
        while (reachedEnd == false) {

            totalMoves++;

            for (int i = 0; i < 17; i = i + 2) {
                for (int j = 0; j < 17; j = j + 2) {
                    breakBucle++;
                    if (matrix[i][j].equals(actualPosition)) {
                        matrix = tryGoFront(matrix, i, j, String.valueOf(totalMoves));
                        matrix = tryGoLeft(matrix, i, j, String.valueOf(totalMoves));
                        matrix = tryGoRight(matrix, i, j, String.valueOf(totalMoves));
                        matrix = tryGoBack(matrix, i, j, String.valueOf(totalMoves));
                    }
                }
            }

            if (breakBucle == 10000) {
                break;
            }
            
            for (int i = 0; i < 17; i = i + 2) {
                if (!matrix[0][i].equals("D") && !matrix[0][i].equals("E") && !matrix[0][i].equals("F") && !matrix[0][i].equals(" ")) {
//                    reader.showMatrix(matrix);
                    reachedEnd = true;

                    if (totalMoves == minMoves && Math.random()>0.5) {
                        setBestMove(matrix, totalMoves, 0, i);

                        for (int k = 0; k < 17; k = k + 2) {
                            for (int m = 0; m < 17; m = m + 2) {
                                if (matrix[k][m].equals(pawn)) {

                                    fromX = k / 2;
                                    fromY = m / 2;
                                    System.out.println("fromX: " + fromX);
                                    System.out.println("fromY: " + fromY);
                                    System.out.println("toX: " + toX);
                                    System.out.println("toY: " + toY);
                                }
                            }
                        }
                    }
                    
                    if (totalMoves < minMoves) {
                        setBestMove(matrix, totalMoves, 0, i);

                        for (int k = 0; k < 17; k = k + 2) {
                            for (int m = 0; m < 17; m = m + 2) {
                                if (matrix[k][m].equals(pawn)) {

                                    fromX = k / 2;
                                    fromY = m / 2;
                                    System.out.println("fromX: " + fromX);
                                    System.out.println("fromY: " + fromY);
                                    System.out.println("toX: " + toX);
                                    System.out.println("toY: " + toY);
                                }
                            }
                        }
                    }
                    
                    break;
                }
            }

            actualPosition = String.valueOf(totalMoves);

        }
        return totalMoves;
    }

    public void setBestMove(String[][] matrix, int ActualPlace, int x, int y) {
        Integer[][] intMatrix = new Integer[17][17];
        intMatrix[x][y] = ActualPlace;
        int newx = x;
        int newy = y;

        mainProccess:
        while (ActualPlace > 1) {
            ActualPlace--;

            newx = x;
            newy = y - 2;
            if (newx >= 0 && newx <= 16 && newy >= 0 && newy <= 16) {
                if (matrix[newx][newy].equals(String.valueOf(ActualPlace))) {
                    intMatrix[newx][newy] = ActualPlace;
                    x = newx;
                    y = newy;
                    continue mainProccess;
                }
            }

            newx = x;
            newy = y + 2;
            if (newx >= 0 && newx <= 16 && newy >= 0 && newy <= 16) {
                if (matrix[newx][newy].equals(String.valueOf(ActualPlace))) {
                    intMatrix[newx][newy] = ActualPlace;
                    x = newx;
                    y = newy;
                    continue mainProccess;
                }
            }

            newx = x - 2;
            newy = y;
            if (newx >= 0 && newx <= 16 && newy >= 0 && newy <= 16) {
                if (matrix[newx][newy].equals(String.valueOf(ActualPlace))) {
                    intMatrix[newx][newy] = ActualPlace;
                    x = newx;
                    y = newy;
                    continue mainProccess;
                }
            }

            newx = x + 2;
            newy = y;
            if (newx >= 0 && newx <= 16 && newy >= 0 && newy <= 16) {
                if (matrix[newx][newy].equals(String.valueOf(ActualPlace))) {
                    intMatrix[newx][newy] = ActualPlace;
                    x = newx;
                    y = newy;
                    continue mainProccess;
                }
            }

        }
        toX = newx / 2;
        toY = newy / 2;
//        reader.showIntMatrix(intMatrix);
    }

    public String[][] tryGoFront(String[][] matrix, int x, int y, String newValue) {
        int newX = x;
        int newY = y - 2;
        int wallX = x;
        int wallY = y - 1;
        int wallPassX = x;
        int wallPassY = y - 3;
        if ((newX) >= 0 && (newX) <= 16) {
            if ((newY) >= 0 && (newY) <= 16) {
                if (matrix[wallX][wallY].equals("|")) {

                    if (matrix[newX][newY].equals(" ")) {
                        matrix[newX][newY] = newValue;
                        return matrix;
                    }
                    if (!matrix[x][y].equals("D") && !matrix[x][y].equals("E") && !matrix[x][y].equals("F")) {
                        if (matrix[newX][newY].equals("D") || matrix[newX][newY].equals("E") || matrix[newX][newY].equals("F")) {
                            if ((wallPassX) >= 0 && (wallPassX) <= 16 && (wallPassY) >= 0 && (wallPassY) <= 16) {

                                if (matrix[wallPassX][wallPassY].equals("|")) {
                                    matrix = tryGoFront(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                                if (matrix[wallPassX][wallPassY].equals("X")) {
                                    matrix = tryGoLeft(matrix, newX, newY, newValue);
                                    matrix = tryGoRight(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                            }
                        }
                    }
                }
            }
        }

        return matrix;
    }

    public String[][] tryGoBack(String[][] matrix, int x, int y, String newValue) {
        int newX = x;
        int newY = y + 2;
        int wallX = x;
        int wallY = y + 1;
        int wallPassX = x;
        int wallPassY = y + 3;
        if ((newX) >= 0 && (newX) <= 16) {
            if ((newY) >= 0 && (newY) <= 16) {
                if (matrix[wallX][wallY].equals("|")) {

                    if (matrix[newX][newY].equals(" ")) {
                        matrix[newX][newY] = newValue;
                        return matrix;
                    }
                    if (!matrix[x][y].equals("D") && !matrix[x][y].equals("E") && !matrix[x][y].equals("F")) {
                        if (matrix[newX][newY].equals("D") || matrix[newX][newY].equals("E") || matrix[newX][newY].equals("F")) {
                            if ((wallPassX) >= 0 && (wallPassX) <= 16 && (wallPassY) >= 0 && (wallPassY) <= 16) {

                                if (matrix[wallPassX][wallPassY].equals("|")) {
                                    matrix = tryGoBack(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                                if (matrix[wallPassX][wallPassY].equals("X")) {
                                    matrix = tryGoLeft(matrix, newX, newY, newValue);
                                    matrix = tryGoRight(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                            }
                        }
                    }
                }
            }
        }

        return matrix;
    }

    public String[][] tryGoLeft(String[][] matrix, int x, int y, String newValue) {
        int newX = x - 2;
        int newY = y;
        int wallX = x - 1;
        int wallY = y;
        int wallPassX = x - 3;
        int wallPassY = y;
        if ((newX) >= 0 && (newX) <= 16) {
            if ((newY) >= 0 && (newY) <= 16) {
                if (matrix[wallX][wallY].equals("|")) {

                    if (matrix[newX][newY].equals(" ")) {
                        matrix[newX][newY] = newValue;
                        return matrix;
                    }
                    if (!matrix[x][y].equals("D") && !matrix[x][y].equals("E") && !matrix[x][y].equals("F")) {
                        if (matrix[newX][newY].equals("D") || matrix[newX][newY].equals("E") || matrix[newX][newY].equals("F")) {
                            if ((wallPassX) >= 0 && (wallPassX) <= 16 && (wallPassY) >= 0 && (wallPassY) <= 16) {

                                if (matrix[wallPassX][wallPassY].equals("|")) {
                                    matrix = tryGoLeft(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                                if (matrix[wallPassX][wallPassY].equals("X")) {
                                    matrix = tryGoFront(matrix, newX, newY, newValue);
                                    matrix = tryGoBack(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                            }
                        }
                    }
                }
            }
        }

        return matrix;
    }

    public String[][] tryGoRight(String[][] matrix, int x, int y, String newValue) {
        int newX = x + 2;
        int newY = y;
        int wallX = x + 1;
        int wallY = y;
        int wallPassX = x + 3;
        int wallPassY = y;
        if ((newX) >= 0 && (newX) <= 16) {
            if ((newY) >= 0 && (newY) <= 16) {
                if (matrix[wallX][wallY].equals("|")) {

                    if (matrix[newX][newY].equals(" ")) {
                        matrix[newX][newY] = newValue;
                        return matrix;
                    }
                    if (!matrix[x][y].equals("D") && !matrix[x][y].equals("E") && !matrix[x][y].equals("F")) {
                        if (matrix[newX][newY].equals("D") || matrix[newX][newY].equals("E") || matrix[newX][newY].equals("F")) {
                            if ((wallPassX) >= 0 && (wallPassX) <= 16 && (wallPassY) >= 0 && (wallPassY) <= 16) {

                                if (matrix[wallPassX][wallPassY].equals("|")) {
                                    matrix = tryGoRight(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                                if (matrix[wallPassX][wallPassY].equals("X")) {
                                    matrix = tryGoFront(matrix, newX, newY, newValue);
                                    matrix = tryGoBack(matrix, newX, newY, newValue);
                                    return matrix;
                                }
                            }
                        }
                    }
                }
            }
        }

        return matrix;
    }

    // QUITAR COMENTARIO PARA MOVER B Y C TMB
    public String nextPawn(String pawn) {
//        if (pawn.equals("A")) {
//            pawn = "B";
//            return pawn;
//        }
//        if (pawn.equals("B")) {
//            pawn = "C";
//            return pawn;
//        }
        return "end";
    }
}
