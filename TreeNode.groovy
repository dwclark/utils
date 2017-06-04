public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static TreeNode tnode(final int val, final TreeNode left, final TreeNode right) {
        def ret = new TreeNode(val);
        ret.left = left;
        ret.right = right;
        return ret;
    }

    void inOrderWalk(TreeNode node, Closure closure) {
        if(node.left != null) {
            inOrderWalk(node.left, closure);
        }

        closure(node);

        if(node.right != null) {
            inOrderWalk(node.right, closure);
        }
    }

    String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        Closure closure = { n -> sb.append("${n.val} "); };
        inOrderWalk(this, closure);
        return sb.toString();
    }
}
