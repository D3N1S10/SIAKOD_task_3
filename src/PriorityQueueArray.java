import java.util.ArrayList;

public class PriorityQueueArray {
    private final ArrayList<Element> elements;

    public PriorityQueueArray() {
        elements = new ArrayList<>();
    }

    public void insert(String x, int p) {
        elements.add(new Element(x, p));
    }

    public void extractMax() {
        if (elements.isEmpty()) return;
        int maxIndex = 0;
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i).getPriority() > elements.get(maxIndex).getPriority()) {
                maxIndex = i;
            }
        }
        elements.remove(maxIndex);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
