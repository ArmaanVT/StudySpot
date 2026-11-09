package studyspot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Node. AI assistance: drafted with ChatGPT. */
public class NodeTest {
    @Test
    public void testSingleNode() {
        Student s = new Student("Jaden");
        Node node = new Node(s);
        assertSame(s, node.getData());
        assertNull(node.getNext());
    }


    @Test
    public void testLinks() {
        Node last = new Node(new Student("Ethan"));
        Node first = new Node(new Student("Jaden"), last);
        assertSame(last, first.getNext());
        Node replacement = new Node(new Student("Armaan"));
        first.setNext(replacement);
        assertSame(replacement, first.getNext());
        first.setNext(null);
        assertNull(first.getNext());
    }


    @Test
    public void testNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> new Node(null));
        assertThrows(IllegalArgumentException.class, () -> new Node(null,
            null));
    }
}
