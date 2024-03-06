public class LargeNumber {
    private LinkedList largeNumber;

    public LargeNumber(String numStr) {
        this.largeNumber = stringToLinkedList(numStr);
    }

    /**
     * This method adds two different large numbers up
     * @param other the second large number that we want to add
     * @return LargeNumber value the addition result
     */
    public LargeNumber add(LargeNumber other) {    //This method adds two different large numbers up and returns a result as a LargeNumber object.
        LinkedList num2 = other.largeNumber;

        LinkedList result = new LinkedList();

        int carry = 0;

        int i = largeNumber.numberOfElements()-1;
        int j = num2.numberOfElements()-1;

        while(i>=0 || j>=0 || carry!=0) {
            int sum = carry;

            if(i >= 0) {
                sum += largeNumber.getNodeI(i).getData();
                i--;
            }

            if(j >= 0) {
                sum += num2.getNodeI(j).getData();
                j--;
            }

            result.insertFirst(new Node(sum % 10));
            carry = sum/10;
        }

        return linkedListToLargeNumber(result);  //Since variable result is a LinkedList, the linkedListToLargeNumber method is used.
    }

    public LargeNumber subtract(LargeNumber other) {  //This method does the subtraction. Second large number(num2) should be smaller.
        LinkedList num2 = other.largeNumber;

        LinkedList result = new LinkedList();

        int borrow = 0;

        int i = largeNumber.numberOfElements()-1;
        int j = num2.numberOfElements()-1;

        while(i>=0 || j>=0 || borrow!=0) {
            int difference = borrow;

            if(i>=0) {
                difference += largeNumber.getNodeI(i).getData();
                i--;
            }
            if(j>=0) {
                difference -= num2.getNodeI(j).getData();
                j--;
            }
            if(difference < 0) {
                difference += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }

            result.insertFirst(new Node(difference));
        }

        return linkedListToLargeNumber(result);  //Since variable result is a LinkedList, the linkedListToLargeNumber method is used.
    }

    public LargeNumber multiply(LargeNumber other) {  //This method does the multiplication. Second number(num2) should be smaller.
        LinkedList num2 = other.largeNumber;
        LargeNumber result = new LargeNumber("0");

        for(int i = num2.numberOfElements()-1; i>=0; i--) {
            int digit2 = num2.getNodeI(i).getData();
            LinkedList partialResult = new LinkedList();
            int carry = 0;

            for(int j = largeNumber.numberOfElements()-1; j>=0; j--) {
                int digit1 = largeNumber.getNodeI(j).getData();
                int product = digit1 * digit2 + carry;
                partialResult.insertFirst(new Node(product % 10));
                carry = product / 10;
            }
            if(carry > 0) {
                partialResult.insertFirst(new Node(carry));
            }
            for(int k = num2.numberOfElements()-1; k > i; k--) {
                partialResult.insertLast(new Node(0));
            }

            result = result.add(linkedListToLargeNumber(partialResult));
        }
        return result;
    }

    public LargeNumber divide(int divisor) {
        LinkedList quotient = new LinkedList();
        int carry = 0;

        Node current = largeNumber.getHead();
        while(current != null) {
            int currentDigit = current.getData() + carry*10;
            int currentQuotient = currentDigit / divisor;
            carry = currentDigit % divisor;
            if(!(quotient.getHead() == null && currentQuotient == 0)) {
                quotient.insertLast(new Node(currentQuotient));
            }
            current = current.getNext();
        }
        if(quotient.getHead() == null) {
            quotient.insertLast(new Node(0));
        }
        return linkedListToLargeNumber(quotient);
    }
    /**
     *
     * @param numberAsString the number as in String format
     * @return LinkedList
     */
    private LinkedList stringToLinkedList(String numberAsString) {  //This method is for my LargeNumber constructor. It takes String and converts it to a LinkedList.

        LinkedList result = new LinkedList();

        for(int i = 0; i < numberAsString.length(); i++) {
            char digitChar = numberAsString.charAt(i);

            int digit = Character.getNumericValue(digitChar);
            result.insertLast(new Node(digit));
        }
        return result;
    }

    private LargeNumber linkedListToLargeNumber(LinkedList linkedList) {  //This method takes a LinkedList and converts it to a LargeNumber object.
        //Because my methods take some variables as LinkedLists but their return type is LargeNumber.
        StringBuilder result = new StringBuilder();

        Node currentNode = linkedList.getHead();

        while(currentNode != null) {
            result.append(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        return new LargeNumber(result.toString());
    }

    public String toString() {
        StringBuilder resultStr = new StringBuilder();

        for(Node node = largeNumber.getHead(); node!=null; node = node.getNext()) {
            resultStr.append(node.getData());
        }
        return resultStr.toString();
    }
    public int findLetterCount(String text,String letter) {
        int length= text.length();
        String textUpper= text.toUpperCase();
        String letterUpper= letter.toUpperCase();
        int count=0;
        for(int i=0;i<length;i++) {
            if(textUpper.substring(i, i+1)==letterUpper) {
                count++;
            }
        }
        return count;
    }
}