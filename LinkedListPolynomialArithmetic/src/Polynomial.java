public class Polynomial {
    private Node head;

    public Polynomial() {
        this.head = null;
    }

    public void insertTerm(int coefficient, int xExponent, int yExponent, int zExponent) {
        Node newTerm = new Node(coefficient, xExponent, yExponent, zExponent);
        if (head == null) {
            head = newTerm;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTerm;
        }
    }

    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();
        Node current1 = this.head;
        Node current2 = other.head;
        while (current1 != null && current2 != null) {
            if (current1.xExponent > current2.xExponent ||
                    (current1.xExponent == current2.xExponent && current1.yExponent > current2.yExponent) ||
                    (current1.xExponent == current2.xExponent && current1.yExponent == current2.yExponent && current1.zExponent > current2.zExponent)) {
                result.insertTerm(current1.coefficient, current1.xExponent, current1.yExponent, current1.zExponent);
                current1 = current1.next;
            } else if (current1.xExponent < current2.xExponent ||
                    (current1.xExponent == current2.xExponent && current1.yExponent < current2.yExponent) ||
                    (current1.xExponent == current2.xExponent && current1.yExponent == current2.yExponent && current1.zExponent < current2.zExponent)) {
                result.insertTerm(current2.coefficient, current2.xExponent, current2.yExponent, current2.zExponent);
                current2 = current2.next;
            } else {
                int newCoefficient = current1.coefficient + current2.coefficient;
                if (newCoefficient != 0) {
                    result.insertTerm(newCoefficient, current1.xExponent, current1.yExponent, current1.zExponent);
                }
                current1 = current1.next;
                current2 = current2.next;
            }
        }
        while (current1 != null) {
            result.insertTerm(current1.coefficient, current1.xExponent, current1.yExponent, current1.zExponent);
            current1 = current1.next;
        }
        while (current2 != null) {
            result.insertTerm(current2.coefficient, current2.xExponent, current2.yExponent, current2.zExponent);
            current2 = current2.next;
        }
        return result;
    }

    public Polynomial subtract(Polynomial other) {
        Polynomial negatedOther = new Polynomial();
        Node current = other.head;
        while (current != null) {
            negatedOther.insertTerm(-current.coefficient, current.xExponent, current.yExponent, current.zExponent);
            current = current.next;
        }
        return this.add(negatedOther);
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        Node current1 = this.head;
        while (current1 != null) {
            Node current2 = other.head;
            while (current2 != null) {
                int newCoefficient = current1.coefficient * current2.coefficient;
                int newXExponent = current1.xExponent + current2.xExponent;
                int newYExponent = current1.yExponent + current2.yExponent;
                int newZExponent = current1.zExponent + current2.zExponent;
                result.insertTerm(newCoefficient, newXExponent, newYExponent, newZExponent);
                current2 = current2.next;
            }
            current1 = current1.next;
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.toString());
            current = current.next;
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }
}
