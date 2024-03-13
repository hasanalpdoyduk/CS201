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

            // Check for missing coefficient
            int coefficient = 0;
            if (Character.isDigit(input.charAt(index))) {
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    coefficient = coefficient * 10 + Character.getNumericValue(input.charAt(index));
                    index++;
                }
            } else {
                coefficient = sign;  // If no coefficient, use the sign as coefficient (e.g., x becomes 1x)
            }

            int xExponent = 0;
            int yExponent = 0;
            int zExponent = 0;

            // Handle variables without explicit exponents
            if (index < input.length()) {
                char var = input.charAt(index);
                if (var == 'x') {
                    xExponent = 1;  // If no exponent for x, assume x^1
                    index++;
                    if (index < input.length() && input.charAt(index) == '^') {
                        index++;
                        StringBuilder exponentBuilder = new StringBuilder();
                        while (index < input.length() && Character.isDigit(input.charAt(index))) {
                            exponentBuilder.append(input.charAt(index));
                            index++;
                        }
                        xExponent = Integer.parseInt(exponentBuilder.toString());
                    }
                } else if (var == 'y') {
                    yExponent = 1;  // If no exponent for y, assume y^1
                    index++;
                    if (index < input.length() && input.charAt(index) == '^') {
                        index++;
                        StringBuilder exponentBuilder = new StringBuilder();
                        while (index < input.length() && Character.isDigit(input.charAt(index))) {
                            exponentBuilder.append(input.charAt(index));
                            index++;
                        }
                        yExponent = Integer.parseInt(exponentBuilder.toString());
                    }
                } else if (var == 'z') {
                    zExponent = 1;  // If no exponent for z, assume z^1
                    index++;
                    if (index < input.length() && input.charAt(index) == '^') {
                        index++;
                        StringBuilder exponentBuilder = new StringBuilder();
                        while (index < input.length() && Character.isDigit(input.charAt(index))) {
                            exponentBuilder.append(input.charAt(index));
                            index++;
                        }
                        zExponent = Integer.parseInt(exponentBuilder.toString());
                    }
                }
            }

            polynomial.insertTerm(coefficient, xExponent, yExponent, zExponent);
        }
        return polynomial;
    }

}
