package Stack.LinkedListImplementation;

public class Stack {
    Node top;

    public Stack(){
        top = null;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public Node peek(){
        return top;
    }

    public void push(Node node){
        node.setNext(top);
        top = node;
    }

    public Node pop(){
        Node topNode = top;
        top = top.next;
        return topNode;
    }




    
    // Methods for Questions


    // Exercise 17:
    public Node pop(int k){
        if(k < 1){
            throw new IllegalArgumentException("Invalid index");
        }

        Node current = top;
        Node prev = null;
        int count = 1;

        while (current != null && count < k) {
            prev = current;
            current = current.next;
            count++;
        }

        if(current == null){
            throw new IllegalArgumentException("Invalid index");
        }

        if(prev == null){
            top = top.next;
        } else {
            prev.next = current.next;
        }

        return current;
    }
}
