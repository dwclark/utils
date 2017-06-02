public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode next = this;
        while(next != null) {
            sb.append(next.val).append(" ");
            next = next.next;
        }

        return sb.toString().trim()
    }
}


public class Solution {

    static ListNode lnode(int val, ListNode next) {
        ListNode ret = new ListNode(val);
        ret.next = next;
        return ret;
    }

    private ListNode[] copy(final ListNode[] lists) {
        ListNode[] working = new ListNode[lists.length];
        for(int i = 0; i < working.length; ++i) {
            working[i] = lists[i];
        }

        return working;
    }

    private int findLeast(final ListNode[] lists) {
        int index = -1;
        for(int i = 0; i < lists.length; ++i) {
            if(lists[i] != null) {
                if(index == -1) {
                    index = i;
                }
                else if(lists[i].val < lists[index].val) {
                    index = i
                }
            }
        }

        return index;
    }
    
    private ListNode reverse(ListNode list) {
        ListNode ret = null;
        ListNode next = list;
        while(next != null) {
            ret = lnode(next.val, ret);
            next = next.next;
        }

        return ret
    }
    
    public ListNode mergeKLists(final ListNode[] lists) {
        ListNode[] working = copy(lists);
        ListNode current = null;
        int least = findLeast(working);
        while(least != -1) {
            ListNode next = new ListNode(working[least].val);
            next.next = current;
            current = next;
            working[least] = working[least].next;
            least = findLeast(working);
        }

        return reverse(current);
    }
}

def lnode = Solution.&lnode;
ListNode[] lists = new ListNode[2];
lists[0] = lnode(1, lnode(2, lnode(3, null)));
lists[1] = lnode(4, lnode(5, lnode(6, null)));

println(new Solution().mergeKLists(lists));
