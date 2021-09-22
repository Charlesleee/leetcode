class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        // 计算链表长度
        ListNode tmp = head;
        int length = 0;
        while (tmp != null) {
            tmp = tmp.next;
            length++;
        }

        int numOfParts = length / k;
        int additionalPart = length % k;
        ListNode[] res = new ListNode[k];
        if (head == null) {
            for (int i = 0; i < k; i++) {
                res[i] = null;
            }
            return res;
        }

        // 如果length < k，则每个元素依次放入数组，并在后面补空
        // first代表前一个分割的头节点，second代表后一个分割的头节点，需在链表尾部加一个尾节点
        if (numOfParts == 0) {
            int i = 0;
            ListNode first = head;
            ListNode second = first.next;
            while (first != null) {
                res[i] = first;
                res[i].next = null;
                i++;
                first = second;
                second = first.next;
            }
            while (i < k) {
                res[i] = null;
                i++;
            }
            return res;
        }

        ListNode first = head;
        int newNumOfParts = numOfParts + 1;
        int counter = 0;
        int i = 0;
        res = new ListNode[k];
        for (int l = 0; l < k; l++) {
            res[l] = first;
            res[l].next = null;
            if (counter < additionalPart) {
                while (i < newNumOfParts) {
                    first = first.next;
                    i++;
                }
                counter++;
            }
            else {
                while (i < numOfParts) {
                    first = first.next;
                    i++;
                }
            }
            i = 0;
        }
        return  res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        new Solution().splitListToParts(head, 5);
        System.out.println("");
    }
}
