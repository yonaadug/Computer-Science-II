package assignment2;

/**
 * 
 * @author Yonathan Kebede
 *
 */
public class Notation {

	/**
	 * 
	 * @param infix expression
	 * @return Postfix expression
	 */
	public static String convertInfixToPostfix(String infix) {
		// initializing empty String for result 
        String result = new String(""); 
          
        // initializing empty stack 
        MyStack<Character> stack = new MyStack<>(); 
          
        for (int i = 0; i<infix.length(); ++i) 
        { 
            char c = infix.charAt(i); 
              
             // If the scanned character is an operand, add it to output. 
            if (Character.isLetterOrDigit(c)) 
                result += c; 
               
            // If the scanned character is an '(', push it to the stack. 
            else if (c == '(') 
                stack.push(c); 
              
            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (c == ')') 
            { 
                while (!stack.isEmpty() && stack.peek() != '(') 
                    result += stack.pop(); 
                  
                if (!stack.isEmpty() && stack.peek() != '(') 
                    return "Invalid Expression"; // invalid expression                 
                else
                    if(!stack.isEmpty())
                    	stack.pop();
            } 
            else // an operator is encountered 
            { 
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) 
                    result += stack.pop(); 
                stack.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!stack.isEmpty()) 
            result += stack.pop(); 
       
        return result; 
	}

	/**
	 * 
	 * @param postfix
	 * @return infix expression
	 */
	public static String convertPostfixToInfix(String postfix) {
		// TODO Auto-generated method stub
		MyStack<String> operatorStack = new MyStack<>(postfix.length());

		for (int index = 0; index < postfix.length(); index++) {
			char ch = postfix.charAt(index);
			if (ch== ' ') {
				continue;
			} else if (Character.isDigit(ch)) {
				operatorStack.push("" + ch);

			} else if (equalsOperator(ch)) {

				if (operatorStack.isEmpty() || operatorStack.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				String v1 = operatorStack.pop(), v2 = operatorStack.pop();
				operatorStack.push("(" + v2 + ch + v1 + ")");

			}
		}

		if (operatorStack.size() != 1) {
			throw new InvalidNotationFormatException();
		}

		return operatorStack.toString();
		
	}

	/**
	 * 
	 * @param postfix
	 * @return Result of postfix expression
	 */
	public static double evaluatePostfixExpression(String postfix) {
		// TODO Auto-generated method stub
		
		MyStack<Double> valueStack = new MyStack<Double>();
		char nextChar;
		double operandOne, operandTwo;
		double result = 0;
		
		
		for(int i=0; i<postfix.length(); i++) {
			nextChar = postfix.charAt(i);
			
			if(nextChar==' ')
				continue;
			else if (Character.isDigit(nextChar)) {
				valueStack.push((double) Character.getNumericValue(nextChar));
			}else if(equalsOperator(nextChar)){
				if (valueStack.isEmpty() || valueStack.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				operandTwo=valueStack.pop();
				operandOne=valueStack.pop();
				switch(nextChar) {
				case '+':
					result = operandOne + operandTwo;
					break;
				case '-':
					result = operandOne - operandTwo;
					break;
				case '*':
					result = operandOne * operandTwo;
					break;
				case '/':
					result = operandOne / operandTwo;
					break;
				case '^':
					result = Math.pow(operandOne, operandTwo);
					break;
				}
				valueStack.push(result);
				
			}
		}
		return valueStack.peek();
	}

	/**
	 * 
	 * @param infix
	 * @return Result of InfixExpression
	 */
	public static double evaluateInfixExpression(String infix) {
		// TODO Auto-generated method stub
		return evaluatePostfixExpression(convertInfixToPostfix(infix));
	}
	
	/**
	 * 
	 * @param nextChar
	 * @return True iff charcter is an operator
	 */
	private static boolean equalsOperator(char nextChar) {
		
		if((nextChar=='+')||(nextChar=='-')||(nextChar=='*')||(nextChar=='/')) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Used to see precedence of a character
	 * @param ch character
	 * @return the precedence of a character
	 */
	private static int Prec(char ch) {
		switch (ch) 
        { 
        case '+': 
        case '-': 
            return 1; 
       
        case '*': 
        case '/': 
            return 2; 
       
        case '^': 
            return 3; 
        } 
        return -1; 
	}


}