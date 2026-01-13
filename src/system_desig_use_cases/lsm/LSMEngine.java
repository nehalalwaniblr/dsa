package system_desig_use_cases.lsm;

import java.io.*;
import java.util.*;

class LSMEngine {

    private long sequence = 0;

    private final MemTable memtable = new MemTable();
    private final List<SSTable> ssTables = new ArrayList<>();
    private final WAL wal = new WAL("wal.log");

    LSMEngine() throws IOException {
    }

    public synchronized void put(String key, String value) throws IOException {
        long seq = ++sequence;

        Entry e = new Entry(key, value, seq, false);
        wal.append(e);
        memtable.put(key, value, seq);

        flushIfNeeded();
    }

    public synchronized void delete(String key) throws IOException {
        long seq = ++sequence;

        Entry e = new Entry(key, null, seq, true);
        wal.append(e);
        memtable.delete(key, seq);

        flushIfNeeded();
    }

    private void flushIfNeeded() throws IOException {
        if (!memtable.isFull()) return;

        Map<String, Entry> snapshot = memtable.snapshot();
        SSTable table = SSTable.fromMemtable(snapshot);
        ssTables.add(table);
        memtable.clear();
    }

    public synchronized String get(String key) throws IOException {
        // 1) check memtable
        Entry e = memtable.get(key);
        if (e != null) {
            if (e.tombstone) return null;
            return e.value;
        }

        // 2) scan SSTables newest first
        for (int i = ssTables.size() - 1; i >= 0; i--) {
            Entry x = ssTables.get(i).lookup(key);
            if (x == null) continue;
            if (x.tombstone) return null;
            return x.value;
        }

        return null;
    }

    public synchronized void compact() throws IOException {
        if (ssTables.size() < 2) return; // nothing to merge

        Map<String, Entry> latest = new HashMap<>();

        // scan all tables (simple implementation)
        for (SSTable t : ssTables) {
            try (BufferedReader br = new BufferedReader(new FileReader(t.file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    long seq = Long.parseLong(parts[0]);
                    String key = parts[1];
                    String raw = parts[2];
                    boolean tomb = raw.equals("T");

                    Entry e = new Entry(key, tomb ? null : raw, seq, tomb);

                    Entry old = latest.get(key);
                    if (old == null || e.seq > old.seq) {
                        latest.put(key, e);
                    }
                }
            }
        }

        // write compacted table
        File f = File.createTempFile("sstable_compact", ".db");
        Bloom bloom = new Bloom(10_000);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (Entry e : latest.values()) {
                if (e.tombstone) continue;   // drop deletes
                bloom.add(e.key);
                bw.write(e.seq + "," + e.key + "," + e.value);
                bw.newLine();
            }
        }

        // replace old list with new single table
        ssTables.clear();
        ssTables.add(new SSTable(f,bloom));
    }

}
