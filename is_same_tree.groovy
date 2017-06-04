import static TreeNode.tnode;

public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p.val != q.val) {
        return false;
    }

    if(p.left == null && q.right == null) {
        return true;
    }
    else if(p.left == null && q.right != null) {
        return false;
    }
    else if(p.left != null && q.right == null) {
        return false;
    }

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}

def tree1 = tnode(10,
                  tnode(5,
                        tnode(1, null, null),
                        tnode(13, null, null)),
                  tnode(15,
                        tnode(7, null, null),
                        tnode(17, null, null)));

def tree2 = tnode(10,
                  tnode(5,
                        tnode(1, null, null),
                        tnode(13, null, null)),
                  tnode(15,
                        tnode(7, null, null),
                        tnode(17, null, null)));

def tree3 = tnode(10,
                  tnode(5,
                        tnode(1, null, null),
                        tnode(7, null, null)),
                  tnode(15,
                        tnode(13, null, null),
                        tnode(17, null, null)));

assert(isSameTree(tree1, tree2));
assert(!isSameTree(tree1, tree3));
