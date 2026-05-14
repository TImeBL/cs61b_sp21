package deque;

public interface Deque<type> {
    public void addFirst(type item);
    public void addLast(type item);
    default boolean isEmpty() {
        return size() == 0;
    }
    public int size();
    public void printDeque();
    public type removeFirst();
    public type removeLast();
    public type get(int index);
}