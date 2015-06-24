package functionParsing;

import java.util.Stack;

import org.apache.commons.lang3.math.NumberUtils;
 
public class RPNEvaluator{
	
	private static double getSecondOperand(Stack<String> operandsStack) throws Exception{
		String operand = operandsStack.pop().toLowerCase();
		
		if(operand.equals("e")) return Math.E;
		if(operand.equals("pi")) return Math.PI;
		
		return Double.valueOf(operand);
	}
	
	private static double getFirstOperand(Stack<String> operandsStack, double secondOperand) throws Exception{
		String operand = operandsStack.pop().toLowerCase();

		if(operand.equals("e")) return Math.E;
		if(operand.equals("pi")) return Math.PI;
		if(operand.equals("sin")) return getFirstOperand(operandsStack, Math.sin(secondOperand));
		if(operand.equals("cos")) return getFirstOperand(operandsStack, Math.cos(secondOperand));
		
		return Double.valueOf(operand);

	}
	
	public static double evalRPN(String expr) throws Exception{

		Stack<String> operandsStack = new Stack<String>();
		String[] equationElements = expr.split("\\s");
		
		for(int i = 0; i < equationElements.length; ++i){
			
			String currentElement = equationElements[i].toLowerCase();
			boolean isOperand = false;
			
			switch(currentElement){
				case "e":
				case "pi":
				case "sin":
				case "cos":
					operandsStack.push(currentElement);
					isOperand = true;
					break;
				default:
					if(NumberUtils.isParsable(currentElement)){
						operandsStack.push(currentElement);
						isOperand = true;
					}
					break;
			};
			
			if(!isOperand){
				
				//Order is fine
				double secondOperand = getSecondOperand(operandsStack);
				double firstOperand = getFirstOperand(operandsStack, secondOperand);
				
				switch(currentElement){
					case "+":
						operandsStack.push(String.valueOf(firstOperand + secondOperand));
						break;
					case "-":
						operandsStack.push(String.valueOf(firstOperand - secondOperand));
						break;
					case "*":
						operandsStack.push(String.valueOf(firstOperand * secondOperand));
						break;
					case "/":
						operandsStack.push(String.valueOf(firstOperand / secondOperand));
						break;
					case "^":
						operandsStack.push(String.valueOf(Math.pow(firstOperand, secondOperand)));
						break;
				};
			}
			System.out.println(operandsStack);
		}
		
		return Double.parseDouble(operandsStack.pop());
	}
}