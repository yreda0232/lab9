package lab9;

public class Mode0Validator implements ValidatorMode {

    public Mode0Validator() {}

    @Override
    public void execute(int[][] board, Collection<Violation> violations) {
        for(int r=0; r<9; r++)
        {
            Factory.getChecker("row", r, board, violations).run();
        }
        for(int c=0; c<9; c++)
        {
            Factory.getChecker("col", c, board, violations).run();
        }
        for(int b=0; b<9; b++)
        {
            Factory.getChecker("box", b, board, violations).run();
        }
    }
}
