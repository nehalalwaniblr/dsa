import java.util.*;
import java.util.stream.Collectors;

public class Omio2 {
    public static void main(String[] args) {
        List<Integer> etaStream = Arrays.asList(40, 39, 38, 37, 36, 60, 59, 58, 57, 56, 55, 20, 19, 18, 17, 16, 15, 14);

        // Apply smoothing
        List<Integer> smoothedETA = smoothMovingAverage(etaStream, 3); // Window size = 3

        // Print results
        System.out.println("Original ETA: " + etaStream);
        System.out.println("Smoothed ETA: " + smoothedETA);
    }

    public static List<Integer> smoothMovingAverage(List<Integer> etaStream, int windowSize) {
        List<Integer> smoothedList = new ArrayList<>();
        Deque<Integer> window = new ArrayDeque<>();
        Integer sum = 0;

        for (int i = 0; i < etaStream.size(); i++) {
            window.addLast(etaStream.get(i));
            sum += etaStream.get(i);

            if (window.size() > windowSize) {
                sum -= window.removeFirst(); // Remove old value
            }
            List<Integer> sortedWindow = new ArrayList<>(window);
            Collections.sort(sortedWindow);
            smoothedList.add(sortedWindow.get(sortedWindow.size() / 2));
        }

        return smoothedList;
    }
}

