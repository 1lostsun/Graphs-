import java.util.*;

class Task {
    int priority;
    String description;

    Task(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Priority: " + priority + ", Description: " + description;
    }
}

class TaskHeap {
    private List<Task> heap;

    public TaskHeap() {
        this.heap = new ArrayList<>();
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos + 1;
    }

    private int rightChild(int pos) {
        return 2 * pos + 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (heap.size() / 2) && pos < heap.size();
    }

    private void swap(int fpos, int spos) {
        Task tmp = heap.get(fpos);
        heap.set(fpos, heap.get(spos));
        heap.set(spos, tmp);
    }

    private void siftDown(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);
        int largest = pos;

        if (left < heap.size() && heap.get(left).priority > heap.get(largest).priority)
            largest = left;

        if (right < heap.size() && heap.get(right).priority > heap.get(largest).priority)
            largest = right;

        if (largest != pos) {
            swap(pos, largest);
            siftDown(largest);
        }
    }

    private void siftUp(int pos) {
        int current = pos;
        while (current > 0 && heap.get(current).priority > heap.get(parent(current)).priority) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void insert(Task task) {
        heap.add(task);
        siftUp(heap.size() - 1);
    }

    public Task extractMax() {
        if (heap.isEmpty())
            throw new NoSuchElementException("Heap is empty");

        Task popped = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        siftDown(0);
        return popped;
    }

    public void print() {
        for (Task task : heap) {
            System.out.println(task);
        }
    }
}

public class TaskProcessingSystem {
    public static void main(String[] args) {
        TaskHeap taskHeap = new TaskHeap();

        Task task1 = new Task(5, "Task 1");
        Task task2 = new Task(3, "Task 2");
        Task task3 = new Task(17, "Task 3");
        Task task4 = new Task(10, "Task 4");
        Task task5 = new Task(84, "Task 5");
        Task task6 = new Task(19, "Task 6");
        Task task7 = new Task(6, "Task 7");
        Task task8 = new Task(22, "Task 8");
        Task task9 = new Task(9, "Task 9");

        taskHeap.insert(task1);
        taskHeap.insert(task2);
        taskHeap.insert(task3);
        taskHeap.insert(task4);
        taskHeap.insert(task5);
        taskHeap.insert(task6);
        taskHeap.insert(task7);
        taskHeap.insert(task8);
        taskHeap.insert(task9);

        System.out.println("Куча задач после вставок:");
        taskHeap.print();

        System.out.println("Обработка задач в порядке приоритета:");
        while (!taskHeap.isEmpty()) {
            Task task = taskHeap.extractMax();
            System.out.println("Processing: " + task);
        }
    }

    private static boolean isEmpty(TaskHeap taskHeap) {
        return taskHeap.heap.isEmpty();
    }
}