class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        for (int i = 0; i < matrix.length; i++) {
            reverse(matrix, i, 0, matrix[0].length - 1);
        }
    }

    private static void reverse(int[][] matrix, int i, int s, int end) {
        while (s < end) {
            swap(matrix, i, s, end);
            s++;
            end--;
        }
    }

    private static void swap(int[][] matrix, int i, int s, int end) {
        int temp = matrix[i][s];
        matrix[i][s] = matrix[i][end];
        matrix[i][end] = temp;
    }

    private static void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                swap(matrix, i, j);
            }
        }
    }

    private static void swap(int[][] mat, int i, int j) {
        int temp = mat[i][j];
        mat[i][j] = mat[j][i];
        mat[j][i] = temp;
    }
}