package Stack.ArrayImplementation;

public class Runner {
    public static void main(String[] args) {
        Element a;
        Stack c;
        c = new Stack(5);
        a = new Element(4);
        c.push(a);
        System.out.println(c);
    }
}