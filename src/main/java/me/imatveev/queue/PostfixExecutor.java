package me.imatveev.queue;

public class PostfixExecutor {
    private static final char ZERO = '0';
    private static final char NINE = '9';

    private final String postfixExpression;
    private final BoundedStack<Integer> stack;

    public PostfixExecutor(String postfixExpression) {
        this.postfixExpression = postfixExpression;
        this.stack = new BoundedStack<>(postfixExpression.length());
    }


    public int execute() {
        for (int i = 0; i < postfixExpression.length(); ++i) {
            char value = postfixExpression.charAt(i);

            if (ZERO <= value && value <= NINE) {
                stack.push(Integer.valueOf(String.valueOf(value)));
            } else {
                switch (value) {
                    case '+' -> {
                        int number1 = stack.pop();
                        int number2 = stack.pop();

                        stack.push(number1 + number2);
                    }
                    case '-' -> {
                        int number1 = stack.pop();
                        int number2 = stack.pop();

                        stack.push(number2 - number1);
                    }
                    case '*' -> {
                        int number1 = stack.pop();
                        int number2 = stack.pop();

                        stack.push(number1 * number2);
                    }
                    case '/' -> {
                        int number1 = stack.pop();
                        int number2 = stack.pop();

                        stack.push(number2 / number1);
                    }
                }
            }
        }

        return stack.pop();
    }
}
