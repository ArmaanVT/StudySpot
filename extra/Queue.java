package studyspot;

/**
 * A singly linked FIFO queue of students. Duplicate checking belongs to
 * StudySpot. Empty peek and dequeue return null.
 * AI assistance: implementation and tests drafted with ChatGPT.
 * 
 * @author Jaden Moon
 * @version 2026.11.08
 */
public class Queue {
    private Node front;
    private Node rear;
    private int size;

    /** Creates an empty queue. */
    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }


    /**
     * @param s
     *            student to add to the rear
     * @throws IllegalArgumentException
     *             if s is null
     */
    public void enqueue(Student s) {
        Node node = new Node(s);
        if (isEmpty()) {
            front = node;
        }
        else {
            rear.setNext(node);
        }
        rear = node;
        size++;
    }


    /** @return the removed front student, or null when empty */
    public Student dequeue() {
        if (isEmpty()) {
            return null;
        }
        Student student = front.getData();
        front = front.getNext();
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return student;
    }


    /** @return the front student without removal, or null when empty */
    public Student peek() {
        return isEmpty() ? null : front.getData();
    }


    /** @return whether there are no students waiting */
    public boolean isEmpty() {
        return size == 0;
    }


    /** @return number of students waiting */
    public int size() {
        return size;
    }


    /** @return number of students waiting */
    public int getSize() {
        return size;
    }


    /**
     * @param s
     *            student to find
     * @return whether the queue contains s; false for null
     */
    public boolean contains(Student s) {
        for (Node current = front; current != null; current = current
            .getNext()) {
            if (current.getData().equals(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Removes the first matching student. Missing students, including null,
     * leave the queue unchanged.
     * 
     * @param s
     *            student to remove
     */
    public void remove(Student s) {
        Node previous = null;
        Node current = front;
        while (current != null) {
            if (current.getData().equals(s)) {
                if (previous == null) {
                    dequeue();
                }
                else {
                    previous.setNext(current.getNext());
                    if (current == rear) {
                        rear = previous;
                    }
                    size--;
                }
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }


    /**
     * Returns a FIFO snapshot for display without exposing queue nodes.
     * 
     * @return a new array containing the waiting students
     */
    public Student[] toArray() {
        Student[] students = new Student[size];
        Node current = front;
        for (int i = 0; i < size; i++) {
            students[i] = current.getData();
            current = current.getNext();
        }
        return students;
    }
}
