import static TreeNode.tnode;

enum Rooted { LEFT, RIGHT };
enum Child { LEFT, RIGHT };

boolean outOfPlace(final Stack<TreeNode> stack, final TreeNode node, Rooted rooted, Child child) {
    Iterator<TreeNode> iter = stack.iterator();
    TreeNode parent = iter.next();
    
    if(child == Child.LEFT && node.val > parent.val) {
       return true;
    }
    else if(child == Child.RIGHT && node.val < parent.val) {
        return true;
    }

    while(iter.hasNext()) {
        TreeNode ancestor = iter.next();
        if(rooted == Rooted.LEFT && node.val > ancestor.val) {
            return true;
        }
        else if(rooted == Rooted.RIGHT && node.val < ancestor.val) {
            return true;
        }
    }

    return false;
}

void gatherInfo(final Stack<TreeNode> stack, final List<TreeNode> wrong, final TreeNode root,
                final Rooted rooted, final Child child) {

    if(outOfPlace(stack, root, rooted, child)) {
        wrong.add(stack.peek());
        wrong.add(root);
    }

    stack.push(root);
    
    if(root.left != null) {
        gatherInfo(stack, wrong, root.left, rooted, Child.LEFT);
    }
    
    if(root.right != null) {
        gatherInfo(stack, wrong, root.right, rooted, Child.RIGHT);
    }

    stack.pop();
}

void swapNodes(final TreeNode one, final TreeNode two) {
    int tmp = two.val;
    two.val = one.val;
    one.val = tmp;
}

void recoverTree(final TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<TreeNode> wrong = new ArrayList<>();

    stack.push(root);
    if(root.left != null) {
        gatherInfo(stack, wrong, root.left, Rooted.LEFT, Child.LEFT);
    }

    if(root.right != null) {
        gatherInfo(stack, wrong, root.right, Rooted.RIGHT, Child.RIGHT);
    }

    if(wrong.size() == 2) {
        swapNodes(wrong.get(0), wrong.get(1));
    }
    else {
        swapNodes(wrong.get(1), wrong.get(3));
    }
}

def small = tnode(1, null, tnode(0, null, null));

recoverTree(small);
println(small.toStringInOrder());

def three = tnode(1, tnode(2, null, null), tnode(3, null, null));
recoverTree(three);
println(three.toStringInOrder());

def tree = tnode(10,
                 tnode(5,
                       tnode(1, null, null),
                       tnode(13, null, null)),
                 tnode(15,
                       tnode(7, null, null),
                       tnode(17, null, null)));

recoverTree(tree);
println(tree.toStringInOrder());
