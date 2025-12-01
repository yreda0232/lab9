package lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoxChecker implements Checker{
    private final int[][] board;
    private final int boxIndex;
    private final Collection<Violation> out;


    public BoxChecker(int[][] board, int boxIndex, Collection<Violation> out) {
        this.board = board;
        this.boxIndex = boxIndex;
        this.out = out;
    }

    @Override
    public void run()
    {
        int boxRow=(boxIndex/3)*3;
        int boxColumn=(boxIndex%3)*3;

        Map<Integer, List<Integer>> positions = new HashMap<>();
        int pos=0;

        for(int r = boxRow; r<boxRow+3; r++)
            for(int c = boxColumn; c<boxColumn+3; c++)
            {
                pos++;
                int val = board[r][c];
                positions.computeIfAbsent(val, k-> new ArrayList<>()).add(pos);
            }

        for(Map.Entry<Integer, List<Integer>> entry: positions.entrySet())
        {
            if(entry.getValue().size()>1)
                out.add(new Violation(Violation.Type.BOX, boxIndex+1, entry.getKey(), entry.getValue()));
        }
    }
}
