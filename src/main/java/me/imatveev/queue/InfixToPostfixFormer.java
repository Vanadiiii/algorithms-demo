package me.imatveev.queue;

public class InfixToPostfixFormer {
    private final String expression;
    private final BoundedStack<Character> stack;
    private final StringBuilder outputBuilder = new StringBuilder();

    public InfixToPostfixFormer(String expression) {
        this.expression = expression;
        this.stack = new BoundedStack<>(expression.length());
    }

    public String toPostfixForm() {
        for (int i = 0; i < expression.length(); ++i) {
            char value = expression.charAt(i);

            switch (value) {
                case '+', '-', '*', '/' -> putOperand(value);
                case '(' -> stack.push(value);
                case ')' -> closeBrackets();
                case ' ' -> {
                }
                default -> outputBuilder.append(value);
            }
        }

        while (!stack.isEmpty()) {
            outputBuilder.append(stack.pop());
        }

        return outputBuilder.toString();
    }

    private void putOperand(char value) {
        while (!stack.isEmpty()) {
            char topValue = stack.pop();
            if (topValue == '(') {
                stack.push(topValue);
                break;
            } else {
                int priority = getPriority(value);
                int topPriority = getPriority(topValue);

                if (topPriority < priority) {
                    stack.push(topValue);
                    break;
                } else {
                    outputBuilder.append(topValue);
                }
            }
        }
        stack.push(value);
    }

    private void closeBrackets() {
        while (!stack.isEmpty()) {
            char topValue = stack.pop();

            if (topValue == '(') {
                break;
            } else {
                outputBuilder.append(topValue);
            }
        }
    }

    private int getPriority(char operand) {
        if (operand == '+' || operand == '-') {
            return 1;
        } else {
            return 2;
        }
    }
}
