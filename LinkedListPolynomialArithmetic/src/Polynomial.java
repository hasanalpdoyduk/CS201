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

    // Method to add two polynomials
    public Polynomial add(Polynomial otherpolynomial) {
        Polynomial result = new Polynomial();  // Create a new polynomial for the result
        Node current1 = this.head;  // Head of the current polynomial
        Node current2 = otherpolynomial.head;  // Head of the other polynomial

        // Iterate over both polynomials until one of them reaches the end
        while (current1 != null && current2 != null) {
            // Compare the exponents of the current terms
            int comparison = compareExponents(current1, current2);

            if (comparison < 0) {
                // Add the term from the current polynomial to the result
                addTermToResult(result, current1.coefficient, current1.xExponent, current1.yExponent, current1.zExponent);
                current1 = current1.next;  // Move to the next term in the current polynomial
            } else if (comparison > 0) {
                // Add the term from the other polynomial to the result
                addTermToResult(result, current2.coefficient, current2.xExponent, current2.yExponent, current2.zExponent);
                current2 = current2.next;  // Move to the next term in the other polynomial
            } else {
                // Add the coefficients if the exponents are the same
                int newCoefficient = current1.coefficient + current2.coefficient;
                if (newCoefficient != 0) {
                    // Insert the new term into the result if the coefficient is not zero
                    addTermToResult(result, newCoefficient, current1.xExponent, current1.yExponent, current1.zExponent);
                }
                current1 = current1.next;  // Move to the next term in the current polynomial
                current2 = current2.next;  // Move to the next term in the other polynomial
            }
        }

        // Append the remaining terms from the first polynomial, if any
        addRemainingTerms(result, current1);

        // Append the remaining terms from the second polynomial, if any
        addRemainingTerms(result, current2);

        return result; // Return the result polynomial
    }

    // Method to compare the exponents of two nodes
    private int compareExponents(Node node1, Node node2) {
        if (node1.xExponent != node2.xExponent) {
            return Integer.compare(node1.xExponent, node2.xExponent);
        } else if (node1.yExponent != node2.yExponent) {
            return Integer.compare(node1.yExponent, node2.yExponent);
        } else {
            return Integer.compare(node1.zExponent, node2.zExponent);
        }
    }

    // Method to add a term to the result polynomial
    private void addTermToResult(Polynomial result, int coefficient, int xExponent, int yExponent, int zExponent) {
        result.insertTerm(coefficient, xExponent, yExponent, zExponent);
    }

    // Method to add the remaining terms from a polynomial to the result
    private void addRemainingTerms(Polynomial result, Node current) {
        while (current != null) {
            addTermToResult(result, current.coefficient, current.xExponent, current.yExponent, current.zExponent);
            current = current.next;
        }
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

