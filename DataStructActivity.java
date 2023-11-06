/*DATASTRUCT
Name: Rubi, Larie Jane     BSIT 2B*/

import java.util.Scanner;
import java.util.Stack;

public class DataStructActivity
 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String infixExpression = scanner.nextLine();

        String postfixExpression = convertToPostfix(infixExpression);
        String prefixExpression = convertToPrefix(infixExpression);

        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Prefix Expression: " + prefixExpression);

        scanner.close();
    }

    public static String convertToPostfix(String infixExpression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static String convertToPrefix(String infixExpression) {
        String reversedInfix = new StringBuilder(infixExpression).reverse().toString();
        String postfixExpression = convertToPostfix(reversedInfix);
        return new StringBuilder(postfixExpression).reverse().toString();
    }

    public static int getPrecedence(char operator) {
        switch (operator) {
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
