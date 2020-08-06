package com.wsy.leetcode.linknode.AL_328_oddEvenList;

public class Official {


    /**
     * 传入了 一个引用 或者说 指针
     * 你是 修改不了 它的指向的
     * 你只能 利用 它的指向 ！！！
     * 或者说 使用 它指向的链表！
     */
    void reconnect(LinkedNode head) {
        // TODO: 2020/7/13 边界条件，em 后面 好像 不需要 判空，天生支持。。
//        if (head == null) {
//            return;
//        }


        // 偶数链表
        LinkedNode head1 = new LinkedNode(0);
        // 奇数链表
        LinkedNode head2 = new LinkedNode(0);

        // 偶数链表的 当前指针
        LinkedNode current1 = head1;
        // 奇数 当前指针
        LinkedNode current2 = head2;

        // 原链表 当前指针
        LinkedNode current = head;

        // index 计数
        int index = 0;

        while (current != null) {

            if (index % 2 == 0) {
                // TODO: 2020/7/13 注意！是 将当前节点 从 旧链表 删除，加到 新链表 这边来 --》 不是 简单的指向 就好了！
                // 拼接到 偶数链表后面
                current1.next = current;
                current1 = current1.next;

                // 先 原链表 后移
                current = current.next;

                // TODO: 2020/7/13 再 要主动打断 已拼接到 其他链表的 node的 next 节点
                // todo 不然最后 会出现问题的 --》最后一个 node，即 被正确的链表 引用也被 另一个链表的 最后一个节点的 next 指向 ！
                // TODO: 2020/7/13 即 链表 有环了！

                current1.next = null;
            } else {
                current2.next = current;
                current2 = current2.next;

                current = current.next;

                current2.next = null;
            }

            index++;
        }

        // 全部走完后，将 奇数链表 拼接在 偶数的最后面
        current1.next = head2.next;


        // TODO: 2020/7/13 我擦，传入的是一个引用，这里 压根 不需要 赋值，因为 赋值 也没用的！ --》修改不了 传入的 指针的指向！
//        head = head1.next;

//        System.out.println("排序后 hashcode = " + head.hashCode() + "\n" + head);
    }


    /**
     * 和上面 完全一样的做法，就是 node 类 不同 罢了
     * <p>
     * 奇偶 链表
     */
    public ListNode oddEvenList(ListNode head) {
        // TODO: 2020/7/13 边界条件，em 后面 好像 不需要 判空，天生支持。。
//        if (head == null) {
//            return head;
//        }


        // 偶数链表
        ListNode head1 = new ListNode();
        // 奇数链表
        ListNode head2 = new ListNode();

        // 偶数链表的 当前指针
        ListNode current1 = head1;
        // 奇数 当前指针
        ListNode current2 = head2;

        // 原链表 当前指针
        ListNode current = head;

        // index 计数
        int index = 0;

        while (current != null) {

            if (index % 2 == 0) {
                // TODO: 2020/7/13 注意！是 将当前节点 从 旧链表 删除，加到 新链表 这边来 --》 不是 简单的指向 就好了！
                // 拼接到 偶数链表后面
                current1.next = current;
                current1 = current1.next;
                // 先 原链表 后移
                current = current.next;

                // TODO: 2020/7/13 再 要主动打断 已拼接到 其他链表的 node的 next 节点，不然最后 会出现问题的 --》最后一个 node，即 被正确的链表 引用，也被另一个链表的最后一个节点的 next 指向！！！
                current1.next = null;
            } else {
                current2.next = current;
                current2 = current2.next;
                current = current.next;

                current2.next = null;
            }

            index++;
        }

        // 全部走完后，将 奇数链表 拼接在 偶数的最后面
        current1.next = head2.next;

        return head1.next;
    }


    /**
     * todo 更精简的 方法 3
     */
    public ListNode oddEvenList3(ListNode head) {
        if (head == null) {
            return head;
        }

        // 记录 奇、偶 链表，以及 最后 待拼接的偶数 链表头 evenHead
        ListNode odd = head, even = head.next, evenHead = even;

        // 注意，循环里面 用到的 判空！
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }

        // todo 最后拼接！
        odd.next = evenHead;

        // 返回 head 即可！
        return head;
    }
}

class LinkedNode {
    int _value;
    LinkedNode next;

    public LinkedNode(int value) {
        _value = value;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "_value=" + _value +
                ", next=" + next +
                '}';
    }
}


class ListNode {
    int val;

    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}