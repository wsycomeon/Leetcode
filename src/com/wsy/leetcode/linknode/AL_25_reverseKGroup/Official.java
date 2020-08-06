package com.wsy.leetcode.linknode.AL_25_reverseKGroup;

public class Official {


    /**
     * 每 K个一组 反转链表
     * <p>
     * 方法1：
     * <p>
     * 哨兵占位，作为 初始的 pre --》下一个k组的pre
     * 同时，还要有一个 end 标识，一组的end。
     * end.next 就是 下一组的开始；保存起来，
     * 然后 指针置空，方便 反转
     * 注意，最后 如果 end 为null了，说明到头了，且 不到k个，不需要后面的反转操作；
     * 返回 哨兵.next 即可
     * <p>
     * 注意：
     * 反转的一组，先要断开 前后链接，在反转。
     * 最后 在拼接上！
     * <p>
     * 已反转 pre/end  + 待反转 + next 未反转(最后个数不够，可能 不需要 反转了)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 每一组的pre和end
        ListNode pre = dummy;
        ListNode end = dummy;

        // 遍历到 结尾
        while (pre.next != null) {

            // 看下后面的 还有没有 k个元素
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            // todo 前面的循环，可能因为 不到 k个，但是 end = null 到末尾了，提前跳出，整个链表 结束了，最后这一组 就不需要 反转操作了 ！
            if (end == null) {
                break;
            }

            // TODO: 2020/7/22  ------------- 其实之前的操作，就是 和 每两个元素 反转一样，都要先判断 后面的 是否需要 反转（可能 不够 k个！）

            // end 不为 null，表示 这 k 个元素，还需要反转，在此之前，要先保存下 前后节点

            // 下一组的头
            ListNode next = end.next;

            // TODO: 2020/7/22 注意！这是 指针 要 先断开的，不然 没办法 反转！
            end.next = null;

            // 反转，头结点 拼接
            ListNode start = pre.next;

            // TODO: 2020/7/24 反转链表，只需要 head 就可以了， 注意 --》尾部 要 及时断开！
            pre.next = reverse(start);

            // 尾节点 拼接
            start.next = next;

            // 进行 下一组 尝试
            end = pre = start;
        }

        return dummy.next;
    }

    /**
     * 反转 链表 --》 看 206 题
     * 这里，不需要知道 这个链表 有多长，前面 已经处理好了（通过 for循环 k次，节点 不为 null）
     */
    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(-1);

        while (head != null) {
            // 把新结点 从原链表删除，插入dummy的后面
            ListNode next = head.next;

            head.next = dummy.next;
            dummy.next = head;

            head = next;
        }

        return dummy.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

}