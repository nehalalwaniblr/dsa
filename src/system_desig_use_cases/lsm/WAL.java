package system_desig_use_cases.lsm;

import java.io.FileWriter;
import java.io.IOException;

class WAL {
    private final FileWriter writer;

    WAL(String path) throws IOException {
        this.writer = new FileWriter(path, true);
    }

    public synchronized void append(Entry e) throws IOException {
        writer.write(e.seq + "," + e.key + "," + (e.tombstone ? "T" : e.value) + "\n");
        writer.flush();
    }
}
