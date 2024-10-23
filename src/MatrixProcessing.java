import java.util.Locale;
import java.util.Scanner;

public class MatrixProcessing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        boolean running = true;
        while (running) {
            // Print menu
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    addMatrices(scanner);
                    break;
                case 2:
                    multiplyMatrixByConstant(scanner);
                    break;
                case 3:
                    multiplyMatrices(scanner);
                    break;
                case 4:
                    transposeMatrix(scanner);
                    break;
                case 5:
                    calculateDeterminant(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Program terminated.");
    }

    // Universal method to read a matrix
    private static double[][] readMatrix(Scanner scanner, String prompt) {
        System.out.print(prompt);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        double[][] matrix = new double[rows][cols];
        System.out.println("Enter matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    private static void multiplyMatrixByConstant(Scanner scanner) {
        double[][] matrix = readMatrix(scanner, "Enter size of matrix: ");
        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();

        // Multiply the matrix by the constant
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= constant;
            }
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(matrix);
    }

    private static void addMatrices(Scanner scanner) {
        double[][] matrixA = readMatrix(scanner, "Enter size of first matrix: ");
        double[][] matrixB = readMatrix(scanner, "Enter size of second matrix: ");

        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        // Add matrices
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(result);
    }

    private static void multiplyMatrices(Scanner scanner) {
        double[][] matrixA = readMatrix(scanner, "Enter size of first matrix: ");
        double[][] matrixB = readMatrix(scanner, "Enter size of second matrix: ");

        if (matrixA[0].length != matrixB.length) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        double[][] result = new double[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(result);
    }

    private static void transposeMatrix(Scanner scanner) {
        double[][] matrix = readMatrix(scanner, "Enter matrix size: ");
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();

        double[][] result;
        switch (choice) {
            case 1:
                result = transposeMainDiagonal(matrix);
                break;
            case 2:
                result = transposeSideDiagonal(matrix);
                break;
            case 3:
                result = transposeVertical(matrix);
                break;
            case 4:
                result = transposeHorizontal(matrix);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(result);
    }

    private static double[][] transposeMainDiagonal(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    private static double[][] transposeSideDiagonal(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[cols - j - 1][rows - i - 1] = matrix[i][j];
            }
        }
        return transposed;
    }

    private static double[][] transposeVertical(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[i][cols - j - 1] = matrix[i][j];
            }
        }
        return transposed;
    }

    private static double[][] transposeHorizontal(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[rows - i - 1][j] = matrix[i][j];
            }
        }
        return transposed;
    }

    private static void calculateDeterminant(Scanner scanner) {
        double[][] matrix = readMatrix(scanner, "Enter matrix size: ");
        if (matrix.length != matrix[0].length) {
            System.out.println("Determinant can only be calculated for square matrices.");
            return;
        }

        double determinant = calculateDeterminant(matrix);
        System.out.println("The result is: " + determinant);
    }

    private static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0.0;
        for (int i = 0; i < n; i++) {
            double[][] subMatrix = new double[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k < i) {
                        subMatrix[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        subMatrix[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(subMatrix);
        }

        return determinant;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
