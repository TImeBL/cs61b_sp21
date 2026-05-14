package deque;

import java.util.Comparator;

public class MaxArrayDeque<type> extends ArrayDeque<type> {

    private Comparator<type> comparator;

    public MaxArrayDeque(Comparator<type> c) {
        super();
        comparator = c;
    }

    public type max() {
        if (isEmpty()) {
            return null;
        }
        return max(comparator);
    }

    public type max(Comparator<type> c) {
        if (isEmpty()) {
            return null;
        }
        type maxItem = get(0);
        for (type item : this) {
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}