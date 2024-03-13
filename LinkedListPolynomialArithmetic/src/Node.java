public class Node {
    int coefficient; //coefficient of a term of the polynomial
    //if a variable do not exist in the polynomial its exponent should be 0
    int xExponent;  //exponent value for x variable
    int yExponent;  //exponent value for y variable
    int zExponent;  //exponent value for z variable
    Node next; //link to the next node

    //constructor for node
    public Node(int coefficient, int xExponent, int yExponent, int zExponent) {
        this.coefficient = coefficient;
        this.xExponent = xExponent;
        this.yExponent = yExponent;
        this.zExponent = zExponent;
        this.next = null;  //set next to null
    }

    //method for converting the node to string
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (coefficient != 0) {  //do not include non-zero coefficient terms
            if (coefficient < 0) {
                builder.append("-"); //add minus sign for negative coefficient terms
            } else {
                builder.append("+");  //add plus sign for positive coefficient terms
            }
            builder.append(Math.abs(coefficient));  //add absolute value of the coefficient

            //add x variable if coefficient of x is not 0
            if (xExponent != 0) {
                builder.append("x");
                if (xExponent != 1) {
                    builder.append(xExponent);
                }
            }
            //add y variable if coefficient of x is not 0
            if (yExponent != 0) {
                builder.append("y");
                if (yExponent != 1) {
                    builder.append(yExponent);
                }
            }
            //add z variable if coefficient of x is not 0
            if (zExponent != 0) {
                builder.append("z");
                if (zExponent != 1) {
                    builder.append(zExponent);
                }
            }
        }
        return builder.toString();  //return sting form of the node
    }
}
