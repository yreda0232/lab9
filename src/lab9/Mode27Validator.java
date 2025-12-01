/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9;

/**
 *
 * @author cs
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Mode27Validator implements ValidatorMode {
    public Mode27Validator() {
    }
    @Override
    public void execute(int[][] board, Collection<Violation> vio) {
        List<Thread> threads = new ArrayList<>();

        for (int r = 0; r < 9; r++) {
            threads.add(new Thread(Factory.getChecker("row",r,board, vio)));
        }
        for (int c = 0; c < 9; c++) {
            threads.add(new Thread(Factory.getChecker("col",c,board, vio)));
        }
        for (int b = 0; b < 9; b++) {
            threads.add(new Thread(Factory.getChecker("box", b,board, vio)));
        }
        for (Thread t : threads) {
            t.start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

