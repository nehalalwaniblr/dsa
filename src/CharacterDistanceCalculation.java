public class CharacterDistanceCalculation {
//    public static int getCircularDistance(char char1, char char2) {
//        int distanceClockwise = Math.abs(char1 - char2);
//        int distanceCounterClockwise = 26 - distanceClockwise; // 26 letters in the alphabet
//        return Math.min(distanceClockwise, distanceCounterClockwise);
//    }

//    public static int calculateTotalDistance(String str1, String str2) {
//        String shorter = str1.length() <= str2.length() ? str1 : str2;
//        String longer = str1.length() > str2.length() ? str1 : str2;
//
//        int totalDistance = 0;
//
//        // Calculate total distance by summing the distance from each character in shorter to the first character in longer
//        for (int i = 0; i < shorter.length(); i++) {
//            int distanceForChar = getCircularDistance(shorter.charAt(i), longer.charAt(0));
//            totalDistance += distanceForChar * longer.length();
//        }
//
//        return totalDistance;
//    }
    private static int[][] precomputedDistances = new int[26][26];

    static {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int distanceClockwise = Math.abs(i - j);
                int distanceCounterClockwise = 26 - distanceClockwise;
                precomputedDistances[i][j] = Math.min(distanceClockwise, distanceCounterClockwise);
            }
        }
    }

    public static int calculateTotalDistance(String str1, String str2) {
        int totalDistance = 0;

        // Frequency arrays for each character in str1 and str2
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // Calculate frequency of each character in str1 and str2
        for (char c : str1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : str2.toCharArray()) {
            freq2[c - 'a']++;
        }

        // Calculate the total distance based on frequency distributions
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                totalDistance += freq1[i] * freq2[j] * precomputedDistances[i][j];
            }
        }

        return totalDistance;
    }

    public static void main(String[] args) {
        String str1 = "aab";
        String str2 = "azc";
        System.out.println("Total character distance: " + calculateTotalDistance(str1, str2));
    }
}
