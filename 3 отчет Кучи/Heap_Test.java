import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    @Test
    void testInsertion() {
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

        assertEquals(84, maxHeap.extractMax());
        assertEquals(22, maxHeap.extractMax());
        assertEquals(19, maxHeap.extractMax());
    }

    @Test
    void testHeapify() {
        MaxHeap maxHeap = new MaxHeap(15);
        int[] array = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        maxHeap.heapify(array);

        assertEquals(17, maxHeap.extractMax());
        assertEquals(15, maxHeap.extractMax());
        assertEquals(13, maxHeap.extractMax());
    }

    @Test
    void testExtractMax() {
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

        assertEquals(84, maxHeap.extractMax());
        assertEquals(22, maxHeap.extractMax());
        assertEquals(19, maxHeap.extractMax());
    }
}