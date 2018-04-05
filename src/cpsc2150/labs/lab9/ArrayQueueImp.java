package cpsc2150.labs.lab9;

/**
 * By Christine Wendell and Lukas Herman
 */
/**
 * Correspondence: this = myQ[0...depth-1], myQ[0] is the front
 * of the Queue
 * Correspondence: size = depth
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ArrayQueueImp<T> implements IQueue<T> {
    private T[] myQ;
    private int depth;

    //complete the class

    /**
     * Constructor for ArrayQueueImp class
     *
     * @ensures myQ is initialized with the size of MAX_DEPTH and depth = 0
     */
    public ArrayQueueImp() {
        depth = 0;
        myQ = (T[])new Object[MAX_DEPTH];
    }

    @Override
    public void add(T x) {
        myQ[depth] = x;
        depth++;
    }

    @Override
    public T pop() {
        T firstElem = myQ[0];

        for (int i = 0; i < depth; i++) {
            myQ[i] = myQ[i + 1];
        }
        depth--;

        return firstElem;
    }

    public int size() {
        return depth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            sb.append(myQ[i]);
            sb.append('\n');
        }
        return sb.toString();
    }
}
