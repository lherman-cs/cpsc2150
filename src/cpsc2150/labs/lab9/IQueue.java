package cpsc2150.labs.lab9;

/**
 * By Christine Wendell and Lukas Herman
 */

/**
 * A queue containing integers.
 * A queue is a data structure where the first item added to the structure is the first item removed
 * from the structure
 *  This queue is bounded by MAX_DEPTH
 *
 * Initialization ensures the queue is empty
 * Defines: size:Z
 * Constraints: 0 <= size <= MAX_DEPTH
 */
public interface IQueue <T>{
    int MAX_DEPTH = 100;

    /**
     * Adds x to the end of the queue
     *
     * @param x value to be added to the end of the Queue
     * @requires x != NULL and size < MAX_DEPTH
     * @ensures [x is added to the end of the Queue]
     */
    public void add(T x);

    /**
     * Removes and returns the value at the front of the queue
     *
     * @return [The value removed from the front of the queue]
     * @requires [The queue is not empty] and size > 0
     * @ensures [Returns the value that we just removed from the front of the queue]
     */
    public T pop();

    /**
     * Returns the number of values in the Queue
     *
     * @return [The number of values in the queue]
     * @requires [The queue is not NULL]
     * @ensures [Returns the size of the queue]
     */
    public int size();

    /**
     * Returns the value at the front of the Queue without removing it
     *
     * @return [Value at the front of the Queue]
     * @requires [The queue is not NULL]
     * @ensures [Returns value at the front of the queue]
     */
    default public T peek() {
        T val;
        T returnVal = this.pop();
        int size = this.size();

        this.add(returnVal);

        for (int i = 0; i < size; i++) {
            val = this.pop();
            this.add(val);
        }

        return returnVal;
    }

    /**
     * Returns the value at the end of the Queue but does not remove it
     *
     * @return [The value at the end of the queue]
     * @requires [The queue is not NULL]
     * @ensures [Returns the value at the end of the queue]
     */
    default public T endOfQueue() {
        T val;
        T returnVal;
        int size = this.size() - 1;

        for (int i = 0; i < size; i++) {
            val = this.pop();
            this.add(val);
        }

        returnVal = this.pop();

        this.add(returnVal);

        return returnVal;
    }

    /**
     * Inserts x at the provided position in the queue
     *
     * @param x   Value we want to insert
     * @param pos Specific position in the Queue that we want to insert x
     * @requires pos >= 1 and [The queue is not NULL]
     * @ensures [x is inserted at position pos in the Queue]
     */
    default public void insert(T x, int pos) {
        int size = this.size();

        for (int i = 1; i <= size; i++) {
            if (i == pos) this.add(x);
            this.add(this.pop());
        }
    }

    /**
     * Removes the value at position pos in the Queue and returns it
     *
     * @param pos position in the Queue we want to remove from
     * @return [Value at position pos in the queue]
     * @requires [The queue is not NULL] and pos >= 1
     * @ensures [Returns the value removed from position pos]
     */
    default public T remove(int pos) {
        int size = this.size();
        T returnVal = null;

        for (int i = 1; i <= size; i++) {
            if (i == pos) {
                returnVal = this.pop();
                continue;
            }

            this.add(this.pop());
        }
        return returnVal;
    }

    /**
     * Returns the value at position pos without removing it
     *
     * @param pos position in the Queue whose value we want to return
     * @return [Value at the position pos]
     * @requires [The queue is not NULL] and pos >= 1
     * @ensures [Returns the value at position pos]
     */
    default public T get(int pos) {
        int size = this.size();
        T returnVal = null;
        T val;

        for (int i = 1; i <= size; i++) {
            val = this.pop();
            this.add(val);

            if (i == pos) {
                returnVal = val;
            }
        }
        return returnVal;
    }

}
