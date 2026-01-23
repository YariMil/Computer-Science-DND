public class Arithmetic {

	// Evaluates a String exp that has an arithmetic expression, written in classic notation
	public static int evaluate(String exp) {
		// AAAAAAAAAAAAAAA
		// ok
		/*
		 * So We go through string each number we add on automatically operations we add to stack in
		 * an increasing importance thingy if thing alredy here > thing added: GO
		 */
		String stout = convertClassicToStout(exp);
		return evaluateStout(stout);
	}

	// Returns the result of doing operand1 operation operand2,
	// e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation.equals("+")) {
			return operand1 + operand2;
		} else if (operation.equals("-")) {
			return operand1 - operand2;
		} else if (operation.equals("*")) {
			return operand1 * operand2;
		} else if (operation.equals("/")) {
			return operand1 / operand2;
		} else if (operation.equals("^")) {
			return (int) (Math.pow(operand1, operand2));
		} else if (operation.equals("%")) {
			return (int) operand1 % operand2;
		}
		return 0;
	}

	// Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		String[] evaluationsToDo = exp.split(" ");
		MyStack<Integer> evalStack = new MyStack<Integer>();
		for (String str : evaluationsToDo) {
			try {
				evalStack.push(Integer.parseInt(str));
			} catch (Exception e) {
				// A little weird assigning, but the one currently on the stack is our operand 2.
				// The one below is operand 1.
				Integer operand2 = evalStack.peek();
				evalStack.pop();
				Integer operand1 = evalStack.peek();
				evalStack.pop();
				evalStack.push(operate(operand1, operand2, str));
			}
		}
		return evalStack.peek();
	}

	public static String convertClassicToStout(String exp) {
		MyStack<String> operatorStack = new MyStack<String>();
		StringBuilder stoutNotation = new StringBuilder();
		String[] operations = exp.split(" ");
		for (int i = 0; i < operations.length; i++) {
			try {
				stoutNotation.append(Integer.parseInt(operations[i]) + " ");
			} catch (Exception e) {
				// We have an operator on our hands
				// if thing already here >= thing added: GO
				if (operatorStack.empty()) {
					operatorStack.push(operations[i]);

				} else if (operations[i].equals(")")) {
					while (!operatorStack.peek().equals("(")) {
						stoutNotation.append(operatorStack.peek() + " ");
						operatorStack.pop();
					}
					operatorStack.pop();
				} else {
					if (compareStackToPush(operatorStack.peek(), operations[i])
							&& !operatorStack.peek().equals("(")) {
						// We shift the stack around
						stoutNotation.append(operatorStack.peek() + " ");
						operatorStack.pop();
					}
					operatorStack.push(operations[i]);
				}
			}
		}
		while (!operatorStack.empty()) {
			stoutNotation.append(operatorStack.peek());
			operatorStack.pop();
		}
		return stoutNotation.toString();
	}

	public static String autoFormatSpaces(String str) {
		StringBuilder autoFormat = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				autoFormat.append(str.charAt(i));
				autoFormat.append(' ');
			}
		}
		return autoFormat.toString();
	}

	private static boolean compareStackToPush(String stackOperator, String operator2) {
		// Returns true if the operator in the stack is of greater importance than the operator we
		// want to add
		// Returns false if not
		String dictionary = "4+4-5%5*5/6^8(8)";
		if (dictionary.charAt(dictionary.indexOf(stackOperator) - 1) < dictionary
				.charAt(dictionary.indexOf(operator2) - 1)) {
			// stackOperator is of less importance than adding operator
			return false;
		}
		return true;
	}


}
