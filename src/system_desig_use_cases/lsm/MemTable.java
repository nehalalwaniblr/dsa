package system_desig_use_cases.lsm;

import java.util.concurrent.ConcurrentSkipListMap;

class MemTable {
    private final ConcurrentSkipListMap<String, Entry> map = new ConcurrentSkipListMap<>();

    public void put(String key, String value, long seq) {
        map.put(key, new Entry(key, value, seq, false));
    }

    public void delete(String key, long seq) {
        map.put(key, new Entry(key, null, seq, true));
    }

    public Entry get(String key) {
        return map.get(key);
    }

    public boolean isFull() {
        return map.size() > 1000; // example threshold
    }

    public ConcurrentSkipListMap<String, Entry> snapshot() {
        return new ConcurrentSkipListMap<>(map);
    }

    public void clear() {
        map.clear();
    }
}
