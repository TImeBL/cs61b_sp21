package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K thisKey, V thisValue) {
            key = thisKey;
            value = thisValue;
            left = null;
            right = null;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                return true;
            } else if (key.compareTo(cur.key) > 0) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                return cur.value;
            } else if (key.compareTo(cur.key) > 0) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
            size++;
            return;
        }

        BSTNode cur = root;
        while (true) {
            if (key.compareTo(cur.key) == 0) {
                cur.value = value;
                return;
            } else if (key.compareTo(cur.key) > 0) {
                if (cur.right == null) {
                    cur.right = new BSTNode(key, value);
                    size++;
                    return;
                }
                cur = cur.right;
            } else {
                if (cur.left == null) {
                    cur.left = new BSTNode(key, value);
                    size++;
                    return;
                }
                cur = cur.left;
            }
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        if (root != null) {
            printInOrder(root);
        }
    }

    private void printInOrder(BSTNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value.toString());
            printInOrder(node.right);
        }
    }

}
