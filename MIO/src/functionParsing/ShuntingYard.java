package functionParsing;

import java.util.Stack;
 
public class ShuntingYard {

    public static String infixToPostfix(String infix) {

        StringBuilder stringBuilder = new StringBuilder();
        Stack<Operation> stack = new Stack<>();
        
        for (String token : infix.split("\\s")) {
            if (token.isEmpty()) continue;
            
            char c = token.charAt(0);
            Operation operation = Operation.matchOperation(token);
 
            if (operation == Operation.OPENING_BRACKET) {
            	stack.push(Operation.OPENING_BRACKET);
            } 
            else if (operation == Operation.CLOSING_BRACKET) {
                // until '(' on stack, pop operators.
                while (stack.peek() != Operation.OPENING_BRACKET) stringBuilder.append(stack.pop().toString()).append(' ');
                
                stack.pop();
            }
            else if (operation != Operation.NONE) {
            	
                if (stack.isEmpty()) stack.push(operation);
                else {
                    while (!stack.isEmpty()) {
                        int stackOperationPriority = stack.peek().precendence;
                        if (stackOperationPriority > operation.precendence || 
                        		(stackOperationPriority == operation.precendence && operation.associative == Operation.Associativity.LEFT)) 
                        	stringBuilder.append(stack.pop().toString()).append(' ');
                        else break;
                    }
                    stack.push(operation);
                }
            } 
            else {
            	stringBuilder.append(token).append(' ');
            }
        }
        while (!stack.isEmpty())
        	stringBuilder.append(stack.pop().toString()).append(' ');
        return stringBuilder.toString();
    }
}