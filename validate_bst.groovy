import static TreeNode.tnode;

void valid(final boolean[] ret, final TreeNode node) {
    if(node.left == null && node.right == null) {
        return;
    }
    else if(node.left != null && node.right == null) {
        valid(ret, node.left);
    }
    else if(node.left == null && node.right != null) {
        valid(ret, node.right);
    }

    if(node.left.val > node.val || node.right.val < node.val) {
        ret[0] = false;
    }
    else {
        valid(ret, node.left);
        if(ret[0] == true) {
            valid(ret, node.right);
        }
    }
}

public boolean isValidBST(TreeNode root) {
    final boolean[] ret = new boolean[1];
    ret[0] = true;
    valid(ret, root);
    return ret[0];
}

def tree = tnode(2, tnode(1, null, null), tnode(3, null, null));
def badTree = tnode(1, tnode(2, null, null), tnode(3, null, null));

println(isValidBST(tree));
println(isValidBST(badTree));
