import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Main {
    private static final int MIN = -20;
    private static final int MAX = 20;
    private static final String OPERATIONS = "+-/*";
    private static final String FILE_NAME = "results.txt";

    public static void main(String[] args) {
        int first = getNumberFromConsole("first number");
        int second = getNumberFromConsole("second number");
        char operation = getArithmetical();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        writeToFile(dateTime, "Result = " + calculate(first, second, operation));
    }
    private static int getNumberFromConsole(String variableName) {
        String message = String.format("Please enter a %s between %d and %d: ", variableName, MIN, MAX );
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        int number = scanner.nextInt();
        while (!isNumberValid(number, MIN, MAX)) {
            System.out.println(message);
            number = scanner.nextInt();
        }
        return number;
    }
    private static boolean isNumberValid(int value, int min, int max) {
        return value <= max && value >= min;
    }
    private static char getArithmetical() {
        String message = "Enter the arithmetical operation: ";
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        char operation = scanner.next().charAt(0);
        while (!OPERATIONS.contains(String.valueOf(operation))) {
            System.out.println(message);
            operation = scanner.next().charAt(0);
        }
        return operation;
    }
    private static double calculate(int number1, int number2, char operation) {
        double result = 0;
        switch (operation) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                try {
                    result = number1 / number2;
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Error: Division by zero.");
                }
                break;
            default:
                System.out.println("Error: Invalid operation.");
        }
        return result;
    }
    private static void writeToFile(String dateTime, String result) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println(dateTime + ": " + result);
            System.out.printf("Result has been written to a file.", FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error has been written to a file: " + e.getMessage());
        }
    }
}