public class Node {
    int val;
    Node next;
    Node(int x) { val = x; }

    static Node node(final int val, final Node next) {
        Node n = new Node(val);
        n.next = next;
        return n;
    }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        Node next = this;
        while(next != null) {
            sb.append(next.val).append(" ");
            next = next.next;
        }

        return sb.toString().trim()
    }
}

public class Solution {

    static node = Node.&node;

    private static Node reverse(Node list) {
        Node ret = null;
        Node next = list;
        while(next != null) {
            ret = node(next.val, ret);
            next = next.next;
        }

        return ret
    }

    public static Node addTwoNumbers(final Node l1, final Node l2) {
        boolean hasCarry = false;
        Node first = l1;
        Node second = l2;
        Node ret = null;
        while(first != null || second != null) {
            int sum = ((first != null) ? first.val : 0) + ((second != null) ? second.val : 0) + (hasCarry ? 1 : 0);
            int digit = (sum > 9) ? sum - 10 : sum;
            hasCarry = (sum > 9) ? true : false;
            first = (first != null) ? first.next : null;
            second = (second != null) ? second.next : null;
            ret = node(digit, ret);
        }

        if(hasCarry) {
            ret = node(1, ret);
        }

        return reverse(ret);
    }
}

def node = Node.&node;
def first = node(2, node(4, node(3, null)));
def second = node(5, node(6, node(4, null)));
println(Solution.addTwoNumbers(first, second));
