import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedNosedReports {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        var input = OneStar.getInput("red-nosed-reports.txt");

        int sum = 0;
        int nSafe = 0;

        List<List<Integer>> reports1 = new ArrayList<>();
        List<List<Integer>> reports2 = new ArrayList<>();
        for (String line : input) {
            String[] parts = line.split("\\s+");
            List<Integer> report = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            if(isSafeOrCanBeMadeSafe(new ArrayList<>(report))){
                reports1.add(report);
                sum++;
            }

            if(isSafe(new ArrayList<>(report))){
                reports2.add(report);
                nSafe++;
            }

        }

        System.out.println("Safe reports: " + sum);
        System.out.println("Valid reports: " + nSafe);

        // Find the difference: elements in list1 but not in list2
        List<List<Integer>> diff = new ArrayList<>(reports2);
        diff.removeAll(reports1);
        for (List<Integer> report : diff) {
            System.out.println(report);
        }

    }

    private static boolean safeReport(List<Integer> report) {
        if (report.size() < 2) {
            return true;
        }

        var increasing = (report.get(1) > report.get(0));

        for (int i = 0; i < report.size() - 1; i++) {
            var diff = report.get(i + 1) - report.get(i);

            // Excessive difference
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            // Trend violation
            if ((increasing && diff <= 0) || (!increasing && diff >= 0)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSafeOrCanBeMadeSafe(List<Integer> report) {
        if (safeReport(report)) {
            return true;
        }

        for (int i = 0; i < report.size(); i++) {
            if (safeReport(removeAtIndex(report, i))) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSafe(List<Integer> report) {
        if (checkReport(report)) {
            return true;
        }

        for (int i = 0; i < report.size(); i++) {
            if (checkReport(removeAtIndex(report, i))) {
               return true;
            }
        }

        return false;
    }

    public static int absDiff(int x, int y) {
        return Math.abs(x - y);
    }

    public static List<Integer> removeAtIndex(List<Integer> list, int index) {
        List<Integer> newList = new ArrayList<>(list);
        newList.remove(index);
        return newList;
    }

    public static boolean checkReport(List<Integer> report) {
        int prev = 0;
        boolean increasing = false;

        for (int i = 0; i < report.size(); i++) {
            int level = report.get(i);

            if (i == 0) {
                prev = level;
                continue;
            }

            int diff = absDiff(prev, level);

            if (diff == 0 || diff > 3) {
                return false;
            }

            if (i == 1) {
                increasing = level > prev;
                prev = level;
                continue;
            }

            if ((level > prev) != increasing) {
                return false;
            }

            prev = level;
        }

        return true;
    }
}