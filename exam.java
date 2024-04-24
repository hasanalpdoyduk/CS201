//__________________________________________________
// QUEUE

//__________________________________________________
// ARRAY IMPLEMENTATION

public class Queue {
    Element array[];
    int first;
    int last;
    int N;

    public Queue(int N) {
        array = new Element[N];
        this.N = N;
        first = 0;
        last = 0;
    }

    boolean isFull() {
        if (first == (last + 1) % N)
            return true;
        else
            return false;
    }

    boolean isEmpty() {
        if (first == last)
            return true;
        else
            return false;
    }

    public void enqueue(Element element) {
        if (!isFull()) {
            array[last] = element;
            last = (last + 1) % N;
        }
    }

    public Element dequeue() {
        if (!isEmpty()) {
            Element tmp = array[first];
            first = (first + 1) % N;
            return tmp;
        }
        return null;
    }





//Question 1
//Write a function that moves the element currently at the front of the
//queue to the rear of the queue.

    public void moveToRear() {
        if (!isEmpty()) {
            Element frontElement = array[first];
            dequeue();
            enqueue(frontElement);
        }
    }



//Question 2
//For array implementation, write a function that enlarges the queue
//when it is full. The new queue will hold two times more than the
//original queue

    public void enlargeQueue() {
        int newCapacity = array.length * 2;
        Element[] newArray = new Element[newCapacity];

        // Copy elements from the original array to the new array
        for (int i = 0; i < N; i++) {
            newArray[i] = array[(first + i) % N];
        }

        array = newArray;
        first = 0;
        last = N;
        N = newCapacity;
    }

//Question 6
// Write a function that adds a new element after the front element of the
//queue. Write the function for both array and linked list implementations.

    public void insertSecond(Element newElement) {
        if (isEmpty()) {
            System.out.println("error");
        }

        Element frontElement = dequeue();
        enqueue(newElement);
        enqueue(frontElement);
    }



//Question 10
// Write a function that adds a new element after the K’th (K ≥ 0)
//element of the queue. Write the function for both array and linked
//list implementations. You can safely assume that, there are at least K
//elements in the queue.

    public void insertAfterKth(int K, int data) {
        if (K < 0 || K >= (last - first)) {
            return; // Invalid K
        }

        // Shift elements to the right to make space for the new element
        for (int i = last; i > first + K; i--) {
            array[i] = array[i - 1];
        }

        // Insert the new element after the K'th element
        array[first + K + 1] = new Element(data);
        last++; // Update the last index
    }



//Question 11
//Write a function that deletes the element in the K’th (K ≥ 0) position
//of the queue. Write the function for array implementation.

    public void deleteKth(int K) {
        if (isEmpty() || K < 0 || K >= (last - first)) {
            return; // Invalid K or empty queue
        }

        for (int i = first + K; i < last - 1; i++) {
            array[i] = array[i + 1];
        }

        array[last - 1] = null; // Remove the last element
        last--; // Update the last index
    }



//Question 12
//Write a method where the method returns the minimum number in a
//queue. Write the function for both array and linked list implementations.
// Do not use any class or external methods except isEmpty().

    public int minimum() {
        if (isEmpty()) {
            return -1;
        }

        int min = Integer.MAX_VALUE;

        for (int i = first; i < last; i++) {
            if (array[i] != null && array[i].getData() < min) {
                min = array[i].getData();
            }
        }

        return min;
    }



//Question 13
//Write a method which removes and returns the second item from the
//queue. Write the function for both array and linked list implementations.
//Your methods should run in O(1) time. Do not use any class or
//external methods except isEmpty().

    public Element dequeue2nd() {
        if (isEmpty() || last - first < 2) {
            return null; // Queue has less than 2 items
        }

        Element second = array[first + 1];
        first += 2; // Skip the first and second item
        return second;
    }



//Question 14
//Write a function that inserts a new element after the largest element
//of the queue. Write the function for array implementation. You are
//not allowed to use any queue methods, just attributes, constructors,
//getters and setters.

