package Stack.Experimental;

public class Stack {
    int[] stack;
    int top;
    int N;

    public Stack(int N){
        stack = new int[N];
        this.N = N;
        top = -1;
    }

    public boolean isFull(){
        if(top == N - 1) {
            return true;
        } else
            return false;
    }

    public boolean isEmpty(){
        if(top == -1)
            return true;
        else
            return false;
    }

    public void push(int data){
        if(isFull()){
            System.out.println("Stack is full.");
        }
        else{
            top++;
            stack[top] = data;
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int data;
            top--;
            data = stack[top];
            stack[top] = 0;
            return data;
        }
        return 0;
    }
}
