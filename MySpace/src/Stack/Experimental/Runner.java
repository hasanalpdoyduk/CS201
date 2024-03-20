package Stack.Experimental;

public class Runner {
    public static void main(String[] args) {
        Stack st = new Stack(5);
        st.push(2);
        st.push(4);
        st.push(4);
        st.push(4);
        st.push(4);
        System.out.println(st.pop());
        System.out.println(st.isFull());

    }
}
