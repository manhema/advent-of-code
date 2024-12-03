import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class OneStar {
    public static void main(String[] args) {
        System.out.println("*");
    }

    public static List<String> getInput(String name) throws IOException {
        String filePath = "src/data/" + name;

        return Files.readAllLines(Paths.get(filePath));
    }

    public static String getLine(String name) throws IOException {
        String filePath = "src/data/" + name;

        try (var lines = Files.lines(Paths.get(filePath))) {
            return lines.findFirst().orElse("");
        }
    }
}
