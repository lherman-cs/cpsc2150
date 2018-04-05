package cpsc2150.labs.lab9;

import java.util.Scanner;

/**
 * By Christine Wendell and Lukas Herman
 */
public class StringQueueApp {

    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.println("Enter 1 for array implementation or 2 for List implementation");
        int answer = in.nextInt();
        IQueue<String> q;
        if (answer == 1) {
            q = new ArrayQueueImp<>();
        } else {
            q = new ListQueueImp<>();
        }
        displayMenu();
        answer = in.nextInt();

        while (answer != 8) {
            if (answer == 1) {
                addToQueue(q);
            } else if (answer == 2) {
                getNext(q);
            } else if (answer == 3) {
                peekInQueue(q);
            } else if (answer == 4) {
                peekAtEnd(q);
            } else if (answer == 5) {
                insertInQueue(q);
            } else if (answer == 6) {
                getFromQueue(q);
            } else if (answer == 7) {
                removeFromQueue(q);
            } else {
                System.out.println("Not a valid option!");
            }
            System.out.println("Queue is: ");
            System.out.println(q.toString());
            System.out.println(" ");
            displayMenu();
            answer = in.nextInt();
        }
    }

    private static void displayMenu() {
        System.out.println("Select an option: ");
        System.out.println("1. Add to the Queue");
        System.out.println("2. Get next sentence from the Queue");
        System.out.println("3. Peek at the front of the Queue");
        System.out.println("4. Peek at the end of the Queue");
        System.out.println("5. Insert in the Queue");
        System.out.println("6. Get a position in the Queue");
        System.out.println("7. Remove from a position in the Queue");
        System.out.println("8. Quit");
    }

    private static void addToQueue(IQueue<String> q) {
        System.out.println("What sentence to add to the Queue?");
        in.nextLine();
        String newVal = in.nextLine();

        if (q.size() + 1 == 100) System.out.println("Queue is full!");
        else q.add(newVal);
    }

    private static void getNext(IQueue<String> q) {
        if (q.size() == 0) System.out.println("Queue is empty!");

        else {
            System.out.println("Next sentence is " + q.pop());
        }
    }

    private static void peekInQueue(IQueue<String> q) {
        if (q.size() == 0) System.out.println("Queue is empty!");

        else {
            System.out.println("Peek: " + q.peek());
        }
    }

    private static void peekAtEnd(IQueue<String> q) {
        if (q.size() == 0) System.out.println("Queue is empty!");

        else {
            System.out.println("Peek at end: " + q.endOfQueue());
        }
    }

    private static void insertInQueue(IQueue<String> q) {
        System.out.println("What sentence to add to the Queue?");
        in.nextLine();
        String newWord = in.nextLine();

        boolean invalidPos = true;
        int newPos = 0;
        while (invalidPos) {
            System.out.println("What position to insert in?");
            newPos = in.nextInt();

            if (newPos > q.size() + 1) System.out.println("Not a valid position in the Queue!");
            else if (newPos == q.size() + 1) {
                q.add(newWord);
                invalidPos = false;
            } else {
                q.insert(newWord, newPos);
                invalidPos = false;
            }
        }
    }

    private static void getFromQueue(IQueue<String> q) {
        if (q.size() == 0) System.out.println("Queue is empty!");

        else {
            System.out.println("What position to get from the Queue?");
            int newPos = in.nextInt();

            while (newPos < 1 || newPos > q.size()) {
                System.out.println("Not a valid position in the Queue!");

                System.out.println("What position to get from the Queue?");
                newPos = in.nextInt();
            }
            String getVal = q.get(newPos);

            System.out.println(getVal + " is at position " + newPos + " in the queue");
        }
    }

    private static void removeFromQueue(IQueue<String> q) {
        if (q.size() == 0) System.out.println("Queue is empty!");

        else {
            System.out.println("What position to remove from the Queue?");
            int newPos = in.nextInt();

            while (newPos < 1 || newPos > q.size()) {
                System.out.println("Not a valid position in the Queue!");

                System.out.println("What position to remove from the Queue?");
                newPos = in.nextInt();
            }
            String remVal = q.remove(newPos);

            System.out.println(remVal + " was at position " + newPos + " in the queue");
        }

    }
}
