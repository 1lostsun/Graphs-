import java.util.*;

class MaxHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize];
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
        return pos >= (size / 2) && pos < size;
    }

    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void siftDown(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);
        int largest = pos;

        if (left < size && heap[left] > heap[largest])
            largest = left;

        if (right < size && heap[right] > heap[largest])
            largest = right;

        if (largest != pos) {
            swap(pos, largest);
            siftDown(largest);
        }
    }

    private void siftUp(int pos) {
        int current = pos;
        while (current > 0 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void insert(int element) {
        if (size >= maxSize)
            return;

        heap[size] = element;
        siftUp(size);
        size++;
    }

    public int extractMax() {
        int popped = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        return popped;
    }

    public void heapify(int[] array) {
        if (array.length > maxSize)
            throw new IllegalArgumentException("Array size exceeds heap max size");

        heap = Arrays.copyOf(array, maxSize);
        size = array.length;

        for (int pos = (size / 2) - 1; pos >= 0; pos--) {
            siftDown(pos);
        }
    }

    public void print() {
        for (int i = 0; i < size / 2; i++) {
            System.out.print("Parent: " + heap[i]);
            if (leftChild(i) < size)
                System.out.print(" Left Child: " + heap[leftChild(i)]);
            if (rightChild(i) < size)
                System.out.print(" Right Child: " + heap[rightChild(i)]);
            System.out.println();
        }
    }
}

public class HeapExample {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(15);

        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);

        System.out.println("Куча после вставок:");
        maxHeap.print();

        System.out.println("Удален максимальный элемент: " + maxHeap.extractMax());
        System.out.println("Куча после удаления:");
        maxHeap.print();

        int[] array = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        maxHeap.heapify(array);
        System.out.println("Куча после окучивания массива:");
        maxHeap.print();
    }
}
