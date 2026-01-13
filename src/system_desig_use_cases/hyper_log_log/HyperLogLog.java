package system_desig_use_cases.hyper_log_log;

import java.util.*;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
/*Estimate distinct count - like likes in social media*/

public class HyperLogLog {

    private final int p;           // number of prefix bits
    private final int m;           // number of registers (2^p)
    private final int[] registers; // each holds max trailing zero count

    public HyperLogLog(int p) {
        this.p = p;
        this.m = 1 << p;
        this.registers = new int[m];
    }

//    private int hash(String value) {
//        return value.hashCode(); // good enough for demo
//    }

    private long hash64(String value) {
        return Hashing.murmur3_128()
                .hashString(value, StandardCharsets.UTF_8)
                .asLong();
    }

    // number of leading zeros in remaining bits
    private int countLeadingZeros(int x) {
        int count = 1;
        while ((x & 1) == 0 && x != 0) {
            count++;
            x >>= 1;
        }
        return count;
    }

    public void add(String value) {
        long x = hash64(value);

        int bucket = (int) (x >>> (64 - p));        // first p bits
        long w = x << p;                            // remaining bits

        int rho = Long.numberOfLeadingZeros(w) + 1; // position of first 1
        registers[bucket] = Math.max(registers[bucket], rho);
    }
    public double estimate() {
        double alpha;

        switch (m) {
            case 16:   alpha = 0.673; break;
            case 32:   alpha = 0.697; break;
            case 64:   alpha = 0.709; break;
            default:   alpha = 0.7213 / (1 + 1.079 / m);
        }

        double sum = 0.0;
        for (int r : registers) {
            sum += 1.0 / (1 << r);
        }

        double raw = alpha * m * m / sum;

        return raw;
    }

    public static void main(String[] args) {

        HyperLogLog hll = new HyperLogLog(10); // 2^10 = 1024 registers

//        List<String> data = Arrays.asList(
//                "A","B","C","D","A","B","X","Y","Z","Z",
//                "K","L","M","N","O","P","Q","R","S","T",
//                "A1","B1","C1","D1","E1","F1","A","B","C","100"
//        );
//
//        for (String s : data) hll.add(s);
//
//        System.out.println("Estimated distinct count (HLL): " + Math.round(hll.estimate()));
//        System.out.println("Actual distinct count          : " + new HashSet<>(data).size());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < 1_000_000; i++) {
            hll.add("user_" + (i % 50000));   // duplicates but many uniques
            set.add("user_" + (i % 50000));
        }

        System.out.println("Estimated distinct count (HLL): " + Math.round(hll.estimate()));
        System.out.println("Actual distinct count          : " + set.size());


    }
}