    public void insertAfterLargest(int data) {
        if (isEmpty()) {
            enqueue(new Element(data));
            return;
        }

        int largestIndex = 0;
        int largestValue = array[0].getData();

        // Find the index of the largest element
        for (int i = 1; i < N; i++) {
            if (array[i] != null && array[i].getData() > largestValue) {
                largestValue = array[i].getData();
                largestIndex = i;
            }
        }

        // Shift elements to the right to make space for the new element
        for (int i = last; i > largestIndex; i--) {
            array[i] = array[i - 1];
        }

        // Insert the new element after the largest element
        array[largestIndex + 1] = new Element(data);
        last++; // Update the last index
    }



//Question 15
//Write a function that creates and returns a new queue by removing
//even indexed elements from the original queue and inserting into the
//newly created queue. Write the function for both array and linked list
//implementations. The first node has index 1. You are not allowed
//to use any queue or linked list methods, just attributes, constructors,
//getters and setters.

    public Queue divideQueue() {
        Queue newQueue = new Queue(N); // New queue to hold even-indexed elements

        for (int i = 0; i < N; i++) {
            if (i % 2 != 0) { // Odd index, add the element to the new queue
                newQueue.enqueue(array[i]);
            }
        }

        return newQueue;
    }



//Question 16
//void removeOddIndexed()
//which removes only the odd indexed (1, 3, . . .) elements from the
//queue. The first element has index 1. You are only allowed to use
//enqueue, dequeue, isEmpty functions. You should use external
//queue. You are not allowed to use any queue attributes such as first,
//last, array etc.

    public void removeOddIndexed() {
        Queue tempQueue = new Queue(N); // External queue

        int index = 1;
        while (!isEmpty()) {
            Element currentElement = dequeue();

            if (index % 2 == 0) { // Even index, keep the element
                tempQueue.enqueue(currentElement);
            }

            index++;
        }

        // Restore the original queue by moving elements back from the external queue
        while (!tempQueue.isEmpty()) {
            enqueue((Element) tempQueue.dequeue());
        }
    }


//Question 17
//Element dequeue(int k), Node dequeue(int k)
//which dequeues data as the k’th element from the first. dequeue(1) is
//equal to the original dequeue, that is, the first element has index 1.
//You are not allowed to use any queue methods and any external structures
//(arrays, queues, trees, etc).
//You are allowed to use attributes,constructors, getters and setters.

    public Element dequeue(int k) {
        if (isEmpty() || k < 1 || k > (last - first + 1)) {
            return null; // Invalid k or empty queue
        }

        Element result = array[first + k - 1];

        // Shift elements to the left to fill the gap
        for (int i = first + k - 1; i < last; i++) {
            array[i] = array[i + 1];
        }

        last--; // Update the last index
        return result;
    }

}

//__________________________________________________
// LINKEDLIST IMPLEMENTATION

public class Queue {

    protected Node first;
    protected Node last;

    public Queue() {
        first = null;
        last = null;
    }

    boolean isEmpty() {
        if (first == null)
            return true;
        else
            return false;
    }

    public void enqueue(Node newNode) {
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
    }

    public Node dequeue() {
        Node result = first;
        if (!isEmpty()) {
            first = first.getNext();
            if (isEmpty()) {
                last = null;
            }
        }
        return result;
    }





//Question 1
//Write a function that moves the element currently at the front of the
//queue to the rear of the queue.

    public void moveToRear() {
        if (!isEmpty() && first != last) { // Check if queue is not empty and has more than one element
            Node frontNode = dequeue(); // Remove front node
            enqueue(frontNode); // Enqueue front node to rear
        }
    }

  

//Question 5
//For linked list implementation, write a function that moves the element
//currently at the rear of the queue to the front of the queue.
//void moveToFront()

