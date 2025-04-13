public class MyMinHeap<T extends Comparable<T>> {
        private final MyArrayList<T> list = new MyArrayList<>();

        public void add(T element) {
            list.add(element);
            int index = list.size() - 1;
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (list.get(index).compareTo(list.get(parentIndex)) >= 0) {
                    break;
                }
                swap(index, parentIndex);
                index = parentIndex;
            }
        }

        public void swap(int i, int j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        public T remove() {
            if (list.size() == 0) {
                return null;
            }
            T minElement = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            heapify(0);
            return minElement;
        }

        private void heapify(int index) {
            int smallest = index;
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (leftChildIndex < list.size() && list.get(leftChildIndex).compareTo(list.get(smallest)) < 0) {
                smallest = leftChildIndex;
            }

            if (rightChildIndex < list.size() && list.get(rightChildIndex).compareTo(list.get(smallest)) < 0) {
                smallest = rightChildIndex;
            }

            if (smallest != index) {
                swap(smallest, index);
                heapify(smallest);
            }
        }

        public T peek() {
            return list.get(0);
        }

        public int size() {
            return list.size();
        }

        public boolean isEmpty() {
            return list.size() == 0;
        }
    }
