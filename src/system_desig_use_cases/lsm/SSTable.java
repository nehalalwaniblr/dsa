package system_desig_use_cases.lsm;

import java.io.*;
import java.util.*;

class SSTable {

    final File file;
    final Bloom bloom;

    SSTable(File file, Bloom bloom) {
        this.file = file;
        this.bloom = bloom;
    }

    /*public static SSTable fromMemtable(Map<String, Entry> mem) throws IOException {
        File f = File.createTempFile("sstable", ".db");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (Entry e : mem.values()) {
                bw.write(e.seq + "," + e.key + "," + (e.tombstone ? "T" : e.value));
                bw.newLine();
            }
        }
        return new SSTable(f);
    }*/

    public static SSTable fromMemtable(Map<String, Entry> mem) throws IOException {
        Bloom bloom = new Bloom(10_000);

        File f = File.createTempFile("sstable", ".db");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (Entry e : mem.values()) {
                bloom.add(e.key);
                bw.write(e.seq + "," + e.key + "," + (e.tombstone ? "T" : e.value));
                bw.newLine();
            }
        }
        return new SSTable(f, bloom);
    }

    /*public Entry lookup(String key) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Entry best = null;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                long seq = Long.parseLong(parts[0]);
                String k = parts[1];
                String v = parts[2];

                if (!k.equals(key)) continue;

                boolean tombstone = v.equals("T");
                Entry e = new Entry(k, tombstone ? null : v, seq, tombstone);

                if (best == null || e.seq > best.seq) {
                    best = e;
                }
            }
            return best;
        }
    }*/

    public Entry lookup(String key) throws IOException {
        if (!bloom.mightContain(key)) return null;   // skip fast!

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Entry best = null;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                long seq = Long.parseLong(parts[0]);
                String k = parts[1];
                String raw = parts[2];

                if (!k.equals(key)) continue;

                boolean tomb = raw.equals("T");
                Entry e = new Entry(k, tomb ? null : raw, seq, tomb);

                if (best == null || e.seq > best.seq) best = e;
            }
            return best;
        }
    }

}
