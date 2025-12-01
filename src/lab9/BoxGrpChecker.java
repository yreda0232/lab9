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
public class BoxGrpChecker implements Checker{
    private final int[][] board;
    private final Collection<Violation> vio;
    public BoxGrpChecker(int[][] board, Collection<Violation> vio) {
        this.board = board;
        this.vio = vio;
    }

    @Override
    public void run(){
        for(int i=0;i<9;i++){
            Factory.getChecker("box",i,board,vio).run();
        }
    }
}
