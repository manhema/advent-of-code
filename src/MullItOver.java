import java.io.IOException;

// https://adventofcode.com/2024/day/3
public class MullItOver {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        var lines = OneStar.getInput("mull-it-over.txt");
        String input = String.join(" ", lines);

        // var value = fun("do()", input);
        var value = fun("don't()", input);

        String[] array = value.split("mul");

        System.out.println(array.length);

        var sum = 0;
        for (String local : array) {
            var result = multiply(local);
            sum = sum + result;
        }

        System.out.println(sum);
    }

    private static String fun(String delimiter, String input) {
        int index = input.indexOf(delimiter);

        if (index == -1) {
            return input;
        }

        String beforeDelimiter = input.substring(0, index);
        String afterDelimiter = input.substring(index + delimiter.length());

        if (delimiter.equals("don't()")) {
            return beforeDelimiter + fun("do()", afterDelimiter);
        }

        return fun("don't()", afterDelimiter);
    }

    private static  int multiply(String input) {
        if (input.length() < 5) {
            return 0;
        }

        enum State {
            start,
            x,
            y,
            end
        }
        var state = State.start;
        StringBuilder x = new StringBuilder();
        StringBuilder y = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (state == State.start && c == '(') {
                state = State.x;
                continue;
            }

            if (state == State.x && c == ',') {
                state = State.y;
                continue;
            }

            if (state == State.y && c == ')') {
                state = State.end;
                continue;
            }

            if (state == State.x && isAsciiNumber(c)) {
                x.append(c);
            } else if (state == State.y && isAsciiNumber(c)) {
                y.append(c);
            }else {
                // if any of the conditions are not met, break the loop
                break;
            }
        }

        if (state == State.end && !x.isEmpty() && !y.isEmpty()) {
            return Integer.parseInt(x.toString()) * Integer.parseInt(y.toString());
        }

        return 0;
    }

    public static boolean isAsciiNumber(char c) {
        // Check if the character is between '0' and '9'
        return c >= '0' && c <= '9';
    }
}