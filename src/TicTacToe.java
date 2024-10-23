import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean xTurn = true;
        printBoard(board);

        while (true) {
            System.out.print("Enter the coordinates: ");
            int row, col;

            try {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (board[row][col] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                board[row][col] = xTurn ? 'X' : 'O';
                printBoard(board);

                if (checkWin(board, xTurn ? 'X' : 'O')) {
                    System.out.println((xTurn ? 'X' : 'O') + " wins");
                    break;
                }

                if (isDraw(board)) {
                    System.out.println("Draw");
                    break;
                }

                xTurn = !xTurn; // Зміна черги ходу
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                scanner.next(); // Очищення вхідного буфера
            }
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    public static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
