package studyspot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Queue. AI assistance: drafted with ChatGPT. */
public class QueueTest {
    private Queue queue;
    private Student a;
    private Student b;
    private Student c;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
        a = new Student("Jaden");
        b = new Student("Armaan");
        c = new Student("Catherine");
    }

    @Test
    public void testEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertEquals(0, queue.getSize());
        assertNull(queue.peek());
        assertNull(queue.dequeue());
        queue.remove(a);
        assertEquals(0, queue.size());
        assertFalse(queue.contains(a));
        assertArrayEquals(new Student[0], queue.toArray());
    }

    @Test
    public void testFifoAndReuse() {
        queue.enqueue(a);
        assertFalse(queue.isEmpty());
        assertSame(a, queue.peek());
        assertEquals(1, queue.size());
        queue.enqueue(b);
        queue.enqueue(c);
        assertEquals(3, queue.getSize());
        assertSame(a, queue.peek());
        assertEquals(3, queue.size());
        assertSame(a, queue.dequeue());
        assertEquals(2, queue.size());
        assertSame(b, queue.dequeue());
        assertSame(c, queue.dequeue());
        assertTrue(queue.isEmpty());
        queue.enqueue(b);
        assertSame(b, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveFront() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.remove(a);
        assertEquals(1, queue.size());
        assertSame(b, queue.dequeue());
        assertFalse(queue.contains(a));
    }

    @Test
    public void testRemoveMiddle() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.enqueue(c);
        queue.remove(b);
        assertEquals(2, queue.size());
        assertArrayEquals(new Student[] {a, c}, queue.toArray());
    }

    @Test
    public void testRemoveRearThenEnqueue() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.remove(b);
        queue.enqueue(c);
        assertEquals(2, queue.size());
        assertSame(a, queue.dequeue());
        assertSame(c, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveOnlyStudentThenEnqueue() {
        queue.enqueue(a);
        queue.remove(a);
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
        queue.enqueue(b);
        assertSame(b, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testMissingAndNull() {
        queue.enqueue(a);
        queue.remove(b);
        queue.remove(null);
        assertFalse(queue.contains(null));
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
        assertEquals(1, queue.size());
        assertSame(a, queue.peek());
    }

    @Test
    public void testContainsAndIdentity() {
        queue.enqueue(a);
        queue.enqueue(b);
        assertTrue(queue.contains(a));
        assertTrue(queue.contains(b));
        assertFalse(queue.contains(c));
        assertFalse(queue.contains(new Student("Jaden")));
        queue.remove(b);
        assertFalse(queue.contains(b));
    }

    @Test
    public void testDuplicatesRemoveFirstOnly() {
        queue.enqueue(a);
        queue.enqueue(b);
        queue.enqueue(a);
        queue.remove(a);
        assertEquals(2, queue.size());
        assertSame(b, queue.dequeue());
        assertSame(a, queue.dequeue());
    }

    @Test
    public void testSnapshotDoesNotExposeQueue() {
        queue.enqueue(a);
        queue.enqueue(b);
        Student[] snapshot = queue.toArray();
        assertArrayEquals(new Student[] {a, b}, snapshot);
        snapshot[0] = c;
        assertSame(a, queue.peek());
        assertEquals(2, queue.size());
    }
}
