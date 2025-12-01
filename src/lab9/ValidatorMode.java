/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9;

/**
 *
 * @author cs
 */
import java.util.Collection;

public interface ValidatorMode {
    void execute(int[][] board, Collection<Violation> violations);
}

