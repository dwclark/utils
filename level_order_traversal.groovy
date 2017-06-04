import static TreeNode.tnode;

class Level {
    int level;
    TreeNode node;

    Level(final int level, final TreeNode node) {
        this.level = level;
        this.node = node;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<Level> queue = new LinkedList<>();
    List<List<Integer>> ret = new ArrayList<>();
    queue.offer(new Level(0, root));
    while(!queue.isEmpty()) {
        Level level = queue.poll();
        List<Integer> levelList;
        if(ret.size() - 1 == level.level) {
            levelList = ret.get(level.level);
        }
        else {
            levelList = new ArrayList<>();
            ret.add(levelList);
        }
        
        levelList.add(level.node.val);
        if(level.node.left != null) {
            queue.offer(new Level(level.level + 1, level.node.left));
        }

        if(level.node.right != null) {
            queue.offer(new Level(level.level + 1, level.node.right));
        }
        
        ret.set(level.level, levelList);
    }

    return ret;
}

def tree = tnode(3,
                 tnode(9, null, null),
                 tnode(20,
                       tnode(15, null, null),
                       tnode(7, null, null)));

def larger = tnode(10,
                   tnode(5,
                         tnode(1, null, null),
                         tnode(13, null, null)),
                   tnode(15,
                         tnode(7, null, null),
                         tnode(17, null, null)));


levelOrder(tree).each { List<Integer> list ->
    println(list);
};

levelOrder(larger).each { List<Integer> list ->
    println(list);
};
