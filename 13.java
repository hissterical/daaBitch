import java.util.Scanner;

public class NQueens {
    private int n;
    private int[] queens;
    private int solutionCount;
    
    public NQueens(int n) {
        this.n = n;
        this.queens = new int[n];
        this.solutionCount = 0;
    }
    
    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            // Check column and diagonals
            if (queens[i] == col || 
                Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }
    
    private void solveAll(int row) {
        if (row == n) {
            solutionCount++;
            printSolution();
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                queens[row] = col;
                solveAll(row + 1);
            }
        }
    }
    
    private void printSolution() {
        System.out.println("Solution " + solutionCount + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Queen " + (i + 1) + ": (" + i + "," + queens[i] + ")");
        }
        System.out.println();
    }
    
    public void solvePuzzle() {
        long startTime = System.nanoTime();
        
        solveAll(0);
        
        long endTime = System.nanoTime();
        
        if (solutionCount > 0) {
            System.out.println("Total solutions found: " + solutionCount);
        } else {
            System.out.println("No solution exists for " + n + " queens");
        }
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of queens (N): ");
        int n = sc.nextInt();
        
        if (n <= 0) {
            System.out.println("Please enter a positive number");
            return;
        }
        
        NQueens nq = new NQueens(n);
        nq.solvePuzzle();
        
        sc.close();
    }
}