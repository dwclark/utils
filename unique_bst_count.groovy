class Tree {
    int val;
    Tree left;
    Tree right;

    static int total = 0;
    
    Tree(int val) {
        this.val = val;
    }
    
    boolean canPlaceLeft(int toTry) {
        if(toTry > val) {
            return false;
            
    }

    boolean canPlaceRight(int toTry) {
        return val < toTry;
    }

    static List<Integer> remove(int val, final List<Integer> remaining) {
        List<Integer> list = new ArrayList(remaining.size() + 1);
        for(Integer i : remaining) {
            if(val != i) {
                list.add(i);
            }
        }

        return list;
    }

    void placeNext(List<Integer> remaining) {
        if(remaining.size() == 0) {
            ++total;
            return;
        }
        
        for(Integer i : remaining) {
            if(canPlaceLeft(i)) {
                left = new Tree(val);
                left.placeNext(remove(i, remaining));
            }
            else if(canPlaceRight(i)) {
                right = new Tree(val);
                right.placeNext(remove(i, remaining))
            }
        }
    }
}

public int numTrees(int n) {
    Tree.total = 0;
    
    List<Integer> remaining = new ArrayList<>(n);
    for(int i = 1; i <= n; ++i) {
        remaining.add(i);
    }

    for(Integer val : remaining) {
        Tree t = new Tree(val);
        List<Integer> nextList = Tree.remove(val, remaining);
        t.placeNext(nextList);
    }

    return Tree.total;
}

println("numTrees(1): ${numTrees(1)}");
println("numTrees(3): ${numTrees(3)}");
