import java.util.EmptyStackException;

public class MyStack<E> {
    private ListNode<E> head;

    public MyStack() {
        head = null;
    }

    public boolean push(E val) {
        ListNode<E> addition = new ListNode<E>(val);
        if (head != null) {
            addition.setNext(head);
        }
        head = addition;
        return true;
    }

    public boolean pop() {
        if (head == null) {
            throw new EmptyStackException();
        }
        head = head.getNext();
        return true;
    }

    public E peek() {
        if (head == null) {
            throw new EmptyStackException();
        }
        return head.getValue();
    }

    public boolean empty() {
        return head == null;
    }
}
