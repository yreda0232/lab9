/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author youssef
 */
public class SudokuIo {
    public static int[][] readCsv(String path) throws IOException {
        int[][] board = new int[9][9];
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        int r = 0;
        while ((line = br.readLine()) != null && r < 9) {
            String[] parts = line.trim().split("\\s*,\\s*");
            if (parts.length < 9) {
                br.close();
                throw new IOException("Each row must have 9 values (found" + parts.length + "on row" + (r + 1) + ")");
            }
            for (int c = 0; c < 9; c++) {
                try {
                    board[r][c] = Integer.parseInt(parts[c]);
                } catch (NumberFormatException nfe) {
                    br.close();
                    throw new IOException("Invalid number at row" + (r + 1) + ",column" + (c + 1));
                }
            }
            r++;
        }
        br.close();
        if (r < 9) throw new IOException("File must have 9 rows (found" + r + ")");
        return board;
    }

    public static void printBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(board[r][c]);
                if (c < 8) System.out.print(" ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
    String csvPath = null;
    int mode = 0;

    if (args.length == 2) {
        csvPath = args[0];
        try {
            mode = Integer.parseInt(args[1]);
            if (mode < 1 || mode > 3) {
                System.out.println("Mode must be 1 (Mode0), 2 (Mode3), or 3 (Mode27)");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Mode must be an integer: 1, 2, or 3");
            return;
        }
    } else {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter CSV file path: ");
        csvPath = "C:\\Users\\ADMIN\\Documents\\GitHub\\lab9\\valid_sudoku.csv";
        System.out.print("Enter mode (1=Mode0, 2=Mode3, 3=Mode27): ");
        try {
            mode = Integer.parseInt(sc.nextLine());
            if (mode < 1 || mode > 3) {
                System.out.println("Invalid mode");
                sc.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Mode must be an integer");
            sc.close();
            return;
        }
        sc.close();
    }

    int[][] board;
    try {
        board = SudokuIo.readCsv(csvPath);
    } catch (IOException e) {
        System.out.println("Failed to read CSV: " + e.getMessage());
        return;
    }

    Collection<Violation> violations = new java.util.concurrent.ConcurrentLinkedQueue<>();
    ValidatorMode validator = Factory.getMode(mode);
    validator.execute(board, violations);

    if (violations.isEmpty()) {
        System.out.println("VALID SUDOKU");
        return;
    }

    System.out.println("INVALID\n");
    List<Violation> list = new java.util.ArrayList<>(violations);
    list.sort(
            java.util.Comparator.comparing((Violation v) -> v.type.toString())
                    .thenComparingInt(v -> v.index)
                    .thenComparingInt(v -> v.value)
    );

    for (Violation v : list) if (v.type == Violation.Type.ROW) System.out.println(v);
    System.out.println("------------------------------------------");
    for (Violation v : list) if (v.type == Violation.Type.COL) System.out.println(v);
    System.out.println("------------------------------------------");
    for (Violation v : list) if (v.type == Violation.Type.BOX) System.out.println(v);
}
    
}
