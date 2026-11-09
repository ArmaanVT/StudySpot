package studyspot;

/**
 * One link in the student queue.
 * AI assistance: implementation and tests drafted with ChatGPT.
 * 
 * @author Jaden Moon
 * @version 2026.11.08
 */
public class Node {
    private Student data;
    private Node next;

    /**
     * @param data
     *            student stored in this node
     */
    public Node(Student data) {
        this(data, null);
    }


    /**
     * @param data
     *            student stored in this node
     * @param next
     *            following node, or null
     */
    public Node(Student data, Node next) {
        if (data == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        this.data = data;
        this.next = next;
    }


    /** @return the stored student */
    public Student getData() {
        return data;
    }


    /** @return the next node, or null */
    public Node getNext() {
        return next;
    }


    /**
     * @param next
     *            the next node, or null to end the chain
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
