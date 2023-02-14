package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = "Щ--**---------*-**-----*-*---*-*--------------*-----*--*-------*--***--------" +
                "*---------*-------**---";
        String[][] myMatrix = inputMatrix(str, 10);
        System.out.println("Дано поле:");
        printMatrix(myMatrix, 10);
        System.out.println("Необходимо выбрать клетку, где будет стоять человек. Человек не может стоять на месте кактуса.");

        System.out.println("Введите значение координаты Х");
        int x = sc.nextInt();
        System.out.println("Введите значение координаты Y");
        int y = sc.nextInt();
        System.out.println("Итак, Вы выбрали координаты (" + x + ";" + y + ")");

        String[][] path = find_path(myMatrix, x, y);
        printMatrix(path, 10);
        sc.close();
    }

    //заполнение матрицы
    public static String[][] inputMatrix(String str, int n) {
        String[][] matrix = new String[n][n];
        int k = 0;
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                matrix[i][j] = String.valueOf(str.charAt(k));
                k++;
            }
        }
        return matrix;
    }

    //вывод матрицы
    public static void printMatrix(String[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] find_path(String[][] matrix, int x, int y) {
        String str = "????????????????????????????????????????????????????????????????????????????????????????????????????";
        String[][] memory = inputMatrix(str, 10);
        int x0 = x;
        int y0 = y;
        if (x == 0 && y == 0) {
            System.out.println("Человек и собака в одной клетке!");
        }
        while (x != 0 || y != 0) {
            String direction = where_from(matrix, x, y, memory);
            if (direction.equals("N")) {
                System.out.println("Пути нет");
                break;
            } else if (direction.equals("U")) {
                matrix[x][y] = "x";
                y--;
            } else if (direction.equals("L")) {
                matrix[x][y] = "x";
                x--;
            }
        }
        matrix[x0][y0] = "Ч";
        return matrix;
    }

    public static String where_from(String[][] matrix, int x, int y, String[][] memory) {

        if (!memory[x][y].equals("?")) {
            return memory[x][y];
        }
        if (x > 0) {
            int leftX = x - 1;
            int leftY = y;
            if (leftX == 0 && leftY == 0) {
                memory[x][y] = "L";
                return "L";
            }
            if (!matrix[leftX][leftY].equals("*")) {
                if (!where_from(matrix, leftX, leftY, memory).equals("N")) {
                    memory[x][y] = "L";
                    return "L";
                }
            }
        }

        if (y > 0) {
            int upX = x;
            int upY = y - 1;
            if (upX == 0 && upY == 0) {
                memory[x][y] = "U";
                return "U";
            }
            if (!matrix[upX][upY].equals("*")) {
                if (!where_from(matrix, upX, upY, memory).equals("N")) {
                    memory[x][y] = "U";
                    return "U";
                                    }
            }
        }
        return "N";
    }
}
