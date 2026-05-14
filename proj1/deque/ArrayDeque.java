package deque;

import java.util.Iterator;

public class ArrayDeque<type> implements Iterable<type>, Deque<type> {

    // 构造类属性
    private int size;
    private type[] queue;
    private int front;
    private int rear;

    // 初始化类循环队列
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        size = 0;
        queue = (type[]) new Object[8];
        front = 0;
        rear = 0;
    }

    // 创建私有方法
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        type[] newque = (type[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newque[i] = queue[(front + i) % queue.length];
        }
        queue = newque;
        front = 0;
        rear = size - 1;
    }

    // 创建队列方法
    public int minusOne(int index) {
        return (index - 1 + queue.length) % queue.length;
    }

    public int plusOne(int index) {
        return (index + 1) % queue.length;
    }

    public void addFirst(type input) {
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        front = minusOne(front);
        queue[front] = input;
        size++;
    }

    public void addLast(type input) {
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[rear] = input;
        rear = plusOne(rear);
        size++;
    }

    /*
    public boolean isEmpty() {
        return size == 0;
    }
     */

    public int size() {
        return size;
    }

    public void printDeque() {
        int cur = front;
        while (cur != rear) {
            System.out.print(queue[cur] + " ");
            cur = plusOne(cur);
        }
        System.out.println();
    }

    public type removeFirst() {
        if (size == 0) {
            return null;
        }
        type temp = queue[front];
        front = plusOne(front);
        size--;

        if (queue.length >= 16 && size <= queue.length / 4) {
            resize(queue.length / 2);
        }
        return temp;
    }

    public type removeLast() {
        if (size == 0) {
            return null;
        }
        type temp = queue[minusOne(rear)];
        rear = minusOne(rear);
        size--;

        if (queue.length >= 16 && size <= queue.length / 4) {
            resize(queue.length / 2);
        }
        return temp;
    }

    public type get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int cur = (front + index) % queue.length;
        return queue[cur];
    }

    public type getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    // 重写迭代器接口
    @Override
    public Iterator<type> iterator() {
        return new ArrayDequeIterator();
    }

    // 重写迭代器类型和方法
    private class ArrayDequeIterator implements Iterator<type> {
        private int wizPos;

        ArrayDequeIterator() {
            wizPos = front;
        }

        @Override
        public boolean hasNext() {
            return plusOne(wizPos) != rear - 1;
        }

        @Override
        public type next() {
            return queue[wizPos++];
        }
    }

    // 重写 equals()
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque) {
            ArrayDeque<type> lst = (ArrayDeque<type>) o;
            if (size != lst.size) {
                return false;
            }
            Iterator<type> lstIterator = lst.iterator();
            while (iterator().hasNext() && lstIterator.hasNext()) {
                if (iterator().next() != lstIterator.next()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}