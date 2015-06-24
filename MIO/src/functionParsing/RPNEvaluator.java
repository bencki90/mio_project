package functionParsing;

import java.util.LinkedList;
 
public class RPNEvaluator{
	public static double evalRPN(String expr){

		LinkedList<Double> stack = new LinkedList<Double>();
		for(String token : expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s]", "").split("\\s")){

			Double tokenNum = null;
			/*try{
				tokenNum = Double.parseDouble(token);
			}catch(NumberFormatException e){}
			
			if(tokenNum != null){

				stack.push(Double.parseDouble(token + ""));
			}
			else{*/
			try{
				tokenNum = Double.parseDouble(token);
				stack.push(tokenNum);
			}catch(NumberFormatException e) {}
				
			if(tokenNum == null){
				double secondOperand;
				double firstOperand;
				
				switch(token){
					case "*":
						secondOperand = stack.pop();
						firstOperand = stack.pop();
						stack.push(firstOperand * secondOperand);
						break;
					case "/":
						secondOperand = stack.pop();
						firstOperand = stack.pop();
						stack.push(firstOperand / secondOperand);
						break;
					case "-":
						secondOperand = stack.pop();
						firstOperand = stack.pop();
						stack.push(firstOperand - secondOperand);
						break;
					case "+":
						secondOperand = stack.pop();
						firstOperand = stack.pop();
						stack.push(firstOperand + secondOperand);
						break;
					case "^":
						secondOperand = stack.pop();
						firstOperand = stack.pop();
						stack.push(Math.pow(firstOperand, secondOperand));
						break;
					
				};
			}
		}
		return stack.pop();
	}
}