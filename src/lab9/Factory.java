package lab9;

public class Factory {
    public static Checker getChecker(String type, int index, int[][] board, Collection<Violation> out){
        switch(type){
            case "row": return new RowChecker(board, index, out);
            case "col": return new ColChecker(board, index, out);
            case "box": return new BoxChecker(board, index, out);
            default: throw new IllegalArgumentException("Unknown checker type: " + type);
        }
    }


    public static ValidatorMode getMode(int type){
        switch (type){
            case 1: return new Mode0Validator();
            case 2: return new Mode3Validator();
            case 3: return new Mode27Validator();
            default: throw new IllegalArgumentException("Unknown checker type: " + type);
        }
    }


    public static Checker getGrpChecker(String type, int[][] board, Collection<Violation> out){
        switch(type){
            case "row": return new RowsGrpChecker(board, out);
            case "col": return new ColsGrpChecker(board, out);
            case "box": return new BoxGrpChecker(board, out);
            default: throw new IllegalArgumentException("Unknown checker type: " + type);
        }
    }





}
