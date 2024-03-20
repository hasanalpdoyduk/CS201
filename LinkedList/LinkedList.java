package LinkedList;

public class LinkedList {

    protected Node head;
    protected Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Node getHead(){
        return head;
    }

    public void insertFirst(Node newNode) {
        if (isEmpty()) {
            tail = newNode;
        }
        newNode.setNext(head);
        head = newNode;
    }

    public void insertLast(Node newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
    }

    public void insertMiddle(Node newNode, Node previous) {
        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
    }

    /**
     * @param value The value to be searched.
     * @return The node that has the data value. If no node exists, returns null.
     */
    public Node search(int value) {
        Node tmp = head;
        while (tmp != null) {
            if (value == tmp.getData()) {
                return tmp;
            }
            tmp = tmp.getNext();
        }
        return null;
    }

    public Node getNodeI(int i) {
        Node tmp = head;
        int index = 0;
        while (tmp != null) {
            if (index == i){
                return tmp;
            }
            index++;
            tmp = tmp.getNext();
        }
        return null;
    }

    public int numberOfElements(){
        Node tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.getNext();
        }
        return count;
    }

    public void deleteFirst(){
        head = head.getNext();
        if (isEmpty()){
            tail = null;
        }
    }

    public Node getPrevious(Node node){
        Node tmp = head;
        Node previous = null;
        while (tmp != node) {
            previous = tmp;
            tmp = tmp.getNext();
        }
        return previous;
    }

    public void deleteValue(int value){
        Node tmp = head;
        Node previous = null;
        while (tmp != null) {
            if (tmp.getData() == value){
                if (previous != null){
                    previous.setNext(tmp.next);
                    if (tmp.next == null){
                        tail = previous;
                    }
                } else {
                    head = tmp.next;
                    if (head == null){
                        tail = null;
                    }
                }
                break;
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
    }

    public void deleteLast(){
        tail = getPrevious(tail);
        if (tail != null){
            tail.setNext(null);
        } else {
            head = null;
        }
    }

    public void deleteMiddle(Node node){
        Node previous;
        previous = getPrevious(node);
        previous.setNext(node.getNext());
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        Node tmp = head;
        while (tmp != null) {
            result.append(tmp).append(" ");
            tmp = tmp.getNext();
        }
        return result.toString();
    }






    // Methods for Book Questions


    // Solved Example 2:
     void insertSecond(Node node){
        if (isEmpty())
            insertFirst (node);
        else{
            if (head.next == null)
                insertLast(node);
            else{
                node.next = head.next;
                head.next = node;
            }
        }
    }

    // Solved Example 4:
    static LinkedList primes(int N){
        int i, j ;
        boolean isPrime;
        Node node;
        LinkedList primeList = new LinkedList();
        for (i = 2; i <= N; i++){
            isPrime = true;
            for (j = 2; j < N; j++){
                if (i % j == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime){
                node = new Node(i);
                primeList.insertLast(node);
            }
        }
        return primeList;
    }


    // Solved Example 5:
    static LinkedList union(LinkedList l1, LinkedList l2){
        int data;
        LinkedList result ;
        Node tmp1, tmp2, node;
        result = new LinkedList();
        tmp1 = l1.head;
        tmp2 = l2.head;
        while (tmp1 != null && tmp2 != null){
            if (tmp1.data < tmp2.data){
                data = tmp1.data;
                tmp1 = tmp1.next;
            } else if (tmp1.data > tmp2.data){
                data = tmp2.data;
                tmp2 = tmp2.next;
            } else {
                data = tmp1.data;
                tmp1 = tmp1.next;
                tmp2 = tmp2.next;
            }
            node = new Node(data);
            result . insertLast(node);

        }
        return result;
    }

    // Exercise 1:
    public int smallest(){
        Node tmp = head.getNext();
        int smallest = head.getData();
        Node current = head.getNext();
        if (isEmpty()){
            System.out.println("This list is empty.");
        }
        else {
            while (tmp != null){
                int value = current.getData();
                if (smallest > value){
                    smallest = value;
                }
                current = current.getNext();
                tmp = tmp.getNext();
            }
        }
        return smallest;
    }

    // Exercise 2:
    public void deleteSecond(){
        if (isEmpty() || head.getNext() == null){
            System.out.println("This list is empty or there isn't 2 nodes in this list.");
        }
        else {
            Node jumper = head.getNext();
            head.setNext(jumper.getNext());
        }
        if (head.getNext() == null){
            tail = head;
        }
    }

    // Exercise 3:
    public LinkedList oddIndexedElements(){
        LinkedList oddLinkedList = new LinkedList();
        Node previous = null;
        Node current = head;
        int index = 0;

        while (current != null) {
            if (index % 2 != 0) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                    if (current.getNext() == null) {
                        tail = previous; // Update tail if deleting the last node
                    }
                } else {
                    head = current.getNext();
                    if (head == null) {
                        tail = null; // Update tail if the only node is deleted
                    }
                }

                oddLinkedList.insertLast(current);
            }
            else {
                previous = current;
            }

            current = current.getNext();
            index++;
        }

        return oddLinkedList;
    }

    // Exercise 4:

    // Exercise 5:

    //Exercise 26:
    public void printReverse() {
        if (head == null) {
            return; // Empty list, nothing to print
        }

        Node current = head;
        Node previous = null;
        Node next = null;
        Node tail = null;

        // Move to the end of the list to find the tail
        while (current != null) {
            next = current.getNext();
            current.setNext(previous); // Reverse the link
            previous = current;
            current = next;
            if (next == null) {
                tail = previous; // Mark the tail node
            }
        }

        // Print the reversed list starting from the tail
        current = tail;
        while (current != null) {
            System.out.println(current.getData());
            next = current.getNext(); // Save the next node
            current.setNext(previous); // Reverse the link back
            previous = current;
            current = next;
        }

        // Restore the original list order
        current = previous;
        previous = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        // Update head and tail
        head = previous;
        tail = null;
    }


    // Exercise 27:
    public void addAfterEachNode(Node newNode) {
        Node current = head;

        while (current != null) {
            Node nextNode = current.getNext();
            current.setNext(newNode);
            newNode.setNext(nextNode);
            current = nextNode;
        }

        // Update tail if newNode is added at the end
        if (tail == null) {
            tail = newNode;
        }
    }



    // Exercise 28:
    public void printOddNodes() {
        Node current = head;
        int index = 1;

        while (current != null) {
            if (index % 2 != 0) {
                System.out.println(current.getData());
            }
            current = current.getNext();
            index++;
        }
    }


    // Exercise 29:
    public void deletePrimes() {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (isPrime(current.getData())) {
                // Delete the current node
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                if (current == tail) {
                    tail = previous; // Update tail if current is the last node
                }
                Node temp = current;
                current = current.getNext();
                temp.setNext(null); // Disconnect the node from the list
            } else {
                previous = current;
                current = current.getNext();
            }
        }
    }



    // Exercise 30:
    public void deleteBetween(int p, int q) {
        if (p >= q || head == null) {
            return; // Invalid indices or empty list
        }

        Node current = head;
        Node previous = null;
        Node nodeBeforeP = null;
        Node nodeAfterQ = null;
        int index = 0;

        // Find the nodes before and after the range to be deleted
        while (current != null) {
            if (index == p - 1) {
                nodeBeforeP = current;
            }
            if (index == q) {
                nodeAfterQ = current.getNext();
                break;
            }
            previous = current;
            current = current.getNext();
            index++;
        }

        // Check if the range is valid
        if (nodeBeforeP == null || nodeAfterQ == null) {
            return; // Invalid range
        }

        // Delete nodes between p and q
        Node tmp = nodeBeforeP.getNext();
        while (tmp != nodeAfterQ) {
            Node next = tmp.getNext();
            tmp.setNext(null); // Disconnect node from the list
            tmp = next;
        }

        // Connect the nodes before and after the deleted range
        nodeBeforeP.setNext(nodeAfterQ);
        if (nodeAfterQ == null) {
            tail = nodeBeforeP; // Update tail if q is the last node
        }
    }


    // Exercise 31:
    public void sieveOfEratosthenes(int N) {
        LinkedList numbers = new LinkedList();

        // Populate the linked list with numbers from 2 to N
        for (int i = 2; i <= N; i++) {
            numbers.insertLast(new Node(i));
        }

        Node current = numbers.getHead();
        while (current != null) {
            int p = current.getData();
            System.out.println(p); // Print the prime number

            Node next = current.getNext();
            while (next != null) {
                if (next.getData() % p == 0) {
                    // Remove multiples of p
                    Node temp = next;
                    next = next.getNext();
                    numbers.deleteValue(temp.getData());
                } else {
                    next = next.getNext();
                }
            }

            current = current.getNext(); // Move to the next number
        }
    }


    // Exercise 33:
    public boolean containsOnlyDuplicates() {
        if (head == null) {
            return true; // Empty list is considered to contain only duplicates
        }

        Node current = head;
        while (current != null) {
            Node runner = head;
            int count = 0;
            while (runner != null) {
                if (current.getData() == runner.getData()) {
                    count++;
                }
                runner = runner.getNext();
            }
            if (count != 2) {
                return false;
            }
            current = current.getNext();
        }

        return true;
    }



    // Exercise 34:
    public LinkedList primeDivisors(int N) {
        LinkedList divisors = new LinkedList();

        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                // Add the prime number to the list of divisors
                while (N % i == 0) {
                    divisors.insertLast(new Node(i));
                    N /= i;
                }
            }
        }
        return divisors;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    // Exercise 35:
    public Node lastOneWins(int k) {
        Node current = head;
        Node previous = tail; // Set previous to tail to make it circular
        int count = 1;

        while (current != null && current.getNext() != current) {
            if (count == k) {
                // Delete the current node
                previous.setNext(current.getNext());
                current = current.getNext();
                count = 1;
            } else {
                previous = current;
                current = current.getNext();
                count++;
            }
        }
        return current;
    }


    // Exercise 37:
    public boolean evenOddSorted() {
        Node oddCurrent = head;
        Node evenCurrent = head != null ? head.getNext() : null;
        // Check if the list has at least two nodes
        if (evenCurrent == null) {
            return true;
        }
        // Iterate over the list
        while (oddCurrent != null && evenCurrent != null) {
            // Check odd-indexed elements
            if (oddCurrent.getNext() != null && oddCurrent.getData() > oddCurrent.getNext().getData()) {
                return false;
            }
            if (oddCurrent.getNext() != null) {
                oddCurrent = oddCurrent.getNext().getNext();
            } else {
                oddCurrent = null;
            }
            // Check even-indexed elements
            if (evenCurrent.getNext() != null && evenCurrent.getData() < evenCurrent.getNext().getData()) {
                return false;
            }
            if (evenCurrent.getNext() != null) {
                evenCurrent = evenCurrent.getNext().getNext();
            } else {
                evenCurrent = null;
            }
        }
        // If the loop completes without returning false, the list is sorted
        return true;
    }






}