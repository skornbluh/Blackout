import java.io.BufferedReader;
import java.io.FileReader;

public class Blackout {
	public static double answer = 0.0;
	public static String parsedEquation = "";
	
	public static String getData(String fileName) {
		String data = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String line = "";
			do {
				line = br.readLine();
				if (line != null) {
					sb.append(line);
				}
			} while (line != null);
			data = sb.toString();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} 
		return data;
	}
	
	/**
	 * makes sure equation is valid, evaluates equation and stores the answer as a class variable
	 * @param equation
	 * @return the valid expression as a string, or empty string if expression is invalid
	 */
	private static boolean parseExpression(String equation) {
		char[] charArray = equation.toCharArray();
		boolean isValid = isValidExpression(equation);
		double answer1 = 0.0, answer2 = 0.0;
		String[] expr = equation.split("=");
		for (int i = 0; i < equation.length(); i++) {
			answer1 = evaluate(expr[0]);
			answer2 = evaluate(expr[1]);
		}	
		return answer1 == answer2;
	}

	
	private static int evaluate(String string) {
		
		return 0;
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
		// valid operators are: +-*/%
		for (int i = 0; i < equation.length() - 1; i++) {
			if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i+1))) {
				return false;
			}
		}
		
		return true;
	}

	private static boolean isOperator(char symbol) {
		if (symbol == '+' || symbol == '-'|| symbol == '*'|| symbol == '/'|| symbol == '%') {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		String equation = getData("test_blackout_01.txt");
		System.out.println("Equation is: " + equation);
		if (parseExpression(equation)) {
			System.out.println("Equation " + parsedEquation + " is invalid.");
		} else {
			System.out.println("Equation " + parsedEquation + " is valid.");
		}

	}

}
