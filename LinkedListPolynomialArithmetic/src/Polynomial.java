public class Polynomial {
    private Node head;  //the head of the polynomial

    //constructor for empty polynomial
    public Polynomial() {
        this.head = null;
    }

    //method for inserting a new term
    public void insertTerm(int coefficient, int xExponent, int yExponent, int zExponent) {
        Node newTerm = new Node(coefficient, xExponent, yExponent, zExponent);  //create a new node for the new term
        if (head == null) {  //if the polynomial is empty set the new term as head
            head = newTerm;
        } else {  //if the polynomial is not empty insert the new term to the end
            Node current = head;
            while (current.next != null) {  //find the last term of the polynomial
                current = current.next;
            }
            current.next = newTerm;  //insert the new term
        }
    }

    //method for adding polynomials
    public Polynomial add(Polynomial otherpolynomial) {
        Polynomial result = new Polynomial();  //create a new polynomial for the result
        Node current1 = this.head;  //start at the head of the first polynomial
        Node current2 = otherpolynomial.head;  //start at the head of the second polynomial

        // Iterate over both polynomials until one of them reaches the end
        while (current1 != null && current2 != null) {
            // Compare the exponents of the current terms to determine the order
            if (current1.xExponent > current2.xExponent ||
                    (current1.xExponent == current2.xExponent && current1.yExponent > current2.yExponent) ||
                    (current1.xExponent == current2.xExponent && current1.yExponent == current2.yExponent && current1.zExponent > current2.zExponent)) {
                // Add the term from the first polynomial to the result
                result.insertTerm(current1.coefficient, current1.xExponent, current1.yExponent, current1.zExponent);
                current1 = current1.next; // Move to the next term in the first polynomial
            } else if (current1.xExponent < current2.xExponent ||
                    (current1.xExponent == current2.xExponent && current1.yExponent < current2.yExponent) ||
                    (current1.xExponent == current2.xExponent && current1.yExponent == current2.yExponent && current1.zExponent < current2.zExponent)) {
                // Add the term from the second polynomial to the result
                result.insertTerm(current2.coefficient, current2.xExponent, current2.yExponent, current2.zExponent);
                current2 = current2.next; // Move to the next term in the second polynomial
            } else {
                // Add the coefficients if the exponents are the same
                int newCoefficient = current1.coefficient + current2.coefficient;
                if (newCoefficient != 0) {
                    // Insert the new term into the result if the coefficient is not zero
                    result.insertTerm(newCoefficient, current1.xExponent, current1.yExponent, current1.zExponent);
                }
                current1 = current1.next; // Move to the next term in the first polynomial
                current2 = current2.next; // Move to the next term in the second polynomial
            }
        }

        // Append the remaining terms from the first polynomial, if any
        while (current1 != null) {
            result.insertTerm(current1.coefficient, current1.xExponent, current1.yExponent, current1.zExponent);
            current1 = current1.next;
        }

        // Append the remaining terms from the second polynomial, if any
        while (current2 != null) {
            result.insertTerm(current2.coefficient, current2.xExponent, current2.yExponent, current2.zExponent);
            current2 = current2.next;
        }

        return result; // Return the result polynomial
    }

    // Method to subtract one polynomial from another
    public Polynomial subtract(Polynomial other) {
        // To subtract a polynomial, negate the coefficients of the terms in the other polynomial
        Polynomial negatedOther = new Polynomial();
        Node current = other.head;
        while (current != null) {
            negatedOther.insertTerm(-current.coefficient, current.xExponent, current.yExponent, current.zExponent);
            current = current.next;
        }
        // Subtracting is equivalent to adding the negated polynomial
        return this.add(negatedOther);
    }

    //method for multiplying polynomials
    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial(); // Create a new polynomial to store the result
        Node current1 = this.head; // Start at the head of the first polynomial
        while (current1 != null) {
            Node current2 = other.head; // Start at the head of the second polynomial
            while (current2 != null) {
                // Multiply the coefficients and add the exponents to get the new term
                int newCoefficient = current1.coefficient * current2.coefficient;
                int newXExponent = current1.xExponent + current2.xExponent;
                int newYExponent = current1.yExponent + current2.yExponent;
                int newZExponent = current1.zExponent + current2.zExponent;
                // Insert the new term into the result polynomial
                result.insertTerm(newCoefficient, newXExponent, newYExponent, newZExponent);
                current2 = current2.next;  //move to the next term for second polynomial
            }
            current1 = current1.next;  //move to the next term for first polynomial
        }
        return result; //return the result
    }

    //method for converting the polynomial to string
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = head;
        while (current != null) {
            builder.append(current.toString());  //add the string form of the current term
            current = current.next;  //move to the next term
        }
        if (builder.isEmpty()) {
            return "0";  //return "0" if the polynomial is empty
        }
        return builder.toString();  //return the final string form of the polynomial
    }
}

