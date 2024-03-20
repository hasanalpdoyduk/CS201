package Stack.ArrayImplementation;

public class Stack {
    Element array[];
    int top;
    int N;

    public Stack(int N){
        array = new Element[N];
        this.N = N;
        top = -1;
    }

    Element top(){
        return array[top];
    }

    boolean isFull(){
        if(top == N - 1)
            return true;
        else
            return false;
    }

    boolean isEmpty(){
        if(top == -1)
            return true;
        else
            return false;
    }

    void push(Element element){
        if(!isFull()){
            top++;
            array[top] = element;
        }
    }

    Element pop(){
        if (!isEmpty()){
            top--;
            return array[top + 1];
        }
        return null;
    }

    
    Element peek(){
        return array[top];
    }




    
    // Methods for Questions

    /*
    In order to use it
    You should initializ a variable maxSize in top lines and you should write it in constructor
    // Exercise 16:
    public void removeOddIndexed() {
        Stack tempStack = new Stack(maxSize);
        boolean isOdd = true;

        while (!isEmpty()) {
            int element = pop();
            if (isOdd) {
                tempStack.push(element);
            }
            isOdd = !isOdd;
        }

        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }
    }
     */


    // Exercise 17:
    Element pop(int k){
        if (k < 1 || k > top + 1){
            throw new IllegalArgumentException("Invalid index");
        }
        Element removedElement = array[top - k + 1];

        for (int i = top - k + 1; i < top; i++){
            array[i] = array[i + 1];
        }

        top--;

        return removedElement;
    }

}

