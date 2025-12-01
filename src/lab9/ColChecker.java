package lab9;

public class ColChecker implements  Checker
{
    private final int[][] board;
    private final int colIndex;
    private final Collection<Violation> out;


    public ColChecker(int[][] board, int colIndex, Collection<Violation> out) {
        this.board = board;
        this.colIndex = colIndex;
        this.out = out;
    }

    @Override
    public void run()
    {
        Map<Integer, List<Integer>> positions = new HashMap<>();

        for(int r=0; r<9; r++)
        {
            int val = board[r][colIndex];
            positions.computeIfAbsent(val, k-> new ArrayList<>()).add(r+1);
        }

        for(Map.Entry<Integer, List<Integer>> entry: positions.entrySet())
        {
            if(entry.getValue().size()>1)
                out.add(new Violation(Violation.Type.COL, colIndex+1, entry.getKey(), entry.getValue()));
        }
    }
}
