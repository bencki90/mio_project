package functionParsing;

import java.util.Stack;
 
public class ShuntingYard {
 
    /*public static void main(String[] args) {
        String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        System.out.printf("infix:   %s%n", infix);
        System.out.printf("postfix: %s%n", infixToPostfix(infix));
    }*/
 
    static String infixToPostfix(String infix) {
        final String ops = "-+/*^";
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        
        String[] spl = infix.split("\\s");
 
        for (String token : spl) {
            if (token.isEmpty()) continue;
            
            char c = token.charAt(0);
            int idx = ops.indexOf(c);
 
            // check for operator
            if (idx != -1) {
                if (stack.isEmpty())
                	stack.push(idx);
 
                else {
                    while (!stack.isEmpty()) {
                        int prec2 = stack.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                        	stringBuilder.append(ops.charAt(stack.pop())).append(' ');
                        else break;
                    }
                    stack.push(idx);
                }
            } 
            else if (c == '(') {
            	stack.push(-2); // -2 stands for '('
            } 
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (stack.peek() != -2)
                	stringBuilder.append(ops.charAt(stack.pop())).append(' ');
                stack.pop();
            }
            else {
            	stringBuilder.append(token).append(' ');
            }
        }
        while (!stack.isEmpty())
        	stringBuilder.append(ops.charAt(stack.pop())).append(' ');
        return stringBuilder.toString();
    }
}