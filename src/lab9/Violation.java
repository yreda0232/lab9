/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9;

/**
 *
 * @author cs
 */
import java.util.List;

public class Violation {
    enum Type { ROW , COL ,BOX}
    Type type;
    int index;
    int value;
    List<Integer> positions;
    public Violation(Type type, int index, int value,  List<Integer> positions)
    {
        this.type = type;
        this.index = index;
        this.value = value;
        this.positions = positions;
    }

    @Override
    public String toString()
    {
        String prefix = type == Type.ROW? "ROW" : (type == Type.BOX? "BOX":"COL");
        return String.format("%s %d, #%d, %s",prefix,index,value,positions.toString());
    }

}

