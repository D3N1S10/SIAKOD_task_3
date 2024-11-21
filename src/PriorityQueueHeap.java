import java.util.ArrayList;
import java.util.List;

public class PriorityQueueHeap {
    private final List<Element> heap;

    public PriorityQueueHeap() {
        heap = new ArrayList<>();
    }

    public void insert(String x, int p) {
        heap.add(new Element(x, p));
        siftUp(heap.size() - 1);
    }

    public void extractMax() {
        if (heap.isEmpty()) {
            return;
        }
        if (heap.size() == 1) {
            heap.remove(0);
        } else {
            heap.set(0, heap.remove(heap.size() - 1));
            siftDown(0);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).getPriority() <= heap.get(parent).getPriority()) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && heap.get(leftChild).getPriority() > heap.get(largest).getPriority()) {
                largest = leftChild;
            }

            if (rightChild < size && heap.get(rightChild).getPriority() > heap.get(largest).getPriority()) {
                largest = rightChild;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int i, int j) {
        Element temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
