package deque;

public class LinkedListDeque<type> {
    // 构造结点类型
    private static class NewNode<item> {
        item data;
        NewNode<item> prev;
        NewNode<item> next;

        NewNode(item input) {
            data = input;
        }

        item getRecursive(int index) {
            if (index == 0) {
                return data;
            }
            return next.getRecursive(index - 1);
        }
    }

    // 初始化循环链表
    private int size;
    private NewNode<type> head = new NewNode<>(null);
    public LinkedListDeque() {
        size = 0;
        head.prev = head;
        head.next = head;
    }

    // 创建链表方法
    public void addFirst(type input) {
        NewNode<type> newNode = new NewNode<>(input);
        newNode.prev = head;
        newNode.next = head.next;
        newNode.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void addLast(type input) {
        NewNode<type> newNode = new NewNode<>(input);
        newNode.next = head;
        newNode.prev = head.prev;
        newNode.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        NewNode<type> cur = head.next;
        while (cur != head) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public type removeFirst() {
        if (size == 0) {
            return null;
        }
        NewNode<type> temp = head.next;
        head.next = temp.next;
        head.next.prev = head;
        size--;
        return temp.data;
    }

    public type removeLast() {
        if (size == 0) {
            return null;
        }
        NewNode<type> temp = head.prev;
        head.prev = temp.prev;
        head.prev.next = head;
        size--;
        return temp.data;
    }

    public type get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        NewNode<type> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.data;
    }

    public type getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return head.getRecursive(index);
    }
}