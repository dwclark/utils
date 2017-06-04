
public List<Integer> makeList(final List<List<Integer>> all, final int row) {
    List<Integer> list = new ArrayList<>(row);
    for(int i = 0; i < row; ++i) {
        list.add(0);
    }

    all.add(list);
    return list;
}

public List<Integer> triangle(List<List<Integer>> all, int row) {
    if(row == 1) {
        List<Integer> list = makeList(all, 1);
        list.set(0, 1);
        return list;
    }

    List<Integer> prev = triangle(all, row - 1);
    List<Integer> list = makeList(all, row);
    list.set(0, 1);
    list.set(list.size() - 1, 1);
    for(int i = 1; i < list.size() - 1; ++i) {
        list.set(i, prev.get(i-1) + prev.get(i));
    }

    return list;
}

public List<List<Integer>> generate(int numRows) {
    if(numRows < 1) {
        return new ArrayList<>();
    }
    
    List<List<Integer>> ret = new ArrayList<>(numRows);
    triangle(ret, numRows);
    return ret;
}

generate(8).each { List<Integer> list ->
    println(list);
}
