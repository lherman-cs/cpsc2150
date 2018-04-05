package cpsc2150.labs.lab9;

import java.util.*;

/**
 * By Christine Wendell and Lukas Herman
 */
public class QueueApp{
    /*
    You will add in code here to ask the user whether they want an array implementation or a list implementation. Then use their answer to initialize q appropriately
    */
    public static void main(String[] args) {
        IQueue<Integer> q;

        Scanner scan = new Scanner(System.in);
        System.out.println("Which implementation do you want to use?");
        System.out.println("   1. Array");
        System.out.println("   2. Linked List");
        int input = scan.nextInt();

        if (input == 1) {
            q = new ArrayQueueImp<>();
        } else {
            q = new ListQueueImp<>();
        }

        Integer x = 42;
        q.add(x);
        x = 17;
        q.add(x);
        x = 37;
        q.add(x);
        x = 36;
        q.add(x);
        x = 12;
        q.add(x);
        //Add the code to print the queue. After the code is finished, the queue should contain all its values in order

        while (q.size() > 0) {
            System.out.println(q.pop());
        }
    }
}
