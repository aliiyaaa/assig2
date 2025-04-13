import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private class MyNode {
        T data;
        MyNode next, prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private MyNode head, tail;
    private int size;

    public MyArrayList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void add(T data) {
        addLast(data);
    }

    @Override
    public void addFirst(T data) {
        MyNode newNode = new MyNode(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T data) {
        MyNode newNode = new MyNode(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            checkIndex(index);
            MyNode current = getNode(index);
            MyNode newNode = new MyNode(data);

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;

            size++;
        }
    }

    @Override
    public void set(int index, T data) {
        getNode(index).data = data;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new IllegalStateException("List is empty");
        return tail.data;
    }

    @Override
    public void remove(int index) {
        MyNode node = getNode(index);
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public int indexOf(T item) {
        int index = 0;
        for (MyNode n = head; n != null; n = n.next, index++) {
            if (n.data.equals(item)) return index;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T item) {
        int index = size - 1;
        for (MyNode n = tail; n != null; n = n.prev, index--) {
            if (n.data.equals(item)) return index;
        }
        return -1;
    }

    @Override
    public boolean exists(T item) {
        return indexOf(item) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (MyNode n = head; n != null; n = n.next) {
            array[i++] = n.data;
        }
        return array;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private MyNode getNode(int index) {
        checkIndex(index);
        MyNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    @Override
    public void sort() {
        if (size <= 1) return;

        for (MyNode i = head.next; i != null; i = i.next) {
            T key = i.data;
            MyNode j = i.prev;
            while (j != null && j.data.compareTo(key) > 0) {
                j.next.data = j.data;
                j = j.prev;
            }
            if (j == null) {
                head.data = key;
            } else {
                j.next.data = key;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
