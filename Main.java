public class Main {
    public static void main(String[] args) {

        System.out.println("number1 is 200000000000000000000000000 and number2 is 10000000");
        System.out.println("");
        String a = "1";
        for(int i = 0; i<10000; i++){
            a +="0";
        }

        LargeNumber number1 = new LargeNumber(a);
        LargeNumber number2 = new LargeNumber("10000000");
        System.out.println("Addition (number1 + 327) : " + number1.add(new LargeNumber("327")));

        LargeNumber additionResult = number1.add(number2);
        System.out.println("Addition (number1 + number2) : " + additionResult);


        System.out.println("Subtraction (number1-number2) : " + number1.subtract(number2));

        System.out.println("Multiplication (number1 x number2) : " + number1.multiply(number2).toString());
        System.out.println("Multiplication (number1 x 327) : " + number1.multiply(new LargeNumber("327")));

        LargeNumber num1 = new LargeNumber("200000017");
        LargeNumber result = num1.divide(8);
        System.out.println("Division: " + result);


    }
}


