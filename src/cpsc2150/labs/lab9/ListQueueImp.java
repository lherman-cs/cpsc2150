package cpsc2150.labs.lab9;

import java.util.*;

/**
 * By Christine Wendell and Lukas Herman
 */
/**
 * Correspondence: this = myQ. The front of myQ is the front of the Queue
 * Correspondence: size = myQ.size();
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ListQueueImp<T> implements IQueue<T> {
    private List<T> myQ;

    //complete the class

    /**
     * Constructor for ListQueueImp class
     *
     * @ensures myQ is initialized
     */
    public ListQueueImp() {
        myQ = new LinkedList<>();
    }

    @Override
    public void add(T x) {
        myQ.add(x);
    }

    @Override
    public T pop() {
        return myQ.remove(0);
    }

    public int size() {
        return myQ.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        myQ.forEach((x) -> {
            sb.append(x);
            sb.append('\n');
        });

        return sb.toString();
    }
}
