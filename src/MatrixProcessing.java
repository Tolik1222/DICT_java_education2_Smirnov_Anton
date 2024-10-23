import java.util.Locale;
import java.util.Scanner;

public class MatrixProcessing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);  // Використовуємо англійську локаль для дробових чисел

        while (true) {
            // Print menu
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                // Exit the program
                break;
            }

            switch (choice) {
                case 1:
                    // Add matrices
                    addMatrices(scanner);
                    break;
                case 2:
                    // Multiply matrix by a constant
                    multiplyMatrixByConstant(scanner);
                    break;
                case 3:
                    // Multiply matrices
                    multiplyMatrices(scanner);
                    break;
                case 4:
                    // Transpose matrix
                    transposeMatrix(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void multiplyMatrixByConstant(Scanner scanner) {
        // Read the matrix
        System.out.print("Enter size of matrix: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        double[][] matrix = new double[rows][cols];
        System.out.println("Enter matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        // Read the constant
        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();

        // Multiply the matrix by the constant
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] *= constant;
            }
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(matrix);
    }

    private static void addMatrices(Scanner scanner) {
        // Read the first matrix
        System.out.print("Enter size of first matrix: ");
        int rowsA = scanner.nextInt();
        int colsA = scanner.nextInt();
        double[][] matrixA = new double[rowsA][colsA];
        System.out.println("Enter first matrix:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                matrixA[i][j] = scanner.nextDouble();
            }
        }

        // Read the second matrix
        System.out.print("Enter size of second matrix: ");
        int rowsB = scanner.nextInt();
        int colsB = scanner.nextInt();
        if (rowsA != rowsB || colsA != colsB) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        double[][] matrixB = new double[rowsB][colsB];
        System.out.println("Enter second matrix:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                matrixB[i][j] = scanner.nextDouble();
            }
        }

        // Add matrices
        double[][] result = new double[rowsA][colsA];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(result);
    }

    private static void multiplyMatrices(Scanner scanner) {
        // Read the first matrix
        System.out.print("Enter size of first matrix: ");
        int rowsA = scanner.nextInt();
        int colsA = scanner.nextInt();
        double[][] matrixA = new double[rowsA][colsA];
        System.out.println("Enter first matrix:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                matrixA[i][j] = scanner.nextDouble();
            }
        }

        // Read the second matrix
        System.out.print("Enter size of second matrix: ");
        int rowsB = scanner.nextInt();
        int colsB = scanner.nextInt();
        if (colsA != rowsB) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        double[][] matrixB = new double[rowsB][colsB];
        System.out.println("Enter second matrix:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                matrixB[i][j] = scanner.nextDouble();
            }
        }

        // Multiply matrices
        double[][] result = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        // Print the result
        System.out.println("The result is:");
        printMatrix(result);
    }

    private static void transposeMatrix(Scanner scanner) {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();

        // Read the matrix
        System.out.print("Enter matrix size: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        double[][] matrix = new double[rows][cols];
        System.out.println("Enter matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

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

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
