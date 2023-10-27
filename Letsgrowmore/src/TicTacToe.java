import java.util.Scanner;

    public class TicTacToe {

        private static final char[][] board = new char[3][3];
        private static char currentPlayer = 'X';

        public static void main(String[] args) {
            initializeBoard();
            boolean gameOver = false;

            System.out.println("Welcome to Tic-Tac-Toe!");

            while (!gameOver) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter row and column (0, 1, or 2):");
                int row = getUserInput("Row: ");
                int col = getUserInput("Column: ");

                if (isValidMove(row, col)) {
                    makeMove(row, col);

                    if (checkForWin()) {
                        printBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameOver = true;
                    } else if (isBoardFull()) {
                        printBoard();
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        switchPlayer();
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }
        }

        private static void initializeBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }
        }

        private static void printBoard() {
            System.out.println("-------------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println("\n-------------");
            }
        }

        private static int getUserInput(String message) {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            return scanner.nextInt();
        }

        private static boolean isValidMove(int row, int col) {
            return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
        }

        private static void makeMove(int row, int col) {
            board[row][col] = currentPlayer;
        }

        private static boolean checkForWin() {
            return checkRows() || checkColumns() || checkDiagonals();
        }

        private static boolean checkRows() {
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                    return true;
                }
            }
            return false;
        }

        private static boolean checkColumns() {
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                    return true;
                }
            }
            return false;
        }

        private static boolean checkDiagonals() {
            return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                    (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
        }

        private static boolean isBoardFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        }

        private static void switchPlayer() {
            if (currentPlayer == 'X') {
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }
        }
    }
