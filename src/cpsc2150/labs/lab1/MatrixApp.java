package cpsc2150.labs.lab1;

import java.util.Scanner;

/**
 * Created by Lukas Herman (lukash) on 1/22/2018.
 */
public class MatrixApp {

    /**
     * This is a helper function which will print out
     * the given matrix.
     *
     * Example:
     * <pre>
     * {@code
     *  int[][] matrix = new int[2][2];
     *  matrix[0][0] = 0;
     *  matrix[0][1] = 1;
     *  matrix[1][0] = 2;
     *  matrix[1][1] = 3;
     * }
     * >>> |0|1|
     * >>> |2|3|
     * </pre>
     *
     * @param matrix is a 2D array of integers
     * @return the human-readable matrix string
     */
    private static String printMatrix(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : matrix) {
            for (int col : row)
                stringBuilder.append("|" + col);
            stringBuilder.append("|\n");
        }
        return stringBuilder.toString();
    }

    /**
    * This is a helper function which will print out
    * the given vector.
    *
    * Example:
    * <pre>
    * {@code
    *  int[] matrix = new int[2];
    *  matrix[0] = 0;
    *  matrix[1] = 1;
    * }
    * >>> |0|1|
    * </pre>
    *
    * @param vector is a 1D array of integers
    * @return the human-readable vector string
    */
    private static String printVector(int[] vector) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int e : vector)
            stringBuilder.append("|" + e);
        stringBuilder.append("|\n");
        return stringBuilder.toString();
    }

    /**
     * This function returns the transpose of the given
     * matrix.
     *
     * @param matrix is a 2D array of integers
     * @return a new transposed matrix of the given matrix
     */
    private static int[][] transpose(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] transposedMatrix = new int[cols][rows];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                transposedMatrix[col][row] = matrix[row][col];
        return transposedMatrix;
    }

    /**
     * This function returns the product of the sums of each row.
     *
     * @param matrix is a 2D array of integers
     * @return the product of the sums of each row
     */
    private static int productSums(int[][] matrix) {
        int product = 1;
        for (int[] row : matrix) {
            int sum = 0;
            for (int col : row)
                sum += col;
            product *= sum;
        }
        return product;
    }

    /**
     * This function returns a single floating number of the average
     * of all the elements in the matrix.
     *
     * @param matrix is a 2D array of integers
     * @return the average of all the elements in the matrix
     */
    private static double average(int[][] matrix) {
        double avg = 0;
        for (int[] row : matrix)
            for (int col : row)
                avg += col;

        return avg / (matrix.length * matrix[0].length);
    }

    /**
     * This function returns an array of integers with each element in
     * the array is equal to the sum of all the numbers in each column in
     * the matrix.
     *
     * @param matrix is a 2D array of integers
     * @return sums of each column in the matrix
     */
    private static int[] rowSums(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[] sums = new int[rows];

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                sums[row] += matrix[row][col];

        return sums;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many rows should your matrix have?");
        int rows = scanner.nextInt();

        while (rows <= 0 || rows > 10) {
            System.out.println("Error: Please enter a number 1-10");
            System.out.println("How many rows should your matrix have?");
            rows = scanner.nextInt();
        }

        System.out.println("How many columns should your matrix have?");
        int cols = scanner.nextInt();

        while (cols <= 0 || cols > 10) {
            System.out.println("Error: Please enter a number 1-10");
            System.out.println("How many columns should your matrix have?");
            cols = scanner.nextInt();
        }

        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++) {
                System.out.println("What number should go in Row: " + row + " Col: " + col);
                matrix[row][col] = scanner.nextInt();
            }

        scanner.close();

        System.out.println("Your matrix is:");
        System.out.println(printMatrix(matrix));

        System.out.println("Your transposed matrix is:");
        System.out.println(printMatrix(transpose(matrix)));

        System.out.println("The product sum is:");
        System.out.println(productSums(matrix));

        System.out.println("The average is:");
        System.out.println(average(matrix));

        System.out.println("The sums of each Row are:");
        System.out.print(printVector(rowSums(matrix)));

        System.out.println("The sums of each Column are:");
        System.out.print(printVector(rowSums(transpose(matrix))));
    }
}
