package Compiler;

import java.util.Scanner;
import java.util.Stack;

public class SyntaxCheck {

    static int i, top = 0, flag = 0;
    static char a[] = new char[30];
    static String str;
    static String postfixStr;

    public static void main(String arg[]) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the string");
            str = input.nextLine();

            operation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void operation() {
        char d, t;

        if (str.length() == 1 || str.length() == 2) {
            System.out.println("Compilation Error");
        } else if (isAnOperatorInFirst(str.charAt(0)) == true || isAnOperatorInLast(str.charAt(str.length() - 1)) == true) {
            System.out.println("Compilation Error");
        } else {
            for (i = 0; i < str.length(); i++) {
                d = str.charAt(i);
                switch (d) {
                    case '(':
                        push(d);
                        break;

                    case '{':
                        push(d);
                        break;

                    case '[':
                        push(d);
                        break;

                    case ')':
                        t = pop();
                        if (t != '(') {
                            flag = 1;
                        }
                        break;

                    case '}':
                        t = pop();
                        if (t != '{') {
                            flag = 1;
                        }
                        break;

                    case ']':
                        t = pop();
                        if (t != '[') {
                            flag = 1;
                        }
                        break;

                }
            }

            if (flag == 0 && top == 0) {
                postfixStr = infixToPostfix(str);
                System.out.println("After postfix conversion: " + postfixStr);

                System.out.println("Evaluation of this expression: " + evaluatePostfix(postfixStr));

            } else {
                System.out.println("Compilation Error");
            }
        }
    }

    public static boolean isAnOperatorInFirst(char c) {
        return c == '*' || c == '/' || c == '+';
    }

    public static boolean isAnOperatorInLast(char c) {   // -a-
        return c == '*' || c == '/' || c == '-' || c == '+';
    }

    public static void push(char c) {
        a[top] = c;
        top++;
    }

    public static char pop() {
        char h;
        if (top != 0) {
            top--;
            h = a[top];
            return h;
        } else {
            return 0;
        }
    }

    static String infixToPostfix(String exp) {
        String result = "";

        Stack<Character> stack = new Stack<>();

        for (int j = 0; j < exp.length(); j++) {
            char c = exp.charAt(j);

            if (Character.isLetterOrDigit(c)) {
                result += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid Expression";
                } else {
                    stack.pop();
                }
            } else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    static int Prec(char ch) {
        switch (ch) {
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

    static int evaluatePostfix(String exp) {
        //create a stack
        Stack<Integer> stack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } //  If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;

                    case '-':
                        stack.push(val2 - val1);
                        break;

                    case '/':
                        stack.push(val2 / val1);
                        break;

                    case '*':
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
