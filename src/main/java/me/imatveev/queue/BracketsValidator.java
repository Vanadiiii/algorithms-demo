package me.imatveev.queue;

public class BracketsValidator {
    private final String brackets;
    private final BoundedStack<Character> stack;

    public BracketsValidator(String brackets) {
        this.brackets = brackets;
        this.stack = new BoundedStack(brackets.length());
    }

    public boolean validate() {
        for (int i = 0; i < brackets.length(); ++i) {
            char bracket = brackets.charAt(i);

            switch (bracket) {
                case '(', '{', '[' -> stack.push(bracket);
                case ')', '}', ']' -> {
                    if (!stack.isEmpty()) {
                        char topBracket = stack.pop();

                        if (!isCompliment(topBracket, bracket)) {
                            stack.push(topBracket);
                            stack.push(bracket);
                        }
                    } else {
                        return false;
                    }
                }
                default -> throw new IllegalArgumentException("Not a bracket - " + bracket);
            }
        }

        return stack.isEmpty();
    }

    private boolean isCompliment(char open, char close) {
        return switch (open) {
            case '(' -> close == ')';
            case '[' -> close == ']';
            case '{' -> close == '}';
            default -> false;
        };
    }
}
