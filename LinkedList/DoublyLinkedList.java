package LinkedList;

public class DoublyLinkedList extends LinkedList{

    public void insertFirst(DoublyNode newNode) {
        if (tail == null) {
            tail = newNode;
        } else {
            ((DoublyNode) head).setPrevious(newNode);
        }
        newNode.setNext(head);
        head = newNode;
    }

    public void insertMiddle(DoublyNode newNode, DoublyNode previous) {
        newNode.setNext(previous.getNext());
        newNode.setPrevious(previous);
        ((DoublyNode) previous.getNext()).setPrevious(newNode);
        previous.setNext(newNode);
    }

    public void insertLast(DoublyNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        newNode.setPrevious((DoublyNode) tail);
        tail = newNode;
    }

    public void deleteFirst(){
        head = head.getNext();
        if (head == null){
            tail = null;
        } else {
            ((DoublyNode)head).setPrevious(null);
        }
    }

    public void deleteMiddle(DoublyNode node){
        ((DoublyNode) node.getNext()).setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
    }

    public void deleteLast(){
        tail = ((DoublyNode)tail).getPrevious();
        if (tail != null){
            tail.setNext(null);
        } else {
            head = null;
        }
    }





    // Methods for Questions

    // Exercise 32:
    public DoublyLinkedList getEvenOnes() {
        DoublyLinkedList evenList = new DoublyLinkedList();
        DoublyNode current = (DoublyNode) head;
        int index = 1;

        while (current != null) {
            if (index % 2 == 0) {
                evenList.insertLast(new DoublyNode(current.getData()));
            }
            current = (DoublyNode) current.getNext();
            index++;
        }

        return evenList;
    }

    // Exercise 36:
    public DoublyLinkedList sortElements() {
        DoublyLinkedList sortedList = new DoublyLinkedList();

        // Find the largest number N in the linked list
        int largestNumber = Integer.MIN_VALUE;
        DoublyNode current = (DoublyNode) head;
        while (current != null) {
            if (current.getData() > largestNumber) {
                largestNumber = current.getData();
            }
            current = (DoublyNode) current.getNext();
        }

        // Count the number of times each number i appears in the linked list
        int[] count = new int[largestNumber + 1];
        current = (DoublyNode) head;
        while (current != null) {
            count[current.getData()]++;
            current = (DoublyNode) current.getNext();
        }

        // Insert numbers into the new linked list based on the count
        for (int i = 1; i <= largestNumber; i++) {
            for (int j = 0; j < count[i]; j++) {
                sortedList.insertLast(new DoublyNode(i));
            }
        }

        return sortedList;
    }

}