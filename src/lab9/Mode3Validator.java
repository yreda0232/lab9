/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9;

import java.util.Collection;

/**
 *
 * @author youssef
 */
public class Mode3Validator implements ValidatorMode {
    public Mode3Validator() {
    }
    @Override
    public void execute(int[][] board, Collection<Violation> violations){
        Thread Rchecker = new Thread (Factory.getGrpChecker("row", board, violations));
        Thread Cchecker = new Thread (Factory.getGrpChecker("col",board, violations));
        Thread Bchecker = new Thread (Factory.getGrpChecker("box",board, violations));

        Rchecker.start();
        Cchecker.start();
        Bchecker.start();

        try{
            Rchecker.join();
            Cchecker.join();
            Bchecker.join();

        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
