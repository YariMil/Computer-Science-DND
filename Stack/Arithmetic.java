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
		return 0;
	}

	// Returns the result of doing operand1 operation operand2,
	// e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		return 0;
	}

	// Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		return 0;
	}

	public static String convertClassicToStout(String exp) {
		MyStack<String> operatorStack = new MyStack<String>();
		StringBuilder stoutNotation = new StringBuilder();
		String[] operations = exp.split(" ");
		for (String str : operations) {
			try {
				stoutNotation.append(Integer.parseInt(str));
			} catch (Exception e) {
				// We have an operator on our hands
				// if thing already here >= thing added: GO
				if (operatorStack.empty()) {
					operatorStack.push(str);
				} else {
					if (compareStackToPush(operatorStack.peek(), str)) {
						// We shift the stack around
						stoutNotation.append(operatorStack.peek());
						operatorStack.pop();
					}
					operatorStack.push(str);
				}
			}
		}
		while (!operatorStack.empty()) {
			stoutNotation.append(operatorStack.peek());
			operatorStack.pop();
		}
		return stoutNotation.toString();
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
