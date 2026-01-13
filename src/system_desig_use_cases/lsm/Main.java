package system_desig_use_cases.lsm;

public class Main {

    public static void main(String[] args) throws Exception {

        LSMEngine db = new LSMEngine();

        System.out.println("=== WRITES ===");
        db.put("a", "value1");
        db.put("b", "value2");

        System.out.println("a => " + db.get("a"));
        System.out.println("b => " + db.get("b"));

        System.out.println("\n=== UPDATE a ===");
        db.put("a", "value11");
        System.out.println("a => " + db.get("a"));

        System.out.println("\n=== DELETE b ===");
        db.delete("b");

        System.out.println("b => " + db.get("b"));   // should print null

        // Force many writes to trigger memtable flushes
        System.out.println("\n=== BULK WRITES ===");
        for (int i = 0; i < 5000; i++) {
            db.put("k" + i, "v" + i);
        }

        System.out.println("Lookup k123 => " + db.get("k123"));

        System.out.println("\n=== RUN COMPACTION ===");
        db.compact();

        System.out.println("Post-compaction lookup a => " + db.get("a"));
        System.out.println("Post-compaction lookup b => " + db.get("b"));
    }
}
