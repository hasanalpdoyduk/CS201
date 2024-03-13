public class Node {
    int coefficient;
    int xExponent;
    int yExponent;
    int zExponent;
    Node next;

    public Node(int coefficient, int xExponent, int yExponent, int zExponent) {
        this.coefficient = coefficient;
        this.xExponent = xExponent;
        this.yExponent = yExponent;
        this.zExponent = zExponent;
        this.next = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coefficient != 0) {
            if (coefficient < 0) {
                sb.append("-");
            } else {
                sb.append("+");
            }
            sb.append(Math.abs(coefficient));
            if (xExponent != 0) {
                sb.append("x");
                if (xExponent != 1) {
                    sb.append(xExponent);
                }
            }
            if (yExponent != 0) {
                sb.append("y");
                if (yExponent != 1) {
                    sb.append(yExponent);
                }
            }
            if (zExponent != 0) {
                sb.append("z");
                if (zExponent != 1) {
                    sb.append(zExponent);
                }
            }
        }
        return sb.toString();
    }
}