    public void moveToFront() {
        if (!isEmpty() && first != last) { // Check if queue is not empty and has more than one element
            Node current = first;
            Node previous = null;

            // Traverse to the last node
            while (current.getNext() != null) {
                previous = current;
                current = current.getNext();
            }

            // Move the last node to the front
            current.setNext(first);
            first = current;

            // Update the last node
            previous.setNext(null);
            last = previous;
        }
    }



//Question 6
// Write a function that adds a new element after the front element of the
//queue. Write the function for both array and linked list implementations.

    public void insertSecond(Node newNode) {
        if (isEmpty()) {
            System.out.println("error");
        }

        Node frontNode = dequeue();
        enqueue(newNode);
        enqueue(frontNode);
    }



//Question 10
// Write a function that adds a new element after the K’th (K ≥ 0)
//element of the queue. Write the function for both array and linked
//list implementations. You can safely assume that, there are at least K
//elements in the queue.

    public void insertAfterKth(int K, int data) {
        if (K < 0) {
            return; // Invalid K
        }

        Node current = first;
        // Traverse to the K'th node
        for (int i = 0; i < K; i++) {
            current = current.getNext();
        }

        Node newNode = new Node(data);
        newNode.setNext(current.getNext());
        current.setNext(newNode);

        if (current == last) {
            last = newNode; // Update the last node if K is the last node
        }
    }



// Question 12
//Write a method where the method returns the minimum number in a
//queue. Write the function for both array and linked list implementations.
// Do not use any class or external methods except isEmpty().

    public int minimum() {
        if (isEmpty()) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        Node current = first;

        while (current != null) {
            if (current.getData() < min) {
                min = current.getData();
            }
            current = current.getNext();
        }

        return min;
    }



//Question 13
//Write a method which removes and returns the second item from the
//queue. Write the function for both array and linked list implementations.
//Your methods should run in O(1) time. Do not use any class or
//external methods except isEmpty().

    public Node dequeue2nd() {
        if (isEmpty() || first.getNext() == null || first.getNext().getNext() == null) {
            return null; // Queue has less than 2 items
        }

        Node second = first.getNext();
        first.setNext(second.getNext());
        return second;
    }



//Question 15
//Write a function that creates and returns a new queue by removing
//even indexed elements from the original queue and inserting into the
//newly created queue. Write the function for both array and linked list
//implementations. The first node has index 1. You are not allowed
//to use any queue or linked list methods, just attributes, constructors,
//getters and setters.

    public Queue divideQueue() {
        Queue newQueue = new Queue(); // New queue to hold even-indexed elements

        Node current = first;
        int index = 1;
        while (current != null) {
            if (index % 2 != 0) { // Odd index, add the element to the new queue
                newQueue.enqueue(new Node(current.getData()));
            }
            current = current.getNext();
            index++;
        }

        return newQueue;
    }



//Question 17
//Element dequeue(int k), Node dequeue(int k)
//which dequeues data as the k’th element from the first. dequeue(1) is
//equal to the original dequeue, that is, the first element has index 1.
//You are not allowed to use any queue methods and any external structures (arrays, queues, trees, etc).
//You are allowed to use attributes,constructors, getters and setters.

    public Node dequeue(int k) {
        if (isEmpty() || k < 1) {
            return null; // Invalid k or empty queue
        }

        Node current = first;
        Node previous = null;
        int index = 1;

        // Traverse the queue to find the k-th node
        while (current != null && index < k) {
            previous = current;
            current = current.getNext();
            index++;
        }

        // If k is larger than the size of the queue, return null
        if (current == null) {
            return null;
        }

        if (current == first) {
            // Dequeue the first node
            first = first.getNext();
            if (first == null) {
                last = null; // Queue becomes empty
            }
        } else {
            // Remove the k-th node
            previous.setNext(current.getNext());
            if (current == last) {
                last = previous; // Update the last node
            }
        }

        return current;
    }


}
