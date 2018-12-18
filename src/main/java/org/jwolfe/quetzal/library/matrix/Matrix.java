package org.jwolfe.quetzal.library.matrix;

public class Matrix {
    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        if(matrix1 == null || matrix1.length == 0 || matrix1[0] == null || matrix1[0].length == 0
            || matrix2 == null || matrix2.length == 0 || matrix2[0] == null || matrix2[0].length == 0) {
            return null;
        }

        int m1Rows = matrix1.length;
        int m2Rows = matrix2.length;

        int m1Columns = matrix1[0].length;
        int m2Columns = matrix2[0].length;

        if(m1Columns != m2Rows) {
            // Cannot mutiply
            return null;
        }

        // Ensure matrix integrity
        for (int i = 0; i < m1Rows; i++) {
            if(matrix1[i] == null || matrix1[i].length != m1Columns) {
                return null;
            }
        }
        for (int i = 0; i < m2Rows; i++) {
            if(matrix2[i] == null || matrix2[i].length != m2Columns) {
                return null;
            }
        }

        int[][] product = new int[m1Rows][m2Columns];
        for (int i = 0; i < m1Rows; i++) {
            for (int j = 0; j < m2Columns; j++) {
                product[i][j] = 0;
                for (int k = 0; k < m1Columns; k++) {
                    product[i][j] += (matrix1[i][k] * matrix2[k][j]);
                }
            }
        }

        return product;
    }

    public static int getTrace(int[][] matrix) {
        if(matrix == null) {
            return 0;
        }

        // Not defined for non-square matrixes
        int length = matrix.length;
        int trace = 0;
        for (int i = 0; i < length; i++) {
            int[] row = matrix[i];
            if(row == null
                || row.length != length) {
                return 0;
            }

            trace += matrix[i][i];
        }

        return trace;
    }

    public static int[][] getTranspose(int[][] matrix) {
        if(matrix == null
            || matrix.length == 0) {
            return null;
        }

        int length = matrix.length;
        int[][] transpose = new int[length][length];
        for (int i = 0; i < length; i++) {
            if(length != matrix[i].length) {
                return null;
            }

            for (int j = 0; j < length; j++) {
                transpose[i][j] = matrix[j][i];
            }
        }

        return transpose;
    }

    public static boolean isIdentity(int[][] matrix) {
        if (matrix == null
                || matrix.length == 0) {
            return false;
        }

        int length = matrix.length;

        for (int i = 0; i < length; i++) {
            if (length != matrix[i].length) {
                return false;
            }

            for (int j = 0; j < length; j++) {
                if (i == j) {
                    if (matrix[i][j] != 1) {
                        return false;
                    }
                } else {
                    if (matrix[i][j] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
