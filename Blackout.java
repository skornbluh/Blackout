import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Blackout {

	private static String equation;
	public static String parsedEquation = "";
	public static String blackedOutEquation;
	private static char firstBlackoutCharacter;
	private static char secondBlackoutCharacter;
	private static double total = 0;

	// this black outs numbers
	public static String blackoutNumbers(String equation) {
		blackedOutEquation = null;

		for (int i = 0; i < equation.length(); i++) {
			firstBlackoutCharacter = equation.charAt(i);
			for (int j = i + 1; j < equation.length(); j++) {
				secondBlackoutCharacter = equation.charAt(j);

				blackedOutEquation = equation.substring(0, i) + equation.substring(i + 1, j)
						+ equation.substring(j + 1, equation.length());

				boolean result = isValidExpression(blackedOutEquation);

				if (result == true) {
					boolean result2 = parseExpression(blackedOutEquation);
					if (result2 == true) {
						System.out.println("Blacked out: " + firstBlackoutCharacter + ", " + secondBlackoutCharacter);
						System.out.println("Resulting equations: " + blackedOutEquation);
						System.out.println("Value: " + total);

					}
				}

			}

		}

		return blackedOutEquation;

	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String equation = scan.nextLine();

		blackoutNumbers(equation);

	}

	private static double evaluate(String string) {

		// Get all of the numbers out of whichever side of the equation is being
		// evaluated
		String[] expressionNumbers = string.split("[*+-/%]");

		// This variable holds the running total as we loop through the equation
		// and do the calculations
		total = 0;
		int operatorsFoundCounter = 0;

		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			// If the character is an operator, then we want to do a calculation
			if (isOperator(character)) {

				int firstNumber = Integer.parseInt(expressionNumbers[operatorsFoundCounter]);
				int secondNumber = Integer.parseInt(expressionNumbers[operatorsFoundCounter + 1]);

				// Increment the counter every time we find an operator
				operatorsFoundCounter++;

				int result = 0;
				switch (character) {
				case '+':
					result += firstNumber + secondNumber;
					break;
				case '-':
					result += firstNumber - secondNumber;
					break;
				case '/':
					result += firstNumber / secondNumber;
					break;
				case '*':
					result += firstNumber * secondNumber;
					break;
				case '%':
					result += firstNumber % secondNumber;
					break;
				default:
					throw new RuntimeException("unkonwn operator: " + character);
				}

				expressionNumbers[operatorsFoundCounter] = Integer.toString(result);
				// add the result of this operation to the running total
				total = result;
			}
		}

		// If you never find any operators, then the string must be the result

		if (operatorsFoundCounter == 0) {
			total = Integer.parseInt(string);
		}

		return total;

	}

	private static boolean isOperator(char symbol) {
		if (symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '%' || symbol == '=') {
			return true;
		}
		return false;
	}

	private static boolean isValidExpression(String equation) {
		// ensure that there is one equal sign
		int count = 0;
		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == '=') {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			return false;
		}
		// ensure that we do not have two or more consecutive operators
		for (int i = 0; i < equation.length() - 1; i++) {
			if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i + 1))) {
				return false;
			}
		}

		// ensures that equation does not start or end with an operator
		if (isOperator(equation.charAt(0)) || isOperator(equation.charAt(equation.length() - 1))) {
			return false;
		}

		return true;
	}

	private static boolean parseExpression(String equation) {
		String[] expression = equation.split("=");

		double answer1 = evaluate(expression[0]);
		double answer2 = evaluate(expression[1]);

		if (answer1 == answer2) {
			return true;
		} else {
			return false;
		}

	}

}
