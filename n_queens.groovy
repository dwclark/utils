class Board {
    public final int size;
    public final List<char[]> chars;
    
    public Board(final int size) {
        this.size = size;
        this.chars = new ArrayList<>(size);
        
        for(int i = 0; i < size; ++i) {
            char[] ary = new char[size];
            for(int j = 0; j < size; ++j) {
                ary[j] = '.';
            }

            chars.add(ary);
        }
    }

    public Board(final Board toCopy) {
        this.size = toCopy.size;
        this.chars = new ArrayList<>(size);
        for(char[] ary : toCopy.chars) {
            chars.add(Arrays.copyOf(ary, ary.length));
        }
    }

    List<String> toList() {
        List<String> ret = new ArrayList<>(size);
        for(char[] ary : chars) {
            ret.add(new String(ary));
        }

        return ret;
    }

    public boolean canPlace(final int row, final int col) {
        //check same column
        for(char[] ary : chars) {
            if(ary[col] == 'Q') {
                return false;
            }
        }

        //check diagonal up-right
        int upRow = row - 1;
        int upCol = col + 1;
        while(upRow >= 0 && upCol < size) {
            if(chars.get(upRow)[upCol] == 'Q') {
                return false;
            }

            --upRow;
            ++upCol;
        }

        //check diagonal up-left
        upRow = row - 1;
        upCol = col - 1;
        while(upRow >= 0 && upCol >= 0) {
            if(chars.get(upRow)[upCol] == 'Q') {
                return false;
            }

            --upRow;
            --upCol;
        }

        return true;
    }

    public Board place(final int row, final int col) {
        Board ret = new Board(this);
        ret.chars.get(row)[col] = 'Q';
        return ret;
    }
}

void solve(List<List<String>> solutions, Board board, int row) {
    for(int col = 0; col < board.size; ++col) {
        if(board.canPlace(row, col)) {
            Board newBoard = board.place(row, col);
            if(row + 1 == board.size) {
                solutions.add(newBoard.toList());
            }
            else {
                solve(solutions, newBoard, row + 1);
            }
        }
    }
}

List<List<String>> solveNQueens(int n) {
    List<List<String>> solutions = new ArrayList<>();
    solve(solutions, new Board(n), 0);
    return solutions;
}

List<List<String>> solutions = solveNQueens(8);
solutions.eachWithIndex { List<String> bList, int index ->
    println("Board #${index+1}");
    bList.each { String s -> println(s); };
    println();
}
