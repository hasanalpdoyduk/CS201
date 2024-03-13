import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/input.txt");
            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter("src/output.txt");

            int numCases = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < numCases; i++) {
                if (scanner.hasNextLine()) {
                    String polynomial1String = scanner.nextLine().trim();
                    if (scanner.hasNextLine()) {
                        String polynomial2String = scanner.nextLine().trim();
                        if (scanner.hasNextLine()) {
                            char operation = scanner.nextLine().charAt(0);

                            Polynomial polynomial1 = parsePolynomial(polynomial1String);
                            Polynomial polynomial2 = parsePolynomial(polynomial2String);

                            Polynomial result = null;
                            switch (operation) {
                                case '+':
                                    result = polynomial1.add(polynomial2);
                                    break;
                                case '-':
                                    result = polynomial1.subtract(polynomial2);
                                    break;
                                case '*':
                                    result = polynomial1.multiply(polynomial2);
                                    break;
                            }

                            writer.println(result != null ? result.toString() : "Invalid operation");
                        }
                    }
                }
            }


            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Polynomial parsePolynomial(String input) {
        Polynomial polynomial = new Polynomial();
        input = input.replaceAll("\\s", ""); // Remove all whitespace
        int index = 0;
        while (index < input.length()) {
            int sign = 1;
            if (input.charAt(index) == '+') {
                index++;
            } else if (input.charAt(index) == '-') {
                sign = -1;
                index++;
            }
            int coefficient = 0;
            if (Character.isDigit(input.charAt(index))) {
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    coefficient = coefficient * 10 + Character.getNumericValue(input.charAt(index));
                    index++;
                }
            } else {
                coefficient = 1;
            }
            int xExponent = 0;
            int yExponent = 0;
            int zExponent = 0;
            if (index < input.length() && input.charAt(index) == 'x') {
                index++;
                if (index < input.length() && input.charAt(index) == '^') {
                    index++;
                    StringBuilder exponentBuilder = new StringBuilder();
                    while (index < input.length() && Character.isDigit(input.charAt(index))) {
                        exponentBuilder.append(input.charAt(index));
                        index++;
                    }
                    xExponent = Integer.parseInt(exponentBuilder.toString());
                } else {
                    xExponent = 1;
                }
            }
            if (index < input.length() && input.charAt(index) == 'y') {
                index++;
                if (index < input.length() && input.charAt(index) == '^') {
                    index++;
                    StringBuilder exponentBuilder = new StringBuilder();
                    while (index < input.length() && Character.isDigit(input.charAt(index))) {
                        exponentBuilder.append(input.charAt(index));
                        index++;
                    }
                    yExponent = Integer.parseInt(exponentBuilder.toString());
                } else {
                    yExponent = 1;
                }
            }
            if (index < input.length() && input.charAt(index) == 'z') {
                index++;
                if (index < input.length() && input.charAt(index) == '^') {
                    index++;
                    StringBuilder exponentBuilder = new StringBuilder();
                    while (index < input.length() && Character.isDigit(input.charAt(index))) {
                        exponentBuilder.append(input.charAt(index));
                        index++;
                    }
                    zExponent = Integer.parseInt(exponentBuilder.toString());
                } else {
                    zExponent = 1;
                }
            }
            polynomial.insertTerm(sign * coefficient, xExponent, yExponent, zExponent);
        }
        return polynomial;
    }


}
