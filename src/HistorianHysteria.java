import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/// Day 1: Historian Hysteria
public class HistorianHysteria {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        var input = OneStar.getInput("historian-hysteria.txt");

        List<Integer> array1 = new ArrayList<>();
        List<Integer> array2 = new ArrayList<>();

        for (String line : input) {
            String[] parts = line.split("\\s+");
            array1.add(Integer.parseInt(parts[0]));
            array2.add(Integer.parseInt(parts[1]));
        }

        var result = getSum(array1, array2);
        System.out.println(result);

        var score = getSimilarityScore(array1, array2);
        System.out.println("Similarity Score: " + score);
    }



    private static Integer getSum(List<Integer> array1, List<Integer> array2) {
        Collections.sort(array1);
        Collections.sort(array2);

        int sum = 0;
        for (int i = 0; i <array1.size(); i++) {
            sum = sum + Math.abs(array1.get(i) - array2.get(i));
        }

        return sum;
    }

    private static Integer getSimilarityScore(List<Integer> array1, List<Integer> array2) {
        Collections.sort(array1);
        Collections.sort(array2);

        HashMap<Integer, Integer> hash = new HashMap<>();

        int sum = 0;

        for (Integer value : array2) {
            if (hash.containsKey(value)) {
                hash.put(value, hash.get(value) + 1);
            } else {
                hash.put(value, 1);
            }
        }

        for (Integer value : array1) {
            if (hash.containsKey(value)) {
                sum = sum + (value * hash.get(value));
            }
        }

        return sum;
    }
}