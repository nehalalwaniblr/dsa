package system_desig_use_cases.hyper_log_log;

import java.util.*;
import java.security.MessageDigest;

/*Estimate distinct count - like likes in social media*/
public class FlajoletMartin {

    private int maxZeroes = 0;

    // Simple hashing to 32-bit integer using Java's hashCode
    private int hash(String value) {
        return value.hashCode();
    }

    // Count trailing zeros in binary representation
    private int countTrailingZeros(int x) {
        int count = 0;
        while ((x & 1) == 0 && x != 0) {
            count++;
            x >>= 1;
        }
        return count;
    }

    public void add(String value) {
        int h = hash(value);
        int tz = countTrailingZeros(h);
        maxZeroes = Math.max(maxZeroes, tz);
    }

    public long estimate() {
        return (long) Math.pow(2, maxZeroes);
    }

    public static void main(String[] args) {
        FlajoletMartin fm = new FlajoletMartin();

//        List<String> data = Arrays.asList(
//                "A","B","C","D","A","B","X","Y","Z","Z","K","L","C","M"
//        );
//
//        for (String s : data) fm.add(s);
//
//        System.out.println("Estimated distinct count (FM): " + fm.estimate());
//        System.out.println("Actual distinct count       : " + new HashSet<>(data).size());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < 1_000_000; i++) {
            fm.add("user_" + (i % 50000));   // duplicates but many uniques
            set.add("user_" + (i % 50000));
        }

        System.out.println("Estimated distinct count (FM): " + Math.round(fm.estimate()));
        System.out.println("Actual distinct count          : " + set.size());

    }
}
