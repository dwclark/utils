import static TreeNode.tnode;

boolean allLessThan(def node, int val) {
    if(node == null) {
        return true;
    }
    
    return node.val < val && allLessThan(node.left, val) && allLessThan(node.right, val);
}

boolean allGreaterThan(def node, int val) {
    if(node == null) {
        return true;
    }
    
    return node.val > val && allGreaterThan(node.left, val) && allGreaterThan(node.right, val);
}

boolean isValidBST(def t) {
    if(t == null) {
        return true;
    }
    
    return (allLessThan(t.left, t.val) &&
            allGreaterThan(t.right, t.val) &&
            isValidBST(t.left) &&
            isValidBST(t.right));
}

def tree = tnode(2, tnode(1, null, null), tnode(3, null, null));
def badTree = tnode(1, tnode(2, null, null), tnode(3, null, null));
def tricky = tnode(2, tnode(1, null, tnode(4, null, null)), tnode(3, null, null));

println("tree: ${tree.valid}");
println("badTree: ${badTree.valid}");
println("tricky: ${tricky.valid}");
