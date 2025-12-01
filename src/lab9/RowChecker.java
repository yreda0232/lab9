package lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowChecker implements Checker
{
    private final int[][] board;
    private final int rowIndex;
    private final Collection<Violation> out;

    public RowChecker(int[][] board, int rowIndex, Collection<Violation> out)
    {
        this.board = board;
        this.rowIndex = rowIndex;
        this.out = out;
    }

    @Override
    public void run()
    {
        Map<Integer, List<Integer>> positions = new HashMap<>();

        for(int c=0; c<9; c++)
        {
            int val = board[rowIndex][c];
            positions.computeIfAbsent(val, k-> new ArrayList<>()).add(c+1);
        }


        for(Map.Entry<Integer, List<Integer>> e: positions.entrySet())
        {
            if(e.getValue().size()>1)
                out.add(new Violation(Violation.Type.ROW, rowIndex+1, e.getKey(), e.getValue()));
        }
    }
}
